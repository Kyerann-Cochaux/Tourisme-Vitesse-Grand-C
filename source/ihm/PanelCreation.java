package source.ihm;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import source.Controleur ;

public class PanelCreation extends JPanel implements ActionListener
{
	private Controleur    ctrl;
	private FrameCreation frame;
	
	private JPanel panelAccueil;
	
	private JLabel lblMenu  ;
	
	private JButton btnNouveau;
	private JButton btnOuvrir ;
	
	
	
	public PanelCreation(Controleur ctrl, FrameCreation frame)
	{
		this.ctrl  = ctrl;
		this.frame = frame;
		
		/* ---------------------------------- */
		/*       Création des composants      */
		/* ---------------------------------- */
		
		this.panelAccueil = new JPanel();
		this.panelAccueil.setLayout(new GridLayout(4, 1, 0, 20) );
		
		this.lblMenu = new JLabel ("Edition de Plateau", SwingConstants.CENTER);
		this.lblMenu.setFont      (FrameCreation.POLICE_TEXTE        );
		this.lblMenu.setForeground(FrameCreation.COULEUR_TITRE        );
		
		this.btnNouveau = new JButton("Nouveau");
		this.btnOuvrir  = new JButton("Ouvrir" );
		
		
		/* ---------------------------------- */
		/*    Positionnement des composants   */
		/* ---------------------------------- */
		
		this.panelAccueil.add( this.lblMenu );
		this.panelAccueil.add( new JPanel() );
		this.panelAccueil.add( this.btnNouveau);
		this.panelAccueil.add( this.btnOuvrir );
		
		
		this.add(this.panelAccueil);
		
		/* ------------------------------- */
		/*    Activation des Composants    */
		/* ------------------------------- */
		
		this.btnNouveau.addActionListener(this);
		this.btnOuvrir.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == this.btnNouveau)
		{
			this.frame.ouvrirPanelInit();
		}
		
		if (e.getSource() == this.btnOuvrir)
		{
			
		}
	}
}
