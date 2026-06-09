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
	protected static final Color COULEUR_FOND_FONCE  = new Color (85, 64, 98);
	protected static final Color COULEUR_FOND_CLAIR  = new Color (70, 70, 70);

	protected static final int PANEL_INIT     = 1;
	protected static final int PANEL_EDITION  = 2;
	protected static final int PANEL_CREATION = 3;
	
	private AppliCreation    ctrl;
	private JPanel           panelActuelle;
	private MenuBarreEdition menuBarreEdition;

	// À BOUGER DANS LE MÉTIER DÈS QUE POSSIBLE, IDEM POUR LA MÉTHODE GET
	private String nomSauvegardeChargee;

	// attributs utilisés pour placer la frame le plus au centre de l'écran en fonction de sa taille

	private int posFrameX, posFrameY;

	// Variable locales pour placer la frame avec le premier panel au centre de l'écran
	// Pourrait fonctionner sans 4 variables locales, mais pour plus de lisibilité, mieux vaut décomposer l'appel de chaque méthode

	private int largeurEcran, hauteurEcran;
	private int largeurFrame, hauteurFrame;
	
	public FrameCreation(AppliCreation ctrl) 
	{

		this.setTitle   ("Tourisme à Vitesse Grand C");
		this.setSize    (350, 250            );

		/* À tester sous Linux pour voir si la frame se place au centre de l'écran*/

		//                                 Permet de récupérer la largeur de l'écran
		this.largeurEcran = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth ();

		//                                 Permet de récupérer la hauteur de l'écran
		this.hauteurEcran = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();

		// La taille étant définie au dessus, on peut réutiliser ces valeurs pour le positionnement
		this.largeurFrame = this.getWidth ();
		this.hauteurFrame = this.getHeight();

 		//                 centre écran largeur      taille frame largeur
		this.posFrameX = (this.largeurEcran / 2) - (this.largeurFrame / 2);

		//                 centre écran hauteur      taille frame hauteur
		this.posFrameY = (this.hauteurEcran / 2) - (this.hauteurFrame / 2);

		this.setLocation( this.posFrameX, this.posFrameY );
		
		/* ---------------------------------- */
		/*       Création des composants      */
		/* ---------------------------------- */
		
		this.ctrl             = ctrl;
		this.panelActuelle    = new PanelCreation   (this.ctrl, this);
		this.menuBarreEdition = new MenuBarreEdition(this.ctrl, this);
		
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
	

	// Place sur la frame le panel correspondant à l'entier en paramètre
	// Permet de rendre le code plus modulaire, car plusieurs actions se répétaient dans les 4 méthodes ci dessus

	public void ouvrirPanel(int numeroPanel)
	{
		this.remove(this.panelActuelle);

		switch (numeroPanel) 
		{
			case FrameCreation.PANEL_INIT -> 
			{
				this.panelActuelle = new PanelInit(this.ctrl, this                  );
				this.setSize                      (800, this.hauteurEcran -50 );
				this.setExtendedState             (JFrame.NORMAL                    );
				this.setJMenuBar                  (null);
				this.nomSauvegardeChargee = "sauvegarde";
			}

			case FrameCreation.PANEL_EDITION ->
			{
				this.panelActuelle = new PanelEdition(this.ctrl, this      );
				this.setExtendedState                (JFrame.MAXIMIZED_BOTH);
				this.setJMenuBar                     (this.menuBarreEdition);
			}

			case FrameCreation.PANEL_CREATION ->
			{
				this.panelActuelle = new PanelCreation(this.ctrl, this       );
				this.setSize                          (350, 250);
				this.setExtendedState                 (JFrame.NORMAL         );
				this.setJMenuBar                      (null);

			}	
		}

		this.add             ( this.panelActuelle           );
		this.majPositionFrame(numeroPanel                   );
		this.setLocation     (this.posFrameX, this.posFrameY);
		this.revalidate      (                              );

	}

	private void majPositionFrame(int numeroPanel)
	{
		this.largeurFrame = this.getWidth ();
		this.hauteurFrame = this.getHeight();

		this.posFrameX = (this.largeurEcran / 2) - (this.largeurFrame / 2);

		if (numeroPanel == FrameCreation.PANEL_INIT) this.posFrameY = 0;
		else                                         this.posFrameY = (this.hauteurEcran / 2) - (this.hauteurFrame / 2);

	}

	public String getNomSauvegarde()                  {return this.nomSauvegardeChargee;}
	public void setNomSauvegarde  (String nomFichier) { this.nomSauvegardeChargee = nomFichier;}

	public String chargerFichier()
	{
		JFileChooser explorateur = new JFileChooser();
		String retFichier = "";
		this.nomSauvegardeChargee = "";
		
		explorateur.setDialogTitle     ("Ouvrir plateau..."            );
		explorateur.setCurrentDirectory(new File ("../source/metier/sauvegardes/") );
		
		if (explorateur.showOpenDialog(this) == JFileChooser.APPROVE_OPTION)
		{
			this.nomSauvegardeChargee = explorateur
				.getSelectedFile()
				.getName()
				.replaceAll(".data", "");
				
			retFichier = explorateur
				.getSelectedFile()
				.getAbsolutePath();
		}
		
		return retFichier;

	}
	
}
