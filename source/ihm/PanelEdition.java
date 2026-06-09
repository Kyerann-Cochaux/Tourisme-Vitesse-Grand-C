package source.ihm;

import source.AppliCreation ;

import javax.swing.*;
import java.awt.event.* ;

import java.awt.BorderLayout ;
import java.awt.GridLayout ;
import java.awt.GridBagLayout ;
import java.awt.GridBagConstraints ;
import java.awt.Dimension ;

import java.util.ArrayList ;

/**
 * Panel Edition
 * 
 * Panel utiliser pour editer des plateaux manuellement
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
	
	private String          typeBtnSlct ;

	private JToggleButton[] tabTypePlanete; // Partie Planete
	private JToggleButton[] tabTypeEspece ; // Partie Espece

	// Partie Plateau
	private JScrollPane  spPlateau;
	private PanelPlateau panelPlateau;

	// Partie Système
	private JToggleButton tbgOutilZone;

	private JLabel  lblSelectionZone;
	private JButton btnPlusZone;
	private JButton btnMoinsZone;
	
	public PanelEdition( AppliCreation ctrl, FrameCreation frameCreation )
	{
		JPanel panelPlanete;
		JPanel panelSysteme;
		JPanel panelZone;

		JPanel panelLotBaseA;
		JPanel panelLotPlaneteA;

		JPanel panelLotBaseB;
		JPanel panelLotPlaneteB;

		JPanel widgetSelectionZone;
		JPanel panelPlusMoins;
		
		ButtonGroup btgBtnActifs;

		/*------------------------------*/
		/*    Configuration du Panel    */
		/*------------------------------*/
		this.ctrl          = ctrl ;
		this.frameCreation = frameCreation;
		
		this.typeBtnSlct   = null ;
		
		this.setLayout( new BorderLayout() );
		
		/*-------------------------------*/
		/*    Création des Composants    */
		/*-------------------------------*/
		
		btgBtnActifs    = new ButtonGroup();
		this.ensBoutonActifs = new ArrayList<JToggleButton>(4);

		this.tabTypeEspece   = new JToggleButton[this.ctrl.getNbTypeEspeces ()];
		this.tabTypePlanete  = new JToggleButton[this.ctrl.getNbTypePlanetes()];


		// Création des JToggleButton pour les espèces
		for (int cpt = 0; cpt < this.tabTypeEspece.length; cpt++) 
			this.tabTypeEspece[cpt] = 
			new JToggleButton(new ImageIcon(PanelEdition.REP_IMAGES + "Espece-" + this.ctrl.getNomEspece(cpt) + ".png") );

		// Création des JToggleButton pour les planètes
		for (int cpt = 0; cpt < this.tabTypePlanete.length; cpt++) 
		{
			this.tabTypePlanete[cpt] = new JToggleButton 
			(
				new ImageIcon(PanelEdition.REP_IMAGES + "Planete-" + this.ctrl.getNomPlanete(cpt).charAt(0) + ".png") 
			);
		}
		

		//Panel des Planètes 
		panelPlanete = new JPanel    (new GridLayout  (10,2) );

		panelLotPlaneteA  = new JPanel(new GridLayout(1,2) );
		panelLotBaseA     = new JPanel(new GridLayout(1,2) );
		panelLotBaseB     = panelLotPlaneteB = null;

		if (this.ctrl.getNbTypePlanetes() >= 3 ) panelLotPlaneteB = new JPanel( new GridLayout(1,this.ctrl.getNbTypePlanetes() /2) );
		if (this.ctrl.getNbTypeEspeces () >= 3 ) panelLotBaseB    = new JPanel( new GridLayout(1,this.ctrl.getNbTypePlanetes() /2) );

		// Panel d'affichage du Plateau
		this.panelPlateau   = new PanelPlateau      (this.ctrl);
		JPanel panelCentrer = new JPanel( new GridBagLayout() );

		panelCentrer.setSize      ( this.panelPlateau.getWidth() + 2, this.panelPlateau.getHeight() +2 );
		panelCentrer.setBackground( FrameCreation.COULEUR_FOND_FONCE                                   );
		panelCentrer.add          ( this.panelPlateau, new GridBagConstraints()                        );

		this.spPlateau = new JScrollPane( panelCentrer );
		
		// Panel des Systèmes
		panelSysteme          = new JPanel( new GridLayout(10,1) );
		panelZone             = new JPanel( new GridLayout(1,2 ) );

		this.tbgOutilZone     = new JToggleButton( "Zone"                     );
		
		widgetSelectionZone = new JPanel ( new GridLayout( 2,1 ) );
		this.lblSelectionZone    = new JLabel ( ""+this.ctrl.getNbSysteme(), JLabel.CENTER );
		panelPlusMoins           = new JPanel( new GridLayout(1,2) );
		this.btnPlusZone            = new JButton( "+" );
		this.btnMoinsZone           = new JButton( "-" );
		
		/*------------------------------------*/
		/*    Configuration des Composants    */
		/*------------------------------------*/
		
		// Panel des Planètes

		panelPlanete.setBackground   ( FrameCreation.COULEUR_FOND_CLAIR          );
		panelPlanete.setPreferredSize( new Dimension ( 200 , 900 ) );

		// Panel d'affichage du Plateau
		this.spPlateau.setBackground               (FrameCreation.COULEUR_FOND_FONCE          );
		this.spPlateau.setVerticalScrollBarPolicy  (JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED  );
		this.spPlateau.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		// Panel des Système
		panelSysteme.setBackground   ( FrameCreation.COULEUR_FOND_CLAIR          );
		panelSysteme.setPreferredSize( new Dimension ( 200 , 900 ) );
		
		/*-------------------------------------*/
		/*    Positionnement des Composants    */
		/*-------------------------------------*/
		
		/* Panel Planètes */
		// Partie Planete

		panelLotPlaneteA    .add(this.tabTypePlanete[0]);
		this.ensBoutonActifs.add(this.tabTypePlanete[0]);
		panelLotPlaneteA    .add(this.tabTypePlanete[1]);
		this.ensBoutonActifs.add(this.tabTypePlanete[1]);

		panelPlanete.add( panelLotPlaneteA);
		
		if ( this.ctrl.getNbTypePlanetes() >= 3 )
		{
			panelLotPlaneteB    .add(this.tabTypePlanete[2] );
			this.ensBoutonActifs.add(this.tabTypePlanete[2] );

			if ( this.ctrl.getNbTypePlanetes() == 4 )
			{
				panelLotPlaneteB    .add(tabTypePlanete[3] );
				this.ensBoutonActifs.add(tabTypePlanete[3] );
			}

			panelPlanete.add( panelLotPlaneteB );
		}

		panelPlanete.add( new JLabel("") ); // Séparateur

		// Partie Base

		panelLotBaseA       .add(this.tabTypeEspece[0] );
		this.ensBoutonActifs.add(this.tabTypeEspece[0] );
		panelLotBaseA       .add(this.tabTypeEspece[1] );
		this.ensBoutonActifs.add(this.tabTypeEspece[1] );
		panelPlanete        .add( panelLotBaseA        );

		if ( this.ctrl.getNbTypeEspeces() >= 3 )
		{
			panelLotBaseB       .add( this.tabTypeEspece[2] );
			this.ensBoutonActifs.add(this.tabTypeEspece [2] );
			
			if ( this.ctrl.getNbTypeEspeces() == 4 )
			{
				panelLotBaseB       .add( this.tabTypeEspece[3]);
				this.ensBoutonActifs.add(this.tabTypeEspece [3]);
			}
			panelPlanete.add( panelLotBaseB );
		}
		
		this.add( panelPlanete, BorderLayout.WEST );
		
		// Panel Plateau
		this.add( this.spPlateau, BorderLayout.CENTER );
		this.panelPlateau.repaint();
		
		// Panel Système
		widgetSelectionZone.add(this.lblSelectionZone);
		panelPlusMoins.add( this.btnMoinsZone );
		panelPlusMoins.add( this.btnPlusZone );
		widgetSelectionZone.add(panelPlusMoins);

		panelZone           .add(this.tbgOutilZone);
		this.ensBoutonActifs.add(this.tbgOutilZone);
		panelZone           .add(widgetSelectionZone);
		
		panelSysteme.add(panelZone);
		this.add( panelSysteme, BorderLayout.EAST );
		
		// Ajout des Boutons dans le ButtonGroup
		for ( JToggleButton btn : this.ensBoutonActifs )
			btgBtnActifs.add(btn);
	
		/*---------------------------------*/
		/*    Activation des Composants    */
		/*---------------------------------*/
		
		// Activations des Boutons
		for ( JToggleButton btn : this.ensBoutonActifs )
			btn.addActionListener(this);
		
		// Activation des Boutons du Widget de Selection de Zone
		this.btnMoinsZone.addActionListener(this);
		this.btnPlusZone .addActionListener(this);
		
		// Activation du Panel Plateau
		this.panelPlateau.addMouseListener( this.gererSouris() );
	}
	
	/*----------------------------------------*/
	/*    Gestion de selection des boutons    */
	/*----------------------------------------*/
	public void actionPerformed( ActionEvent e )
	{
		// On parcours les boutons Planete pour voir si il est pressé
		for ( int cpt=0 ; cpt < this.tabTypePlanete.length ; cpt++ )
		{
			if ( e.getSource() == this.tabTypePlanete[cpt] ) { this.typeBtnSlct = this.ctrl.getNomPlanete(cpt) ; }
		}
		
		// On parcours les boutons Espece pour voir si il est pressé
		for ( int cpt=0 ; cpt < this.tabTypeEspece.length ; cpt++ )
		{
			if ( e.getSource() == this.tabTypeEspece[cpt] ) { this.typeBtnSlct = this.ctrl.getNomEspece(cpt) ; }
		}
		
		// On regarde si l'Outil Zone est selectionner
		if ( e.getSource() == this.tbgOutilZone ) { this.typeBtnSlct = "Zone" ; }
		
		// Code de la gestion de la selection de zone
		if ( e.getSource() == this.btnMoinsZone )
		{
			int nbZoneAct = Integer.parseInt( this.lblSelectionZone.getText() );
			
			// System.out.println( "Nombre de Système Actuelle : " + this.ctrl.getNbSysteme() );
			
			if ( nbZoneAct > 0 )
			{
				nbZoneAct--;
				this.lblSelectionZone.setText( "" + nbZoneAct );
			}
		}
		
		if ( e.getSource() == this.btnPlusZone )
		{
			int nbZoneAct = Integer.parseInt( this.lblSelectionZone.getText() );
			
			// System.out.println( "Nombre de Système Actuelle : " + this.ctrl.getNbSysteme() );
			
			if ( nbZoneAct <= this.ctrl.getNbSysteme()-1 )
			{
				nbZoneAct++;
				// System.out.println( "On passe à la zone suivante : " + nbZoneAct );
				this.lblSelectionZone.setText( "" + nbZoneAct );
			}
		}
		
		// System.out.println( "Bouton Selectionné : " + this.typeBtnSlct );
	}
	
	/*----------------------------------------------------*/
	/*    Gestion de l'ajout d'éléments sur le Plateau    */
	/*----------------------------------------------------*/
	
	private MouseAdapter gererSouris()
	{
		return new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
			{
				if( e.getButton() == MouseEvent.BUTTON1 && typeBtnSlct != null ) 
				{
					ajouterElement(e);
				}
				
				if( e.getButton() == MouseEvent.BUTTON3 ) { supprimerElement(e) ; }
			}
		} ;
	}
	
	// Méthode utiliser par notre MouseAdapter
	private void ajouterElement(MouseEvent e)
	{
		int posLigClk = (int) ( e.getY() / this.panelPlateau.getTailleCase() ) ;
		int posColClk = (int) ( e.getX() / this.panelPlateau.getTailleCase() ) ;
		
		// Ajout d'une Planète
		for( int ind=0 ; ind < this.ctrl.getNbTypePlanetes() ; ind++ )
		{
			if ( this.ctrl.getNomPlanete(ind).equals(this.typeBtnSlct) )
			{
				this.ctrl.ajouterPlanete( posColClk, posLigClk, this.typeBtnSlct );
			}
		}
		
		// Ajout d'un Départ d'Espèce
		for( int ind=0 ; ind < this.ctrl.getNbTypeEspeces() ; ind++ )
		{
			if ( this.ctrl.getNomEspece(ind).equals(this.typeBtnSlct) )
			{
				this.ctrl.ajouterEspece( posColClk, posLigClk, this.typeBtnSlct );
			}
		}
		
		// Ajout d'une Zone
		if ( this.typeBtnSlct.equals("Zone") )
		{
			this.ctrl.ajouterSysteme( 
			                          Integer.parseInt( this.lblSelectionZone.getText() ),
			                          posColClk,
			                          posLigClk
			                        );
		}
		
		this.panelPlateau.repaint();
	}
	
	private void supprimerElement(MouseEvent e)
	{
		int posLigClk = (int) ( e.getY() / this.panelPlateau.getTailleCase() ) ;
		int posColClk = (int) ( e.getX() / this.panelPlateau.getTailleCase() ) ;
		
		boolean especeDejaSupprimer = false ;
		
		// Suppression d'une Espèce
		if ( this.ctrl.getPlanete( posColClk, posLigClk ) != null && this.ctrl.getPlanete( posColClk, posLigClk ).getEspece() != null )
		{
			this.ctrl.supprimerEspece( posColClk, posLigClk );
			especeDejaSupprimer = true ;
		}
		
		// Suppression d'une Planète
		if ( this.ctrl.getPlanete( posColClk, posLigClk ) != null && especeDejaSupprimer == false )
		{
			this.ctrl.supprimerPlanete( posColClk, posLigClk );
		}
		
		this.panelPlateau.repaint();
	}
}
