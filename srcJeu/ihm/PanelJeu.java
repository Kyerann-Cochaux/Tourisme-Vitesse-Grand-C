package srcJeu.ihm;

import srcJeu.AppliJeu;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.Point;


public class PanelJeu extends JPanel
{

	private AppliJeu ctrl;
	private FrameJeu frameJeu;
	
	private JLabel[] ensLblCartes;
	private JLabel[] ensLblEspeces; 

	private PanelPlateau panelPlateau;
	private JScrollPane  spPlateau;
	
	private JLabel iconeCrosiereActuelle;
	
	private JLabel lblTexteEspece;
	private JLabel lblTexteScore;
	private JLabel lblTextePioche;
	private JLabel lblTexteStandards;
	private JLabel lblTextePremium;
	private JLabel lblActionPioche;

	private JPanel panelCartesStandards;
	private JPanel panelCartesPremium;

	private String[] tabStandardsPosee;
	private String[] tabPremiumPosee;

	//Panel Carte

	private JPanel panelCartes;

	public PanelJeu(AppliJeu ctrl, FrameJeu frameJeu)
	{
		/* -------- Panels principaux ------- */

		JPanel panelCentre;

		JPanel panelScore;

		//sous panels du panelScore
		JPanel panelScoreLabels;
		JPanel panelScoreEspeces;
		JPanel panelPioche;


		this.ctrl     = ctrl;
		this.frameJeu = frameJeu;

		this.setLayout(new BorderLayout() );

		/* ---------------------------------- */
		/*       Création des composants      */
		/* ---------------------------------- */
		
		panelScore        = new JPanel(new GridLayout(4,1) );
		panelScoreLabels  = new JPanel(new GridLayout(2,1) );
		panelScoreEspeces = new JPanel();

		this.panelPlateau = new PanelPlateau(ctrl);
		panelCentre       = new JPanel(new GridBagLayout() );
		

		this.ensLblCartes = new JLabel[this.ctrl.getNbTypePlanetes() * 2 +2];

		GridLayout gl1 = new GridLayout(5,1,0,9);

		if (this.ctrl.getNbTypeEspeces() >= 3) gl1.setRows(gl1.getRows() + this.ctrl.getNbTypeEspeces() /2);

		panelScoreEspeces.setLayout(gl1);
	
		this.panelCartes = new JPanel(new GridLayout(4,1) );
		panelPioche = new JPanel(new GridLayout(2,1) );

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

		this.lblTexteEspece = new JLabel
		(

			"<html>"+
				"<body> "+
					"<h1 style='text-align : center;'>"+ 
						"Croisière des <br> " + this.ctrl.getEspCroisiereCrt() + 
					"</h1>"+
				" </body> "
			+"</html>", SwingConstants.CENTER

		);

		this.lblTexteScore     = new JLabel("Score des croisières : ");
		this.lblTextePremium   = new JLabel("Cartes Premium : "      );
		this.lblTexteStandards = new JLabel("Cartes Standards : "    );
		this.lblTextePioche    = new JLabel
		(
			"<html>" + 
				"<body> " +
					"<h1 style='text-align : center;'>" + 
						"Destination <br> Actuelle : " + 
					"</h1>"+
				"</body>"+
			"</html>", SwingConstants.CENTER  
		);

		this.lblActionPioche = new JLabel(new ImageIcon("../images/Cartes/Carte-" +  this.ctrl.getSommet() +".png") );
		
		/* ---------------------------------- */
		/*    Configuration des composants    */
		/* ---------------------------------- */

		panelScore       .setBackground(FrameJeu.COULEUR_FOND_CLAIRE);
		panelScoreEspeces.setBackground(FrameJeu.COULEUR_FOND_CLAIRE);
		panelScoreLabels .setBackground(FrameJeu.COULEUR_FOND_CLAIRE);

		panelCentre.setBackground(FrameJeu.COULEUR_FOND_CLAIRE);
		panelCartes.setBackground(FrameJeu.COULEUR_FOND_CLAIRE);

		this.spPlateau.setBackground               (FrameJeu.COULEUR_FOND_FONCE               );
		this.spPlateau.setVerticalScrollBarPolicy  (JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED  );
		this.spPlateau.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		this.lblTexteEspece.setFont      (FrameJeu.POLICE_TEXTE);
		this.lblTexteEspece.setForeground(FrameJeu.COULEUR_ZONE);
		this.lblTexteEspece.setOpaque    (false       );

		this.lblTexteScore.setForeground(FrameJeu.COULEUR_ZONE);
		this.lblTexteScore.setOpaque    (false       );
		this.lblTexteScore.setFont(new Font("Goldman", Font.BOLD, 17) );

		this.lblTexteStandards.setForeground(FrameJeu.COULEUR_ZONE);
		this.lblTexteStandards.setOpaque    (false       );
		this.lblTexteStandards.setFont(new Font("Goldman", Font.BOLD, 17) );

		this.lblTextePremium.setForeground(FrameJeu.COULEUR_ZONE);
		this.lblTextePremium.setOpaque    (false       );
		this.lblTextePremium.setFont(new Font("Goldman", Font.BOLD, 17) );

		this.lblTextePioche.setFont      (FrameJeu.POLICE_TEXTE );
		this.lblTextePioche.setForeground(FrameJeu.COULEUR_ZONE);
		this.lblTextePioche.setOpaque    (false       );




		/* ---------------------------------- */
		/*    Positionnement des composants   */
		/* ---------------------------------- */

		/* -------- Panels principaux ------- */

		panelScoreLabels.add(this.lblTexteEspece);
		panelScoreLabels.add( this.iconeCrosiereActuelle = new JLabel(new ImageIcon("../images/Tuiles/XL-Espece-" + this.ctrl.getEspCroisiereCrt()  + ".png")  ) );
	

		panelScoreEspeces.add(new JLabel() );
		panelScoreEspeces.add(this.lblTexteScore );
		panelScoreEspeces.add(new JLabel() );


		for (int cpt = 0; cpt < this.ctrl.getNbTypeEspeces(); cpt++) 
		{
			JLabel lbl = new JLabel
			(String.format("%-11s",this.ctrl.getNomEspece(cpt) ) +" : " +
			 String.format("%-3d", /*this.ctrl.calculerScore()*/ 99), 
			new ImageIcon("../images/Tuiles/Centre-Espece-" + this.ctrl.getNomEspece(cpt) + ".png" ), SwingConstants.LEFT ) ;

			lbl.setFont      (new Font    ("Monospaced", Font.BOLD, 17) );
			lbl.setForeground(FrameJeu.COULEUR_ZONE);
			lbl.setOpaque    (false       );

			panelScoreEspeces.add(lbl);
			
		}

		panelCentre.add( this.panelPlateau, new GridBagConstraints() );

		
		panelScore.add(panelScoreLabels );
		panelScore.add(panelScoreEspeces);
		panelScore.add(this.lblTextePioche );
		panelScore.add( this.lblActionPioche );


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
		this.panelPlateau.addMouseListener   (this.gererClic() );
		this.lblActionPioche.addMouseListener(this.gererClic() );

	}

