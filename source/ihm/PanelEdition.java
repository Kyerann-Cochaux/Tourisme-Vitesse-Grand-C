package source.ihm;

import source.AppliCreation ;

import javax.swing.*;
import java.awt.event.* ;

import java.awt.BorderLayout ;
import java.awt.GridLayout ;
import java.awt.Panel;
import java.awt.GridBagLayout ;
import java.awt.GridBagConstraints ;
import java.awt.FlowLayout ;
import java.awt.Dimension ;
import java.awt.Graphics ;
import java.awt.Graphics2D ;

import java.util.ArrayList ;

/**
 * Panel Edition
 * 
 * Panel utiliser pour editer des plateau manuellement
 * 
 * @author Groupe 5
 * 
 */

public class PanelEdition extends JPanel implements ActionListener
{
	private static final String REP_IMAGES = "../source/ihm/images/Tuiles/" ;
	
	private AppliCreation ctrl;
	private FrameCreation frameCreation;
	
	private ArrayList<JToggleButton> ensBoutonActifs ;
	
	private ButtonGroup bgBtnActifs;
	
	// Partie Planete
	private JToggleButton[] tabTypePlanete;

	// Partie Planete
	private JToggleButton[] tabTypeEspece ;

	// Partie Plateau
	private JScrollPane  scrollPlateau;
	private PanelPlateau panelPlateau;
	
	// Partie Système
	private JPanel panelSysteme;
	
	private JPanel        sectionZone;
	private JToggleButton outilZone;
	private JLabel        selectionZone;
	
