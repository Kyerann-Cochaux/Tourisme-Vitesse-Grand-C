package srcJeu.ihm;

import srcJeu.AppliJeu;

import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.io.File;

public class FrameJeu extends JFrame
{
	protected static final Font  POLICE_TEXTE  = new Font    ("Goldman", Font.BOLD, 25);

	protected static final Color COULEUR_TITRE = Color.decode("#f1c232");
	protected static final Color COULEUR_ZONE  = Color.decode("#f3f3f3");

	protected static final Color COULEUR_FOND_FONCE   = new Color (85, 64, 98);
	protected static final Color COULEUR_FOND_CLAIR  = new Color (70, 70, 70);
	protected static final Color COULEUR_FOND_PLATEAU = new Color (31, 31, 31);

	protected static final Color[] TAB_COUL_LIENS = { Color.decode("#a37343"), // Chlorophite
	                                                  Color.decode("#63b6e0"), // Felinoïd
	                                                  Color.decode("#ffd541"), // Azimae
	                                                  Color.decode("#56e4ae")  // Silikon
	                                                };
	
	protected static final int PANEL_JEU = 1;
	
	private AppliJeu ctrl;
	private JPanel   panelActuel;

	private String nomSauvegardeChargee;
	
	public FrameJeu(AppliJeu ctrl)
	{
		this.setTitle   ("Tourisme à Vitesse Grand C");
		this.setSize    (500, 350                    );
		this.setLocation(675, 400                    );
		
		/* ---------------------------------- */
		/*       Création des composants      */
		/* ---------------------------------- */
		
		this.ctrl        = ctrl;
		this.panelActuel = new PanelMenu(this.ctrl, this);
		
		/* ---------------------------------- */
		/*    Positionnement des composants   */
		/* ---------------------------------- */
		
		this.add(this.panelActuel);
		
		/* ---------------------------------- */
		/*      Activation des composants     */
		/* ---------------------------------- */
		
		this.setVisible              (true);
		//this.setResizable            (false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void ouvrirPanel(int numeroPanel)
	{
		this.remove(this.panelActuel);

		if (numeroPanel == FrameJeu.PANEL_JEU)
		{
			this.add             (new PanelJeu(ctrl, this) );
			this.setExtendedState(JFrame.MAXIMIZED_BOTH    );
		}
	}

	public String chargerFichier()
	{
		JFileChooser explorateur  = new JFileChooser();
		String retFichier         = "";
		this.nomSauvegardeChargee = "";
		
		explorateur.setDialogTitle     ("Ouvrir plateau..."          );
		explorateur.setCurrentDirectory(new File ("../sauvegardes/") );
		
		if (explorateur.showOpenDialog(this) == JFileChooser.APPROVE_OPTION)
		{
			this.nomSauvegardeChargee = explorateur
				.getSelectedFile()
				.getName        ()
				.replaceAll     (".data", "");
				
			retFichier = explorateur.getSelectedFile().getAbsolutePath();
		}
		return retFichier;

	}
}
