package srcJeu.ihm;

import javax.swing.*;
import javax.imageio.ImageIO ;

import java.io.File;
import java.io.IOException ;

import java.awt.Dimension ;
import java.awt.Graphics ;
import java.awt.Graphics2D ;
import java.awt.Point;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage ;

import srcJeu.AppliJeu ;

/**
 * Panel Plateau
 * 
 * Panel utiliser pour render les différentes couches du Plateau
 * 
 * @author Groupe 5
 * 
 */

public class PanelPlateau extends JPanel
{
	private static final int TAILLE_CASE = 50 ;

	// Coloriage des voyages celons les espèces
	
	private static final int TAILLE_CURSEUR = 1 ; // Plus ce chiffre est grand plus le curseur est petit
	
	private AppliJeu ctrl ;

	private Dimension dimPlateau ;

	private Point posExtremiteSlct ; // X=Col & Y=Lig dans le Plateau côté metier
	
	public PanelPlateau( AppliJeu ctrl )
	{
		// Configuration du Panel
		this.ctrl = ctrl ;
		
		this.dimPlateau = new Dimension( this.ctrl.getNbColonnes() * PanelPlateau.TAILLE_CASE + 1,
		                                 this.ctrl.getNbLignes  () * PanelPlateau.TAILLE_CASE + 1 );
		
		this.setPreferredSize( this.dimPlateau                  );
		this.setBackground   ( FrameJeu.COULEUR_FOND_PLATEAU );
		this.posExtremiteSlct = null ;
		
	}

	 /* ---------------------------------- */
	 /*             Accesseurs             */
	 /* ---------------------------------- */

	public int   getTailleCase      () { return PanelPlateau.TAILLE_CASE ; }
	public Point getPosExtremiteSlct() { return this.posExtremiteSlct    ; }

	/* ---------------------------------- */
	/*            Modificateurs           */
	/* ---------------------------------- */

	public void setExtremiteSlct( Point posClk ) { this.posExtremiteSlct = posClk ;}
	
