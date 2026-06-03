package source.ihm;

import java.awt.*;
import javax.swing.*;

import source.Controleur ;

public class PanelMenu extends JPanel
{


	private JLabel  lblMenu   ;

	private JButton btnJouer  ;
	private JButton btnEdition;

	private Controleur ctrl;

	public PanelMenu(Controleur ctrl)
	{
		JPanel sPanelAction;
		this.ctrl = ctrl;

		this.setLayout(new GridLayout(3, 1) );

		/* ---------------------------------- */
		/*       Création des composants      */
		/* ---------------------------------- */

		sPanelAction = new JPanel();
		sPanelAction = new JPanel();

		this.lblMenu = new JLabel("MENU", SwingConstants.CENTER);
		this.lblMenu.setFont      (FrameAppli.POLICE_TEXTE);
		this.lblMenu.setForeground(FrameAppli.COULEUR_ZONE);

		this.btnJouer   = new JButton("Jouer");
		this.btnEdition = new JButton("Edition");

		
		/* ---------------------------------- */
		/*    Positionnement des composants   */
		/* ---------------------------------- */
		
		sPanelAction.add(this.btnJouer);
		sPanelAction.add(this.btnEdition);

		this.add(this.lblMenu);
		this.add( new JPanel() );
		this.add(sPanelAction, new GridBagConstraints() );

	}
}