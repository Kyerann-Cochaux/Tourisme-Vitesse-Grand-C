package srcJeu.ihm;

import srcJeu.AppliJeu;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

public class PanelJeu extends JPanel
{

	private AppliJeu ctrl;
	private FrameJeu frameJeu;
	
	private JButton[] ensBtnCartes;
	private JLabel [] ensLblEspeces; // À changer en JLabel


	private PanelPlateau panelPlateau;
	private JScrollPane  spPlateau;

	private JLabel lblTexteEspece;

	public PanelJeu(AppliJeu ctrl, FrameJeu frameJeu)
	{
		/* -------- Panels principaux ------- */

		JPanel panelCentre;
		JPanel panelPioche;
		JPanel panelScore;

		JPanel panelScoreEspeces;
		JPanel panelCartesStandards;
		JPanel panelCartesPremium;

		JPanel panelScoreLabels;

		ButtonGroup btgEspeces;

		this.ctrl     = ctrl;
		this.frameJeu = frameJeu;

		this.setLayout(new BorderLayout() );

		/* ---------------------------------- */
		/*       Création des composants      */
		/* ---------------------------------- */

		this.panelPlateau = new PanelPlateau(ctrl);
		panelCentre       = new JPanel(new GridBagLayout() );
		panelScore        = new JPanel(new GridLayout(3,1) );
		panelPioche       = new JPanel(new GridLayout(3,1) );

		panelScoreEspeces    = new JPanel(new GridLayout(6,1) );
		panelCartesStandards = new JPanel(new GridLayout(1,3) );
		panelCartesPremium   = new JPanel(new GridLayout(1,3) );

		panelScoreLabels   = new JPanel(new GridLayout(2,1) );

		this.ensBtnCartes = new JButton     [10];
		btgEspeces        = new ButtonGroup();

		/*for (int cpt = 0; cpt < ensRbEspeces.length; cpt++) 
		{
			this.ensRbEspeces[cpt] = new JRadioButton(this.ctrl.getNomEspece(cpt) );
			this.ensRbEspeces[cpt].setEnabled(false);
			this.ensRbEspeces[cpt].setOpaque(false         );
			btgEspeces            .add      (this.ensRbEspeces[cpt] );
		}*/
	
		this.spPlateau = new JScrollPane(panelCentre);

		for (int cpt = 0; cpt < this.ensBtnCartes.length; cpt++) 
		{
			this.ensBtnCartes[cpt] = new JButton(new ImageIcon("../images/Cartes/Carte-G.png") );
			this.ensBtnCartes[cpt].setEnabled(false);
		}

		this.lblTexteEspece = new JLabel
		(
			"<html>"+
				"<body> "+
					"<h1 style='text-align : center;'>"+ 
						"Croisière des <br> " + this.ctrl.getNomEspece(0) + 
					"</h1>"+
				" </body> "
			+"</html>", SwingConstants.CENTER

		);
		
		/* ---------------------------------- */
		/*    Configuration des composants    */
		/* ---------------------------------- */

		panelScore       .setBackground(FrameJeu.COULEUR_FOND_CLAIRE);
		panelScoreEspeces.setBackground(FrameJeu.COULEUR_FOND_CLAIRE);
		panelScoreLabels .setBackground(FrameJeu.COULEUR_FOND_CLAIRE);


		this.spPlateau.setBackground               (FrameJeu.COULEUR_FOND_FONCE               );
		this.spPlateau.setVerticalScrollBarPolicy  (JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED  );
		this.spPlateau.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		this.lblTexteEspece.setFont      (FrameJeu.POLICE_TEXTE);
		this.lblTexteEspece.setForeground(FrameJeu.COULEUR_ZONE);
		this.lblTexteEspece.setOpaque    (false       );

		/* ---------------------------------- */
		/*    Positionnement des composants   */
		/* ---------------------------------- */

		/* -------- Panels principaux ------- */

		panelScoreLabels.add(this.lblTexteEspece);
		//panelScoreLabels.add(new JLabel(new ImageIcon("../images/Tuiles/Espece-" + this.ctrl.getNomEspece(2)  + "-XL.png")  ) );

		//PanelScoreEspeces
		/*for (int cpt = 0; cpt < this.ensRbEspeces.length; cpt++) 
			panelScoreEspeces.add(this.ensRbEspeces[cpt] );*/

		for (int cpt = 0; cpt < this.ctrl.getNbTypeEspeces(); cpt++) 
		{
			JLabel lbl = new JLabel(this.ctrl.getNomEspece(cpt), new ImageIcon("../images/Tuiles/Centre-Espece-" + this.ctrl.getNomEspece(cpt) + ".png" ), SwingConstants.CENTER ) ;
			lbl.setFont      (FrameJeu.POLICE_TEXTE);
			lbl.setForeground(FrameJeu.COULEUR_ZONE);
			lbl.setOpaque    (false       );

			panelScoreEspeces.add(lbl);
			
		}

		panelCentre.add( this.panelPlateau, new GridBagConstraints() );

		// panelCartes
		for (int cpt = 0; cpt < this.ensBtnCartes.length ; cpt++) 
		{	
			if (cpt < 5) panelCartesStandards.add(this.ensBtnCartes[cpt] );
			else         panelCartesPremium  .add(this.ensBtnCartes[cpt] );
		}
		
		panelScore.add(panelScoreLabels );
		panelScore.add(panelScoreEspeces);

		this.add(panelScore    , BorderLayout.WEST  );
		this.add(this.spPlateau, BorderLayout.CENTER);
		this.add(panelPioche   , BorderLayout.EAST  );



		/* ---------------------------------- */
		/*      Activation des composants     */
		/* ---------------------------------- */



	}
}