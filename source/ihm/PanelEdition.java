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
	private int nbForme;
	private int nbEspece;
	
	private File fichier;
	
	private JPanel panelGauche;
	private JPanel panelCentre;
	private JPanel panelDroit;
	
	public PanelEdition(AppliCreation ctrl, int nbLigne, int nbColonne, int nbForme, int nbEspece)
	{
		/*------------------------------*/
		/*    Configuration du Panel    */
		/*------------------------------*/
		this.ctrl      = ctrl ;
		this.nbLigne   = nbLigne;
		this.nbColonne = nbColonne;
		this.nbForme   = nbForme;
		this.nbEspece  = nbEspece;
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
		this.setBackground( FrameJeu.COULEUR_FOND_FONCE );
		
		/*-------------------------------*/
		/*    Création des Composants    */
		/*-------------------------------*/
		
		// Panel Centre
		this.panelCentre = new JPanel();
		
		// Panel Droite
		this.panelDroit = new JPanel();
		this.panelDroit.setLayout(new GridLayout());
		this.panelDroit.setBackground( FrameJeu.COULEUR_FOND_CLAIRE );
		
		// Panel Gauche
		this.panelGauche = new JPanel();
		this.panelGauche.setLayout(new GridLayout());
		this.panelDroit.setBackground( FrameJeu.COULEUR_FOND_CLAIRE );
		
		
		/*------------------------------------*/
		/*    Configuration des Composants    */
		/*------------------------------------*/
		
		
		/*-------------------------------------*/
		/*    Positionnement des Composants    */
		/*-------------------------------------*/
		
		this.add( this.panelGauche, BorderLayout.WEST   );
		this.add( this.panelCentre, BorderLayout.CENTER );
		this.add( this.panelDroit,  BorderLayout.EAST   );
		
		/*---------------------------------*/
		/*    Activation des Composants    */
		/*---------------------------------*/
		
		
	}
}