	public PanelEdition(AppliCreation ctrl, FrameCreation frameCreation )
	{
		JPanel panelPlanete;
		JPanel lotBaseA;
		JPanel lotPlaneteA;	
		JPanel lotBaseB;	
		JPanel lotPlaneteB;

		/*------------------------------*/
		/*    Configuration du Panel    */
		/*------------------------------*/
		this.ctrl          = ctrl ;
		this.frameCreation = frameCreation;

		this.setLayout( new BorderLayout() );
		
		/*-------------------------------*/
		/*    Création des Composants    */
		/*-------------------------------*/
		
		this.bgBtnActifs     = new ButtonGroup();
		this.ensBoutonActifs = new ArrayList<JToggleButton>(4);

		this.tabTypeEspece   = new JToggleButton[this.ctrl.getNbEspeces ()];
		this.tabTypePlanete  = new JToggleButton[this.ctrl.getNbPlanetes()];


		// Création des JToggleButton pour les planètes
		for (int cpt = 0; cpt < this.tabTypeEspece.length; cpt++) 
			this.tabTypeEspece[cpt] = 
		    new JToggleButton(new ImageIcon(PanelEdition.REP_IMAGES + "Espece-" + this.ctrl.getNomEspece(cpt) + ".png") );
			
		for (int cpt = 0; cpt < this.tabTypePlanete.length; cpt++) 
		{
			this.tabTypePlanete[cpt] = new JToggleButton 
			( 
				new ImageIcon(PanelEdition.REP_IMAGES + "Planete-" + this.ctrl.getNomPlanete(cpt).charAt(0) + ".png") 
			);
		}

		/* Panel des Planètes */
		// Partie Planete

		panelPlanete = new JPanel    (new GridLayout  (10,2) );

		lotPlaneteA  = new JPanel(new GridLayout(1,2) );
		lotBaseA     = new JPanel(new GridLayout(1,2) );
		lotBaseB     = lotPlaneteB = null;

		if ( this.ctrl.getNbPlanetes() >= 3 ) lotPlaneteB = new JPanel( new GridLayout(1,this.ctrl.getNbPlanetes() /2) );
		if (this.ctrl.getNbEspeces  () >= 3 ) lotBaseB    = new JPanel( new GridLayout(1,this.ctrl.getNbPlanetes() /2) );

		// Panel d'affichage du Plateau
		this.panelPlateau   = new PanelPlateau      (this.ctrl);
		JPanel panelCentrer = new JPanel( new GridBagLayout() );

		panelCentrer.setSize      ( this.panelPlateau.getWidth() + 2, this.panelPlateau.getHeight() +2 );
		panelCentrer.setBackground( FrameCreation.COULEUR_FOND_FONCE                                   );
		panelCentrer.add          (this.panelPlateau, new GridBagConstraints()                         );

		this.scrollPlateau = new JScrollPane( panelCentrer );
		
		// Panel des Systèmes
		this.panelSysteme = new JPanel();
		
		this.sectionZone   = new JPanel       ( new GridLayout(1,2 ) );
		this.outilZone     = new JToggleButton( "Zone"                     );
		this.selectionZone = new JLabel       ( "0", JLabel.CENTER         );
		
		/*------------------------------------*/
		/*    Configuration des Composants    */
		/*------------------------------------*/
		
		// Panel des Planètes

		panelPlanete.setBackground   ( FrameCreation.COULEUR_FOND_CLAIR          );
		panelPlanete.setPreferredSize( new Dimension ( 200 , 900 ) );

		// Panel d'affichage du Plateau
		this.scrollPlateau.setBackground               ( FrameCreation.COULEUR_FOND_FONCE         );
		this.scrollPlateau.setVerticalScrollBarPolicy  (JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED  );
		this.scrollPlateau.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		// Panel des Système
		this.panelSysteme.setLayout( new GridLayout(10,1) );
		this.panelSysteme.setBackground( FrameCreation.COULEUR_FOND_CLAIR );
		this.panelSysteme.setPreferredSize( new Dimension( 200 , 900 ) );
		
		/*-------------------------------------*/
		/*    Positionnement des Composants    */
		/*-------------------------------------*/
		
		/* Panel Planètes */
		// Partie Planete

		lotPlaneteA          .add(this.tabTypePlanete[0]);
		this.ensBoutonActifs .add(this.tabTypePlanete[0]);
		lotPlaneteA          .add(this.tabTypePlanete[1]);
		this.ensBoutonActifs .add(this.tabTypePlanete[1]);
		panelPlanete         .add( lotPlaneteA  );
		
		if ( this.ctrl.getNbPlanetes() >= 3 )
		{
			lotPlaneteB         .add(this.tabTypePlanete[2] );
			this.ensBoutonActifs.add(this.tabTypePlanete[2] );

			if ( this.ctrl.getNbPlanetes() == 4 )
			{
				lotPlaneteB         .add(tabTypePlanete[3] );
				this.ensBoutonActifs.add(tabTypePlanete[3] );
			}

			panelPlanete.add( lotPlaneteB );
		}
		panelPlanete.add( new JLabel("") ); // Séparateur

		// Partie Base

		lotBaseA            .add(this.tabTypeEspece[0] );
		this.ensBoutonActifs.add(this.tabTypeEspece[0] );
		lotBaseA            .add(this.tabTypeEspece[1] );
		this.ensBoutonActifs.add(this.tabTypeEspece[1] );
		panelPlanete        .add( lotBaseA             );

		if ( this.ctrl.getNbEspeces() >= 3 )
		{
			lotBaseB            .add( this.tabTypeEspece[2] );
			this.ensBoutonActifs.add(this.tabTypeEspece [2] );
			
			if ( this.ctrl.getNbEspeces() == 4 )
			{
				lotBaseB            .add( this.tabTypeEspece[3]);
				this.ensBoutonActifs.add(this.tabTypeEspece [3]);
			}
			panelPlanete.add( lotBaseB );
		}
		
		this.add( panelPlanete, BorderLayout.WEST );
		
		// Panel Plateau
		this.add( this.scrollPlateau, BorderLayout.CENTER );
		this.panelPlateau.repaint();
		
		// Panel Système
		this.sectionZone    .add(this.outilZone     );
		this.ensBoutonActifs.add(this.outilZone      );
		this.sectionZone    .add(this.selectionZone );
		this.panelSysteme   .add(this.sectionZone   );
		
		this.add( this.panelSysteme, BorderLayout.EAST );
		
		// Ajouts des Boutons dans le ButtonGroup

		for ( JToggleButton btn : this.ensBoutonActifs )
			this.bgBtnActifs.add(btn);
	
		
		/*---------------------------------*/
		/*    Activation des Composants    */
		/*---------------------------------*/
		
		// Activations des Boutons
		for ( JToggleButton btn : this.ensBoutonActifs )
			btn.addActionListener(this);
		
	}
	
	

	
	public void actionPerformed( ActionEvent e )
	{
		
	}
}
