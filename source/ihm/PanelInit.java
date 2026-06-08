package source.ihm;

import javax.swing.*;

import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;

import source.AppliCreation;

/**
 * Panel Init
 * 
 * Panel de Prise de Paramètre pour créer un nouveau Plateau
 * 
 * @author Groupe 5
 * 
 */

public class PanelInit extends JPanel implements ActionListener, FocusListener
{
	
	private static final String TEXTE_TAILLE   = "Valeur entre 5 et 30";
	private static final String TEXTE_QUANTITE = "Valeur entre 2 et 4" ;
	
	private static final int NB_CARA = 50;
	
	// Création de 2 tableaux pour faciliter la modification des composants
	private JLabel    [] tabLbl;
	private JTextField[] tabZoneTxt;
	
	private int[] tabParametreEntrer;
	
	private JButton btnLancer;
	private JButton btnReset ;
	private JButton btnRetour;
	
	private AppliCreation ctrl ;
	private FrameCreation frameCreation ;
	
	public PanelInit( AppliCreation ctrl, FrameCreation frameCreation )
	{

		JPanel panelAction    ;
		JPanel panelSaisie    ;

		this.ctrl          = ctrl;
		this.frameCreation = frameCreation;
		
		this.setLayout    (new BorderLayout() );
		this.setBackground(FrameCreation.COULEUR_FOND_FONCE);
		
		/* ---------------------------------- */
		/*       création des composants      */
		/* ---------------------------------- */

		panelAction     = new JPanel();
		panelSaisie     = new JPanel( new GridLayout(6,1) );
		
		this.tabLbl     = new JLabel    [6];
		this.tabZoneTxt = new JTextField[5];

		this.tabLbl[0]        = new JLabel("Nouveau Plateau"                 , SwingConstants.CENTER); // Titre
		this.tabLbl[1]        = new JLabel("Nom du plateau : "               , SwingConstants.CENTER);
		this.tabLbl[2]        = new JLabel("Nombre de lignes :"              , SwingConstants.CENTER); // nbLignes
		this.tabLbl[3]        = new JLabel("Nombre de colonnes :"            , SwingConstants.CENTER); // nbColonnes
		this.tabLbl[4]        = new JLabel("Nombre de planètes différentes :", SwingConstants.CENTER); // nbPlanete
		this.tabLbl[5]        = new JLabel("Nombre d'espèces différentes :"  , SwingConstants.CENTER); // nbEspece
		

		/*
			Si le compteur vaut 0 ou 1, le texte est "valeur entre 1 et 30", sinon c'est "valeur entre 2 et 4"
			La boucle permet d'éviter de répéter 4 fois la même instruction, sachant qu'il y a 2 fois 2 textes identiques
		*/
		
		for (int cpt = 0; cpt < tabZoneTxt.length; cpt++) 
		{
			if (cpt == 0)             this.tabZoneTxt[cpt] = new JTextField("Entrez le nom du plateau", PanelInit.NB_CARA);
			if (cpt == 1 || cpt == 2) this.tabZoneTxt[cpt] = new JTextField(PanelInit.TEXTE_TAILLE  , PanelInit.NB_CARA);
			if (cpt > 2             ) this.tabZoneTxt[cpt] = new JTextField(PanelInit.TEXTE_QUANTITE, PanelInit.NB_CARA);
		}
		
		this.btnLancer  = new JButton("Lancer"       );
		this.btnReset   = new JButton("Réinitialiser");
		this.btnRetour  = new JButton("Retour"       );
		
		/* ---------------------------------- */
		/*    Configuration des composants    */
		/* ---------------------------------- */
		
		panelAction       .setOpaque(false);
		panelSaisie       .setOpaque(false);
		
		
		/*--- COULEURS ET POLICES ---*/
		
		for (JLabel label : tabLbl) 
		{
			label.setFont      (FrameCreation.POLICE_TEXTE );
			label.setForeground(FrameCreation.COULEUR_TITRE);
		}
		
		// Modification individuelle de la police du Titre, car c'est le seul JLabel où la police est différente des autres
		
		this.tabLbl[0].setFont( new Font(this.tabLbl[0].getFont().getName(), this.tabLbl[0].getFont().getStyle(),50) );
		
		/*--- CONFIGURATION ET CENTRAGE DES JTEXTFIELD ---*/
		
		for (JTextField txtF : this.tabZoneTxt)
		{
			txtF.setFont               (FrameCreation.POLICE_TEXTE);
			txtF.setForeground         (FrameCreation.COULEUR_ZONE);
			txtF.setHorizontalAlignment(JTextField   .CENTER      );
		}
		
		/* ---------------------------------- */
		/*    Positionnement des composants   */
		/* ---------------------------------- */
		
		panelSaisie.add(this.tabLbl[0]); // Titre
		// On part de 1 car on ajoute pas le titre, déjà ajouté au dessus, et qu'il n'a pas besoin d'être sur un sous panel
		
		
		for (int cpt = 1; cpt < tabLbl.length; cpt++) 
			panelSaisie.add(this.creerPanelCentre(this.tabLbl[cpt], this.tabZoneTxt[cpt -1]) );
			                                       // JTextfield         JLabel
			                                       // Dans l'ordre des tableaux définis au dessus.
		
		panelAction.add(btnLancer);
		panelAction.add(btnReset );
		panelAction.add(btnRetour);
		
		this.add(panelSaisie, BorderLayout.CENTER);
		this.add(panelAction, BorderLayout.SOUTH );
		
		/* ------------------------------- */
		/*    Activation des Composants    */
		/* ------------------------------- */
		
		// Activation des Zone d'entrée de texte
		for (JTextField txtF : this.tabZoneTxt)
			txtF.addFocusListener(this);
		
		// Activation des Boutons d'Action

		this.btnLancer .addActionListener(this);
		this.btnReset  .addActionListener(this);
		this.btnRetour .addActionListener(this);
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
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if (e.getSource() == this.btnReset) 
		{
			this.reinitialiserTexte();
		}
		
		if ( e.getSource() == this.btnLancer )
		{
			if ( this.valeursVerifier() )
			{
				int nbLignes   = Integer.parseInt( this.tabZoneTxt[1].getText() );
				int nbColonnes = Integer.parseInt( this.tabZoneTxt[2].getText() );
				int nbFormes   = Integer.parseInt( this.tabZoneTxt[3].getText() );
				int nbEspeces  = Integer.parseInt( this.tabZoneTxt[4].getText() );
				
				JFileChooser jfc = new JFileChooser("./metier/sauvegardes/");

				
			
				for (File fExistant : jfc.getCurrentDirectory().listFiles() ) 
				{

					if (this.tabZoneTxt[0].getText().equals("Entrez un nom pour le plateau") )
					{
						JOptionPane.showMessageDialog
						(this, "Le nom du fichier n'est pas renseigné",
						"Nom du fichier inexistant", JOptionPane.ERROR_MESSAGE);
						return;

					}
					if ((this.frameCreation.getNomSauvegarde() + ".data").equals(fExistant.getName() ) )
					{
						JOptionPane.showMessageDialog
						(this, "Le nom du fichier existe déjà",
						"Fichier déjà existant", JOptionPane.ERROR_MESSAGE);
						return;

					}
					else this.frameCreation.setNomSauvegarde( tabZoneTxt[0].getText() );
					
				}
				

				
				this.ctrl         .initialiserPlateau(nbLignes, nbColonnes, nbFormes, nbEspeces);
				this.frameCreation.ouvrirPanel(FrameCreation.PANEL_EDITION);



			}
		}
		
		if ( e.getSource() == this.btnRetour )
		{
			
			// this.frameCreation.ouvrirPanelCreation();
			this.frameCreation.ouvrirPanel(FrameCreation.PANEL_CREATION);
		}
	}
	
