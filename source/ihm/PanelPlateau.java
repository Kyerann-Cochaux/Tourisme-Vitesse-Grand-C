package source.ihm;

import javax.swing.*;
import javax.imageio.ImageIO ;

import java.io.File;
import java.io.IOException ;
import java.awt.Dimension ;
import java.awt.Graphics ;
import java.awt.Graphics2D ;
import java.awt.Color ;
import java.awt.Image ;
import java.awt.image.BufferedImage ;

import source.AppliCreation ;

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
	
	private AppliCreation ctrl ;
	
	private Dimension dimPlateau ;
	
	public PanelPlateau( AppliCreation ctrl )
	{
		// Configuration du Panel
		this.ctrl = ctrl ;
		
		this.dimPlateau = new Dimension( this.ctrl.getPlateau().getNbColonnes() * PanelPlateau.TAILLE_CASE + 1,
		                                 this.ctrl.getPlateau().getNbLignes()   * PanelPlateau.TAILLE_CASE + 1 );
		
		this.setSize( this.dimPlateau );
		this.setPreferredSize( this.dimPlateau );
		this.setBackground( FrameJeu.COULEUR_FOND_FONCE );
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D) g;
		
		// Affichage du Fond
		this.affichageFond(g2);
		
		// Affichage des Cases
		this.affichageCases(g2);
		
		// Affichage des Zones (Non Fonctionnel)
		this.affichageZones(g2);
		
		// Affichage des Liens
		this.affichageLiens(g2);
		
		// Affichage des Planètes
		this.affichagePlanetes(g2);
		
		// Affichage des Départs des Espèces
		this.affichageDepartEspece(g2);
	}
	
	/*----------------------*/
	/* Méthodes d'Affichage */
	/*----------------------*/
	
	private void affichageFond( Graphics2D g2 )
	{
		int nbLigne   = this.ctrl.getPlateau().getNbLignes();
		int nbColonne = this.ctrl.getPlateau().getNbColonnes();
		
		for( int cptLig=0 ; cptLig < nbLigne ; cptLig++ )
		{
			for( int cptCol=0 ; cptCol < nbColonne ; cptCol++ )
			{
				BufferedImage image = null ;
				try
				{
					File inputFile = new File("../source/ihm/images/Tuiles/Fond.png");
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
	
	private void affichageCases( Graphics2D g2 )
	{
		g2.setColor( new Color(194, 231, 242) );
		
		int nbLigne   = this.ctrl.getPlateau().getNbLignes();
		int nbColonne = this.ctrl.getPlateau().getNbColonnes();
		
		for( int cptLig=0 ; cptLig < nbLigne ; cptLig++ )
		{
			for( int cptCol=0 ; cptCol < nbColonne ; cptCol++ )
			{
				g2.drawRect(
				             TAILLE_CASE * cptCol, /*  Position X  */
				             TAILLE_CASE * cptLig, /*  Position Y  */
				             TAILLE_CASE,          /* LARGEUR CASE */
				             TAILLE_CASE           /* LONGEUR CASE */
				           );
			}
		}
	}
	
	private void affichageZones( Graphics2D g2 )
	{
		g2.setColor( Color.RED );
		
		int nbLigne   = this.ctrl.getPlateau().getNbLignes();
		int nbColonne = this.ctrl.getPlateau().getNbColonnes();
		
		// System.out.println( "Taille du Plateau : " + nbLigne + " Lignes et " + nbColonne + " Colonnes" );
		
		for( int cptLig=0 ; cptLig < nbLigne ; cptLig++ )
		{
			for( int cptCol=0 ; cptCol < nbColonne ; cptCol++ )
			{
				// System.out.println( "Vérification de la case à " + cptLig + " Lig " + cptCol + " Col" );
				
				int zoneCaseAct     = this.ctrl.getPlateau().getCase( cptCol, cptLig ).getNumSysteme();
				int zoneCaseADroite = -1;
				int zoneCaseEnBas   = -1;
				
				if ( cptCol+1 < nbColonne-1 )
				{
					zoneCaseADroite = this.ctrl.getPlateau().getCase( cptCol+1, cptLig ).getNumSysteme();
				}
				
				if ( cptLig+1 < nbLigne-1 )
				{
					zoneCaseEnBas   = this.ctrl.getPlateau().getCase( cptCol, cptLig+1 ).getNumSysteme();
				}
				
				if ( (zoneCaseAct != zoneCaseADroite) && (zoneCaseADroite != -1) )
				{
					// Dessiner La ligne sur le côté droit de la case actuelle
					// g2.drawLine(
					             /* Départ X */
					             /* Départ Y */
					             /* Arrivé X */
					             /* Arrivé Y */
					//           );
				}
				
				if ( (zoneCaseAct != zoneCaseEnBas) && (zoneCaseEnBas != -1) )
				{
					// Dessiner La ligne sur le côté bas de la case actuelle
					// g2.drawLine(
					             /* Départ X */
					             /* Départ Y */
					             /* Arrivé X */
					             /* Arrivé Y */
					//            );
				}
			}
		}
	}
	
	private void affichageLiens( Graphics2D g2 )
	{
		g2.setColor( Color.WHITE );
		
		int nbVoyage = this.ctrl.getPlateau().getNbVoyages();
		
		// System.out.println("Nombre de Voyage à render : " + nbVoyage);
		
		int milieuCase = TAILLE_CASE / 2 ;
		
		for( int ind=0 ; ind < nbVoyage ; ind++ )
		{
			int departPosX  = this.ctrl.getPlateau().getVoyage(ind).getPlaneteSource().getPosX()      * milieuCase ;
			int departPosY  = this.ctrl.getPlateau().getVoyage(ind).getPlaneteSource().getPosY()      * milieuCase ;
			int arriverPosX = this.ctrl.getPlateau().getVoyage(ind).getPlaneteDestination().getPosX() * milieuCase ;
			int arriverPosY = this.ctrl.getPlateau().getVoyage(ind).getPlaneteDestination().getPosY() * milieuCase ;
			
			// System.out.println("Render du Voyage " + ind + "  depX:"+ departPosX + "/depY:" + departPosY + " | arrX:" + arriverPosX + "/arrY:" + arriverPosY );
			
			g2.drawLine( departPosX, departPosY, arriverPosX, arriverPosY );
		}
	}
	
	private void affichagePlanetes( Graphics2D g2 )
	{
		int nbLigne   = this.ctrl.getPlateau().getNbLignes();
		int nbColonne = this.ctrl.getPlateau().getNbColonnes();
		
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
						File inputFile = new File("../source/ihm/images/Tuiles/Planete-" + symbPlanete + ".png");
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
		int nbLigne   = this.ctrl.getPlateau().getNbLignes();
		int nbColonne = this.ctrl.getPlateau().getNbColonnes();
		
		for( int cptLig= 0 ; cptLig < nbLigne ; cptLig++ )
		{
			for( int cptCol= 0 ; cptCol < nbColonne ; cptCol++ )
			{
				if ( this.ctrl.getPlanete(cptCol, cptLig) != null )
				{
					if ( this.ctrl.getPlanete(cptCol, cptLig).getEspece() != null  )
					{
						String nomEspece = this.ctrl.getPlanete(cptCol, cptLig).getEspece();
						
						BufferedImage image = null ;
						try
						{
							File inputFile = new File("../source/ihm/images/Tuiles/Espece-" + nomEspece + ".png");
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
}
