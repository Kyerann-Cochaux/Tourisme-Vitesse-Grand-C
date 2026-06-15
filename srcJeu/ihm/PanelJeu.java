package srcJeu.ihm;

import srcJeu.AppliJeu;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.Point;
import java.awt.Graphics;
import java.awt.Graphics2D;


public class PanelJeu extends JPanel
{

	private AppliJeu ctrl;
	private FrameJeu frameJeu;
	
	private JLabel[] ensLblCartes;
	private JLabel[] ensLblEspeces; 

	private PanelPlateau panelPlateau;
	private JScrollPane  spPlateau;
	
	private JLabel iconeCroisiereActuelle;
	
	private JLabel lblTexteEspece;
	private JLabel lblTexteScore;
	private JLabel lblTextePioche;
	private JLabel lblTexteStandards;
	private JLabel lblTextePremium;
	private JLabel lblActionPioche;


	private JPanel panelScoreEspeces;
	
	//Panel Carte
	private JPanel panelCartes;

	private JPanel panelCartesStandards;
	private JPanel panelCartesPremium;

	private String[] tabStandardsPosee;
	private String[] tabPremiumPosee;

	private JPanel panelPioche;


	public PanelJeu(AppliJeu ctrl, FrameJeu frameJeu)
	{
		/* -------- Panels principaux ------- */

		JPanel panelCentre;
		JPanel panelScore;

		//sous panels du panelScore
		JPanel panelScoreLabels;


		// RGB utilisé pour la couleur du texte de l'espèce
		int r, g, b;


		this.ctrl     = ctrl;
		this.frameJeu = frameJeu;

		this.setLayout(new BorderLayout() );

		/* ---------------------------------- */
		/*       Création des composants      */
		/* ---------------------------------- */
		
		panelScore             = new JPanel(new GridLayout(4,1) );
		panelScoreLabels       = new JPanel(new GridLayout(2,1) );
		this.panelScoreEspeces = new JPanel();

		this.panelPlateau = new PanelPlateau(ctrl);
		panelCentre       = new JPanel(new GridBagLayout() );
		

		this.ensLblCartes = new JLabel[this.ctrl.getNbTypePlanetes() * 2 +2];

		GridLayout gl1 = new GridLayout(6,1,0,8);

		if (this.ctrl.getNbTypeEspeces() >= 3) gl1.setRows(gl1.getRows() + this.ctrl.getNbTypeEspeces() /2);

		this.panelScoreEspeces.setLayout(gl1);
	
		this.panelCartes = new JPanel(new GridLayout(4,1) );
		this.panelPioche      = new JPanel();

		this.spPlateau = new JScrollPane(panelCentre);

		for (int cpt = 0; cpt < this.ensLblCartes.length; cpt++) 
			this.ensLblCartes[cpt] = new JLabel(new ImageIcon("../images/Cartes/Carte-Dos.png") );

		this.tabStandardsPosee = new String[this.ensLblCartes.length /2];
		this.tabPremiumPosee   = new String[this.ensLblCartes.length /2];

		for (int cpt = 0; cpt < this.tabStandardsPosee.length; cpt++) 
		{
			this.tabStandardsPosee[cpt] = null;
			this.tabPremiumPosee  [cpt] = null;
		}

		this.panelCartesPremium   = this.creerPanelCarte(true );
		this.panelCartesStandards = this.creerPanelCarte(false);

		this.panelCartesPremium.setPreferredSize(this.panelCartes.getPreferredSize() );


		r = FrameJeu.TAB_COUL_LIENS[ this.ctrl.getEnsEspece().indexOf(this.ctrl.getEspCroisiereCrt() ) ].getRed  ();
		g = FrameJeu.TAB_COUL_LIENS[ this.ctrl.getEnsEspece().indexOf(this.ctrl.getEspCroisiereCrt() ) ].getGreen();
		b = FrameJeu.TAB_COUL_LIENS[ this.ctrl.getEnsEspece().indexOf(this.ctrl.getEspCroisiereCrt() ) ].getBlue ();
		
		this.lblTexteEspece = new JLabel
		(

			"<html>"+
				"<body> "+
					"<h1 style='text-align : center;'>"                                         + 
						"Croisière des <br> <p style='text-align : center; color:"              +
						" rgb("+ r + ","  +g + "," + b +");'>" + this.ctrl.getEspCroisiereCrt() + 
					"</p></h1>"+
				" </body> "
			+"</html>", SwingConstants.CENTER

		);
		

		this.lblTexteScore     = new JLabel("Score des croisières : ", SwingConstants.CENTER);
		this.lblTextePremium   = new JLabel("    Cartes Premium : "      );
		this.lblTexteStandards = new JLabel("    Cartes Standards : "    );
		this.lblTextePioche    = new JLabel
		(
			"<html>" + 
				"<body> " +
					"<h1 style='text-align : center;'>" + 
						"Destination <br> Actuelle : "  + 
					"</h1>"+
				"</body>"+
			"</html>", SwingConstants.CENTER  
		);

		this.lblActionPioche = new JLabel(new ImageIcon("../images/Cartes/Carte-" +  this.ctrl.getSommet() +".png") );
		
		/* ---------------------------------- */
		/*    Configuration des composants    */
		/* ---------------------------------- */

		panelScore            .setBackground(FrameJeu.COULEUR_FOND_CLAIR);
		this.panelScoreEspeces.setBackground(FrameJeu.COULEUR_FOND_CLAIR);
		panelScoreLabels      .setBackground(FrameJeu.COULEUR_FOND_CLAIR);

		this.panelPioche.setOpaque(false);

		panelCentre.setBackground(FrameJeu.COULEUR_FOND_PLATEAU);

		panelCartes.setBackground(FrameJeu.COULEUR_FOND_CLAIR);

		this.spPlateau.setBackground               (FrameJeu.COULEUR_FOND_FONCE               );
		this.spPlateau.setVerticalScrollBarPolicy  (JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED  );
		this.spPlateau.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		this.lblTexteEspece.setFont      (FrameJeu.POLICE_TEXTE);
		this.lblTexteEspece.setForeground(FrameJeu.COULEUR_ZONE);
		this.lblTexteEspece.setOpaque    (false                );

		this.lblTexteScore.setForeground(FrameJeu.COULEUR_ZONE              );
		this.lblTexteScore.setOpaque    (false                              );
		this.lblTexteScore.setFont      (new Font("Goldman", Font.BOLD, 21) );

		this.lblTexteStandards.setForeground(FrameJeu.COULEUR_ZONE              );
		this.lblTexteStandards.setOpaque    (false                              );
		this.lblTexteStandards.setFont      (new Font("Goldman", Font.BOLD, 19) );

		this.lblTextePremium.setForeground(FrameJeu.COULEUR_ZONE              );
		this.lblTextePremium.setOpaque    (false                              );
		this.lblTextePremium.setFont      (new Font("Goldman", Font.BOLD, 19) );

		this.lblTextePioche.setFont      (FrameJeu.POLICE_TEXTE);
		this.lblTextePioche.setForeground(FrameJeu.COULEUR_ZONE);
		this.lblTextePioche.setOpaque    (false                );

		/* ---------------------------------- */
		/*    Positionnement des composants   */
		/* ---------------------------------- */

		/* -------- Panels principaux ------- */

		panelScoreLabels.add(this.lblTexteEspece);
		panelScoreLabels.add( this.iconeCroisiereActuelle = 
			new JLabel(new ImageIcon("../images/Tuiles/XL-Espece-" + this.ctrl.getEspCroisiereCrt()  + ".png")  ) );
		
		this.majScoreEspece();

		this.panelPioche.add(this.lblActionPioche);
		

		panelScore.add(panelScoreLabels      );
		panelScore.add(this.panelScoreEspeces);
		panelScore.add(this.lblTextePioche   );
		panelScore.add( this.panelPioche );
		
		panelCentre.add( this.panelPlateau, new GridBagConstraints() );

		this.panelCartes.add( this.lblTextePremium    );
		this.panelCartes.add(this.panelCartesPremium  );
		this.panelCartes.add(this.lblTexteStandards   );
		this.panelCartes.add(this.panelCartesStandards);

		this.add(panelScore    , BorderLayout.WEST  );
		this.add(this.spPlateau, BorderLayout.CENTER);
		this.add(panelCartes   , BorderLayout.EAST  );

		/* ---------------------------------- */
		/*      Activation des composants     */
		/* ---------------------------------- */

		// Activation du Panel Plateau
		this.panelPlateau   .addMouseListener      (this.gererClic() );
		this.lblActionPioche.addMouseListener      (this.gererClic() );
		this.lblActionPioche.addMouseMotionListener(this.gererClic() );
	}

