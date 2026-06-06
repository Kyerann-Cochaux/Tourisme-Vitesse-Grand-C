package source.ihm;

import source.AppliCreation ;

import javax.swing.*;
import java.awt.event.* ;

import java.awt.BorderLayout ;
import java.awt.GridLayout ;
import java.awt.GridBagLayout ;
import java.awt.GridBagConstraints ;
import java.awt.FlowLayout ;
import java.awt.Dimension ;
import java.awt.Graphics ;
import java.awt.Graphics2D ;

import java.util.ArrayList ;
import java.io.File;

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
	private final String imagePath = "../source/ihm/images/Tuiles/" ;
	
	private AppliCreation ctrl;
	private FrameCreation frameCreation;
	
	private int nbLigne;
	private int nbColonne;
	private int nbPlanete;
	private int nbEspece;
	
	private File fichier;
	
	private ArrayList<JToggleButton> ensBouton ;
	
	private ButtonGroup grpBouton;
	
	// Partie Planete
	private JPanel panelPlanete;
	
	private JPanel lotPlaneteA;
	private JToggleButton planeteType1;
	private JToggleButton planeteType2;
	
	private JPanel lotBaseA;
	private JToggleButton baseType1;
	private JToggleButton baseType2;
	
	private JPanel lotBaseB;
	private JToggleButton baseType3;
	private JToggleButton baseType4;
	
	private JPanel lotPlaneteB;
	private JToggleButton planeteType3;
	private JToggleButton planeteType4;
	
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
		/*------------------------------*/
		/*    Configuration du Panel    */
		/*------------------------------*/
		this.ctrl          = ctrl ;
		this.frameCreation = frameCreation;
		this.nbLigne       = this.ctrl.getPlateau().getNbLignes();
		this.nbColonne     = this.ctrl.getPlateau().getNbColonnes();
		this.nbPlanete     = this.ctrl.getPlateau().getNbPlanetes();
		this.nbEspece      = this.ctrl.getPlateau().getNbEspeces();
		
		this.creationInterfaceEdition();
	}
	
	public PanelEdition(AppliCreation ctrl, FrameCreation frameCreation, File fichier)
	{
		/*------------------------------*/
		/*    Configuration du Panel    */
		/*------------------------------*/
		this.ctrl    = ctrl ;
		this.fichier = fichier;
		this.frameCreation = frameCreation;
		this.nbLigne       = this.ctrl.getPlateau().getNbLignes();
		this.nbColonne     = this.ctrl.getPlateau().getNbColonnes();
		this.nbPlanete     = this.ctrl.getPlateau().getNbPlanetes();
		this.nbEspece      = this.ctrl.getPlateau().getNbEspeces();
	}
	
	public void creationInterfaceEdition()
	{
		this.setLayout( new BorderLayout() );
		
		/*-------------------------------*/
		/*    Création des Composants    */
		/*-------------------------------*/
		
		this.grpBouton = new ButtonGroup();
		this.ensBouton = new ArrayList<JToggleButton>(4);
		
		/* Panel des Planètes */
		// Partie Planete
		this.panelPlanete = new JPanel();
		
		this.lotPlaneteA = new JPanel();
		this.planeteType1 = new JToggleButton( new ImageIcon(this.imagePath + "Planete-G.png") );
		this.planeteType2 = new JToggleButton( new ImageIcon(this.imagePath + "Planete-O.png") );
		
		if ( this.nbPlanete >= 3 )
		{
			this.lotPlaneteB = new JPanel();
			this.lotPlaneteB.setLayout( new GridLayout(1,2) );
			this.planeteType3 = new JToggleButton( new ImageIcon(this.imagePath + "Planete-T.png") );
			
			if ( this.nbPlanete == 4 ) { this.planeteType4 = new JToggleButton( new ImageIcon(this.imagePath + "Planete-V.png") ); }
		}
		// Partie Base
		this.lotBaseA  = new JPanel();
		this.baseType1 = new JToggleButton( new ImageIcon(this.imagePath + "Espece-Chlorophite.png") );
		this.baseType2 = new JToggleButton( new ImageIcon(this.imagePath + "Espece-Felinoid.png") );
		
		if ( this.nbEspece >= 3 )
		{
			this.lotBaseB = new JPanel();
			this.lotBaseB.setLayout( new GridLayout(1,2) );
			this.baseType3 = new JToggleButton( new ImageIcon(this.imagePath + "Espece-Azimae.png") );
			
			if ( this.nbEspece == 4 ) { this.baseType4 = new JToggleButton( new ImageIcon(this.imagePath + "Espece-Silikon.png") ); }
		}
		
		// Panel d'affichage du Plateau
		this.panelPlateau   = new PanelPlateau(this.ctrl);
		JPanel panelCentrer = new JPanel( new GridBagLayout() );
		panelCentrer.setSize( this.panelPlateau.getWidth()+2, this.panelPlateau.getHeight()+2 );
		panelCentrer.setBackground( FrameCreation.COULEUR_FOND );
		panelCentrer.add( this.panelPlateau, new GridBagConstraints() );
		
		this.scrollPlateau = new JScrollPane( panelCentrer );
		
		// Panel des Systèmes
		this.panelSysteme = new JPanel();
		
		this.sectionZone   = new JPanel();
		this.outilZone     = new JToggleButton( "Zone" );
		this.selectionZone = new JLabel( "0", JLabel.CENTER );
		
		/*------------------------------------*/
		/*    Configuration des Composants    */
		/*------------------------------------*/
		
		// Panel des Planètes
		this.panelPlanete.setLayout(new GridLayout(10,2));
		this.panelPlanete.setBackground( FrameCreation.COULEUR_FOND_CLAIR );
		this.panelPlanete.setPreferredSize( new Dimension( 200 , 900 ) );
		
		this.lotPlaneteA.setLayout( new GridLayout(1,2) );
		this.lotBaseA.setLayout( new GridLayout(1,2) );
		
		// Panel d'affichage du Plateau
		this.scrollPlateau.setBackground               ( FrameCreation.COULEUR_FOND               );
		this.scrollPlateau.setVerticalScrollBarPolicy  (JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED  );
		this.scrollPlateau.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		// Panel des Système
		this.panelSysteme.setLayout( new GridLayout(10,1) );
		this.panelSysteme.setBackground( FrameCreation.COULEUR_FOND_CLAIR );
		this.panelSysteme.setPreferredSize( new Dimension( 200 , 900 ) );
		
		this.sectionZone.setLayout( new GridLayout(1,2) );
		
		/*-------------------------------------*/
		/*    Positionnement des Composants    */
		/*-------------------------------------*/
		
			/* Panel Planètes */
		// Partie Planete
		this.lotPlaneteA .add( this.planeteType1 );
		this.ensBouton   .add(this.planeteType1  );
		this.lotPlaneteA .add( this.planeteType2 );
		this.ensBouton   .add(this.planeteType2  );
		this.panelPlanete.add( this.lotPlaneteA  );
		
		if ( this.nbPlanete >= 3 )
		{
			this.lotPlaneteB.add( this.planeteType3 );
			this.ensBouton.add(this.planeteType3);
			if ( this.nbPlanete == 4 )
			{
				this.lotPlaneteB.add( this.planeteType4 );
				this.ensBouton.add(this.planeteType4);
			}
			this.panelPlanete.add( this.lotPlaneteB );
		}
		this.panelPlanete.add( new JLabel("") ); // Séparateur
		// Partie Base
		this.lotBaseA.add( this.baseType1 );
		this.ensBouton.add(this.baseType1);
		this.lotBaseA.add( this.baseType2 );
		this.ensBouton.add(this.baseType2);
		this.panelPlanete.add( this.lotBaseA );
		if ( this.nbEspece >= 3 )
		{
			this.lotBaseB.add( this.baseType3 );
			this.ensBouton.add(this.baseType3);
			if ( this.nbEspece == 4 )
			{
				this.lotBaseB.add( this.baseType4 );
				this.ensBouton.add(this.baseType4);
			}
			this.panelPlanete.add( this.lotBaseB );
		}
		
		this.add( this.panelPlanete, BorderLayout.WEST );
		
		// Panel Plateau
		this.add( this.scrollPlateau, BorderLayout.CENTER );
		this.panelPlateau.repaint();
		
		// Panel Système
		this.sectionZone.add( this.outilZone );
		this.ensBouton.add(this.outilZone);
		this.sectionZone.add( this.selectionZone );
		this.panelSysteme.add( this.sectionZone );
		
		this.add( this.panelSysteme, BorderLayout.EAST );
		
		// Ajouts des Boutons dans le ButtonGroup
		for ( JToggleButton btn : this.ensBouton )
		{
			this.grpBouton.add(btn);
		}
		
		/*---------------------------------*/
		/*    Activation des Composants    */
		/*---------------------------------*/
		
		// Activations des Boutons
		for ( JToggleButton btn : this.ensBouton )
		{
			btn.addActionListener(this);
		}
	}
	
	public void actionPerformed( ActionEvent e )
	{
		
	}
}