	private int getNbCartesDos(String[] tabCartesPosees)
	{
		int nbCarteNeutre = 0;


		for (int cpt = 0; cpt < tabCartesPosees.length; cpt++) 
			if (tabCartesPosees[cpt] == null) 
				
				nbCarteNeutre++;
		
		return nbCarteNeutre;
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

		

		//String fic = "../images/Cartes/Carte-";


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

		//System.out.println("nbElt ->" + panelPrc.getComponentCount() );

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
					if ( e.getButton() == MouseEvent.BUTTON1 && PanelJeu.this.ctrl.estExtremite(posColClk,posLigClk) )
					{
						PanelJeu.this.selectionnerExtremite( posColClk, posLigClk );
					}

					// Gestion de la création de Voyage
					if ( e.getButton() == MouseEvent.BUTTON1                      &&
					     PanelJeu.this.panelPlateau.getPosExtremiteSlct() != null
					   )
					{
						PanelJeu.this.effectuerVoyage( posColClk, posLigClk );
					}
				}
				
				// Gestion de l'affichage des Cartes Destionnations
				if ( e.getSource() == PanelJeu.this.lblActionPioche && PanelJeu.this.ctrl.getTaillePioche() > 0 )
				{
					String carteJouee = PanelJeu.this.ctrl.getSommet();
					String[] tab = null;
					tab = PanelJeu.this.tabPremiumPosee;
					System.out.println("Carte Premiums Posée : " + tab[PanelJeu.this.getNbCartesDos(tab) - PanelJeu.this.getNbCartesFace(tab) -1]);

					if (PanelJeu.this.ctrl.sommetPremium() )
					{
					
						//tab[PanelJeu.this.getNbCartesDos(tab) - PanelJeu.this.getNbCartesFace(tab) -1] = carteJouee;
					}
					else
					{
						//PanelJeu.this.tabStandardsPosee.add(carteJouee);
					}
					
					PanelJeu.this.ctrl.enleverCarte();
					
					if ( PanelJeu.this.ctrl.getTaillePioche() > 0 ) 
					{
						PanelJeu.this.lblActionPioche.setIcon(new ImageIcon("../images/Cartes/Carte-" +  PanelJeu.this.ctrl.getSommet() +".png") );
						PanelJeu.this.panelCartesPremium   = PanelJeu.this.creerPanelCarte(true);
						PanelJeu.this.panelCartesStandards = PanelJeu.this.creerPanelCarte(false);
					}
				}
			}
		};
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
			//System.out.println( "IHM PanelJeu : Voyage Ajouté vers la Planete " + posColClk + "/" + posLigClk ); 
			this.panelPlateau.setExtremiteSlct(null);
			
			// On passe à la Destination Suivante 
			this.lblActionPioche.setIcon(new ImageIcon("../images/Cartes/Carte-" +  PanelJeu.this.ctrl.getSommet() +".png") );
			
			// On vérifie si la croisières de la manche courante s'est terminer
			this.iconeCrosiereActuelle.setIcon( new ImageIcon("../images/Tuiles/XL-Espece-" + this.ctrl.getEspCroisiereCrt()  + ".png") );
		}
		
		this.frameJeu.revalidate();
		this.panelPlateau.repaint();
	}
}
