package source.ihm;

import source.Controleur;

import javax.swing.*;
import java.awt.Color;
import java.awt.Font;

public class FrameJeu extends JFrame
{
	protected static final Font  POLICE_TEXTE  = new Font    ("Goldman", Font.BOLD, 25);
	protected static final Color COULEUR_TITRE = Color.decode("#f1c232");
	protected static final Color COULEUR_ZONE  = Color.decode("#f3f3f3");
	protected static final Color COULEUR_FOND  = new Color (37, 37, 37);
	
	private Controleur ctrl;
	private JPanel     panelActuelle;
	
	public FrameJeu(Controleur ctrl) 
	{
		this.setTitle("Tourisme à Vitesse Grand C");
		this.setSize(300, 250);
		this.setLocation(800, 450);
		
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
