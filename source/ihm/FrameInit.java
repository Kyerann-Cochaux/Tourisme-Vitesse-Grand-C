package source.ihm;

import source.Controleur;

import javax.swing.*;

public class FrameInit extends JFrame
{
	private Controleur ctrl;
	private PanelInit  panelInit;

	public FrameInit(Controleur ctrl) 
	{
		this.setTitle("Tourisme à Vitesse Grand C");
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);

		/* ---------------------------------- */
		/*       Création des composants      */
		/* ---------------------------------- */

		this.ctrl      = ctrl;
		this.panelInit = new PanelInit(ctrl);

		/* ---------------------------------- */
		/*    Positionnement des composants   */
		/* ---------------------------------- */

		this.add(this.panelInit);

		/* ---------------------------------- */
		/*      Activation des composants     */
		/* ---------------------------------- */

		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	

	}
}
