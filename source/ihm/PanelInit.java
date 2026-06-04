package source.ihm;

import javax.swing.*;

import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import source.Controleur ;

public class PanelInit extends JPanel implements ActionListener, FocusListener
{
	
	private static final String TEXTE_TAILLE   = "Valeur entre 1 et 30";
	private static final String TEXTE_QUANTITE = "Valeur entre 2 et 4" ;
	
	private static final int NB_CARA = 50;
	
	// Création de 2 tableaux pour faciliter la modification des composants
	
	private JLabel    [] tabLbl;
	private JTextField[] tabZoneTxt;
	
	private int[] tabParametreEntrer;
	
	private JButton btnLancer;
	private JButton btnReset ;
	
	private Controleur ctrl ;

	public PanelInit( Controleur ctrl )
	{
	
		JPanel panelAction;
		JPanel panelSaisie;
		
		this.ctrl = ctrl;
		
		this.setLayout(new BorderLayout() );
		this.setBackground(FrameCreation.COULEUR_FOND);

		/* ---------------------------------- */
		/*       création des composants      */
		/* ---------------------------------- */
		
		panelAction = new JPanel();
		panelSaisie = new JPanel(new GridLayout(5, 1  ) );
		
		this.tabLbl = new JLabel    [5];
		this.tabZoneTxt = new JTextField[4];
		
		this.tabLbl[0] = new JLabel("Initialisation du Plateau"       , SwingConstants.CENTER); // Titre
		this.tabLbl[1] = new JLabel("Nombre de lignes :"              , SwingConstants.CENTER); // nbLignes
		this.tabLbl[2] = new JLabel("Nombre de colonnes :"            , SwingConstants.CENTER); // nbColonnes
		this.tabLbl[3] = new JLabel("Nombre d'espèces différentes :"  , SwingConstants.CENTER); // nbEspece
		this.tabLbl[4] = new JLabel("Nombre de planètes différentes :", SwingConstants.CENTER); // nbPlanete
		
		/*
			Si le compteur vaut 0 ou 1, le texte est "valeur entre 1 et 30", sinon c'est "valeur entre 2 et 4"
			La boucle permet d'éviter de répéter 4 fois la même instruction, sachant qu'il y a 2 fois 2 textes identiques
		*/
		
		for (int cpt = 0; cpt < tabZoneTxt.length ; cpt++) 
		{
			if (cpt < 2) this.tabZoneTxt[cpt] = new JTextField(PanelInit.TEXTE_TAILLE  , PanelInit.NB_CARA);
			else         this.tabZoneTxt[cpt] = new JTextField(PanelInit.TEXTE_QUANTITE, PanelInit.NB_CARA);
		}
		
		this.btnLancer  = new JButton("Lancer");
		this.btnReset   = new JButton("Réinitialiser" );
		
		/* ---------------------------------- */
		/*    Configuration des composants    */
		/* ---------------------------------- */
		
		panelAction.setOpaque(false);
		panelSaisie.setOpaque(false);
		
		/*--- COULEURS ET POLICES ---*/
		
		for (JLabel label : tabLbl) 
		{
			label.setFont      ( FrameCreation.POLICE_TEXTE  );
			label.setForeground( FrameCreation.COULEUR_TITRE );
		}
		
		// Modification individuelle de la police du Titre, car c'est le seul JLabel où la police est différente des autres
		
		this.tabLbl[0].setFont( new Font(this.tabLbl[0].getFont().getName(), this.tabLbl[0].getFont().getStyle(),50) );
		
		/*--- CO3NFIGURATION ET CENTRAGE DES JTEXTFIELD ---*/
		
		for (JTextField txtF : this.tabZoneTxt)
		{
			txtF.setFont               ( FrameCreation.POLICE_TEXTE   );
			txtF.setForeground         ( FrameCreation.COULEUR_ZONE   );
			txtF.setHorizontalAlignment( JTextField   .CENTER         );
		}
		
		/* ---------------------------------- */
		/*    Positionnement des composants   */
		/* ---------------------------------- */
		
		panelSaisie.add(this.tabLbl[0]); // Titre
		
		// On part de 1 car on ajoute pas le titre, déjà ajouté au dessus, et qu'il n'a pas besoin d'être sur un sous panel
		// Car il ne possède pas de JTextField associé.
		
		
		for (int cpt = 1; cpt < tabLbl.length; cpt++) 
			panelSaisie.add(this.creerPanelCentre(this.tabLbl[cpt], this.tabZoneTxt[cpt -1]) );
													// JTextfield         JLabel
													// Dans l'ordre des tableaux définis au dessus.
		
		panelAction.add(btnLancer);
		panelAction.add(btnReset );
		
		this.add(panelSaisie, BorderLayout.CENTER);
		this.add(panelAction, BorderLayout.SOUTH );
		
		/* ------------------------------- */
		/*    Activation des Composants    */
		/* ------------------------------- */
		
		// Activation des Zone d'entrée de texte

		for (JTextField txtF : this.tabZoneTxt)
			txtF.addFocusListener(this);
		
		// Activation des Boutons d'Action

		this.btnLancer.addActionListener(this);
		this.btnReset .addActionListener(this);
	}
	
	
	/*
		Méthode utilitaire permettant d'encapsuler dans un panel les 2 composants fournis en paramètres
		Le JPanel renvoyé contient 2 sous panel contenant chacun un des composants fournis
		
	*/
	
