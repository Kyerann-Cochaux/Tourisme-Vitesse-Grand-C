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
		
		int nbLigne   = this.ctrl.getPlateau().getNbLignes();
		int nbColonne = this.ctrl.getPlateau().getNbColonnes();
		this.dimPlateau = new Dimension( nbColonne * PanelPlateau.TAILLE_CASE + 1, nbLigne * PanelPlateau.TAILLE_CASE + 1 );
		
		this.setSize( this.dimPlateau );
		this.setPreferredSize( this.dimPlateau );
		this.setBackground( FrameJeu.COULEUR_FOND_FONCE );
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D) g;
		
		// Render des Cases
		this.renderCases(g2);
		
		// Render des Liens
		this.renderLiens(g2);
		
		// Render des Planètes
		this.renderPlanete(g2);
	}
	
	private void renderCases( Graphics2D g2 )
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
	
	private void renderLiens( Graphics2D g2 )
	{
		g2.setColor( Color.WHITE );
		
		int nbVoyage = this.ctrl.getPlateau().getNbVoyages();
		
		int milieuCase = TAILLE_CASE / 2 ;
		
		for( int ind=0 ; ind < nbVoyage ; ind++ )
		{
			int departPosX  = this.ctrl.getPlateau().getVoyage(ind).getPlaneteSource().getPosX()      * milieuCase ;
			int departPosY  = this.ctrl.getPlateau().getVoyage(ind).getPlaneteSource().getPosY()      * milieuCase ;
			int arriverPosX = this.ctrl.getPlateau().getVoyage(ind).getPlaneteDestination().getPosX() * milieuCase ;
			int arriverPosY = this.ctrl.getPlateau().getVoyage(ind).getPlaneteDestination().getPosY() * milieuCase ;
			
			g2.drawLine( departPosX, departPosY, arriverPosX, arriverPosY );
		}
	}
	
	private void renderPlanete( Graphics2D g2 )
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
}
