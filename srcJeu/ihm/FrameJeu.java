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
	protected static final Color COULEUR_FOND_CLAIR   = new Color (70, 70, 70);
	protected static final Color COULEUR_FOND_PLATEAU = new Color (31, 31, 31);

	protected static final Color[] TAB_COUL_LIENS = { Color.decode("#a37343"), // Chlorophite
	                                                  Color.decode("#63b6e0"), // Felinoïd
	                                                  Color.decode("#ffd541"), // Azimae
	                                                  Color.decode("#56e4ae")  // Silikon
	                                                };
	
	protected static final int PANEL_JEU  = 1;
	protected static final int PANEL_MENU = 2;
	
	private AppliJeu ctrl;
	private JPanel   panelActuel;

	private String nomSauvegardeChargee;

	private JMenuBar  menubDemo;
	private JMenu     menuDemo ;
	private JMenuItem menuiDemo;

	private JFileChooser explorateur;
	
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

		this.menubDemo = new JMenuBar ();
		this.menuDemo  = new JMenu    ();
		this.menuiDemo = new JMenuItem();

		this.explorateur = new JFileChooser(new File("../sauvegardes/"));
		
		/* ---------------------------------- */
		/*    Positionnement des composants   */
		/* ---------------------------------- */
		
		this.menuDemo .add(menuiDemo);
		this.menubDemo.add(menuDemo );

		this.menuiDemo.setAccelerator(KeyStroke .getKeyStroke(KeyEvent.VK_D, 
			InputEvent.CTRL_DOWN_MASK + InputEvent.ALT_DOWN_MASK + InputEvent.SHIFT_DOWN_MASK)); 

		this.setJMenuBar(    menubDemo   );	
		this.add        (this.panelActuel);
		/* ---------------------------------- */
		/*      Activation des composants     */
		/* ---------------------------------- */
		
		this.setVisible              (true                );
		//this.setResizable            (false               );
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		UIManager.put("FileChooser.openButtonText"         , "Ouvrir"      );
		UIManager.put("FileChooser.cancelButtonText"       , "Annuler"     );
		UIManager.put("FileChooser.lookInLabelText"        , "Chercher"    );
		UIManager.put("FileChooser.fileNameLabelText"      , "Fichier"     );
		UIManager.put("FileChooser.filesOfTypeLabelText"   , "Type"        );
		UIManager.put("FileChooser.openButtonToolTipText"  , "Ouvrir"      );
		UIManager.put("FileChooser.acceptAllFileFilterText", "type fichier");
		

		menuiDemo.addActionListener(this);

	}

	public void ouvrirPanel(int numeroPanel)
	{
		this.remove(this.panelActuel);

		if (numeroPanel == FrameJeu.PANEL_JEU)
		{
			this.panelActuel = new PanelJeu(ctrl, this);
			this.add             (this.panelActuel );
			this.setExtendedState(JFrame.MAXIMIZED_BOTH    );

			//this.setJMenuBar(null);
		}

		this.revalidate      ();


		if (numeroPanel == FrameJeu.PANEL_MENU)
		{
			this.panelActuel = new PanelMenu(ctrl, this);
			this.add             (this.panelActuel );
			this.setSize    (500, 350                    );
			this.setLocation(675, 400                    );

			//this.setJMenuBar(null);
		}
	}

	public String chargerFichier()
	{


		String retFichier         = "";
		this.nomSauvegardeChargee = "";
		
		explorateur.setDialogTitle     ("Ouvrir plateau..."          );
		//explorateur.setCurrentDirectory(new File ("../sauvegardes/") );
		
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
			this.explorateur = new JFileChooser(new File("../sauvegardes/demo/"));
			String       fichier     = "";

			if (this.explorateur.showOpenDialog(this) == JFileChooser.APPROVE_OPTION)

			{
				fichier = this.explorateur.getSelectedFile().getAbsolutePath();
				this.menubDemo = new MenuBarreDemo(this.ctrl, this); // On change la MenuBar vers une MenuBar similaire au mode Edition
				this.setJMenuBar(this.menubDemo);
				this.ctrl.chargerPlateau(fichier, true);
				this.ouvrirPanel        (FrameJeu.PANEL_JEU);
			}
			
			//System.out.println(this.nomSauvegardeChargee);
			

			//this.setTitle("COUCOU");
			//this.revalidate();
			//this.repaint();
		}
		
	}
}
