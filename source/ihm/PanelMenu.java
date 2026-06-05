package source.ihm;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.*;

import source.AppliCreation ;

public class PanelMenu extends JPanel implements ActionListener
{
	private AppliCreation    ctrl;
	
	private FrameJeu frameJeu;
	
	private JPanel panelAccueil;
	
	private JLabel lblMenu;
	
	private JButton btnCharger ;
	
	public PanelMenu(AppliCreation ctrl, FrameJeu frameJeu)
	{
		this.ctrl     = ctrl;
		this.frameJeu = frameJeu;

		this.setBackground(FrameJeu.COULEUR_FOND_FONCE);
		
		/* ---------------------------------- */
		/*       Création des composants      */
		/* ---------------------------------- */
		
		this.panelAccueil = new JPanel();
		this.panelAccueil.setLayout(new GridLayout(4, 1, 0, 20) );
		
		this.lblMenu = new JLabel ("Tourisme à Vitesse Grand C", SwingConstants.CENTER);
		this.lblMenu.setFont      (FrameJeu.POLICE_TEXTE         );
		this.lblMenu.setForeground(FrameJeu.COULEUR_TITRE        );
		
		this.btnCharger  = new JButton("Charger un plateau" );
		
		
		/* ---------------------------------- */
		/*    Positionnement des composants   */
		/* ---------------------------------- */
		
		this.panelAccueil.add( this.lblMenu );
		JPanel panelVide = new JPanel();
		panelVide.setOpaque(false);
		this.panelAccueil.add( panelVide );
		this.panelAccueil.add( this.btnCharger );
		
		this.panelAccueil.setOpaque(false);
		
		
		this.add(this.panelAccueil);
		
		/* ------------------------------- */
		/*    Activation des Composants    */
		/* ------------------------------- */
		
		this.btnCharger.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == this.btnCharger)
		{
			JFileChooser explorateur = new JFileChooser();
			explorateur.setDialogTitle("Ouvrir un plateau existant");
			
			explorateur.setCurrentDirectory(new File("."));
			
			int resultat = explorateur.showOpenDialog(this.frameJeu);
			
			if (resultat == JFileChooser.APPROVE_OPTION)
			{
				File fichierSelectionne = explorateur.getSelectedFile();
			}
		}
	}
}
