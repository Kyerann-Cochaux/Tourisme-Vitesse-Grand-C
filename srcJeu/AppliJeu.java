package srcJeu;

import srcJeu.metier.*;
import srcJeu.metier.plateau.*;
import srcJeu.metier.manches.*;

import java.util.ArrayList;
import java.util.List;

import srcJeu.ihm.*;

public class AppliJeu
{
	private Metier   metier;
	private FrameJeu frameJeu;

	public AppliJeu()
	{
		this.metier   = new Metier  ();
		this.frameJeu = new FrameJeu(this);
	}
	
	/* ---------------------------------- */
	/*             Accesseurs             */
	/* ---------------------------------- */
	
	public int getNumManche     () {return this.metier.getNumManche()              ;}
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
	
	public Planete getPlanete  (int indCol, int indLig) {return this.metier.getPlateau().getCase(indCol,indLig).getPlanete();}
	
	public int getTaillePioche()  {return this.metier.getMancheCourante().getPioche().getTaillePioche();}
	public int getScoreTotal  ()  {return this.metier.getScoreTotal    ()                              ;}
	
	public String getSommet()
	{
		String symbole = "";
		
		// Si il n'y a plus de sommet on renvoie rien.
		if ( this.metier.getSommet() == null ) return null ;
		if ( this.metier.getSommet().getPremium() ) symbole += "Prem-";
		
		return symbole + this.metier.getSommet().getSymbole().charAt(0);
	}

	public boolean sommetPremium() { return this.metier.getSommet().getPremium();}

	// Retourne le nom de l'espèce en croisières dans la manche actuelle
	public String getEspCroisiereCrt() { return this.metier.getMancheCourante().getEspece() ; }

	public ArrayList<String> getEnsEspece()     
	{ 
		ArrayList<String> lstEspece = new ArrayList<String>();

		for (String string : this.metier.getPlateau().getEnsEspeces() ) 
			lstEspece.add(string);
			
		return lstEspece;
	}

	/* ---------------------------------- */
	/*            Modificateurs           */
	/* ---------------------------------- */
	
	/* ---------------------------------- */
	/*           Autres méthodes          */
	/* ---------------------------------- */
	
	public boolean effectuerVoyage(int xDep, int yDep, int xFin, int yFin, String espece)
	{
		return this.metier.effectuerVoyage(xDep, yDep, xFin, yFin, espece);
	}
	
	public boolean decouvrirCarte() { return this.metier.decouvrirCarte() ; }

	public void initialiserPlateau(int nbLignes, int nbColonnes, int nbFormes, int nbEspeces)
	{
		this.metier.initialiserPlateau(nbLignes, nbColonnes, nbFormes, nbEspeces);
	}
	
	public void    supprimerEspece( int indCol, int indLig )    {       this       .getPlanete( indCol, indLig ).setEspece(null);}
	public void    chargerPlateau (String fichier,boolean demo) {       this.metier.chargerPlateau(fichier,demo)                ;}
	public int     calculerScore  (String espece)               {return this.metier.calculerScore (espece )                     ;}
	public boolean estMancheFinie ()                            {return this.metier.getMancheCourante().estMancheFinie()        ;}
	public boolean estExtremite   (int col, int lig)            {return this.metier.estExtremite(col, lig)                      ;}



	public static void main(String[] args) 
	{
		new AppliJeu();
	}
}
