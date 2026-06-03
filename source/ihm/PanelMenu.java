package source.ihm;

import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.GridLayout;

import java.awt.*;
import javax.swing.*;

import source.Controleur ;

public class PanelMenu extends JPanel
{
	private static final Font  POLICE_TEXTE  = new Font    ("Goldman", Font.BOLD, 100);
	private static final Color COULEUR_TEXTE = Color.decode("#f1c232");

	private JLabel  lblMenu   ;

	private JButton btnJouer  ;
	private JButton btnEdition;

	private Controleur ctrl;

	public PanelMenu(Controleur ctrl)
	{
		this.ctrl = ctrl;

		this.setLayout(new GridLayout(20, 1));

		this.lblMenu = new JLabel("MENU", SwingConstants.CENTER);
		this.lblMenu.setFont(PanelMenu.POLICE_TEXTE);
		this.lblMenu.setForeground(PanelMenu.COULEUR_TEXTE);

		JPanel panel1 = new JPanel();

		this.btnJouer   = new JButton("Jouer  ");
		panel1.add(this.btnJouer);
		
		JPanel panel2 = new JPanel();

		this.btnEdition = new JButton("Edition");
		panel2.add(this.btnEdition);

		this.add(this.lblMenu);

		JPanel panelVide = new JPanel();
		this.add(panelVide);


		this.add(panel1);

		this.add(panel2);

	}
}