	private void reinitialiserTexte()
	{
		for (int cpt = 0; cpt < tabZoneTxt.length ; cpt++) 
		{
			if (cpt == 0)             this.tabZoneTxt[cpt].setText("Entrez le nom du plateau");
			if (cpt == 1 || cpt == 2) this.tabZoneTxt[cpt].setText(PanelInit.TEXTE_TAILLE  );
			if (cpt > 2             ) this.tabZoneTxt[cpt].setText(PanelInit.TEXTE_QUANTITE);
		}
	}
	
	// Méthodes privées vérifiant les valeurs saisie dans les JTextField
	// Si des valeurs sont erronées, un pop-up indique où sont les erreurs, et la méthode renvoie faux
	// Dans le cas où aucune erreur n'a été trouvé, la méthode renvoie vrai

	private boolean valeursVerifier()
	{
		boolean erreurLimPasTrouver = true ;
		boolean erreurNumPasTrouver = true ;
		
		for (int cpt = 1 ; cpt < this.tabZoneTxt.length ; cpt++ )
		{
			// Si le texte entrée n'est pas numérique valeurValide retourne 2
			if ( valeurValide( this.tabZoneTxt[cpt], cpt ) == 1 )
			{
				// Il faut éviter d'ouvrir une fenêtre pour la même erreur plusieurs fois.
				if ( erreurNumPasTrouver == true )
				{
					JOptionPane.showMessageDialog
					(this, "Certaines valeurs ne sont pas numérique",
					"Erreur de saisie", JOptionPane.ERROR_MESSAGE);
				}
				
				this.tabZoneTxt[cpt].setText("Erreur : Valeur Invalide");
				erreurNumPasTrouver = false ;
			}
			
			// Si une erreur de limite est trouvée valeurValide retourne 1
			if ( valeurValide( this.tabZoneTxt[cpt], cpt ) == 2 )
			{
				// Il faut éviter d'ouvrir une fenêtre pour la même erreur plusieurs fois.
				if ( erreurLimPasTrouver == true && erreurNumPasTrouver == true )
				{
					JOptionPane.showMessageDialog
					(this, "Certaines valeurs ne sont pas comprises dans les limites indiquées",
					 "Erreur de saisie", JOptionPane.ERROR_MESSAGE);
				}
				
				this.tabZoneTxt[cpt].setText("Erreur : Valeur Invalide");
				erreurLimPasTrouver = false ;
			}
		}
		
		return erreurLimPasTrouver && erreurNumPasTrouver ;
	}
	
