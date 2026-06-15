package srcJeu.ihm;

import java.awt.event.*;
import javax.swing   .*;

import srcJeu.AppliJeu;


public class MenuBarreDemo extends JMenuBar implements ActionListener
{
	private AppliJeu ctrl;
	private FrameJeu frameMere;


	public MenuBarreDemo(AppliJeu ctrl,FrameJeu frameMere)
	{

		String[][] modeleBarre = { { "M",   "Fichier",          "F"                 },
		                           { "I"  , "Ouvrir" ,          "O", "CTRL+O"       },
		                           { "I"  , "Fermer",           "F", "CTRL+W"       },
		                           { "S"                                            },
		                           { "I"  , "Quitter",          "Q", "ALT+F4"       } };

		this.ctrl      = ctrl;
		this.frameMere = frameMere;

		/* ---------------------------------- */
		/*      Composition de la MenuBar     */
		/* ---------------------------------- */

		JMenu     menu     = null;
		JMenuItem menuItem = null;

		for (int cptLig = 0; cptLig < modeleBarre.length; cptLig++) 
		{
			for (int cptCol = 0; cptCol < modeleBarre[cptLig].length; cptCol++)
			{
				
				if (modeleBarre[cptLig][cptCol].equals("M") )
				{
				
					menu = new JMenu( modeleBarre[cptLig][cptCol +1] );
					menu.setMnemonic( modeleBarre[cptLig][cptCol +2].charAt(0) );

					this.add(menu);
				}

				if (modeleBarre[cptLig][cptCol].equals("I") )
				{
					menuItem = new JMenuItem(modeleBarre[cptLig][cptCol +1] );
					menuItem.setMnemonic( modeleBarre[cptLig][cptCol +2].charAt(0) );

					menuItem.addActionListener(this);

					if (modeleBarre[cptLig][modeleBarre[cptLig].length -1].contains("+") )
					{
						int    iSep = 0;
						char sAccelerator;
						String sInput = "";
						String sLigne = modeleBarre[cptLig][modeleBarre[cptLig].length -1];

						for (int cpt = 0; cpt < sLigne.length(); cpt++) 
						{
							if (sLigne.charAt(cpt) == '+') 
								iSep = cpt;
					
						}

						sInput       = sLigne.substring(0,iSep);
						sAccelerator = sLigne.charAt(iSep +1);;

						String kStroke = "";

						if (sInput.contains("CTRL" ) ) kStroke += "control ";
						if (sInput.contains("SHIFT") ) kStroke += "shift "  ;
						if (sInput.contains("ALT"  ) ) kStroke += "alt "    ;

						menuItem.setAccelerator(KeyStroke.getKeyStroke(kStroke += sAccelerator) );
					}
	
					menu.add( menuItem);
				}

				if (modeleBarre[cptLig][0].equals("S") ) menu.addSeparator();
			}
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if (e.getSource() instanceof JMenuItem)
		{
			JMenuItem menui = ( (JMenuItem) e.getSource() );
			
			
			switch (menui.getText())
			{
				
				case "Fermer" ->
				{
					this.frameMere.ouvrirPanel(FrameJeu.PANEL_MENU);
				}
				
				case "Quitter" ->
				{
					System.exit(JFrame.EXIT_ON_CLOSE);
				}
				
				case "Ouvrir" ->
				{
					String cheminFichier = this.frameMere.chargerFichier();
					
					if (!cheminFichier.isEmpty())
					{
						// Appelez ici la méthode qui va lire le fichier et actualiser le plateau
						this.ctrl.chargerPlateau(cheminFichier,true);
						this.frameMere.ouvrirPanel(FrameJeu.PANEL_JEU);
					}
				}
			}
			
		}
	}
	
}