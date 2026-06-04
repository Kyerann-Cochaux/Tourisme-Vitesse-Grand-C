package source.ihm ;

import javax.swing.*;

import java.awt.BorderLayout ;

import source.Controleur ;

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
	private Controleur ctrl ;
	private JPanel panelGauche;
	private JPanel panelCentre;
	private JPanel panelDroit;
	
	public PanelEdition(Controleur ctrl)
	{
		/*------------------------------*/
		/*    Configuration du Panel    */
		/*------------------------------*/
		this.ctrl = ctrl ;
		
		this.setLayout( new BorderLayout() );
		this.setBackground(FrameCreation.COULEUR_FOND);
		
		/*-------------------------------*/
		/*    Création des Composants    */
		/*-------------------------------*/
		
		this.panelCentre = new JPanel();

		this.panelDroit = new JPanel();
		this.panelDroit.setLayout(new GridLayout());

		this.panelGauche = new JPanel();
		this.panelGauche.setLayout(new GridLayout());

		
		/*------------------------------------*/
		/*    Configuration des Composants    */
		/*------------------------------------*/
		
		
		/*-------------------------------------*/
		/*    Positionnement des Composants    */
		/*-------------------------------------*/
		
		/*---------------------------------*/
		/*    Activation des Composants    */
		/*---------------------------------*/
		
		
	}
}
