package source.metier;

import java.util.ArrayList;
import java.util.List;

public class Plateau
{
	
	private static final int TAILLE_MAX = 30;
	
	public static final String[] TAB_PLANETES = {"Gazeuze","Océan", "Tellurique", "Volcanique" };
	public static final String[] TAB_ESPECES  = {"Chlorophite", "Felinoid", "Azimae", "Silikon"};
	                                          // Marron         BLeu        Rouge     Vert
	
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
			this.ensPlanetes[cpt] = Plateau.TAB_PLANETES[cpt];
		
		for (int cpt = 0; cpt < this.ensEspeces.length; cpt++)
			this.ensEspeces[cpt] = Plateau.TAB_ESPECES[cpt];
		
		this.ensCases = new Case[this.nbLignes][this.nbColonnes];
		
		for (int lig = 0; lig < this.nbLignes; lig++)
			for (int col = 0; col < this.nbColonnes; col++)
				ensCases[lig][col] = new Case(col, lig);
	}
	
	/* ---------------------------------- */
	/*            Accesseurs              */
	/* ---------------------------------- */

	public String getNomEspece (int indice) { return this.ensEspeces [indice];}
	public String getNomPlanete(int indice) { return this.ensPlanetes[indice];}
	
	public int getNbLignes  () { return this.nbLignes         ;}
	public int getNbColonnes() { return this.nbColonnes       ;}
	public int getNbSysteme () { return this.nbSysteme        ;}

	public int getNbEspeces () { return this.ensEspeces.length;}
	public int getNbPlanetes  () { return this.ensPlanetes .length;}
	
	public String[] getEnsPlanetes() {return this.ensPlanetes;}
	public String[] getEnsEspeces () {return this.ensEspeces ;}
	
	public Voyage getVoyage   (int indice) { return this.lstVoyages .get(indice);}
	public int    getNbVoyages()           { return this.lstVoyages.size()      ;}
	
	public Case     getCase    (int x, int y) {return this.ensCases[y][x];}
	public Case[][] getEnsCases()             {return this.ensCases      ;}

	public int getNbEspecesPosees()
	{
		int nbEspecePosees = 0;


		for (int lig = 0; lig < this.ensCases.length; lig++) 
		{
			// On parcours les colonnes
			for (int col = 0; col < this.ensCases[lig].length; col++) 
			{
				if (this.ensCases[lig][col].getPlanete().getEspece() != null)
					nbEspecePosees++;
					
				
			}
		}

		return nbEspecePosees;
	}



	
	
	/* ---------------------------------- */
	/*         Modificateurs              */
	/* ---------------------------------- */

	/*
	 Cette méthode vérifie s'il est possible d'associer la case fournie en paramètre avec le numéro du système 
	 Si le numéro du système existe déjà et qu'aucune Case portant ce dit numéro n'est à proximité de la case en paramètre,
	 on ne peut pas attribuer ce numéro du système à la case en paramètre.
	 
	*/
	
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
	
	protected boolean setNumSysteme(int numSysteme, int x, int y, boolean forcerPlacement)
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
		
		
		boolean estAdjascent = false;

		// en haut
		if ( y-1 >= 0              ) estAdjascent |= this.ensCases[y-1][x  ].getNumSysteme() == numSysteme;
		// en bas
		if ( y+1 < this.nbLignes   ) estAdjascent |= this.ensCases[y+1][x  ].getNumSysteme() == numSysteme;
		// à droite
		if ( x+1 < this.nbColonnes ) estAdjascent |= this.ensCases[y  ][x+1].getNumSysteme() == numSysteme;
		// à gauche
		if ( x-1 >= 0              ) estAdjascent |= this.ensCases[y  ][x-1].getNumSysteme() == numSysteme;
		
		if (!estAdjascent) return false;
		
		this.ensCases[y][x].setNumSysteme(numSysteme);
		
		this.corrigerDecoupeZone(x, y);
		
		return true;
		
	}

	public boolean setEspece(Planete planete, String espece)
	{
		if (!this.especeExiste(espece) ) return false;
		
		Planete planeteTemp = null;
		
		//List<String> lstEspecePosee = new ArrayList<String>(4);
		
		// On parcours les lignes
		for (int lig = 0; lig < this.ensCases.length; lig++) 
		{
			// On parcours les colonnes
			for (int col = 0; col < this.ensCases[lig].length; col++) 
			{
				planeteTemp = this.ensCases[lig][col].getPlanete();

				if ( planeteTemp != null && planeteTemp.getEspece() != null && planeteTemp.getEspece().equals(espece) )
					planeteTemp.setEspece(null);
				
			}
		}
		
		if (planete == null) return false;

		planete.setEspece(espece);
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
	
	public void remplirZoneVide()
	{
		for (int numLig = 0; numLig < this.ensCases.length; numLig++)
			for (int numCol = 0; numCol < this.ensCases[numLig].length; numCol++)
			{
				if(this.ensCases[numLig][numCol].getNumSysteme() == -1)
				{
					this.remplirZone(this.nbSysteme, this.ensCases[numLig][numCol]);
				}
			}
	}
	

	public boolean ajouterPlanete(int x, int y, Planete p) 
	{
		boolean baseExiste = false;

		if (!coordonneesValide(   x, y        ) ) return false;
		if (!planeteValide    (p, x, y        ) ) return false;
		if (!planeteExiste    (p.getSymbole() ) ) return false;

		System.out.println("AJOUT PLANETE : " + x + "/" + y );
		
		
		// Dans le cas où la planète fournie en paramètre est une base, il faut vérifier qu'elle n'est pas 
		// déjà présente sur le plateau.
		
		// On regarde si le jeton en paramètres est une base.
		if (p.estBase() )
		{
			// On parcours les lignes
			for (int lig = 0; lig < this.ensCases.length; lig++) 
			{
				// On parcours les colonnes
				for (int col = 0; col < this.ensCases[lig].length; col++) 
				{
					Case cTemp = this.ensCases[lig][col];

					// On regarde si la case à une planète
					if (!cTemp.estVide() )
					{
						// On regarde si la planète de cTemp est une base
						if (cTemp.getPlanete().estBase() )
						{
							// On regarde si les 2 planètes ont la même esp
							if (cTemp.getPlanete().getEspece().equals(p.getEspece() ) )

								return false;
						}
						
					}
				}
			}
		}
		
		//System.out.println("AJOUT PLANETE");
		
		this.ensCases[y][x].setPlanete(p);
		
		this.actualiserVoyages();
		
		return true;
	}

	public boolean retirerPlanete(int x, int y)
	{
		if (this.ensCases[y][x].getPlanete() == null) return false;

		this.ensCases[y][x].setPlanete(null);
		
		this.actualiserVoyages();
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

	private boolean coordonneesValide(int x, int  y)
	{
		return (x >= 0 && x < this.nbColonnes && y >= 0 && y < this.nbLignes);
	}

	// Vérifie si la planète n'est pas null, que la Case ne possède pas déjà 

	private boolean planeteValide(Planete j, int x, int y)
	{
		return j != null && j != this.ensCases [y][x].getPlanete()  &&
		                         this.ensCases [y][x].getPlanete() == null ;
	}
	
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
	
	// Vérifie si la planète est dans le tableau de jeu
	private boolean planeteExiste(char forme)
	{
		for(String nomPlanete : this.ensPlanetes)
		{
			if(nomPlanete.charAt(0) == forme){return true;}
		}
		return false;
	}
	
	// Vérifie si l'espèce est dans le tableau de jeu et
	// que la 

	private boolean especeExiste(String espece)
	{
		boolean bExiste = false;

		// Regarde si l'espèce existe dans le tableau d'espèces utilisées si l'espèce existe dans le tableau d'espèces utilisées
		for(String nomEspece : this.ensEspeces)
		{
			if ( nomEspece.equals(espece) ) bExiste |= true;
			else                            bExiste |= false;
		}

		return bExiste;
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
