package source.metier;

import java.util.ArrayList;
import java.util.List;

public class Plateau
{

	private static final int TAILLE_MAX = 30;
	private static final int NB_TYPE_MAX = 4;

	protected static final String[] TAB_FORMES  = {"Gazeuze","Océan", "Tellurique", "Volcanique" };
	
	private static final String[] TAB_ESPECES = {"Chlorophite", "Felihoïd", "Azimae", "Silikon"};
												 // Vert           BLeu       Rouge    Magenta

	private List<Jeton>   lstJetons;
	private List<Liaison> lstLiaisons;

	private int nbLignes, nbColonnes;

	private String[] ensEspeces; // Nom des Espèces  utilisées dans le Plateau, entre 2 et 4
	private String[] ensFormes;  // Nom des Planètes utilisées dans le Plateau, entre 2 et 4

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
		this.lstJetons   = new ArrayList<Jeton  >();
		this.lstLiaisons = new ArrayList<Liaison>();

		this.nbLignes   = nbLignes;
		this.nbColonnes = nbColonnes;
		
		this.ensFormes  = new String[nbFormes ];
		this.ensEspeces = new String[nbEspeces];

		for (int cpt = 0; cpt < this.ensFormes.length; cpt++) 
			this.ensFormes[cpt] = Plateau.TAB_FORMES[cpt];

		for (int cpt = 0; cpt < this.ensEspeces.length; cpt++) 
			this.ensEspeces[cpt] = Plateau.TAB_ESPECES[cpt];

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

	public Jeton getJeton   (int indice) { return this.lstJetons.get(indice);}
	public int   getNbJeton ()           { return this.lstJetons.size()     ;}
	public void retirerJeton(Jeton j)    { this.lstJetons       .remove(j)  ;}

	public Liaison getLiaison  (int indice) { return this.lstLiaisons.get(indice);}
	public int     getNbLiaison()           { return this.lstLiaisons.size()     ;}





}
