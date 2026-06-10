package srcJeu.metier.plateau;

import java.util.ArrayList;
import java.util.List;

import srcJeu.metier.Metier;

public class Plateau
{
	
	private static final int TAILLE_MAX = 30;
	
	private Case[][] ensCases;
	private String[] ensEspeces;   // Nom des Espèces  utilisées dans le Plateau, entre 2 et 4
	private String[] ensPlanetes ; // Nom des Planètes utilisées dans le Plateau, entre 2 et 4

	private List<Voyage>  lstVoyages;
	
	private int nbLignes, nbColonnes;
	private int nbSysteme;
	
	// rajouter une liste de cases dans Plateau, car au début il n'y a pas de Systeme, mais il y a des cases
	
	// Permettre l'ajout des cases dans une Systeme uniquement à partir des cases qui ne sont pas encore affectées dans une Systeme.
	
	public static Plateau creerPlateau(int nbLignes, int nbColonnes, int nbPlanetes, int nbEspeces)
	{
		if (nbLignes   < 5 || nbLignes   > TAILLE_MAX  ) nbLignes   = 5;
		if (nbColonnes < 5 || nbColonnes > TAILLE_MAX  ) nbColonnes = 5;
		if (nbPlanetes < 2 || nbPlanetes > 4           ) nbPlanetes = 2;
		if (nbEspeces  < 2 || nbEspeces  > 4           ) nbEspeces  = 2;
		
		return new Plateau(nbLignes, nbColonnes, nbPlanetes, nbEspeces);
		
	}
	
	private Plateau(int nbLignes, int nbColonnes, int nbPlanetes, int nbEspeces)
	{
		this.lstVoyages     = new ArrayList<Voyage >();
		
		this.nbLignes   = nbLignes;
		this.nbColonnes = nbColonnes;
		this.nbSysteme  = 0;
		
		this.ensPlanetes = new String[nbPlanetes];
		this.ensEspeces  = new String[nbEspeces ];
		
		/* Ces tableaux permettent de savoir quelles valeurs sont utilisées lors d'une partie 
			
			Si il y a 3 Planetes, on prends les 3 premières dans le tableau de constantes.
			S'il y a 4 couleurs, on prends les 4 couleurs dans le tableau de constantes.
		*/
		
		for (int cpt = 0; cpt < this.ensPlanetes.length; cpt++)
			this.ensPlanetes[cpt] = Metier.TAB_PLANETES[cpt];
		
		for (int cpt = 0; cpt < this.ensEspeces.length; cpt++)
			this.ensEspeces[cpt] = Metier.TAB_ESPECES[cpt];
		
		this.ensCases = new Case[this.nbLignes][this.nbColonnes];
		
		for (int lig = 0; lig < this.nbLignes; lig++)
			for (int col = 0; col < this.nbColonnes; col++)
				ensCases[lig][col] = new Case(col, lig);
	}
	
	/* ---------------------------------- */
	/*            Accesseurs              */
	/* ---------------------------------- */
	
	public String getNomEspece (int indice) { return this.ensEspeces [indice]; }
	public String getNomPlanete(int indice) { return this.ensPlanetes[indice]; }
	
	public int getNbLignes  () { return this.nbLignes;   }
	public int getNbColonnes() { return this.nbColonnes; }
	public int getNbSysteme () { return this.nbSysteme;  }
	
	public int getNbEspeces  () { return this.ensEspeces .length; } // retourne le nombre d'especes  différentes
	public int getNbPlanetes () { return this.ensPlanetes.length; } // retourne le nombre de planete différentes
	
	public String[] getEnsPlanetes() { return this.ensPlanetes; }
	public String[] getEnsEspeces () { return this.ensEspeces ; }
	
	public Voyage getVoyage   (int indice) { return this.lstVoyages .get(indice); }
	public int    getNbVoyages()           { return this.lstVoyages.size()      ; }
	
	public Case     getCase    (int x, int y) { return this.ensCases[y][x]; }
	public Case[][] getEnsCases()             { return this.ensCases      ; }
	
	
	
	/* ---------------------------------- */
	/*         Modificateurs              */
	/* ---------------------------------- */

	/*
	 Cette méthode vérifie s'il est possible d'associer la case fournie en paramètre avec le numéro du système 
	 Si le numéro du système existe déjà et qu'aucune Case portant ce dit numéro n'est à proximité de la case en paramètre,
	 on ne peut pas attribuer ce numéro du système à la case en paramètre.
	 
	*/
	