	private int getNbCartesDos(String[] tabCartesPosees)
	{
		int nbCarteDos = 0;

		for (int cpt = 0; cpt < tabCartesPosees.length; cpt++) 
			if (tabCartesPosees[cpt] == null) 
				
				nbCarteDos++;
		
		return nbCarteDos;
	}

	private int getNbCartesFace(String[] tabCartesPosees)
	{
		int nbCarteFace = 0;

		for (int cpt = 0; cpt < tabCartesPosees.length; cpt++) 
			if (tabCartesPosees[cpt] != null) 
				
				nbCarteFace++;
		
		return nbCarteFace;

	}

	private JPanel creerPanelCarte(boolean premium)
	{
		JPanel panel1, panel2;
		JPanel panelPrc;

		panelPrc = new JPanel(new GridLayout(2,1) );
		panel1   = new JPanel();
		panel2   = new JPanel();

		panelPrc.setOpaque(false);
		panel1  .setOpaque(false);
		panel2  .setOpaque(false);

		for (int cpt = 0; cpt < this.tabPremiumPosee.length; cpt++) 
		{
			String fic = "../images/Cartes/Carte-";

			if (premium)
			{
				if (this.tabPremiumPosee[cpt] == null) fic += "Dos";
				else                                   fic += this.tabPremiumPosee[cpt];

			}
			
			else
			{
				if (this.tabStandardsPosee[cpt] == null) fic += "Dos";
				else                                     fic += this.tabStandardsPosee[cpt];
			}
			
			fic += ".png";
			
			if (cpt < 3) panel1.add(new JLabel(new ImageIcon(fic) ) );
			else         panel2.add(new JLabel(new ImageIcon(fic) ) );
		}

		panelPrc.add(panel1);
		panelPrc.add(panel2);

		return panelPrc;
	}
	
