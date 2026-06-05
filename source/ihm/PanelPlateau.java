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
	
	private int nbLigne;
	private int nbColonne;
	private int nbPlanete;
	private int nbEspece;
	
	private boolean casesAfficher;
	
	private Dimension dimPlateau ;
	
	private Graphics2D g2;
	
	public PanelPlateau( AppliCreation ctrl )
	{
		// Configuration du Panel
		this.ctrl = ctrl ;
		
		this.nbLigne   = this.ctrl.getPlateau().getNbLignes();
		this.nbColonne = this.ctrl.getPlateau().getNbColonnes();
		this.nbPlanete = this.ctrl.getPlateau().getNbPlanetes();
		this.nbEspece  = this.ctrl.getPlateau().getNbEspeces();
		this.dimPlateau = new Dimension( this.nbColonne * PanelPlateau.TAILLE_CASE + 1, this.nbLigne * PanelPlateau.TAILLE_CASE + 1 );
		
		// Configuration des états de Render
		casesAfficher = false ;
		
		this.setSize( this.dimPlateau );
		this.setPreferredSize( this.dimPlateau );
		this.setBackground( FrameJeu.COULEUR_FOND_FONCE );
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		// Render des Cases
			this.renderCases(g);
		
		// Render des Planètes
		this.renderPlanete(g);
	}
	
	private void renderCases( Graphics g )
	{
		g2 = (Graphics2D) g;
		
		g2.setColor( new Color(194, 231, 242) );
		
		
		for( int cptLig=0 ; cptLig < this.nbLigne ; cptLig++ )
		{
			for( int cptCol=0 ; cptCol < this.nbColonne ; cptCol++ )
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
	
	private void renderPlanete( Graphics g )
	{
		g2 = (Graphics2D) g;
		
		for( int cptLig=0 ; cptLig < this.nbLigne ; cptLig++ )
		{
			for( int cptCol=0 ; cptCol < this.nbColonne ; cptCol++ )
			{
				if ( this.ctrl.getPlanete(cptCol, cptLig) != null )
				{
					char symbPlanete = this.ctrl.getSymbolePlanete(cptLig, cptCol) ;
					
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
								      image,
								      null,
								      TAILLE_CASE * cptCol,
								      TAILLE_CASE * cptLig
								    );
					}
				}
			}
		}
	}
}
