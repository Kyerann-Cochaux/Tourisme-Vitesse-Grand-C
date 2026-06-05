package source.ihm;

import javax.swing.*;

import java.io.File;
import java.awt.Dimension ;
import java.awt.Graphics ;
import java.awt.Graphics2D ;
import java.awt.Color ;

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
	
	private int nbLigne;
	private int nbColonne;
	private int nbPlanete;
	private int nbEspece;
	
	private Dimension dimPlateau ;
	
	private Graphics2D g2;
	
	public PanelPlateau( int nbLigne, int nbColonne, int nbPlanete, int nbEspece )
	{
		// Configuration du Panel
		this.nbLigne   = nbLigne ;
		this.nbColonne = nbColonne ;
		this.nbPlanete = nbPlanete ;
		this.nbEspece  = nbEspece ;
		this.dimPlateau = new Dimension( this.nbColonne * PanelPlateau.TAILLE_CASE + 1, this.nbLigne * PanelPlateau.TAILLE_CASE + 1 );
		
		this.setSize( this.dimPlateau );
		this.setPreferredSize( this.dimPlateau );
		this.setBackground( FrameJeu.COULEUR_FOND_FONCE );
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		boolean casesAfficher = false ;
		// Render des Cases
		this.renderCases(g);
	}
	
	private void renderCases( Graphics g )
	{
		g2 = (Graphics2D) g;
		
		g2.setColor( new Color(194, 231, 242) );
		
		
		for( int cptLig=0 ; cptLig < this.nbLigne ; cptLig++ )
		{
			for( int cptCol=0 ; cptCol < nbColonne ; cptCol++ )
			{
				g2.drawRect( TAILLE_CASE * cptCol, /*  Position X  */
				             TAILLE_CASE * cptLig, /*  Position Y  */
				             TAILLE_CASE,          /* LARGEUR CASE */
				             TAILLE_CASE           /* LONGEUR CASE */
				           );
			}
		}
		
	}
}