	/*-----------------------------------------------------*/
	/*    Gestion de l'ajout des Voyages sur le Plateau    */
	/*-----------------------------------------------------*/
	
	private MouseAdapter gererClic()
	{
		return new MouseAdapter()
		{
			
			public void mousePressed(MouseEvent e)
			{
				if (e.getSource() == PanelJeu.this.panelPlateau)
				{
					// Position Cliqué traduit en Colonne & Ligne du Plateau
					int posColClk = (int) ( e.getX() / PanelJeu.this.panelPlateau.getTailleCase() ) ;
					int posLigClk = (int) ( e.getY() / PanelJeu.this.panelPlateau.getTailleCase() ) ;
					
					// Gestion de la selection d'extremite
					if ( e.getButton() == MouseEvent.BUTTON1                  &&
					     PanelJeu.this.ctrl.estExtremite(posColClk,posLigClk) &&
					     PanelJeu.this.ctrl.getSommet() != null
					   )
					{
						PanelJeu.this.selectionnerExtremite( posColClk, posLigClk );
					}

					// Gestion de la création de Voyage
					if ( e.getButton() == MouseEvent.BUTTON1                       && 
					      PanelJeu.this.panelPlateau.getPosExtremiteSlct() != null &&
					     !PanelJeu.this.ctrl.estExtremite(posColClk,posLigClk)
					   )
					{
						PanelJeu.this.effectuerVoyage( posColClk, posLigClk );
					}
				}
				
				// Gestion de l'affichage des Cartes Destionnations
				if (e.getSource() == PanelJeu.this.lblActionPioche)
				{
					if ( PanelJeu.this.ctrl.getNumManche   () <= PanelJeu.this.ctrl.getNbTypeEspeces() &&
					     PanelJeu.this.ctrl.getTaillePioche() > 0                                        )
					{
						PanelJeu.this.majImages();
					}
				}
			}

			@Override
			public void mouseEntered(MouseEvent e)
			{
				Graphics2D g2 = (Graphics2D) PanelJeu.this.getGraphics();

				if (e.getSource() == PanelJeu.this.lblActionPioche)
				{
					int x, y, w, h;

					x = PanelJeu.this.lblActionPioche.getX     ();
					w = PanelJeu.this.lblActionPioche.getWidth ();
					h = PanelJeu.this.lblActionPioche.getHeight();

					JLabel lblPioche;

					for (Component comp : PanelJeu.this.panelPioche.getComponents() ) 
					{
					//	System.out.println( "(" + ((JLabel)comp).ge  +")");	
					} 

					if (!PanelJeu.this.frameJeu.isResizable() ) y = 675;
					else                                        y = 645;
					

					//g2.setStroke(new BasicStroke(4) );
					//g2.setColor(Color.decode("#eff31d"));
					///g2.drawRect(x -20,y,w + 40,h + 40);	

					PanelJeu.this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR) );
				}
				

			}
			
