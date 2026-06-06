package source.ihm;

import source.AppliCreation;

import javax.swing.*;
import java.awt.event.*;
import java.awt.GridLayout;


import java.io.File;



public class PanelCreation extends JPanel implements ActionListener
{
	// Multiplicités IHM

	private AppliCreation ctrl;
	private FrameCreation frameCreation;

	private JLabel lblMenu;
	
	private JButton btnNouveau;
	private JButton btnOuvrir ;

	public PanelCreation(AppliCreation ctrl, FrameCreation frameCreation)
	{

		JPanel panelAccueil;
		
		this.ctrl          = ctrl;
		this.frameCreation = frameCreation;

		this.setBackground(FrameCreation.COULEUR_FOND);
		
		/* ---------------------------------- */
		/*       Création des composants      */
		/* ---------------------------------- */
		
		panelAccueil = new JPanel();
		panelAccueil.setLayout(new GridLayout(4, 1, 0, 20) );
		
		this.lblMenu = new JLabel ("Edition de Plateau", SwingConstants.CENTER);
		this.lblMenu.setFont      (FrameCreation.POLICE_TEXTE                       );
		this.lblMenu.setForeground(FrameCreation.COULEUR_TITRE                      );
		
		this.btnNouveau = new JButton("Nouveau");
		this.btnOuvrir  = new JButton("Ouvrir" );
		
		/* ---------------------------------- */
		/*    Positionnement des composants   */
		/* ---------------------------------- */

		/* --- Positionnement panelAccueil -- */
		
		panelAccueil.add      ( this.lblMenu   );
		panelAccueil.add      ( new JLabel ()  );
		panelAccueil.add      ( this.btnNouveau);
		panelAccueil.add      ( this.btnOuvrir );
		panelAccueil.setOpaque(false  );

		/* --- Positionment PanelCreation --- */
		
		this.add(panelAccueil);
		
		/* ---------------------------------- */
		/*      Activation des composants     */
		/* ---------------------------------- */
		
		this.btnNouveau.addActionListener(this);
		this.btnOuvrir .addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == this.btnNouveau)
		{
			//this.frameCreation.ouvrirPanelInit();
			this.frameCreation.ouvrirPanel(FrameCreation.PANEL_INIT);
		}
		
		if (e.getSource() == this.btnOuvrir)
		{
			//this.frameCreation.ouvrirPanelEdition(fichierSelectionne);

			String fichier = this.chargerFichier();
			
			if (fichier != null && !fichier.equals("") )
			{

				this.ctrl.chargerPlateau(this.chargerFichier() );            // charge le plateau dans Métier
				this.frameCreation.ouvrirPanel(FrameCreation.PANEL_EDITION); // Affiche le plateau dans IHM
			}

			
		}
	}

	public String chargerFichier()
	{
		String fichiercharge = "";
		JFileChooser explorateur = new JFileChooser();

		explorateur.setDialogTitle     ("Ouvrir plateau..."            );
		explorateur.setCurrentDirectory(new File ("./metier/sauvegardes") );

		if (explorateur.showOpenDialog(this) == JFileChooser.APPROVE_OPTION)
			fichiercharge = explorateur.getSelectedFile().getPath();

		return fichiercharge;

	}
}
