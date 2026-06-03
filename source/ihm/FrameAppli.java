package source.ihm;

import source.Controleur;

import javax.swing.*;
import java.awt.Color;
import java.awt.Font;

public class FrameAppli extends JFrame
{
	protected static final Font  POLICE_TEXTE  = new Font    ("Goldman", Font.BOLD, 25);
	protected static final Color COULEUR_TITRE = Color.decode("#f1c232");
	protected static final Color COULEUR_ZONE  = Color.decode("#f3f3f3");
	protected static final Color COULEUR_FOND  = new Color (37, 37, 37);

	private Controleur ctrl;
	private PanelAcceuil  panelAcceuil;

	public FrameAppli(Controleur ctrl) 
	{
		this.setTitle("Tourisme à Vitesse Grand C");
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);

		/* ---------------------------------- */
		/*       Création des composants      */
		/* ---------------------------------- */

		this.ctrl      = ctrl;
		this.panelAcceuil = new PanelAcceuil(ctrl);

		/* ---------------------------------- */
		/*    Positionnement des composants   */
		/* ---------------------------------- */

		this.add(this.panelAcceuil);

		/* ---------------------------------- */
		/*      Activation des composants     */
		/* ---------------------------------- */

		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	

	}
}