	public boolean setNumSysteme(int numSysteme, int x, int y)
	{
		if ( x < 0 || x >= this.nbColonnes ) return false;
		if ( y < 0 || y >= this.nbLignes   ) return false;
		if ( numSysteme >  this.nbSysteme  ) return false;
		
		this.ensCases[y][x].setNumSysteme(numSysteme);
		if ( numSysteme >= this.nbSysteme ) this.nbSysteme = numSysteme + 1 ;
		return true;
		
	}
	
	/* ---------------------------------- */
	/*           Autres méthodes          */
	/* ---------------------------------- */
	
	public void actualiserVoyages()
	{
		this.lstVoyages = new ArrayList<Voyage>();
		
		int[][] dDir = 
		{//	 dx  dy
			{ 0, -1},
			{+1, -1},
			{+1,  0},
			{+1, +1}
		};
		
		// parcours de toutes les cases
		for (int lig = 0; lig < this.nbLignes; lig++)
		{
			for (int col = 0; col < this.nbColonnes; col++)
			{
				Case caseDep = this.ensCases[lig][col];
					
				if (!caseDep.estVide())
				{
					
					// parcours dans 4 directions
					// jusqu'as tomber sur 
					// soit une planete, soit le bord
					for (int indDeltaDir = 0; indDeltaDir < dDir.length; indDeltaDir++)
					{
						int nX = col + dDir[indDeltaDir][0];
						int nY = lig + dDir[indDeltaDir][1];
						
						if ( nX >= 0 && nX < this.nbColonnes &&
						     nY >= 0 && nY < this.nbLignes      )
						{
							
							Case caseDest = this.ensCases[nY][nX];
							
							while ( nX >= 0 && nX < this.nbColonnes &&
							        nY >= 0 && nY < this.nbLignes   &&
							        caseDest.estVide()                 )
							{
								caseDest = this.ensCases[nY][nX];
								
								nX += dDir[indDeltaDir][0];
								nY += dDir[indDeltaDir][1];
							}
							
							if ( caseDest != null && !caseDest.estVide() )
							{
								this.lstVoyages.add(Voyage.creerVoyage(caseDep, caseDest));
							}
						}
						
					}
					
				}
			}
		}
	}

	public void viderPlateau()
	{
		for (int lig = 0; lig < this.ensCases.length; lig++) 
			for (int col = 0; col < this.ensCases[lig].length; col++) 
			
				this.ensCases[lig][col].setPlanete(null);

	}
	
	// deveras surment etre modifiée pour check si le voyage es possible
	public boolean voyageExiste(Case source, Case destination)
	{

		boolean bVexiste = false;
		for (Voyage vTemp : this.lstVoyages) 
		{
			if (vTemp.getPlaneteSource() == source      && vTemp.getPlaneteDestination() == destination ||
				vTemp.getPlaneteSource() == destination && vTemp.getPlaneteDestination() == source) 
				
				bVexiste = true;
			
		}
		
		return bVexiste;
	}
	
	
	// Méthodes pour voir l'état du plateau en CUI
	public String afficherPlanetes()
	{
		String sRet = "";

		for (int lig = 0; lig < this.ensCases.length; lig++) 
		{
			for (int col = 0; col < this.ensCases[lig].length; col++) 
			{

				if (this.ensCases[lig][col].getPlanete() != null)
				{

					Planete p = this.ensCases[lig][col].getPlanete();

					sRet += "" + (p.estBase() ? Character.toLowerCase(p.getEspece().charAt(0) ) : ' ') + 
					             this.ensCases[lig][col].getPlanete().getSymbole() + ' ';	
				}

				else sRet += " . ";	
			}
	
			sRet += "\n";
			
		}

		return sRet;
	}

	public String afficherSystemes()
	{
		String sRet = "";

		for (int lig = 0; lig < this.ensCases.length; lig++) 
		{
			for (int col = 0; col < this.ensCases[lig].length; col++) 
			{
				if (this.ensCases[lig][col].getNumSysteme() != -1)
					sRet += String.format(" %-2d",this.ensCases[lig][col].getNumSysteme() );
				else sRet += String.format(" %-2s", ".");

			}
			

			sRet += "\n";	
		}

		return sRet;
	}

	public String afficherVoyages()
	{
		String sRet = "";

		for (Voyage voyage : this.lstVoyages)
			sRet += voyage + "\n";
		
		return sRet;
	}

	
	public String toString()
	{
		String sRet = "";

		sRet += "/* --------------------------- */\n" + 
			    "/*   Affichages des planètes   */\n" + 
			    "/* --------------------------- */\n" ;

		sRet += this.afficherPlanetes() + "\n";

		sRet += "/* --------------------------- */\n" + 
		        "/*   Affichages des Systèmes   */\n" + 
		        "/* --------------------------- */\n" ;

		sRet += "\n" + this.afficherSystemes();

		return sRet;
	}
}
