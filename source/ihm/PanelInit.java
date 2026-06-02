package source.ihm;

import javax.swing.*;
import java.awt.*;

public class PanelInit extends JPanel
{

	private static final Font  TITRE = new Font    ("Goldman", Font.BOLD, 100);
	private static final Font  LABEL = new Font    ("Goldman", Font.BOLD, 25 );
	private static final Color OR    = Color.decode("#f1c232");

	private JTextField txtLigne    ;
	private JTextField txtColonne  ;
	private JTextField txtNbEspece ;
	private JTextField txtNbPlanete;

	private JLabel lblTitre     ; 
	private JLabel lblLigne     ;
	private JLabel lblColonne   ;
	private JLabel lblNbEspece  ;
	private JLabel lblNbPlanete ;

	private JButton btnStart ;
	private JButton btnReset ;

	public PanelInit()
	{
		this.setLayout(new GridLayout(10, 1  ));
		this.setBackground(new Color (37, 37, 37));

		/* ---------------------------------- */
		/*       création des composants      */
		/* ---------------------------------- */

		this.txtLigne        = new JTextField(50);
		this.txtColonne      = new JTextField(50);
		this.txtNbEspece     = new JTextField(50);
		this.txtNbPlanete    = new JTextField(50);

        this.lblTitre         = new JLabel("Tourisme à VitesseC", SwingConstants.CENTER); 
	    this.lblLigne         = new JLabel("Entrez le nombre de lignes souhaité pour le plateau (1 à 30) :", SwingConstants.CENTER);
	    this.lblColonne       = new JLabel("Entrez le nombre de colonnes souhaité pour le plateau (1 à 30) :", SwingConstants.CENTER);
	    this.lblNbEspece      = new JLabel("Entrez le nombre d'espèces souhaité pour le plateau (2 à 4) :", SwingConstants.CENTER);
	    this.lblNbPlanete     = new JLabel("Entrez le nombre de planètes souhaité pour le plateau (2 à 4) :", SwingConstants.CENTER);

        this.btnStart = new JButton("Start");
	    this.btnReset = new JButton("Reset");

		/*--- COULEURS ET POLICES ---*/


		this.lblTitre.setFont(policeTitre);
		this.lblTitre.setForeground(couleurOr); 

		this.lblLigne.setFont(policeLabels);
		this.lblLigne.setForeground(couleurOr);

		this.lblColonne.setFont(policeLabels);
		this.lblColonne.setForeground(couleurOr);

		this.lblNbEspece.setFont(policeLabels);
		this.lblNbEspece.setForeground(couleurOr);

		this.lblNbPlanete.setFont(policeLabels);
		this.lblNbPlanete.setForeground(couleurOr);

		/*--- CONFIGURATION ET CENTRAGE DES JTEXTFIELD ---*/
		this.ligne.setHorizontalAlignment    (JTextField.CENTER);
		this.colonne.setHorizontalAlignment  (JTextField.CENTER);
		this.nbEspece.setHorizontalAlignment (JTextField.CENTER);
		this.nbPlanete.setHorizontalAlignment(JTextField.CENTER);

		/*--- AJOUT DES COMPOSANTS ---*/
		this.add(this.lblTitre);

		this.add(this.lblLigne);
		this.add(creerPanelCentre(this.ligne));

		this.add(this.lblColonne);
		this.add(creerPanelCentre(this.colonne));

		this.add(this.lblNbEspece);
		this.add(creerPanelCentre(this.nbEspece));

		this.add(this.lblNbPlanete);
		this.add(creerPanelCentre(this.nbPlanete));

		JPanel panelBtn = new JPanel();
		panelBtn.setLayout(new FlowLayout());
		panelBtn.setOpaque(false);
		panelBtn.add(btnStart);
		panelBtn.add(btnReset);
		this.add(panelBtn);
	}

	/*
	Méthode utilitaire pour encapsuler un JTextField dans un panel centré.
	Cela permet de respecter la taille de 30 colonnes sans l'étirer.
	*/
	private JPanel creerPanelCentre(JTextField textField)
	{
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		panel.setOpaque(false); // Rend le panel transparent pour voir le fond sombre
		panel.add(textField);
		return panel;
	}
}