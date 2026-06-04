package source.ihm;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.*;

import source.Controleur ;

public class PanelCreation extends JPanel implements ActionListener
{
	private Controleur    ctrl;
	private FrameCreation frameCreation;
	
	private JPanel panelAccueil;
	
	private JLabel lblMenu  ;
	
	private JButton btnNouveau;
	private JButton btnOuvrir ;
	
	
	
	public PanelCreation(Controleur ctrl, FrameCreation frameCreation)
	{
		this.ctrl  = ctrl;
		this.frameCreation = frameCreation;

		this.setBackground(FrameCreation.COULEUR_FOND);
		
		/* ---------------------------------- */
		/* Création des composants      */
		/* ---------------------------------- */
		
		this.panelAccueil = new JPanel();
		this.panelAccueil.setLayout(new GridLayout(4, 1, 0, 20) );
		
		this.lblMenu = new JLabel ("Edition de Plateau", SwingConstants.CENTER);
		this.lblMenu.setFont      (FrameCreation.POLICE_TEXTE        );
		this.lblMenu.setForeground(FrameCreation.COULEUR_TITRE        );
		
		this.btnNouveau = new JButton("Nouveau");
		this.btnOuvrir  = new JButton("Ouvrir" );
		
		
		/* ---------------------------------- */
		/* Positionnement des composants   */
		/* ---------------------------------- */
		
		this.panelAccueil.add( this.lblMenu );
		JPanel panelVide = new JPanel();
		panelVide.setOpaque(false);
		this.panelAccueil.add( panelVide );
		this.panelAccueil.add( this.btnNouveau);
		this.panelAccueil.add( this.btnOuvrir );

		this.panelAccueil.setOpaque(false);
		
		
		this.add(this.panelAccueil);
		
		/* ------------------------------- */
		/* Activation des Composants    */
		/* ------------------------------- */
		
		this.btnNouveau.addActionListener(this);
		this.btnOuvrir.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == this.btnNouveau)
		{
			this.frameCreation.ouvrirPanelInit();
		}
		
		if (e.getSource() == this.btnOuvrir)
		{
			JFileChooser explorateur = new JFileChooser();
			explorateur.setDialogTitle("Ouvrir un plateau existant");

			explorateur.setCurrentDirectory(new File("."));

			int resultat = explorateur.showOpenDialog(this.frameCreation);

			if (resultat == JFileChooser.APPROVE_OPTION)
			{
				File fichierSelectionne = explorateur.getSelectedFile();

				this.frameCreation.ouvrirPanelEdition(fichierSelectionne);
			}
		}
	}
}