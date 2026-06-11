package srcJeu.ihm;

import srcJeu.AppliJeu;

import javax.swing.*;
import java.awt.event.*;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Point;


public class PanelJeu extends JPanel
{

	private static final int NB_CARTE_MINI = 6;

	private AppliJeu ctrl;
	private FrameJeu frameJeu;
	
	private JLabel[] ensLblCartes;
	private JLabel[] ensLblEspeces; 

	private PanelPlateau panelPlateau;
	private JScrollPane  spPlateau;

	private JLabel lblTexteEspece;
	private JLabel lblTexteScore;

	private JPanel panelCartesStandards;
	private JPanel panelCartesPremium;

	public PanelJeu(AppliJeu ctrl, FrameJeu frameJeu)
	{
		/* -------- Panels principaux ------- */

		JPanel panelCentre;
		JPanel panelPioche;
		JPanel panelScore;

		//sous panels du panelScore
		JPanel panelScoreEspeces;
		JPanel panelScoreLabels;

		ButtonGroup btgEspeces;

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

		GridLayout gl1 = new GridLayout(5,1,0,9);
		if (this.ctrl.getNbTypeEspeces() >= 3) 
		{
			gl1.setRows(gl1.getRows() + this.ctrl.getNbTypeEspeces() /2);
			this.ensLblCartes = new JLabel[6 + this.ctrl.getNbTypeEspeces() /2];

		}
		panelScoreEspeces.setLayout(gl1);
		
		panelPioche               = new JPanel(new GridLayout(8,1) );

		this.ensLblCartes = new JLabel[10];
		btgEspeces        = new ButtonGroup();
	
		this.spPlateau = new JScrollPane(panelCentre);

		for (int cpt = 0; cpt < this.ensLblCartes.length; cpt++) 
			this.ensLblCartes[cpt] = new JLabel(new ImageIcon("../images/Cartes/Carte-Dos.png") );

		this.panelCartesPremium   = this.creerPanelCarte();
		this.panelCartesStandards = this.creerPanelCarte();

		this.lblTexteEspece = new JLabel
		(
			"<html>"+
				"<body> "+
					"<h1 style='text-align : center;'>"+ 
						"Croisière des <br> " + this.ctrl.getNomEspece(0) + 
					"</h1>"+
				" </body> "
			+"</html>", SwingConstants.CENTER

		);

		this.lblTexteScore = new JLabel("Score des croisières : ");
		
		/* ---------------------------------- */
		/*    Configuration des composants    */
		/* ---------------------------------- */

		panelScore       .setBackground(FrameJeu.COULEUR_FOND_CLAIRE);
		panelScoreEspeces.setBackground(FrameJeu.COULEUR_FOND_CLAIRE);
		panelScoreLabels .setBackground(FrameJeu.COULEUR_FOND_CLAIRE);

		panelCentre.setBackground(FrameJeu.COULEUR_FOND_CLAIRE);
		panelPioche.setBackground(FrameJeu.COULEUR_FOND_CLAIRE);

		this.spPlateau.setBackground               (FrameJeu.COULEUR_FOND_FONCE               );
		this.spPlateau.setVerticalScrollBarPolicy  (JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED  );
		this.spPlateau.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		this.lblTexteEspece.setFont      (FrameJeu.POLICE_TEXTE);
		this.lblTexteEspece.setForeground(FrameJeu.COULEUR_ZONE);
		this.lblTexteEspece.setOpaque    (false       );

		this.lblTexteScore.setForeground(FrameJeu.COULEUR_ZONE);
		this.lblTexteScore.setOpaque    (false       );
		this.lblTexteScore.setFont(new Font("Goldman", Font.BOLD, 17) );




		/* ---------------------------------- */
		/*    Positionnement des composants   */
		/* ---------------------------------- */

		/* -------- Panels principaux ------- */

		panelScoreLabels.add(this.lblTexteEspece);
		panelScoreLabels.add(new JLabel(new ImageIcon("../images/Tuiles/XL-Espece-" + this.ctrl.getNomEspece(0)  + ".png")  ) );
	

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

		panelPioche.add(new JButton("PIOCHE") );
		panelPioche.add(new JLabel("    NOM CARTE JEU", SwingConstants.CENTER) );
		panelPioche.add(this.panelCartesPremium);

		panelPioche.add(new JLabel("LABEL PREMIUM", SwingConstants.CENTER) );

		panelPioche.add(new JLabel("LABEL STANDARD", SwingConstants.CENTER) );


		this.add(panelScore    , BorderLayout.WEST  );
		this.add(this.spPlateau, BorderLayout.CENTER);
		this.add(panelPioche   , BorderLayout.EAST  );



		/* ---------------------------------- */
		/*      Activation des composants     */
		/* ---------------------------------- */

		// Activation du Panel Plateau
		this.panelPlateau.addMouseListener( this.gererClic() );

	}

	private JPanel creerPanelCarte()
	{
		JPanel panel1, panel2;
		JPanel panelPrc;


		panelPrc = new JPanel(new GridLayout(2,1) );
		panel1 = new JPanel();
		panel2 = new JPanel();

		panelPrc.setOpaque(false);
		panel1.setOpaque(false);
		panel2.setOpaque(false);


		for (int cpt = 0; cpt < this.ensLblCartes.length /2; cpt++) 
		{
				if (cpt < 3) panel1.add(new JLabel(new ImageIcon("../images/Cartes/Carte-Dos.png") ) );
				else         panel2.add(new JLabel(new ImageIcon("../images/Cartes/Carte-Dos.png") ));
			
		}

		panelPrc.add(panel1);
		panelPrc.add(panel2);

		System.out.println("nbElt ->" + panelPrc.getComponentCount() );

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
				int posColClk = (int) ( e.getX() / PanelJeu.this.panelPlateau.getTailleCase() ) ;
				int posLigClk = (int) ( e.getY() / PanelJeu.this.panelPlateau.getTailleCase() ) ;
				
				// Gestion de la création de Voyage
				
				if ( e.getButton() == MouseEvent.BUTTON1 && PanelJeu.this.plateauJeu.getPosExtremiteSlct() != null )
				{
					effectuerVoyage( posColClk, posLigClk );
				}
				
				
				// Gestion de la selection d'extremite
				if ( e.getButton() == MouseEvent.BUTTON1  && PanelJeu.this.ctrl.estExtremite(posColClk,posLigClk) )
				{
					selectionnerExtremite( posColClk, posLigClk );
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
		
		voyageAjoute = this.ctrl.setEspece(
		                                    (int) this.panelPlateau.getPosExtremiteSlct().getX(),
		                                    (int) this.panelPlateau.getPosExtremiteSlct().getY(),
		                                    posColClk,
		                                    posLigClk,
		                                    this.ctrl.getEspCroisiereCrt()
		                                  );
		
		if ( voyageAjoute ) { this.panelPlateau.repaint(); }
	}
}