	private int valeurValide( JTextField zoneTxt, int index )
	{
		try
		{
			// Je regarde si le cast est possible...
			int val = Integer.parseInt( zoneTxt.getText() );
			
			if ( ( index > 0 && index < 3 && (val < 5 || val > 30) ) ||
			     ( index >= 3 && (val < 2 || val > 4 ) ) )
			{
				// Si l'un des champs possède une valeur non comprise dans sa plage de valeurs,
				// Je retourne 2
				return 2;
			}
		}
		catch (Exception ex)
		{
			// Si le cast en entier du contenu d'un JTexField n'est pas possible
			// je retourne 1
			return 1;
		}
		
		return 0;
	}
	
	// Méthodes liées aux zone d'entrée de Texte
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
		
		if (txtActuelle.equals( PanelInit.TEXTE_TAILLE ) || txtActuelle.equals("") )
		{
			if (indTxtClc == 0                  ) unfocusTxtField.setText( "Entrez un nom pour le plateau");
			if (indTxtClc == 1 || indTxtClc == 2) unfocusTxtField.setText( PanelInit.TEXTE_TAILLE         );
			if (indTxtClc == 3 || indTxtClc == 4) unfocusTxtField.setText( PanelInit.TEXTE_QUANTITE       );
		}
		
	}
}
