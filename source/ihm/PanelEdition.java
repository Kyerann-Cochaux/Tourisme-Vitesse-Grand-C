package source.ihm ;

import javax.swing.*;

import java.awt.Font ;
import java.awt.Color ;
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
