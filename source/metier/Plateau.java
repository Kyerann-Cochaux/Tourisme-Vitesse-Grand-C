package source.metier;

import java.util.ArrayList;
import java.util.List;

public class Plateau
{

	private static final int TAILLE_MAX = 30;

	protected static final String[] TAB_FORMES  = {"Gazeuze","Océan", "Tellurique", "Volcanique" };
	private   static final String[] TAB_ESPECES = {"Chlorophite", "Felihoïd", "Azimae", "Silikon"};
												   // Vert           BLeu       Rouge    Magenta

	private Case[][]      ensCasesNeutre;
	private List<Liaison> lstLiaisons;
	private List<Zone>    lstZones;

	private String[] ensEspeces; // Nom des Espèces  utilisées dans le Plateau, entre 2 et 4
	private String[] ensFormes;  // Nom des Planètes utilisées dans le Plateau, entre 2 et 4

	private int nbLignes, nbColonnes;

	// rajouter une liste de cases dans Plateau, car au début il n'y a pas de zone, mais il y a des cases

	// Permettre l'ajout des cases dans une Zone uniquement à partir des cases qui ne sont pas encore affectées dans une zone.

	public static Plateau creerPlateau(int nbLignes, int nbColonnes, int nbFormes, int nbEspeces)
	{
		if (nbLignes   < 0 || nbLignes   > TAILLE_MAX  ) return null;
		if (nbColonnes < 0 || nbColonnes > TAILLE_MAX  ) return null;
		if (nbFormes   < 2 || nbFormes   > 4           ) return null;
		if (nbEspeces  < 2 || nbEspeces  > 4           ) return null;

		return new Plateau(nbLignes, nbColonnes, nbFormes, nbEspeces);

	}
	
	private Plateau(int nbLignes, int nbColonnes, int nbFormes, int nbEspeces)
	{
		this.lstLiaisons = new ArrayList<Liaison>();
		this.lstZones    = new ArrayList<Zone>   ();

		this.nbLignes   = nbLignes;
		this.nbColonnes = nbColonnes;
		
		this.ensFormes  = new String[nbFormes ];
		this.ensEspeces = new String[nbEspeces];

		/* Ces tableaux permettent de savoir quels valeurs sont utilisés lors d'une partie 

			Si il y a 3 formes, on prends les 3 premières dans le tableau de constantes.
			S'il y a 4 couleurs, on prends les 4 couleurs dans le tableau de constantes.
		*/

		for (int cpt = 0; cpt < this.ensFormes.length; cpt++) 
			this.ensFormes[cpt] = Plateau.TAB_FORMES[cpt];

		for (int cpt = 0; cpt < this.ensEspeces.length; cpt++) 
			this.ensEspeces[cpt] = Plateau.TAB_ESPECES[cpt];

		this.ensCasesNeutre = new Case[this.nbLignes][this.nbColonnes];

		for (int lig = 0; lig < this.nbLignes; lig++) 
			for (int col = 0; col < this.nbColonnes; col++) 
			
				ensCasesNeutre[lig][col] = new Case(lig, col);
	}

	/* ---------------------------------- */
	/*               Getters              */
	/* ---------------------------------- */

	public int getNbLignes  () { return this.nbLignes         ;}
	public int getNbColonnes() { return this.nbColonnes       ;}
	public int getNbEspeces () { return this.ensEspeces.length;}
	public int getNbFormes  () { return this.ensFormes .length;}

	public String[] getNomFormes () {return this.ensFormes  ;}
	public String[] getNomEspeces() { return this.ensEspeces;}
	
	public String  getEspece (int indice) { return this.ensEspeces[indice]     ;}
	public String  getForme  (int indice) { return this.ensFormes [indice]     ;}
	public Liaison getLiaison(int indice) { return this.lstLiaisons.get(indice);}
	public Zone    getZone   (int indice) { return this.lstZones   .get(indice);}

	public int  getNbLiaisons() { return this.lstLiaisons.size();}
	public int  getNbZone    () { return this.lstZones   .size();}

	/* ---------------------------------- */
	/*               Setters              */
	/* ---------------------------------- */

	/* ---------------------------------- */
	/*           Autres méthodes          */
	/* ---------------------------------- */

	public boolean ajouterZone(Zone z)
	{

		boolean estIdentique = false;

		if (z == null)                  return false;
		if (this.lstZones.contains(z) ) return false;

		for (Zone zTemp : this.lstZones) 
			if (zTemp.getEnsCases().equals(z.getEnsCases() ) ) 

				return false;
	

		if (estIdentique) return false;

		this.lstZones.add(z);

		return true;

	}

	public boolean supprimerZone(Zone z) 
	{
		if (!this.lstZones.contains(z) ) return false;
		this.lstZones.remove(z);

		return true;
	}

	public String afficherPlateau()
	{
		String sRet = "";

		for (int lig = 0; lig < this.ensCasesNeutre.length; lig++) 
		{
			for (int col = 0; col < this.ensCasesNeutre[lig].length; col++) 
			{
				sRet += (this.ensCasesNeutre[lig][col].getJeton() != null ) ? this.ensCasesNeutre[lig][col].getJeton().getSymbole() : '.';		
			}

			sRet += "\n";
			
		}

		return sRet;
	}


}