			@Override
			public void mouseExited(MouseEvent e)
			{
				Graphics2D g2 = (Graphics2D) PanelJeu.this.getGraphics();

				if (e.getSource() == PanelJeu.this.lblActionPioche)
				{
					//int x = PanelJeu.this.lblActionPioche.getX     ();
					//int w = PanelJeu.this.lblActionPioche.getWidth ();
					//int h = PanelJeu.this.lblActionPioche.getHeight();
					//int y;

					//if (!PanelJeu.this.frameJeu.isResizable() ) y = 685;
					//else                                       y = 645;

					//g2.setStroke(new BasicStroke (4)         );
					//g2.setColor (FrameJeu.COULEUR_FOND_CLAIR );
					//g2.drawRect (x -20,y,w + 40,h + 40     );
					
					PanelJeu.this.setCursor(Cursor.getDefaultCursor() );

				}
		
			}
		};
	}


	/**
	 * Maj Images
	 * 
	 * Méthode de mise à jour des éléments graphiques du l'interface.
	 * Cette méthode appelle majDefausse et majScore avant de mettre à jours les éléments 
	 * dont l'état dépend des changements fait par les méthodes cité ci-dessus.
	 * 
	 */
	private void majImages()
	{
		int r, g, b;
		
		this.majDefausse   ();
		this.majScoreEspece();
		
		if (this.getNbCartesFace(tabPremiumPosee) == tabPremiumPosee.length)
		{
			for (int cpt = 0; cpt < this.tabPremiumPosee.length; cpt++) 
			{
				this.tabPremiumPosee  [cpt] = null;
				this.tabStandardsPosee[cpt] = null;	
			}

		}
		
		this.panelCartesPremium   = this.creerPanelCarte(true );
		this.panelCartesStandards = this.creerPanelCarte(false);

		this.panelCartes.remove    (                   1                  );
		this.panelCartes.add       (PanelJeu.this.panelCartesPremium  , 1 );
		this.panelCartes.remove    (                   3                  );
		this.panelCartes.add       (PanelJeu.this.panelCartesStandards, 3 );
		this.panelCartes.revalidate(                                      );
		
		this.panelScoreEspeces.revalidate();
		
		this.lblActionPioche       .setIcon(new ImageIcon("../images/Cartes/Carte-"     + this.ctrl.getSommet         () + ".png") );
		this.iconeCroisiereActuelle.setIcon(new ImageIcon("../images/Tuiles/XL-Espece-" + this.ctrl.getEspCroisiereCrt() + ".png") );
		
		// Affichage lors de la fin du Jeu
		if ( this.ctrl.getSommet    () == null ||
		      this.ctrl.getNumManche() == this.ctrl.getNbTypeEspeces() && this.ctrl.estMancheFinie() )
		{
			this.lblActionPioche.setIcon(null);
			this.lblActionPioche.setText
			(
				"<html>"+
					"<body> "+
						"<h1 style='text-align : center; color : #ffffff'>"+
							"Partie Terminé !"
						+"</h1>"
					+" </body> "
				+"</html>"
			);

			JOptionPane.showMessageDialog (
			    this,  
			    "Fin de la partie, votre score est de " + this.ctrl.getScoreTotal(),
			    "Fin de partie", JOptionPane.INFORMATION_MESSAGE);
				
			this.frameJeu.ouvrirPanel(FrameJeu.PANEL_MENU);
		}

		r = FrameJeu.TAB_COUL_LIENS[ this.ctrl.getEnsEspece().indexOf(this.ctrl.getEspCroisiereCrt() ) ].getRed  ();
		g = FrameJeu.TAB_COUL_LIENS[ this.ctrl.getEnsEspece().indexOf(this.ctrl.getEspCroisiereCrt() ) ].getGreen();
		b = FrameJeu.TAB_COUL_LIENS[ this.ctrl.getEnsEspece().indexOf(this.ctrl.getEspCroisiereCrt() ) ].getBlue ();
		
		this.lblTexteEspece.setText
		(	
			"<html>"+
				"<body> "+
					"<h1 style='text-align : center;'>"                                         + 
						"Croisière des <br> <p style='text-align : center; color:"              +
						" rgb("+ r + "," + g + "," + b +");'>" + this.ctrl.getEspCroisiereCrt() + 
					"</p></h1>"+
				" </body> "
			+"</html>"


		);

		this.panelPlateau.setExtremiteSlct(null);

		this.lblTexteEspece.revalidate();
		this.revalidate();
		this.repaint   ();
	}

	/**
	 * Maj Défausse
	 * 
	 * Met à jour les cartes défaussé avant de tiré la prochaine carte.
	 * C'et cette méthode qui est utilisé pour passé à la destination suivante.
	 * 
	 */
	private void majDefausse()
	{
		String cartePosee = this.ctrl.getSommet();

		String[] tab = null;

		if (cartePosee.contains("Prem") ) 
		{
			tab = this.tabPremiumPosee;
			this.tabPremiumPosee[tab.length - this.getNbCartesDos(tab)]  = cartePosee;
		}
		else
		{
			tab = this.tabStandardsPosee;
			this.tabStandardsPosee[tab.length - this.getNbCartesDos(tab)]  = cartePosee;
		} 

		this.ctrl.decouvrirCarte();

	}

	/**
	 * Maj Score Espèce
	 * 
	 * Met à jour la partie Score du plateau, elle permet aussi d'ajouté un retour visuel sur quelle espèce est en cours de croisière
	 * 
	 */
	private void majScoreEspece()
	{
		this.panelScoreEspeces.removeAll();

		this.panelScoreEspeces.add(new JLabel()       );
		this.panelScoreEspeces.add(this.lblTexteScore );
		this.panelScoreEspeces.add(new JLabel()       );

		for (int cpt = 0; cpt < this.ctrl.getNbTypeEspeces() +1; cpt++) 
		{
			JLabel lblScore = new JLabel();

			lblScore.setOpaque(false);
			
			if (cpt < this.ctrl.getNbTypeEspeces() )
			{

				if (cpt == this.ctrl.getEnsEspece().indexOf(this.ctrl.getEspCroisiereCrt() ) )
				{

					lblScore.setForeground(Color.decode("#28f31d") );
					lblScore.setFont(new Font("Monospaced", Font.BOLD, 21) );
				}
				else
				{
					lblScore.setForeground(FrameJeu.COULEUR_ZONE);
					lblScore.setFont(new Font("Monospaced", Font.BOLD, 19) );
				}

				lblScore.setText (String.format("%-11s",this.ctrl.getNomEspece(cpt) ) +" : " +
				                  String.format("%-3d", this.ctrl.calculerScore(this.ctrl.getNomEspece(cpt) ) ) );
				
				lblScore.setIcon(new ImageIcon("../images/Tuiles/Centre-Espece-" + this.ctrl.getNomEspece(cpt) + ".png" ) )  ;
	
			}
			else 
			{
				lblScore.setFont      (new Font("Monospaced", Font.BOLD, 19) );
				lblScore.setForeground(Color.decode("#0ba8d3"            ) );

				lblScore.setText(String.format("%-16s","     Total ") +" : "+ this.ctrl.getScoreTotal() ) ;
			}

			this.panelScoreEspeces.add(lblScore);
			
		}
	}

	
	private void selectionnerExtremite( int posColClk, int posLigClk )
	{
		this.panelPlateau.setExtremiteSlct( new Point( posColClk, posLigClk ) );
		this.panelPlateau.repaint();
	}
	
	private void effectuerVoyage( int posColClk, int posLigClk )
	{
		boolean voyageAjoute = false ;
		
		voyageAjoute = this.ctrl.effectuerVoyage(
		                                          (int) this.panelPlateau.getPosExtremiteSlct().getX(),
		                                          (int) this.panelPlateau.getPosExtremiteSlct().getY(),
		                                          posColClk,
		                                          posLigClk,
		                                          this.ctrl.getEspCroisiereCrt()
		                                        );
		
		if ( voyageAjoute )
		{
			this.panelPlateau.setExtremiteSlct(null);
			this.majImages();
		}
		
		this.frameJeu    .revalidate();
		this.panelPlateau.repaint   ();

	}

	protected void viderDefausse()
	{
		for (int cpt = 0; cpt < this.tabPremiumPosee.length; cpt++) 
		{
			this.tabPremiumPosee  [cpt] = null;
			this.tabStandardsPosee[cpt] = null;	
		}	

		this.repaint();
		this.revalidate();
	}
}
