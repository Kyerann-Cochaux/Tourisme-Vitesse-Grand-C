package source.metier;

import java.util.ArrayList;
import java.util.List;

public class Plateau
{
	
	private static final int TAILLE_MAX = 30;
	
	public static final String[] TAB_FORMES  = {"Gazeuze","Océan", "Tellurique", "Volcanique" };
	public static final String[] TAB_ESPECES = {"Chlorophite", "Felihoïd", "Azimae", "Silikon"};
	                                               // Marron        BLeu      Rouge     Vert
	
	private Case[][]      ensCasesNeutre;
	private List<Voyage>  lstVoyages;

	private String[] ensEspeces; // Nom des Espèces  utilisées dans le Plateau, entre 2 et 4
	private String[] ensFormes;  // Nom des Planètes utilisées dans le Plateau, entre 2 et 4
	
	private int nbLignes, nbColonnes;
	
	// rajouter une liste de cases dans Plateau, car au début il n'y a pas de Systeme, mais il y a des cases
	
	// Permettre l'ajout des cases dans une Systeme uniquement à partir des cases qui ne sont pas encore affectées dans une Systeme.
	
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
		this.lstVoyages  = new ArrayList<Voyage >();
		
		this.nbLignes   = nbLignes;
		this.nbColonnes = nbColonnes;
		
		this.ensFormes  = new String[nbFormes ];
		this.ensEspeces = new String[nbEspeces];
		
		/* Ces tableaux permettent de savoir quelles valeurs sont utilisés lors d'une partie 
			
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
	/*            Accesseurs              */
	/* ---------------------------------- */
	
	public int getNbLignes  () { return this.nbLignes         ;}
	public int getNbColonnes() { return this.nbColonnes       ;}

	public int getNbEspeces () { return this.ensEspeces.length;}
	public int getNbFormes  () { return this.ensFormes .length;}
	
	public String[] getNomFormes () {return this.ensFormes  ;}
	public String[] getNomEspeces() { return this.ensEspeces;}
	
	public Voyage  getVoyage (int indice) { return this.lstVoyages .get(indice);}
	
	public Case     getCase    (int ligne, int colonne) {return this.ensCasesNeutre[ligne][colonne];}
	public Case[][] getEnsCases()                       {return this.ensCasesNeutre                ;}
	
	public int  getNbVoyages() { return this.lstVoyages .size();}
	
	/* ---------------------------------- */
	/*         Modificateurs              */
	/* ---------------------------------- */
	
	/* ---------------------------------- */
	/*           Autres méthodes          */
	/* ---------------------------------- */

	public boolean ajouterForme(int posX, int posY, Planete j) 
	{
		boolean baseExiste = false;

		if (!coordonneesValide(   posX, posY) ) return false;
		if (!planeteValide    (j, posX, posY) ) return false;

		// Dans le cas où la planète fournie en paramètre est une base, il faut vérifier qu'elle n'est pas 
		// déjà présente sur le plateau.
		
		// On regarde si le jeton en paramètres est une base.
		if (j.estBase() )
		{
			// On parcours les lignes
			for (int lig = 0; lig < this.ensCasesNeutre.length; lig++) 
			{
				// On parcours les colonnes
				for (int col = 0; col < this.ensCasesNeutre[lig].length; col++) 
				{
					if (this.ensCasesNeutre[lig][col].getPlanete().estBase()                             &&
						j.getEspece().equals(this.ensCasesNeutre[lig][col].getPlanete().getEspece() ) )
					
						baseExiste = true;
					
					
				}
				
			}
		}

		if (baseExiste) return false;

		this.ensCasesNeutre[posX][posY].setPlanete(j);

		return true;
	}

	public boolean retirerForme(int posX, int posY)
	{
		if (this.ensCasesNeutre[posX][posY].getPlanete() == null) return false;

		this.ensCasesNeutre[posX][posY].setPlanete(null);

		return true;
	}
	
	public boolean ajouterSysteme()
	{

		return true;

	}

	public String toString()
	{
		String sRet = "";

		for (int lig = 0; lig < this.ensCasesNeutre.length; lig++) 
		{
			for (int col = 0; col < this.ensCasesNeutre[lig].length; col++) 
			{

				if (this.ensCasesNeutre[lig][col].getPlanete() != null)
				{

					Planete p = this.ensCasesNeutre[lig][col].getPlanete();

					sRet += "" + (p.estBase() ? Character.toLowerCase(p.getEspece().charAt(0) ) : ' ') + 
					             this.ensCasesNeutre[lig][col].getPlanete().getSymbole() + ' ';	
				}

				else sRet += " . ";	
			}
	
			sRet += "\n";
			
		}

		return sRet;
	}

	private boolean coordonneesValide(int posX, int  posY)
	{
		return (posX >= 0 && posX < this.ensCasesNeutre      .length ) && 
		       (posY >= 0 && posY < this.ensCasesNeutre[posX].length ); 
	}

	private boolean planeteValide(Planete j, int posX, int posY)
	{
		return j != null && j != this.ensCasesNeutre [posX][posY].getPlanete()  &&
		                         this.ensCasesNeutre [posX][posY].getPlanete() == null ;
	}


}
