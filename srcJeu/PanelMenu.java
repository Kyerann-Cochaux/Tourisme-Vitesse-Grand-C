package source.ihm;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.*;

import source.AppliJeu ;

public class PanelMenu extends JPanel implements ActionListener
{
	private AppliJeu    ctrl;
	
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
		
		this.panelAccueil = new JPanel();
		this.panelAccueil.setLayout(new GridLayout(5, 1, 0, 20) );
		
		this.lblMenu = new JLabel ("Tourisme à Vitesse Grand C", SwingConstants.CENTER);
		this.lblMenu.setFont      (FrameJeu.POLICE_TEXTE         );
		this.lblMenu.setForeground(FrameJeu.COULEUR_TITRE        );
		
		this.btnSolo         = new JButton("Partie Solo" );
		this.btnLocal        = new JButton("Partie Multijoueur Local" );
		this.btnMultijoueur  = new JButton("Partie Multijoueur" );
		
		
		/* ---------------------------------- */
		/*    Positionnement des composants   */
		/* ---------------------------------- */
		
		this.panelAccueil.add( this.lblMenu );
		JPanel panelVide = new JPanel();
		panelVide.setOpaque(false);
		this.panelAccueil.add( panelVide );
		this.panelAccueil.add( this.btnSolo );
		this.panelAccueil.add( this.btnLocal );
		this.panelAccueil.add( this.btnMultijoueur );
		
		this.panelAccueil.setOpaque(false);
		
		
		this.add(this.panelAccueil);
		
		/* ------------------------------- */
		/*    Activation des Composants    */
		/* ------------------------------- */
		
		this.btnSolo.addActionListener(this);
		this.btnLocal.addActionListener(this);
		this.btnMultijoueur.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == this.btnSolo)
		{
			JFileChooser explorateur = new JFileChooser();
			explorateur.setDialogTitle("Ouvrir un plateau existant");
			
			explorateur.setCurrentDirectory(new File("../source/metier/sauvegardes/"));
			
			int resultat = explorateur.showOpenDialog(this.frameJeu);
			
			if (resultat == JFileChooser.APPROVE_OPTION)
			{
				File fichierSelectionne = explorateur.getSelectedFile();
			}
		}
	}
}
