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

	// attributs utilisés pour placer la frame le plus au centre de l'écran en fonction de sa taille

	private int posFrameX, posFrameY;

	// Variable locales pour placer la frame avec le premier panel au centre de l'écran
	// Pourrait fonctionner sans 4 variables locales, mais pour plus de lisibilité, mieux vaut décomposer l'appel de chaque méthode

	private int largeurEcran, hauteurEcran;
	private int largeurFrame, hauteurFrame;
	
	public FrameCreation(AppliCreation ctrl) 
	{

		this.setTitle   ("Tourisme à Vitesse Grand C");
		this.setSize    (300, 250            );

		/* À tester sous Linux pour voir si la frame se place au centre de l'écran*/

		//                                 Permet de récupérer la largeur de l'écran
		this.largeurEcran = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth ();

		//                                 Permet de récupérer la hauteur de l'écran
		this.hauteurEcran = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();

		// La taille étant définie au dessus (cf ligne 32) on peut réutiliser ces valeurs pour le positionnement
		this.largeurFrame = this.getWidth ();
		this.hauteurFrame = this.getHeight();

 		//             centre écran largeur   taille frame largeur
		this.posFrameX = (this.largeurEcran / 2)    - (this.largeurFrame / 2);

		//             centre écran hauteur   taille frame hauteur
		this.posFrameY = (this.hauteurEcran / 2)    - (this.hauteurFrame / 2);

		this.setLocation( this.posFrameX, this.posFrameY );
		
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

		this.setSize(800, this.hauteurEcran - 50);

		this.largeurFrame = this.getWidth ();
		this.posFrameX = (this.largeurEcran / 2)    - (this.largeurFrame / 2);
		this.setLocation(posFrameX, 0);
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


	// Place sur la frame le panel correspondant à l'entier
	// Permet de rendre le code plus modulaire, car plusieurs actions se répétaient dans les 4 méthodes ci dessus
	
	public void ouvrirPanel(int numPanel)
	{
		this.removeAll();

		switch (numPanel) 
		{
			// Ajout du panelInit
			case 1 -> 
			{
				this.add    (new PanelInit(this.ctrl, this)    );
				this.setSize(800, this.hauteurEcran - 50);

			}

			// Ajout du panelEdition
			case 2 ->
			{

			}

			// retour sur panelCreation
			case 3 ->
			{
				this.add     (new PanelCreation(this.ctrl, this) );
				this.setSize (300, 250             );
			}

			
		}

		this.majPositionFrame();
		this.setLocation(this.posFrameX, this.posFrameY);


	}

	private void majPositionFrame()
	{
		this.largeurFrame = this.getWidth ();
		this.hauteurFrame = this.getHeight();

		this.posFrameX = (this.largeurEcran / 2) - (this.largeurFrame / 2);
		this.posFrameY = (this.hauteurEcran / 2) - (this.hauteurFrame / 2);


	}
}
