package srcJeu;

import srcJeu.metier.*;
import srcJeu.metier.plateau.Case;
import srcJeu.metier.plateau.Planete;
import srcJeu.metier.plateau.Voyage;
import srcJeu.ihm.*;

public class AppliJeu
{
	private Metier   metier;
	private FrameJeu frameJeu;

	public AppliJeu()
	{
		this.metier        = new Metier           ();
		this.frameJeu = new FrameJeu(this);
	}

		/* ---------------------------------- */
	/*             Accesseurs             */
	/* ---------------------------------- */
	
	public int getNbLignes      () {return this.metier.getPlateau().getNbLignes  ();}
	public int getNbColonnes    () {return this.metier.getPlateau().getNbColonnes();}
	public int getNbTypePlanetes() {return this.metier.getPlateau().getNbPlanetes();}
	public int getNbTypeEspeces () {return this.metier.getPlateau().getNbEspeces ();}
	public int getNbVoyages     () {return this.metier.getPlateau().getNbVoyages ();}
	public int getNbSysteme     () {return this.metier.getPlateau().getNbSysteme ();}
	
	public String getNomEspece (int indice   ) {return this.metier.getPlateau().getNomEspece (indice);}
	public String getNomPlanete(int indice   ) {return this.metier.getPlateau().getNomPlanete(indice);}
	public Case   getCase      (int x, int y ) {return this.metier.getPlateau().getCase      (x,y)   ;}
	public Voyage getVoyage    (int indice   ) {return this.metier.getPlateau().getVoyage    (indice);}
	
	public Planete getPlanete( int indCol, int indLig ) {return this.metier.getPlateau().getCase(indCol,indLig).getPlanete();}
	

	/* ---------------------------------- */
	/*            Modificateurs           */
	/* ---------------------------------- */

	/* ---------------------------------- */
	/*           Autres méthodes          */
	/* ---------------------------------- */

	public void initialiserPlateau(int nbLignes, int nbColonnes, int nbFormes, int nbEspeces)
	{
		this.metier.initialiserPlateau(nbLignes, nbColonnes, nbFormes, nbEspeces);
	}
	
	public void supprimerEspece( int indCol, int indLig )
	{
		this.getPlanete( indCol, indLig ).setEspece(null);
	}
	
	public void chargerPlateau         (String fichier)       { this.metier.chargerPlateau    (fichier)              ;}

	public static void main(String[] args) 
	{
		new AppliJeu();
	}
}