	// Autres Méthodes
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D) g;
		
		// Affichage des Cases
		// |!| NE PAS UTILISER DANS LA VERSION FINAL  |!|
		// this.affichageCases(g2);
		
		// Affichage des Zones
		this.affichageZones(g2);

		// Affichage des Zones
		//this.affichageNumeroZone(g2);
		
		// Affichage des Liens
		this.affichageLiens(g2);
		
		// Affichage de l'Extremité Selectionner
		if ( this.posExtremiteSlct != null ) { this.affichageExtremiteSelectionnee( g2 ); }
		
		// Affichage des Planètes
		this.affichagePlanetes(g2);
		
		// Affichage des Départs des Espèces
		this.affichageDepartEspece(g2);
	}
	
	/*-----------------------*/
	/* Méthodes d'Affichages */
	/*-----------------------*/
	
	// |!| NE PAS UTILISER DANS LA VERSION FINAL  |!|
	/*private void affichageCases( Graphics2D g2 )
	{
		g2.setColor( new Color(194, 231, 242) );
		
		int nbLigne   = this.ctrl.getNbLignes  ();
		int nbColonne = this.ctrl.getNbColonnes();
		
		for( int cptLig=0 ; cptLig < nbLigne ; cptLig++ )
		{
			for( int cptCol=0 ; cptCol < nbColonne ; cptCol++ )
			{
				g2.drawRect(
				             TAILLE_CASE * cptCol, /*  Position X  */
				            /*  TAILLE_CASE * cptLig, /*  Position Y  */
				            /*  TAILLE_CASE,          /* LARGEUR CASE */
				             /*TAILLE_CASE           /* LONGEUR CASE *//*
				           );
			}
		}
	} */
	
	private void affichageZones( Graphics2D g2 )
	{
		g2.setStroke(new BasicStroke(3) );
		
		int nbLigne   = this.ctrl.getNbLignes  ();
		int nbColonne = this.ctrl.getNbColonnes();
		
		for( int cptLig=0 ; cptLig < nbLigne ; cptLig++ )
		{
			for( int cptCol=0 ; cptCol < nbColonne ; cptCol++ )
			{
				int zoneCaseAct = this.ctrl.getCase( cptCol, cptLig ).getNumSysteme();
				
				g2.setColor( Color.RED );
				
				int zoneCaseADroite = -1;
				int zoneCaseEnBas   = -1;
				
				if ( cptCol+1 < nbColonne )
				{
					zoneCaseADroite = this.ctrl.getCase( cptCol+1, cptLig ).getNumSysteme();
				}
				
				if ( cptLig+1 < nbLigne )
				{
					zoneCaseEnBas   = this.ctrl.getCase( cptCol, cptLig+1 ).getNumSysteme();
				}
				
				//Dessiner La ligne sur le côté droit de la case actuelle
				if ( zoneCaseAct != zoneCaseADroite && cptCol+1 < nbColonne )
				{	
					g2.drawLine(
						cptCol * PanelPlateau.TAILLE_CASE + PanelPlateau.TAILLE_CASE, /* Départ X */
						cptLig * PanelPlateau.TAILLE_CASE,                            /* Départ Y */
						cptCol * PanelPlateau.TAILLE_CASE + PanelPlateau.TAILLE_CASE, /* Arrivé X */
						cptLig * PanelPlateau.TAILLE_CASE + PanelPlateau.TAILLE_CASE  /* Arrivé Y */
					);

				}
				
				// Dessiner La ligne sur le côté bas de la case actuelle
				if ( zoneCaseAct != zoneCaseEnBas && cptLig+1 < nbLigne )
				{	
					g2.drawLine(
						cptCol * PanelPlateau.TAILLE_CASE,                            /* Départ X */
						cptLig * PanelPlateau.TAILLE_CASE + PanelPlateau.TAILLE_CASE, /* Départ Y */
						cptCol * PanelPlateau.TAILLE_CASE + PanelPlateau.TAILLE_CASE, /* Arrivé X */
						cptLig * PanelPlateau.TAILLE_CASE + PanelPlateau.TAILLE_CASE  /* Arrivé Y */
					);
				}
			}
		}
	}
	
	private void affichageLiens( Graphics2D g2 )
	{
		int nbVoyage = this.ctrl.getNbVoyages();

		if (nbVoyage >= 1)
		{
			for( int ind = 0 ; ind < nbVoyage ; ind++ )
			{
				int departPosX  = this.ctrl.getVoyage(ind).getPlaneteSource     ().getPosX() * TAILLE_CASE + TAILLE_CASE / 2 ;
				int departPosY  = this.ctrl.getVoyage(ind).getPlaneteSource     ().getPosY() * TAILLE_CASE + TAILLE_CASE / 2 ;
				int arriverPosX = this.ctrl.getVoyage(ind).getPlaneteDestination().getPosX() * TAILLE_CASE + TAILLE_CASE / 2 ;
				int arriverPosY = this.ctrl.getVoyage(ind).getPlaneteDestination().getPosY() * TAILLE_CASE + TAILLE_CASE / 2 ;
				
				String especeVoyAct = this.ctrl.getVoyage(ind).getEspece();
	
				for ( int cpt=0 ; cpt < this.ctrl.getNbTypeEspeces() ; cpt++ )
				{
					if ( especeVoyAct == this.ctrl.getNomEspece(cpt) ) 
					{ 
						g2.setColor( FrameJeu.TAB_COUL_LIENS[cpt] ); g2.setStroke( new BasicStroke(4) ); 
					}
				}
				
				if ( especeVoyAct == null ) { g2.setColor( Color.WHITE ); g2.setStroke( new BasicStroke(2) ); }
			
				g2.drawLine( departPosX, departPosY, arriverPosX, arriverPosY );
			}
		}
	}
	
	// Pas utiliser pour le Jeu
	
	private void affichageNumeroZone( Graphics2D g2 )
	{
		g2.setColor( Color.YELLOW );
		g2.setFont(new Font("default", Font.BOLD, 14));
		
		int nbLigne   = this.ctrl.getNbLignes  ();
		int nbColonne = this.ctrl.getNbColonnes();
		
		for( int cptLig=0 ; cptLig < nbLigne ; cptLig++ )
		{
			for( int cptCol=0 ; cptCol < nbColonne ; cptCol++ )
			{
				int zoneCaseAct = this.ctrl.getCase( cptCol, cptLig ).getNumSysteme();
				
				// Dessin du Numéro de la Zone dans la case
				g2.drawString( String.format("%3d", zoneCaseAct),
				               cptCol * PanelPlateau.TAILLE_CASE + PanelPlateau.TAILLE_CASE - 25,
				               cptLig * PanelPlateau.TAILLE_CASE + 15
				             );
			}
		}
	}
	
	
	private void affichageExtremiteSelectionnee( Graphics2D g2 )
	{
		g2.setColor( new Color( 255, 0, 255 ) );
		int posExSelcX = (int) this.posExtremiteSlct.getX() * TAILLE_CASE + TAILLE_CASE / 2 - TAILLE_CASE / ( 1 * 2 ) ;
		int posExSelcY = (int) this.posExtremiteSlct.getY() * TAILLE_CASE + TAILLE_CASE / 2 - TAILLE_CASE / ( 1 * 2 ) ;
		
		g2.fillOval( posExSelcX,
		             posExSelcY,
		             (int) TAILLE_CASE,
		             (int) TAILLE_CASE
		           );
	}
	
	private void affichagePlanetes( Graphics2D g2 )
	{
		int nbLigne   = this.ctrl.getNbLignes();
		int nbColonne = this.ctrl.getNbColonnes();
		
		for( int cptLig= 0 ; cptLig < nbLigne ; cptLig++ )
		{
			for( int cptCol= 0 ; cptCol < nbColonne ; cptCol++ )
			{
				if ( this.ctrl.getPlanete(cptCol, cptLig) != null )
				{
					char symbPlanete = this.ctrl.getPlanete(cptCol, cptLig).getSymbole() ;
					
					BufferedImage image = null ;
					try
					{
						File inputFile = new File("../images/Tuiles/Planete-" + symbPlanete + ".png");
						image = ImageIO.read(inputFile);
					}
					catch (IOException e){}
					
					if ( image != null )
					{
						g2.drawImage(
						              image,                /* L'image à afficher */
						              null,                 /* Traitement d'Image (Innutile ici) */
						              TAILLE_CASE * cptCol, /* Position X */
						              TAILLE_CASE * cptLig  /* Position Y */
						            );
					}
				}
			}
		}
	}
	
	private void affichageDepartEspece( Graphics2D g2 )
	{
		int nbLigne   = this.ctrl.getNbLignes  ();
		int nbColonne = this.ctrl.getNbColonnes();
		
		for( int cptLig= 0 ; cptLig < nbLigne ; cptLig++ )
		{
			for( int cptCol= 0 ; cptCol < nbColonne ; cptCol++ )
			{
				if ( this.ctrl.getPlanete(cptCol, cptLig) != null && this.ctrl.getPlanete(cptCol, cptLig).getEspece() != null  )
				{
					String nomEspece = this.ctrl.getPlanete(cptCol, cptLig).getEspece();
					
					BufferedImage image = null ;
					try
					{
						File inputFile = new File("../images/Tuiles/Espece-" + nomEspece + ".png");
						image = ImageIO.read(inputFile);
					}
					catch (IOException e){}
					
					if ( image != null )
					{
						g2.drawImage(
						              image,                /* L'image à afficher */
						              null,                 /* Traitement d'Image (Innutile ici) */
						              TAILLE_CASE * cptCol, /* Position X */
						              TAILLE_CASE * cptLig  /* Position Y */
						            );
					}
				}
			}
		}
	}
}
