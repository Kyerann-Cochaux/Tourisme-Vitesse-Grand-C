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
	
	public Voyage       getVoyage   (int indice) { return this.lstVoyages .get(indice)           ;}
	public int          getNbVoyages()           { return this.lstVoyages.size()                 ;}
	public List<Voyage> getVoyages  ()           { return new ArrayList<Voyage> (this.lstVoyages);}
	
	public Case     getCase    (int x, int y) { return this.ensCases[y][x]; }
	public Case[][] getEnsCases()             { return this.ensCases      ; }
	
	
	
	/* ---------------------------------- */
	/*         Modificateurs              */
	/* ---------------------------------- */

	public boolean setEspece(int xDep, int yDep, int xFin, int yFin, String espece)
	{
		// 1. On récupère la case de départ et la case d'arrivée
		Case caseSource  = this.getCase(xDep, yDep);
		Case caseDest    = this.getCase(xFin, yFin);
		
		if (caseSource == null || caseDest == null) return false;

		// 2. On cherche dans la liste des voyages du plateau celui qui correspond
		for (int cpt = 0; cpt < this.getNbVoyages(); cpt++)
		{
			Voyage v = this.getVoyage(cpt);
			
			// Si le voyage va de Source à Destination (ou l'inverse selon votre règle)
			if ( (v.getPlaneteSource() == caseSource && v.getPlaneteDestination() == caseDest) ||
				(v.getPlaneteSource() == caseDest   && v.getPlaneteDestination() == caseSource) )
			{
				// 3. On applique l'espèce sur le voyage trouvé
				return v.setEspece(espece);
			}
		}
		return false; // Aucun voyage correspondant trouvé entre ces deux cases
	}



	/*
	 Cette méthode vérifie s'il est possible d'associer la case fournie en paramètre avec le numéro du système 
	 Si le numéro du système existe déjà et qu'aucune Case portant ce dit numéro n'est à proximité de la case en paramètre,
	 on ne peut pas attribuer ce numéro du système à la case en paramètre.
	 
	*/

	/*public boolean setEspece(int xDep, int yDep, int xFin, int yFin, String espece)
	{
		Voyage voyAColorer = null;
		
		for (int indVoyage = 0; indVoyage < lstVoyages.size(); indVoyage++)
		{
			Voyage voyTemp = lstVoyages.get(indVoyage);
			
			if ( ( voyTemp.getPlaneteSource     () == this.ensCases[yDep][xDep] || 
			       voyTemp.getPlaneteDestination() == this.ensCases[yDep][xDep]    ) &&
			     
			     ( voyTemp.getPlaneteSource     () == this.ensCases[yFin][xFin] || 
			       voyTemp.getPlaneteDestination() == this.ensCases[yFin][xFin]    ) )
			{
				voyAColorer = voyTemp;
			}
		}
		
		
		for (int indVoyage = 0; indVoyage < lstVoyages.size(); indVoyage++)
		{
			Voyage voyTestCoupe = lstVoyages.get(indVoyage);
			
			if ( voyAColorer != null )
				if ( voyAColorer.coupe(voyTestCoupe) )
					return false;
		}
		if ( voyAColorer == null ) return false;
		
		voyAColorer.setEspece(espece);
		
		return true;
	}*/
	 
	
	private void corrigerDecoupeZone(int x, int y)
	{
		// création de nouvelles zones si besoin
		int[][] dPos = 
		{//   dx  dy
			{ 0, -1},
			{+1,  0},
			{ 0, +1},
			{-1,  0}
		};
		
		for (int indDPos = 0; indDPos < dPos.length; indDPos++)
		{
			int nX = dPos[indDPos][0] + x;
			int nY = dPos[indDPos][1] + y;
			
			if ( nX >= 0 && nX < this.nbColonnes &&
				nY >= 0 && nY < this.nbLignes      )
			{
				Case caseTemp = this.getCase(nX, nY);
				
				if ( caseTemp.getNumSysteme() != -1 && this.estZoneScindee(caseTemp.getNumSysteme()) )
				{
					this.remplirZone(this.nbSysteme, caseTemp);
				}
			}
		}
	}
	
	public boolean setNumSysteme(int numSysteme, int x, int y)
	{
		return this.setNumSysteme(numSysteme, x, y, false);
	}
	
	public boolean setNumSysteme(int numSysteme, int x, int y, boolean forcerPlacement)
	{
		if (forcerPlacement)
		{
			this.ensCases[y][x].setNumSysteme(numSysteme);
			if ( numSysteme >= this.nbSysteme ) this.nbSysteme = numSysteme + 1 ;
			return true;
		}
		
		
		if ( x < 0 || x >= this.nbColonnes ) return false;
		if ( y < 0 || y >= this.nbLignes   ) return false;
		if ( numSysteme >  this.nbSysteme  ) return false;
		
		
		
		if ( numSysteme == this.nbSysteme )
		{
			this.nbSysteme++;
			this.ensCases[y][x].setNumSysteme(numSysteme);
			
			this.corrigerDecoupeZone(x, y);
			
			return true;
		}
		
		
		int numSystemeInitial = this.ensCases[y][x].getNumSysteme();
		
		this.ensCases[y][x].setNumSysteme(numSysteme);
		
		if( this.estZoneScindee(numSysteme) )
		{
			this.ensCases[y][x].setNumSysteme(numSystemeInitial);
			
			return false;
		}
		
		this.corrigerDecoupeZone(x, y);
		
		return true;
		
	}
	
	/* ---------------------------------- */
	/*           Autres méthodes          */
	/* ---------------------------------- */
	
	public ArrayList<Case> parcoursZone(Case caseDep)
	{
		if ( caseDep == null          ) return null;
		int numZone = caseDep.getNumSysteme();
		if ( numZone > this.nbSysteme ) return null;
		if ( numZone < -1             ) return null;
		
		int nbCasesMarquee = 0;
		ArrayList<Case>    lstCaseZonee    = new ArrayList<Case>();
		
		Case caseActuelle = caseDep;
		lstCaseZonee   .add(caseActuelle);
		
		do
		{
			int[][] dPos = 
			{//	 dx  dy
				{ 0, -1},
				{+1,  0},
				{ 0, +1},
				{-1,  0}
			};
			
			int x = caseActuelle.getPosX();
			int y = caseActuelle.getPosY();
			
			for (int indDPos = 0; indDPos < dPos.length; indDPos++)
			{
				int nX = dPos[indDPos][0] + x;
				int nY = dPos[indDPos][1] + y;
				
				
				if ( nX >= 0 && nX < this.nbColonnes &&
				     nY >= 0 && nY < this.nbLignes      )
				{
					Case caseVerif = this.ensCases[nY][nX];
					
					if ( caseVerif.getNumSysteme() == numZone && !lstCaseZonee.contains(caseVerif) )
						lstCaseZonee.add(caseVerif);
				}
				
			}
			
			nbCasesMarquee++;
			
			// passer a la case suivante apres l'avoir explorée
			if ( nbCasesMarquee < lstCaseZonee.size() )
				caseActuelle = lstCaseZonee.get(nbCasesMarquee);
		}
		while ( nbCasesMarquee < lstCaseZonee.size() );
		
		return lstCaseZonee;
	}
	
	public boolean estZoneScindee(int numZone)
	{
		Case caseActuelle = null;
		
		// récuperation de la dernière case de la zone
		for (int lig = 0; lig < this.ensCases.length; lig++)
			for (int col = 0; col < this.ensCases[lig].length; col++)
				if ( this.ensCases[lig][col].getNumSysteme() == numZone )
					caseActuelle = this.ensCases[lig][col];
		
		if ( caseActuelle == null ) return false;
		
		return this.tailleZone(numZone) != this.parcoursZone(caseActuelle).size();
	}
	
	
	// nombre de cases appartenant a la zone numZone
	public int tailleZone(int numZone)
	{
		int nbCases = 0;
		
		for (int numLig = 0; numLig < this.ensCases.length; numLig++)
			for (int numCol = 0; numCol < this.ensCases[numLig].length; numCol++)
				if ( this.ensCases[numLig][numCol].getNumSysteme() == numZone )
					nbCases++;
		
		return nbCases;
	}
	
	// parcours logique pour remplir une zone
	// retourne si la zone as été remplie ou non
	public boolean remplirZone(int numZone, Case caseDep)
	{
		int numZoneInitiale = caseDep.getNumSysteme();
		if ( numZone == numZoneInitiale ) return false; // pas besoin de modifications
		
		if ( numZone >  this.nbSysteme  ) return false;
		if ( this.nbSysteme == numZone )
			this.nbSysteme++;
		
		ArrayList<Case> lstCaseZonee = this.parcoursZone(caseDep);
		
		if ( lstCaseZonee == null ) return false;
		
		for (Case caseAnulation : lstCaseZonee)
			caseAnulation.setNumSysteme(numZone);
		
		if ( this.estZoneScindee(numZone) )
		{
			for (Case caseAnulation : lstCaseZonee)
			{
				caseAnulation.setNumSysteme(numZoneInitiale);
			}
			return false;
		}
		
		return true;
	}
	
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