	private JPanel creerPanelCentre(JLabel lbl,JTextField txtField)
	{
		// Création d'un panel qui contiendra 2 sous-panels
		JPanel panelTemp = new JPanel(new GridLayout(2,1) );
		
		// Création de 2 sous panel qui contiendront chaucun un des composants en paramètres
		
		JPanel sPanelLbl = new JPanel();
		JPanel sPanelTxt = new JPanel();
		sPanelLbl.setOpaque(false);
		sPanelTxt.setOpaque(false);
		
		lbl     .setOpaque(false);
		txtField.setOpaque(false);
		
		
		// Ajout du JLabel sur le premier sous-panel
		sPanelLbl.add(lbl);
		
		// Ajout du JTextField sur le deuxième sous-panel
		sPanelTxt.add(txtField);
		
		//Ajout des sous-panels sur le panel temporaire
		
		panelTemp.add(sPanelLbl);
		panelTemp.add(sPanelTxt);
		
		panelTemp.setOpaque(false);
		return panelTemp;
	}
	
	// Méthodes liée aux boutons d'Actions
	/*
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == this.btnLancer)
		{
			this.tabParametreEntrer = new int[this.tabZoneTxt.length] ;
			
			
			// Vérification des valeurs dans les Zones de Texte
			boolean valeursValide = true ;
			for ( int cpt = 0 ; cpt < this.tabZoneTxt.length ; cpt++ )
			{
				try
				{
					int valActuelle = this.tabParametreEntrer[cpt] = Integer.parseInt(this.tabZoneTxt[cpt].getText() );
					
					if ( cpt == 0 || cpt == 1 )
					{
						if ( valActuelle < 1 || valActuelle > 30 )
						{
							this.tabParametreEntrer[cpt] = -1 ;
							this.tabZoneTxt[cpt].setText("Erreur : Valeur Invalide");
							valeursValide = false ;
						}
					}
					
					if ( cpt == 2 || cpt == 3 )
					{
						if ( valActuelle < 2 || valActuelle > 4 )
						{
							this.tabParametreEntrer[cpt] = -1;
							this.tabZoneTxt[cpt].setText("Erreur : Valeur Invalide");
							valeursValide = false ;
						}
					}
				}
				catch(NumberFormatException ex)
				{
					this.tabZoneTxt[cpt].setText("Erreur : Valeur Invalide");
					valeursValide = false ;
				}
			}
			
			if ( valeursValide )
			{
				int nbLignes   = this.tabParametreEntrer[0] ;
				int nbColonnes = this.tabParametreEntrer[1] ;
				int nbPlanete  = this.tabParametreEntrer[2] ;
				int nbEspece   = this.tabParametreEntrer[3] ;
				this.ctrl.initialiserPlateau(nbLignes, nbColonnes, nbPlanete, nbEspece);

			}
		}
		
		if (e.getSource() == this.btnReset)
		{
			this.reinitialiserTexte();
		}
	}
	*/
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if (e.getSource() == this.btnReset) this.reinitialiserTexte();
		if (e.getSource() == this.btnLancer )
		{
			for (int i=0 ; i < tabZoneTxt.length ; i++ )
			{
				if ( valeursValide() == 1 )
				{
					JOptionPane.showMessageDialog
					(this, "Certaines valeurs ne sont pas comprises dans les limites indiquées",
					       "Erreur de saisie"                                           , JOptionPane.ERROR_MESSAGE);
				}
			}
		}
		
	}
	
	private void reinitialiserTexte()
	{
		for (int cpt = 0; cpt < tabZoneTxt.length ; cpt++) 
		{
			if (cpt < 2) this.tabZoneTxt[cpt] = new JTextField(PanelInit.TEXTE_TAILLE  , PanelInit.NB_CARA);
			else         this.tabZoneTxt[cpt] = new JTextField(PanelInit.TEXTE_QUANTITE, PanelInit.NB_CARA);
		}
	}
	
	// Méthode privée vérifiant les valeurs saisie dans les JTextField
	// Si des valeurs sont erronées, un pop-up indiquant l'erreur est affiché, et la méthode renvoie faux
	// Dans le cas où aucune erreur n'a été trouvé, la méthode renvoie vrai
	
	private int valeursValide()
	{
		int val = 0;
		
		for ( int cpt = 0; cpt < tabZoneTxt.length; cpt++ )
		{
			try
			{
				// Je regarde si le cast est possible...
				
				val = Integer.parseInt(this.tabZoneTxt[cpt].getText() );
				
				if ( (cpt <  2 && (val < 0 || val > 30) ) || (cpt >= 2 && (val < 0 || val > 4 ) ) ) 
				{
					// Si l'un des champs possède une valeur non comprise dans sa plage de valeurs, j'affiche un pop-up
					return 1;
				}
			}
			
			catch (Exception ex) 
			{
				
				// Si le cast en entier du contenu d'un JTexField n'est pas possible, j'affiche un pop-up
				
				JOptionPane.showMessageDialog
				(this, "Certaines valeurs ne sont pas entières", "Erreur de saisie", JOptionPane.ERROR_MESSAGE);
				return 2;
			}
		}
		
		return 0;
	
	}
	
	// Méthodes liée aux Zone d'entrée de Texte
	public void focusGained( FocusEvent e )
	{
		JTextField focusTxtField = (JTextField) e.getSource();
		focusTxtField.setText("");
	
	}
	
	public void focusLost( FocusEvent e )
	{
		JTextField unfocusTxtField = (JTextField) e.getSource() ;
		
		// On cherche quelle zone de texte n'est plus sélectionnée
		int indTxtClc = -1 ;
		
		for ( int cpt = 0 ; cpt < tabZoneTxt.length ; cpt++ )
			if ( tabZoneTxt[cpt].equals(unfocusTxtField) )
			
				indTxtClc = cpt ;
		
		String txtActuelle = unfocusTxtField.getText() ;
		
		if ( (indTxtClc == 0 || indTxtClc == 1) && ( txtActuelle.equals( PanelInit.TEXTE_TAILLE ) || txtActuelle.equals("") ) )
			unfocusTxtField.setText( PanelInit.TEXTE_TAILLE );
		
		if ( (indTxtClc == 2 || indTxtClc == 3) && ( txtActuelle.equals( PanelInit.TEXTE_TAILLE ) || txtActuelle.equals("") )  )
			unfocusTxtField.setText( PanelInit.TEXTE_QUANTITE );
		
		if (txtActuelle.equals( PanelInit.TEXTE_TAILLE ) || txtActuelle.equals("") )
		{
			if (indTxtClc == 0 || indTxtClc == 1) unfocusTxtField.setText( PanelInit.TEXTE_TAILLE  );
			if (indTxtClc == 2 || indTxtClc == 3) unfocusTxtField.setText( PanelInit.TEXTE_QUANTITE);
		}
		
	}
}
