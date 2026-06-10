package srcJeu.ihm;

import srcJeu.AppliJeu ;

import java.awt.event.*;
import javax.swing.*;
import java.awt.GridLayout;

public class PanelMenu extends JPanel implements ActionListener
{
	private AppliJeu ctrl;
	
	private FrameJeu frameJeu;
	
	private JPanel panelAccueil;
	
	private JLabel lblMenu;
	
	private JButton btnSolo ;
	private JButton btnLocal ;
	private JButton btnMultijoueur ;

	public PanelMenu(AppliJeu ctrl, FrameJeu frameJeu)
	{

		this.ctrl     = ctrl;
		this.frameJeu = frameJeu;

		this.setBackground(FrameJeu.COULEUR_FOND_FONCE);

		/* ---------------------------------- */
		/*       Création des composants      */
		/* ---------------------------------- */
		

		this.panelAccueil = new JPanel(new GridLayout(5, 1, 0, 20) );
		this.panelAccueil.setOpaque(false);

		this.lblMenu = new JLabel ("Jouer une partie", SwingConstants.CENTER);
		this.lblMenu.setFont      (FrameJeu.POLICE_TEXTE );
		this.lblMenu.setForeground(FrameJeu.COULEUR_TITRE);
		
		this.btnSolo        = new JButton("Partie Solo"             );
		this.btnLocal       = new JButton("Partie Multijoueur Local");
		this.btnMultijoueur = new JButton("Partie Multijoueur"      );
		
		/* ---------------------------------- */
		/*    Positionnement des composants   */
		/* ---------------------------------- */
		
		this.panelAccueil.add(this.lblMenu       );
		this.panelAccueil.add(new JLabel()       );
		this.panelAccueil.add(this.btnSolo       );
		this.panelAccueil.add(this.btnLocal      );
		this.panelAccueil.add(this.btnMultijoueur);
		
		this.add(this.panelAccueil);
		
		/* ------------------------------- */
		/*    Activation des Composants    */
		/* ------------------------------- */
		
		this.btnSolo       .addActionListener(this);
		this.btnLocal      .addActionListener(this);
		this.btnMultijoueur.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == this.btnSolo)
		{
			this.ctrl.chargerPlateau(this.frameJeu.chargerFichier() );
			this.frameJeu.ouvrirPanel(FrameJeu.PANEL_JEU);
		}
	}
}
