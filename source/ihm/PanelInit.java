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
	private JTextField[] tabTxt;
	
	private int[] tabParametreEntrer ;
	
	private JButton btnLancer       ;
	private JButton btnRenitialiser ;
	
	private Controleur ctrl ;

	public PanelInit( Controleur ctrl )
	{
	
		JPanel panelAction;
		JPanel panelSaisie;
		
		this.ctrl = ctrl ;
		
		this.setLayout(new BorderLayout() );
		this.setBackground(FrameAppli.COULEUR_FOND);
		
		/* ---------------------------------- */
		/*       création des composants      */
		/* ---------------------------------- */
		
		panelAction = new JPanel();
		panelSaisie = new JPanel(new GridLayout(5, 1  ) );
		
		this.tabLbl = new JLabel    [5];
		this.tabTxt = new JTextField[4];
		
		this.tabLbl[0] = new JLabel("Innitialisation du Plateau"      , SwingConstants.CENTER); // Titre
		this.tabLbl[1] = new JLabel("Nombre de lignes :"              , SwingConstants.CENTER); // nbLignes
		this.tabLbl[2] = new JLabel("Nombre de colonnes :"            , SwingConstants.CENTER); // nbColonnes
		this.tabLbl[3] = new JLabel("Nombre d'espèces différentes :"  , SwingConstants.CENTER); // nbEspece
		this.tabLbl[4] = new JLabel("Nombre de planètes différentes :", SwingConstants.CENTER); // nbPlanete
		
		/*
			Si le compteur vaut 0 ou 1, le texte est "valeur entre 1 et 30", sinon c'est "valeur entre 2 et 4"
			La boucle permet d'éviter de répéter 4 fois la même instruction, sachant qu'il y a 2 fois 2 textes identiques
		*/
		
		for (int cpt = 0; cpt < tabTxt.length ; cpt++) 
		{
			if (cpt < 2) this.tabTxt[cpt] = new JTextField("Valeur entre 1 et 30", PanelInit.NB_CARA);
			else         this.tabTxt[cpt] = new JTextField("Valeur entre 2 et 4" , PanelInit.NB_CARA);
		}
		
		this.btnLancer       = new JButton("Lancer"      );
		this.btnRenitialiser = new JButton("Renitialiser");
		
		/* ---------------------------------- */
		/*    Configuration des composants    */
		/* ---------------------------------- */
		
		panelAction.setOpaque(false);
		panelSaisie.setOpaque(false);
		
		/*--- COULEURS ET POLICES ---*/
		
		for (JLabel label : tabLbl) 
		{
			label.setFont      (FrameAppli.POLICE_TEXTE);
			label.setForeground(FrameAppli.COULEUR_TITRE);
		}
		
		// Modification individuelle de la police du Titre, car c'est le seul JLabel où la police est différente des autres
		
		this.tabLbl[0].setFont( new Font(this.tabLbl[0].getFont().getName(), this.tabLbl[0].getFont().getStyle(),50) );
		
		/*--- CO3NFIGURATION ET CENTRAGE DES JTEXTFIELD ---*/
		
		for (JTextField txtF : this.tabTxt)
		{
			txtF.setFont               (FrameAppli.POLICE_TEXTE);
			txtF.setForeground         (FrameAppli.COULEUR_ZONE);
			txtF.setHorizontalAlignment(JTextField.CENTER      );
		}
		
		/* ---------------------------------- */
		/*    Positionnement des composants   */
		/* ---------------------------------- */
		
		panelSaisie.add(this.tabLbl[0]); // Titre
		
		// On part de 1 car on ajoute pas le titre, déjà ajouté au dessus, et qu'il n'a pas besoin d'être sur un sous panel
		// Car il ne possède pas de JTextField associé.
		
		
		for (int cpt = 1; cpt < tabLbl.length; cpt++) 
			panelSaisie.add(this.creerPanelCentre(this.tabLbl[cpt], this.tabTxt[cpt -1]) );
													// JTextfield         JLabel
													// Dans l'ordre des tableaux définis au dessus.
		
		panelAction.add(   btnLancer   );
		panelAction.add(btnRenitialiser);
		
		this.add(panelSaisie, BorderLayout.CENTER);
		this.add(panelAction, BorderLayout.SOUTH );
		
		/* ------------------------------- */
		/*    Activation des Composants    */
		/* ------------------------------- */
		
		// Activation des Zone d'entrée de texte
		for (JTextField txtF : this.tabTxt)
		{
			txtF.addFocusListener(this);
		}
		
		// Activation des Boutons d'Action
		this.btnLancer      .addActionListener(this);
		this.btnRenitialiser.addActionListener(this);
	}
	
	/*
	Méthode utilitaire pour encapsuler un JTextField dans un panel centré.
	Cela permet de respecter la taille de 30 colonnes sans l'étirer.
	*/
	
	/*private JPanel creerPanelCentre(JTextField textField)
	{
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		panel.setOpaque(false); // Rend le panel transparent pour voir le fond sombre
		panel.add(textField);
		return panel;
	}*/
	
	/*
		Méthode utilitaire permettant d'encapsuler dans un panel les 2 composants fournis en paramètres
		
		Le panel renvoyé contient 2 sous panel contenant chacun un des composants fournis
		
		Peut ne pas créer l'affichage souhaitée, à tester
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
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == this.btnLancer)
		{
			this.tabParametreEntrer = new int[this.tabTxt.length] ;
			
			
			// Vérification des valeurs dans les Zones de Texte
			boolean valeursValide = true ;
			for ( int i=0 ; i < this.tabTxt.length ; i++ )
			{
				try
				{
					int valActuelle = this.tabParametreEntrer[i] = Integer.parseInt(this.tabTxt[i].getText());
					
					if ( i == 0 || i == 1 )
					{
						if ( valActuelle < 1 || valActuelle > 30 )
						{
							this.tabParametreEntrer[i] = -1 ;
							this.tabTxt[i].setText("Erreur : Valeur Invalide");
							valeursValide = false ;
						}
					}
					
					if ( i == 2 || i == 3 )
					{
						if ( valActuelle < 2 || valActuelle > 4 )
						{
							this.tabParametreEntrer[i] = -1;
							this.tabTxt[i].setText("Erreur : Valeur Invalide");
							valeursValide = false ;
						}
					}
				}
				catch(NumberFormatException ex)
				{
					this.tabTxt[i].setText("Erreur : Valeur Invalide");
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
		
		if(e.getSource() == this.btnRenitialiser)
		{
			this.reinitialiserTexte();
		}
	}
	
	private void reinitialiserTexte()
	{
		this.tabTxt[0].setText( PanelInit.TEXTE_TAILLE   );
		this.tabTxt[1].setText( PanelInit.TEXTE_TAILLE   );
		this.tabTxt[2].setText( PanelInit.TEXTE_QUANTITE );
		this.tabTxt[3].setText( PanelInit.TEXTE_QUANTITE );
	}
	
	// Méthodes liée aux Zone d'entrée de Texte
	public void focusGained( FocusEvent e )
	{
		JTextField focusTxtField = (JTextField) e.getSource() ;
		
		focusTxtField.setText("");
	}
	
	public void focusLost( FocusEvent e )
	{
		JTextField unfocusTxtField = (JTextField) e.getSource() ;
		
		// On cerche quelle zone de texte n'est plus cliquée
		int indTxtClc = -1 ;
		for ( int cpt = 0 ; cpt < tabTxt.length ; cpt++ )
		{
			if ( tabTxt[cpt].equals(unfocusTxtField) )
			{
				indTxtClc = cpt ;
			}
		}
		
		String txtActuelle = unfocusTxtField.getText() ;
		
		if ( (indTxtClc == 0 || indTxtClc == 1) && ( txtActuelle.equals( PanelInit.TEXTE_TAILLE ) || txtActuelle.equals("") ) )
		{
			unfocusTxtField.setText( PanelInit.TEXTE_TAILLE );
		}
		if ( (indTxtClc == 2 || indTxtClc == 3) && ( txtActuelle.equals( PanelInit.TEXTE_TAILLE ) || txtActuelle.equals("") )  )
		{
			unfocusTxtField.setText( PanelInit.TEXTE_QUANTITE );
		}
	}
}
