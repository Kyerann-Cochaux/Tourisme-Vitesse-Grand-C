package srcEdition;

import srcEdition.metier.*;
import srcEdition.ihm.*;

public class AppliCreation
{
	private Metier        metier;
	private FrameCreation frameCreation;

	public AppliCreation()
	{
		this.metier        = new Metier           ();
		this.frameCreation = new FrameCreation(this);
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
	
	public void ajouterPlanete( int indCol, int indLig, String nomPlanete )
	{
		this.metier.getPlateau().ajouterPlanete(indCol, indLig, Planete.creerPlanete( nomPlanete.charAt(0) ) );
	}
	
	public void supprimerPlanete( int indCol, int indLig )
	{
		this.metier.getPlateau().retirerPlanete( indCol, indLig );
	}
	
	public void ajouterEspece( int indLig, int indCol, String nomEspece )
	{
		this.metier.getPlateau().setEspece( this.getPlanete( indLig, indCol ), nomEspece);
	}
	
	public void supprimerEspece( int indCol, int indLig )
	{
		this.getPlanete( indCol, indLig ).setEspece(null);
	}
	
	public void ajouterSysteme( int numSysteme, int indCol, int indLig )
	{
		this.metier.getPlateau().setNumSysteme( numSysteme, indCol, indLig );
	}
	
	public void chargerPlateau         (String fichier)       { this.metier.chargerPlateau    (fichier)              ;}
	public void sauvegarderPlateau     (String nomSauvegarde) { this.metier.sauvegarderPlateau(nomSauvegarde, true)  ;}
	public void sauvegarderCopiePlateau(String nomSauvegarde) { this.metier.sauvegarderPlateau(nomSauvegarde,false)  ;}
	
	public static void main(String[] args) 
	{
		new AppliCreation();
	}
}
