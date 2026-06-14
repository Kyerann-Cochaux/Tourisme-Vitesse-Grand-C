package srcJeu.ihm;

import srcJeu.AppliJeu;

import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.Locale;

public class FrameJeu extends JFrame implements ActionListener
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

	private JMenuBar  menub;
	private JMenu     menu ;
	private JMenuItem menui;
	
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

		this.menub = new JMenuBar ();
		this.menu  = new JMenu    ();
		this.menui = new JMenuItem();
		
		/* ---------------------------------- */
		/*    Positionnement des composants   */
		/* ---------------------------------- */
		
		this.menu .add(menui);
		this.menub.add(menu );
		
		this.add(this.panelActuel);
		
		/* ---------------------------------- */
		/*      Activation des composants     */
		/* ---------------------------------- */
		
		this.setVisible              (true                );
		//this.setResizable            (false               );
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		

		menui.addActionListener(this);

	}

	public void ouvrirPanel(int numeroPanel)
	{
		this.remove(this.panelActuel);

		if (numeroPanel == FrameJeu.PANEL_JEU)
		{
			this.add             (new PanelJeu(ctrl, this) );
			this.setExtendedState(JFrame.MAXIMIZED_BOTH    );

			this.menui.setAccelerator(KeyStroke .getKeyStroke(KeyEvent.VK_D, 
				                      InputEvent.CTRL_DOWN_MASK + InputEvent.ALT_DOWN_MASK + InputEvent.SHIFT_DOWN_MASK)); 
			this.setJMenuBar(menub);
		}
	}

	public String chargerFichier()
	{
		UIManager.put("FileChooser.openButtonText"         , "Ouvrir"      );
		UIManager.put("FileChooser.cancelButtonText"       , "Annuler"     );
		UIManager.put("FileChooser.lookInLabelText"        , "Chercher"    );
		UIManager.put("FileChooser.fileNameLabelText"      , "Fichier"     );
		UIManager.put("FileChooser.filesOfTypeLabelText"   , "Type"        );
		UIManager.put("FileChooser.openButtonToolTipText"  , "Ouvrir"      );
		UIManager.put("FileChooser.acceptAllFileFilterText", "type fichier");

		JFileChooser explorateur = new JFileChooser();

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

		this.nomSauvegardeChargee = retFichier;
		return retFichier;

	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		int res = JOptionPane.showConfirmDialog
						(this, "Voulez vous activer le mode démo ?",
						"Mode démo", JOptionPane.YES_NO_OPTION);

		if (res == JOptionPane.YES_OPTION) 
		{
			//this.ctrl.chargerPlateau(this.nomSauvegardeChargee, true);
			//this.ouvrirPanel        (FrameJeu.PANEL_JEU             );
			

			//this.setTitle("COUCOU");
			//this.revalidate();
			//this.repaint();
		}
		
	}
}
