package source.ihm;

import source.AppliJeu;

import javax.swing.*;
import java.awt.Color;
import java.awt.Font;

public class FrameJeu extends JFrame
{
	protected static final Font  POLICE_TEXTE        = new Font    ("Goldman", Font.BOLD, 25);

	protected static final Color COULEUR_TITRE       = Color.decode("#f1c232");
	protected static final Color COULEUR_ZONE        = Color.decode("#f3f3f3");

	protected static final Color COULEUR_FOND_FONCE  = new Color (85, 64, 98);
	protected static final Color COULEUR_FOND_CLAIRE = new Color (70, 70, 70);
	
	private AppliJeu ctrl;
	private JPanel        panelActuelle;
	
	public FrameJeu(AppliJeu ctrl)
	{
		this.setTitle("Tourisme à Vitesse Grand C");
		this.setSize(500, 300);
		this.setLocation(675, 400);
		
		/* ---------------------------------- */
		/*       Création des composants      */
		/* ---------------------------------- */
		
		this.ctrl          = ctrl;
		this.panelActuelle = new PanelMenu(this.ctrl, this);
		
		/* ---------------------------------- */
		/*    Positionnement des composants   */
		/* ---------------------------------- */
		
		this.add(this.panelActuelle);
		
		/* ---------------------------------- */
		/*      Activation des composants     */
		/* ---------------------------------- */
		
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
