package source;

import source.metier.*;
import source.ihm.*;

public class AppliCreation
{
	private Metier        metier;
	private FrameCreation frameCreation;

	public AppliCreation()
	{
		this.metier        = new Metier           ();
		this.frameCreation = new FrameCreation(this);
	}
	
	public void initialiserPlateau(int nbLignes, int nbColonnes, int nbFormes, int nbEspeces)
	{
		this.metier.initialiserPlateau(nbLignes, nbColonnes, nbFormes, nbEspeces);
	}

	/* ---------------------------------- */
	/*             Accesseurs             */
	/* ---------------------------------- */

	public Plateau getPlateau()                         {return this.metier.getPlateau()                                    ;}
	public Planete getPlanete( int indLig, int indCol ) {return this.metier.getPlateau().getCase(indLig,indCol).getPlanete();}

	/* ---------------------------------- */
	/*            Modificateurs           */
	/* ---------------------------------- */

	/* ---------------------------------- */
	/*           Autres méthodes          */
	/* ---------------------------------- */
	
	public void ajouterPlanete( int indLig, int indCol, Planete p ) { this.metier.getPlateau().ajouterPlanete( indLig, indCol,p );}
	
	public static void main(String[] args) 
	{
		new AppliCreation();
	}
}
