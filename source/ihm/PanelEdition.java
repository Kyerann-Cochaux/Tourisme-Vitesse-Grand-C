package source.ihm;

import javax.swing.*;

import java.io.File;

import java.awt.BorderLayout ;
import java.awt.GridLayout ;
import java.awt.GridBagLayout ;
import java.awt.GridBagConstraints ;
import java.awt.FlowLayout ;
import java.awt.Dimension ;
import java.awt.Graphics ;
import java.awt.Graphics2D ;

import source.AppliCreation ;

/**
 * Panel Edition
 * 
 * Panel utiliser pour editer des plateau manuellement
 * 
 * @author Groupe 5
 * 
 */

public class PanelEdition extends JPanel
{
	private AppliCreation ctrl;
	private FrameCreation frameCreation;
	
	private int nbLigne;
	private int nbColonne;
	private int nbPlanete;
	private int nbEspece;
	
	private File fichier;
	
	// Partie Planete
	private JPanel panelPlanete;
	
	private JPanel lotPlaneteA;
	private JLabel planeteType1;
	private JLabel planeteType2;
	
	private JPanel lotBaseA;
	private JLabel baseType1;
	private JLabel baseType2;
	
	private JPanel lotBaseB;
	private JLabel baseType3;
	private JLabel baseType4;
	
	private JPanel lotPlaneteB;
	private JLabel planeteType3;
	private JLabel planeteType4;
	
	// Partie Plateau
	private JScrollPane  scrollPlateau;
	private PanelPlateau panelPlateau;
	
	// Partie Système
	private JPanel panelSysteme;
	
	private JPanel sectionZone;
	private JLabel outilZone;
	private JLabel selectionZone;
	
	public PanelEdition(AppliCreation ctrl, FrameCreation frameCreation, int nbLigne, int nbColonne, int nbPlanete, int nbEspece)
	{
		/*------------------------------*/
		/*    Configuration du Panel    */
		/*------------------------------*/
		this.ctrl          = ctrl ;
		this.frameCreation = frameCreation;
		this.nbLigne       = nbLigne;
		this.nbColonne     = nbColonne;
		this.nbPlanete     = nbPlanete;
		this.nbEspece      = nbEspece;
		
		this.creationInterfaceEdition();
	}
	
	public PanelEdition(AppliCreation ctrl, FrameCreation frameCreation, File fichier)
	{
		/*------------------------------*/
		/*    Configuration du Panel    */
		/*------------------------------*/
		this.ctrl    = ctrl ;
		this.fichier = fichier;
	}
	
	public void creationInterfaceEdition()
	{
		this.setLayout( new BorderLayout() );
		
		/*-------------------------------*/
		/*    Création des Composants    */
		/*-------------------------------*/
		
			/* Panel des Planètes */
		// Partie Planete
		this.panelPlanete = new JPanel();
		
		this.lotPlaneteA = new JPanel();
		this.planeteType1 = new JLabel("Gazeuze");
		this.planeteType2 = new JLabel("Océan");
		
		if ( this.nbPlanete >= 3 )
		{
			this.lotPlaneteB = new JPanel();
			this.lotPlaneteB.setLayout( new GridLayout(1,2) );
			this.planeteType3 = new JLabel("Tellurique");
			
			if ( this.nbPlanete == 4 ) { this.planeteType4 = new JLabel("Volcanique"); }
		}
		// Partie Base
		this.lotBaseA  = new JPanel();
		this.baseType1 = new JLabel("Chlorophite");
		this.baseType2 = new JLabel("Felinoïd");
		
		if ( this.nbEspece >= 3 )
		{
			this.lotBaseB = new JPanel();
			this.lotBaseB.setLayout( new GridLayout(1,2) );
			this.baseType3 = new JLabel("Azimae");
			
			if ( this.nbEspece == 4 ) { this.baseType4 = new JLabel("Silikon"); }
		}
		
		// Panel d'affichage du Plateau
		this.panelPlateau   = new PanelPlateau(this.nbLigne, this.nbColonne, this.nbPlanete, this.nbEspece);
		JPanel panelCentrer = new JPanel( new GridBagLayout() );
		panelCentrer.setSize( this.panelPlateau.getWidth()+2, this.panelPlateau.getHeight()+2 );
		panelCentrer.setBackground( FrameCreation.COULEUR_FOND );
		panelCentrer.add( this.panelPlateau, new GridBagConstraints() );
		
		this.scrollPlateau = new JScrollPane( panelCentrer );
		
		// Panel des Systèmes
		this.panelSysteme = new JPanel();
		
		this.sectionZone   = new JPanel();
		this.outilZone     = new JLabel("Zone");
		this.selectionZone = new JLabel("0");
		
		/*------------------------------------*/
		/*    Configuration des Composants    */
		/*------------------------------------*/
		
		// Panel des Planètes
		this.panelPlanete.setLayout(new GridLayout(10,2));
		this.panelPlanete.setBackground( FrameCreation.COULEUR_FOND_CLAIRE );
		this.panelPlanete.setPreferredSize( new Dimension( 200 , 1080 ) );
		
		this.lotPlaneteA.setLayout( new GridLayout(1,2) );
		this.lotBaseA.setLayout( new GridLayout(1,2) );
		
		// Panel d'affichage du Plateau
		this.scrollPlateau.setBackground( FrameCreation.COULEUR_FOND );
		this.scrollPlateau.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		this.scrollPlateau.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		// Panel des Système
		this.panelSysteme.setLayout( new GridLayout(10,1) );
		this.panelSysteme.setBackground( FrameCreation.COULEUR_FOND_CLAIRE );
		this.panelSysteme.setPreferredSize( new Dimension( 200 , 1080 ) );
		
		this.sectionZone.setLayout( new GridLayout(1,2) );
		
		/*-------------------------------------*/
		/*    Positionnement des Composants    */
		/*-------------------------------------*/
		
			/* Panel Planètes */
		// Partie Planete
		this.lotPlaneteA.add( this.planeteType1 );
		this.lotPlaneteA.add( this.planeteType2 );
		this.panelPlanete.add( this.lotPlaneteA );
		if ( this.nbPlanete >= 3 )
		{
			this.lotPlaneteB.add( this.planeteType3 );
			if ( this.nbPlanete == 4 ) { this.lotPlaneteB.add( this.planeteType4 ); }
			this.panelPlanete.add( this.lotPlaneteB );
		}
		this.panelPlanete.add( new JLabel("") ); // Séparateur
		// Partie Base
		this.lotBaseA.add( this.baseType1 );
		this.lotBaseA.add( this.baseType2 );
		this.panelPlanete.add( this.lotBaseA );
		if ( this.nbEspece >= 3 )
		{
			this.lotBaseB.add( this.baseType3 );
			if ( this.nbEspece == 4 ) { this.lotBaseB.add( this.baseType4 ); }
			this.panelPlanete.add( this.lotBaseB );
		}
		
		this.add( this.panelPlanete, BorderLayout.WEST );
		
		// Panel Plateau
		this.add( this.scrollPlateau, BorderLayout.CENTER );
		this.panelPlateau.repaint();
		
		// Panel Système
		this.sectionZone.add( this.outilZone );
		this.sectionZone.add( this.selectionZone );
		this.panelSysteme.add( this.sectionZone );
		
		this.add( this.panelSysteme, BorderLayout.EAST );
		
		/*---------------------------------*/
		/*    Activation des Composants    */
		/*---------------------------------*/
	}
}
