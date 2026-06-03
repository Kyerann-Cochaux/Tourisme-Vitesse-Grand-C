package source.ihm;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import source.Controleur ;

public class panelAcceuil extends JPanel implements ActionListener
{
	private JPanel panelAcceuil;

	private JLabel lblMenu   ;

	private JButton btnNouveau;
	private JButton btnOuvrir ;

	private Controleur ctrl;

	public PanelMenu(Controleur ctrl)
	{
		this.panelAcceuil  = new JPanel();
		this.panelChoix = new JPanel();

		this.ctrl = ctrl;

		this.panelAcceuil.setLayout(new GridLayout(3, 1) );
		this.panelChoix.setLayout(new GridLayout(4, 1) );

		/* ---------------------------------- */
		/*       Création des composants      */
		/* ---------------------------------- */

		this.panelAcceuil = new JPanel();
		this.panelAcceuil = new JPanel();

		this.lblMenu = new JLabel("MENU", SwingConstants.CENTER);
		this.lblMenu.setFont      (FrameAppli.POLICE_TEXTE);
		this.lblMenu.setForeground(FrameAppli.COULEUR_ZONE);

		this.btnNouveau   = new JButton("Jouer");
		this.btnOuvrir    = new JButton("Edition");

		
		/* ---------------------------------- */
		/*    Positionnement des composants   */
		/* ---------------------------------- */
		
		this.panelAcceuil.add(this.btnJouer);
		this.panelAcceuil.add(this.btnEdition);

		this.add(this.lblMenu);
		this.add( new JPanel() );
		this.add(this.panelAcceuil, new GridBagConstraints() );

	}

	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == this.btnNouveau)
		{
			this.remove(this.panelAcceuil);
			this.add(new PanelInit(this.ctrl));
		}
		if (e.getSource() == this.btnOuvrir)
		{
			
		}
	}
}
