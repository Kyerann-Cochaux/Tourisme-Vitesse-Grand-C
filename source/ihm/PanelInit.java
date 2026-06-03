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

	private static final Font  POLICE_TEXTE  = new Font    ("Goldman", Font.BOLD, 25);

	private static final Color COULEUR_TEXTE = Color.decode("#f1c232");
	private static final Color COULEUR_TEXTE2 = Color.decode("#f3f3f3");
	private static final Color COULEUR_FOND  = new Color (37, 37, 37);
	
	private static final String TEXTE_TAILLE   = "Valeur entre 1 et 30" ;
	private static final String TEXTE_QUANTITE = "Valeur entre 2 et 4" ;

	private static final int NB_CARA = 50;
	
	// Création de 2 tableaux pour faciliter la modification des composants

	private JLabel    [] tabLbl;
	private JTextField[] tabTxt;

	private JButton btnLancer       ;
	private JButton btnRenitialiser ;
	
	private Controleur ctrl ;

	public PanelInit(Controleur ctrl)
	{

		JPanel panelAction;
		JPanel panelSaisie;
		
		this.ctrl = ctrl ;
		
		this.setLayout(new BorderLayout() );
		this.setBackground(PanelInit.COULEUR_FOND);

		/* ---------------------------------- */
		/*       création des composants      */
		/* ---------------------------------- */

		panelAction = new JPanel();
		panelSaisie = new JPanel(new GridLayout(5, 1  ) );
		
		this.tabLbl = new JLabel    [5];
		this.tabTxt = new JTextField[4];

		this.tabLbl[0] = new JLabel("Tourisme à Vitesse grand C"      , SwingConstants.CENTER); // Titre
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
			label.setFont      (PanelInit.POLICE_TEXTE );
			label.setForeground(PanelInit.COULEUR_TEXTE);
		}

		// Modification individuelle de la police du Titre, car c'est le seul JLabel où la police est différente des autres

		this.tabLbl[0].setFont( new Font(this.tabLbl[0].getFont().getName(), this.tabLbl[0].getFont().getStyle(),100) );

		/*--- CO3NFIGURATION ET CENTRAGE DES JTEXTFIELD ---*/

		for (JTextField txtF : this.tabTxt)
		{
			txtF.setFont(PanelInit.POLICE_TEXTE);
			txtF.setForeground(PanelInit.COULEUR_TEXTE2);
			txtF.setHorizontalAlignment(JTextField.CENTER);
		}
		
		/* ---------------------------------- */
		/*    Positionnement des composants   */
		/* ---------------------------------- */

		panelSaisie.add(this.tabLbl[0]); // Titre

		// On part de 1 car on ajoute pas le titre, déjà ajouté au dessus, et que le titre n'a pas besoin d'être sur un sous panel
		// Car il ne possède pas de JTextField associé.


		for (int cpt = 1; cpt < tabLbl.length; cpt++) 
			panelSaisie.add(this.creerPanelCentre(this.tabLbl[cpt], this.tabTxt[cpt -1]) );
													// JTextfield         JLabel
													// Dans l'ordre des tableaux définis au dessus.

		panelAction.add(btnLancer);
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
		this.btnLancer.addActionListener(this);
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
	
	// Méthodes liée au Boutons d'Actions
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == this.btnLancer)
		{
			try
			{
				int nbLignes    = Integer.parseInt(this.tabTxt[0].getText());
				int nbColonnes  = Integer.parseInt(this.tabTxt[1].getText());
				int nbEspece    = Integer.parseInt(this.tabTxt[2].getText());
				int nbPlanete   = Integer.parseInt(this.tabTxt[3].getText());
				
				this.ctrl.initialiserPlateau(nbLignes, nbColonnes, nbPlanete, nbEspece);
				
			}
			catch(NumberFormatException ex)
			{
				this.rinitialiserTexte();
			}
		}
		
		if(e.getSource() == this.btnRenitialiser)
		{
			this.rinitialiserTexte();
		}
	}
	
	private void rinitialiserTexte()
	{
		this.tabTxt[0].setText( PanelInit.TEXTE_TAILLE   );
		this.tabTxt[1].setText( PanelInit.TEXTE_TAILLE   );
		this.tabTxt[2].setText( PanelInit.TEXTE_QUANTITE );
		this.tabTxt[3].setText( PanelInit.TEXTE_QUANTITE );
	}
	
	// Méthodes liée aux Zone d'entrée de Texte
	public void focusGained( FocusEvent e )
	{
		JTextField txtFldCliquer = (JTextField) e.getSource() ;
		
		txtFldCliquer.setText("");
	}
	
	public void focusLost( FocusEvent e )
	{
		JTextField txtFldPlusFocus = (JTextField) e.getSource() ;
		
		// On trouve quelle Zone de Texte n'est plus cliquer
		int indTxtClc = -1 ;
		for ( int i=0 ; i < tabTxt.length ; i++ )
		{
			if ( tabTxt[i].equals(txtFldPlusFocus) )
			{
				indTxtClc = i ;
			}
		}
		
		String txtActuelle = txtFldPlusFocus.getText() ;
		
		System.out.println( "Texte de la Zone " + indTxtClc + " : " + txtActuelle);
		
		if ( (indTxtClc == 0 || indTxtClc == 1) && txtActuelle.equals( PanelInit.TEXTE_TAILLE ) )
		{
			txtFldPlusFocus.setText( PanelInit.TEXTE_TAILLE );
		}
		if ( (indTxtClc == 2 || indTxtClc == 3) && txtActuelle.equals( PanelInit.TEXTE_QUANTITE ) )
		{
			txtFldPlusFocus.setText( PanelInit.TEXTE_QUANTITE );
		}
	}
}
