package srcEdition.ihm;

import java.awt.event.*;
import javax.swing   .*;

import srcEdition.AppliCreation;


public class MenuBarreEdition extends JMenuBar implements ActionListener
{
	private AppliCreation ctrl;
	private FrameCreation frameMere;


	public MenuBarreEdition(AppliCreation ctrl,FrameCreation frameMere)
	{

		String[][] modeleBarre = { { "M", "Fichier",          "F"                 },
		                         { "I"  , "Nouveau",          "N", "CTRL+N"       },
		                         { "I"  , "Ouvrir" ,          "O", "CTRL+O"       },
		                         { "I"  , "Fermer",           "F", "CTRL+W"       },
		                         { "S"                                            },
		                         { "I"  , "Enregistrer",      "E", "CTRL+S"       },
		                         { "I"  , "Enregistrer Copie", "C", "CTRL+SHIFT+S"},
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

			if ( menui.getText().equals("Nouveau" ) ) 
			{
				this.ctrl.sauvegarderPlateau( this.frameMere.getNomSauvegarde() );
				
				this.frameMere.ouvrirPanel(FrameCreation.PANEL_INIT);
			}
			else
			{
				
				if (menui.getText().equals("Fermer" ) )  this.frameMere.ouvrirPanel(FrameCreation.PANEL_CREATION);
				if (menui.getText().equals("Quitter" ) ) System.exit(JFrame.EXIT_ON_CLOSE);
				if (menui.getText().equals("Ouvrir"  ) )
				{
					
					String cheminFichier = this.frameMere.chargerFichier();
					
					if (!cheminFichier.isEmpty())
					{
						// Appelez ici la méthode qui va lire le fichier et actualiser le plateau
						this.ctrl.chargerPlateau(cheminFichier);
						this.frameMere.ouvrirPanel(FrameCreation.PANEL_EDITION);
					}
				}
				
				if (menui.getText().equals("Enregistrer"      ) && !(this.ctrl.getNbTypeEspeces () == this.ctrl.getNbEspecesPosees ()))
				{
					JOptionPane.showMessageDialog
					(this, "Certaines éspeces ne sont pas placer sur le plateau",
					"Erreur de saisie", JOptionPane.ERROR_MESSAGE);
				}
				else 
				{
					this.ctrl.sauvegarderPlateau     (this.frameMere.getNomSauvegarde() );
				}
				
				if (menui.getText().equals("Enregistrer Copie") && !(this.ctrl.getNbTypeEspeces () == this.ctrl.getNbEspecesPosees ()))
				{
					JOptionPane.showMessageDialog
					(this, "Certaines éspeces ne sont pas placer sur le plateau",
					"Erreur de saisie", JOptionPane.ERROR_MESSAGE);
				}
				else 
				{
					this.ctrl.sauvegarderCopiePlateau( this.frameMere.getNomSauvegarde() );
				}
			}
		}
	}
	
}