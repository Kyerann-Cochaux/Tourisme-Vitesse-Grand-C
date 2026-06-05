package source.ihm;

import source.AppliCreation;

import java.io.File;

import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;

public class FrameCreation extends JFrame
{
	protected static final Font  POLICE_TEXTE        = new Font ("Goldman", Font.BOLD, 25);

	protected static final Color COULEUR_TITRE       = Color.decode("#f1c232");
	protected static final Color COULEUR_ZONE        = Color.decode("#f3f3f3");
	protected static final Color COULEUR_FOND        = new Color (37, 37, 37);
	protected static final Color COULEUR_FOND_CLAIR  = new Color (70, 70, 70);
	
	private AppliCreation ctrl;
	private JPanel        panelActuelle;
	
	public FrameCreation(AppliCreation ctrl) 
	{
		// Variable locales pour placer la frame avec le premier panel au centre de l'écran
		// Pourrait fonctionner sans 4 variables locales, mais pour plus de lisibilité, mieux vaut décomposer l'appel de chaque méthode
		int largeurEcran, hauteurEcran;
		int largeurFrame, hauteurFrame;

		this.setTitle   ("Tourisme à Vitesse Grand C");
		this.setSize    (300, 250            );
		//this.setLocation(800    , 450                 );


		/* À tester sous Linux pour voir si la frame se place au centre de l'écran*/

		//                                 Permet de récupérer la largeur de l'écran
		largeurEcran = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth ();

		//                                 Permet de récupérer la hauteur de l'écran
		hauteurEcran = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();

		// La taille étant définie au dessus (cf ligne 32) on peut réutiliser ces valeurs pour le positionnement
		largeurFrame = this.getWidth ();
		hauteurFrame = this.getHeight();

		//                centre écran largeur   taille frame largeur   centre écran hauteur    taille frame hauteur
		this.setLocation( (largeurEcran / 2)    - (largeurFrame / 2),    (hauteurEcran /2)   -   ( hauteurFrame / 2) );
		
		/* ---------------------------------- */
		/*       Création des composants      */
		/* ---------------------------------- */
		
		this.ctrl          = ctrl;
		this.panelActuelle = new PanelCreation(this.ctrl, this);
		
		/* ---------------------------------- */
		/*    Positionnement des composants   */
		/* ---------------------------------- */
		
		this.add(this.panelActuelle);
		
		/* ---------------------------------- */
		/*      Activation des composants     */
		/* ---------------------------------- */
		
		this.setVisible              (true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	public void ouvrirPanelCreation()
	{
		this.remove(this.panelActuelle);
		this.panelActuelle = new PanelCreation(this.ctrl, this);
		this.add(this.panelActuelle);
		this.setSize(300, 250);
		this.setLocation(800, 450);
		this.revalidate();
	}
	
	public void ouvrirPanelInit()
	{
		this.remove(this.panelActuelle);
		this.panelActuelle = new PanelInit(this.ctrl, this);
		this.add(this.panelActuelle);
		this.setSize(800, 1080);
		this.setLocation(550, 450);
		this.revalidate();
	}
	
	public void ouvrirPanelEdition(int nbLignes, int nbColonnes, int nbFormes, int nbEspeces)
	{
		this.remove(this.panelActuelle);
		this.ctrl.initialiserPlateau(nbLignes, nbColonnes, nbFormes, nbEspeces);
		this.panelActuelle = new PanelEdition( this.ctrl, this );
		this.add(this.panelActuelle);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.revalidate();
	}
	
	public void ouvrirPanelEdition( File fichier )
	{
		this.remove(this.panelActuelle);
		this.panelActuelle = new PanelEdition(this.ctrl, this, fichier);
		this.add(this.panelActuelle);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.revalidate();
	}
}
