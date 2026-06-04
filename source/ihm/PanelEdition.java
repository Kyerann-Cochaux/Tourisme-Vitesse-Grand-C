package source.ihm;

import javax.swing.*;

import java.io.File;

import java.awt.BorderLayout ;
import java.awt.GridLayout ;

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
	
	private JPanel lotPlaneteB;
	private JLabel planeteType3;
	private JLabel planeteType4;
	
	// Partie Plateau
	private JPanel panelPlateau;
	
	// Partie Espèces
	private JPanel panelEspece;
	
	private JPanel lotEspeceA;
	private JLabel especeType1;
	private JLabel especeType2;
	
	private JPanel lotEspeceB;
	private JLabel especeType3;
	private JLabel especeType4;
	
	public PanelEdition(AppliCreation ctrl, int nbLigne, int nbColonne, int nbPlanete, int nbEspece)
	{
		/*------------------------------*/
		/*    Configuration du Panel    */
		/*------------------------------*/
		this.ctrl      = ctrl ;
		this.nbLigne   = nbLigne;
		this.nbColonne = nbColonne;
		this.nbPlanete = nbPlanete;
		this.nbEspece  = nbEspece;
		
		this.creationInterfaceEdition();
	}
	
	public PanelEdition(AppliCreation ctrl, File fichier)
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
		
		// Panel des Planètes
		this.panelPlanete = new JPanel();
		this.panelPlanete.setLayout(new GridLayout(10,2));
		this.panelPlanete.setBackground( FrameJeu.COULEUR_FOND_CLAIRE );
		
		this.lotPlaneteA = new JPanel();
		this.lotPlaneteA.setLayout( new GridLayout(1,2) );
		
		this.planeteType1 = new JLabel("Gazeuze");
		this.planeteType2 = new JLabel("Océan");
		
		if ( this.nbPlanete >= 3 )
		{
			this.lotPlaneteB = new JPanel();
			this.lotPlaneteB.setLayout( new GridLayout(1,2) );
			this.planeteType3 = new JLabel("Tellurique");
			
			if ( this.nbPlanete == 4 ) { this.planeteType4 = new JLabel("Volcanique"); }
		}
		
		// Panel d'affichage du Plateau
		this.panelPlateau = new JPanel();
		this.panelPlateau.setBackground( FrameJeu.COULEUR_FOND_FONCE );
		
		// Panel des Espèces
		this.panelEspece = new JPanel();
		this.panelEspece.setLayout(new GridLayout(10,1));
		this.panelEspece.setBackground( FrameJeu.COULEUR_FOND_CLAIRE );
		
		this.lotEspeceA = new JPanel();
		this.lotEspeceA.setLayout( new GridLayout(1,2) );
		
		this.especeType1 = new JLabel("Gazeuze");
		this.especeType2 = new JLabel("Océan");
		
		if ( this.nbEspece >= 3 )
		{
			this.lotEspeceB = new JPanel();
			this.lotEspeceB.setLayout( new GridLayout(1,2) );
			this.especeType3 = new JLabel("Tellurique");
			
			if ( this.nbEspece == 4 ) { this.especeType4 = new JLabel("Volcanique"); }
		}
		
		/*------------------------------------*/
		/*    Configuration des Composants    */
		/*------------------------------------*/
		
		
		
		/*-------------------------------------*/
		/*    Positionnement des Composants    */
		/*-------------------------------------*/
		
		// Panel Planètes
		this.lotPlaneteA.add( this.planeteType1 );
		this.lotPlaneteA.add( this.planeteType2 );
		this.panelPlanete.add( this.lotPlaneteA );
		if ( this.nbPlanete >= 3 )
		{
			this.lotPlaneteB.add( this.planeteType3 );
			if ( this.nbPlanete == 4 ) { this.lotPlaneteB.add( this.planeteType4 ); }
			this.panelPlanete.add( this.lotPlaneteB );
		}
		
		this.add( this.panelPlanete, BorderLayout.WEST   );
		
		// Panel Plateau
		this.add( this.panelPlateau, BorderLayout.CENTER );
		
		// Panel Espèces
		this.add( this.panelEspece,  BorderLayout.EAST   );
		
		/*---------------------------------*/
		/*    Activation des Composants    */
		/*---------------------------------*/
		
		
	}
}
