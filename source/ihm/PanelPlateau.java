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
		
		this.dimPlateau = new Dimension( this.ctrl.getNbColonnes() * PanelPlateau.TAILLE_CASE + 1,
		                                 this.ctrl.getNbLignes  () * PanelPlateau.TAILLE_CASE + 1 );
		
		this.setPreferredSize( this.dimPlateau                  );
		this.setBackground   ( FrameCreation.COULEUR_FOND_FONCE );
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
		BufferedImage image = null ;

		int nbLigne   = this.ctrl.getNbLignes  ();
		int nbColonne = this.ctrl.getNbColonnes();

		for( int cptLig=0 ; cptLig < nbLigne ; cptLig++ )
		{
			for( int cptCol=0 ; cptCol < nbColonne ; cptCol++ )
			{
				
				try
				{
					File inputFile = new File("../source/ihm/images/Tuiles/Fond.png");
					image = ImageIO.read(inputFile);

					if ( image != null )
					{
						g2.drawImage( image               , /* L'image à afficher */
						              null                , /* Traitement d'Image (Innutile ici) */
						              TAILLE_CASE * cptCol, /* Position X */
						              TAILLE_CASE * cptLig  /* Position Y */
						            );
					}
				}

				catch (IOException e)
				{
					System.out.println(e);
				}
				
				
			}
		}
	}
	
	private void affichageCases( Graphics2D g2 )
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
		
		int nbLigne   = this.ctrl.getNbLignes();
		int nbColonne = this.ctrl.getNbColonnes();
		
		// System.out.println( "Taille du Plateau : " + nbLigne + " Lignes et " + nbColonne + " Colonnes" );
		
		for( int cptLig=0 ; cptLig < nbLigne ; cptLig++ )
		{
			for( int cptCol=0 ; cptCol < nbColonne ; cptCol++ )
			{
				// System.out.println( "Vérification de la case à " + cptLig + " Lig " + cptCol + " Col" );
				
				int zoneCaseAct     = this.ctrl.getCase( cptCol, cptLig ).getNumSysteme();
				int zoneCaseADroite = -1;
				int zoneCaseEnBas   = -1;
				
				if ( cptCol+1 < nbColonne-1 )
				{
					zoneCaseADroite = this.ctrl.getCase( cptCol+1, cptLig ).getNumSysteme();
				}
				
				if ( cptLig+1 < nbLigne-1 )
				{
					zoneCaseEnBas   = this.ctrl.getCase( cptCol, cptLig+1 ).getNumSysteme();
				}
				
				if ( (zoneCaseAct != zoneCaseADroite) && (zoneCaseADroite != -1) )
				{
					// System.out.println( "\tLimite Trouvé ! La Case [" + cptLig + "/" + cptCol + "|Z:" + zoneCaseAct + "] a une Limite à sa droite." );
					
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
					// System.out.println( "\tLimite Trouvé ! La Case [" + cptLig + "/" + cptCol + "|Z:" + zoneCaseAct + "] a une Limite en bas." );
					
					// Dessiner La ligne sur le côté bas de la case actuelle
					//g2.drawLine(
					               /* Départ X */
					               /* Départ Y */
					               /* Arrivé X */
					               /* Arrivé Y */
					//           );
				}
			}
		}
	}
	
	private void affichageLiens( Graphics2D g2 )
	{
		g2.setColor( Color.WHITE );
		
		int nbVoyage = this.ctrl.getNbVoyages();
		
		// System.out.println("Nombre de Voyage à render : " + nbVoyage);
		
		for( int ind=0 ; ind < nbVoyage ; ind++ )
		{
			int departPosX  = this.ctrl.getVoyage(ind).getPlaneteSource     ().getPosX() * TAILLE_CASE / 2 ;
			int departPosY  = this.ctrl.getVoyage(ind).getPlaneteSource     ().getPosY() * TAILLE_CASE / 2 ;
			int arriverPosX = this.ctrl.getVoyage(ind).getPlaneteDestination().getPosX() * TAILLE_CASE / 2 ;
			int arriverPosY = this.ctrl.getVoyage(ind).getPlaneteDestination().getPosY() * TAILLE_CASE / 2 ;
			
			// System.out.println("Render du Voyage " + ind + "  depX:"+ departPosX + "/depY:" + departPosY + " | arrX:" + arriverPosX + "/arrY:" + arriverPosY );
			
			g2.drawLine( departPosX, departPosY, arriverPosX, arriverPosY );
		}
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
		int nbLigne   = this.ctrl.getNbLignes  ();
		int nbColonne = this.ctrl.getNbColonnes();
		
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
