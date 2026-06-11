package srcJeu.ihm;

import srcJeu.AppliJeu;

import javax.swing.*;
import java.awt.event.*;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;


public class PanelJeu extends JPanel
{

	private AppliJeu ctrl;
	private FrameJeu frameJeu;
	
	private JButton[] ensBtnCartes;
	private JLabel [] ensLblEspeces; // À changer en JLabel


	private PanelPlateau panelPlateau;
	private JScrollPane  spPlateau;

	private JLabel lblTexteEspece;
	private JLabel lblTexteScore;

	public PanelJeu(AppliJeu ctrl, FrameJeu frameJeu)
	{
		/* -------- Panels principaux ------- */

		JPanel panelCentre;
		JPanel panelPioche;
		JPanel panelScore;

		JPanel panelScoreEspeces;
		JPanel panelCartesStandards;
		JPanel panelCartesPremium;

		JPanel panelScoreLabels;

		ButtonGroup btgEspeces;

		this.ctrl     = ctrl;
		this.frameJeu = frameJeu;

		this.setLayout(new BorderLayout() );

		/* ---------------------------------- */
		/*       Création des composants      */
		/* ---------------------------------- */

		this.panelPlateau = new PanelPlateau(ctrl);
		panelCentre       = new JPanel(new GridBagLayout() );

		panelScore        = new JPanel(new GridLayout(4,1) );
		panelScoreEspeces = new JPanel();

		GridLayout gl = new GridLayout(5,1,0,9);
		if (this.ctrl.getNbTypeEspeces() >= 3) gl.setRows(gl.getRows() + this.ctrl.getNbTypeEspeces() /2);
		panelScoreEspeces.setLayout(gl);
		
		panelPioche          = new JPanel(new GridLayout(3,1) );
		panelCartesStandards = new JPanel(new GridLayout(1,3) );
		panelCartesPremium   = new JPanel(new GridLayout(1,3) );
		

		panelScoreLabels  = new JPanel(new GridLayout(2,1) );

		this.ensBtnCartes = new JButton[10];
		btgEspeces        = new ButtonGroup();
	
		this.spPlateau = new JScrollPane(panelCentre);

		for (int cpt = 0; cpt < this.ensBtnCartes.length; cpt++) 
		{
			this.ensBtnCartes[cpt] = new JButton(new ImageIcon("../images/Cartes/Carte-G.png") );
			this.ensBtnCartes[cpt].setEnabled(false);
		}

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


		this.spPlateau.setBackground               (FrameJeu.COULEUR_FOND_FONCE               );
		this.spPlateau.setVerticalScrollBarPolicy  (JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED  );
		this.spPlateau.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		this.lblTexteEspece.setFont      (FrameJeu.POLICE_TEXTE);
		this.lblTexteEspece.setForeground(FrameJeu.COULEUR_ZONE);
		this.lblTexteEspece.setOpaque    (false       );

		this.lblTexteScore.setForeground(FrameJeu.COULEUR_ZONE);
		this.lblTexteScore.setOpaque    (false       );
		this.lblTexteScore.setFont(new Font    ("Goldman", Font.BOLD, 17) );




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
			 String.format("%-3d", 99), 
			new ImageIcon("../images/Tuiles/Centre-Espece-" + this.ctrl.getNomEspece(cpt) + ".png" ), SwingConstants.LEFT ) ;

			lbl.setFont      (new Font    ("Monospaced", Font.BOLD, 17) );
			lbl.setForeground(FrameJeu.COULEUR_ZONE);
			lbl.setOpaque    (false       );

			panelScoreEspeces.add(lbl);
			
		}

		panelCentre.add( this.panelPlateau, new GridBagConstraints() );

		// panelCartes
		for (int cpt = 0; cpt < this.ensBtnCartes.length ; cpt++) 
		{	
			if (cpt < 5) panelCartesStandards.add(this.ensBtnCartes[cpt] );
			else         panelCartesPremium  .add(this.ensBtnCartes[cpt] );
		}
		
		panelScore.add(panelScoreLabels );
		panelScore.add(panelScoreEspeces);

		this.add(panelScore    , BorderLayout.WEST  );
		this.add(this.spPlateau, BorderLayout.CENTER);
		this.add(panelPioche   , BorderLayout.EAST  );



		/* ---------------------------------- */
		/*      Activation des composants     */
		/* ---------------------------------- */

		// Activation du Panel Plateau
		this.panelPlateau.addMouseListener( this.gererClic() );

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
				int posLigClk = (int) ( e.getY() / PanelJeu.this.panelPlateau.getTailleCase() ) ;
				int posColClk = (int) ( e.getX() / PanelJeu.this.panelPlateau.getTailleCase() ) ;
				
				if ( e.getButton() == MouseEvent.BUTTON1 /* && this.ctrl.estExtremite(posColClk,posLigClk) */ )
				{
					
				}
			}
		};
	}
}