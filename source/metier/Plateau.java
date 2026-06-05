package source.metier;

import java.util.ArrayList;
import java.util.List;

public class Plateau
{
	
	private static final int TAILLE_MAX = 30;
	
	public static final String[] TAB_PLANETES = {"Gazeuze","Océan", "Tellurique", "Volcanique" };
	public static final String[] TAB_ESPECES  = {"Chlorophite", "Felihoïd", "Azimae", "Silikon"};
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
		
		/* Ces tableaux permettent de savoir quelles valeurs sont utilisés lors d'une partie 
			
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
				ensCases[lig][col] = new Case(lig, col);
	}
	
	/* ---------------------------------- */
	/*            Accesseurs              */
	/* ---------------------------------- */

	public String getEspece (int indice) { return this.ensEspeces[indice];}
	public String getPlanete(int indice) { return this.ensPlanetes [indice];}
	
	public int getNbLignes  () { return this.nbLignes         ;}
	public int getNbColonnes() { return this.nbColonnes       ;}
	public int getNbSysteme () { return this.nbSysteme        ;}

	public int getNbEspeces () { return this.ensEspeces.length;}
	public int getNbPlanetes  () { return this.ensPlanetes .length;}
	
	public String[] getNomPlanetes () {return this.ensPlanetes  ;}
	public String[] getNomEspeces() { return this.ensEspeces;}
	
	public Voyage getVoyage   (int indice) { return this.lstVoyages .get(indice);}
	public int    getNbVoyages()           { return this.lstVoyages.size()      ;}
	
	public Case     getCase    (int x, int y) {return this.ensCases[y][x];}
	public Case[][] getEnsCases()             {return this.ensCases      ;}



	
	
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
		return this.setNumSysteme(numSysteme, x, y, false);
	}
	
	protected boolean setNumSysteme(int numSysteme, int x, int y, boolean forcerPlacement)
	{
		if (forcerPlacement)
		{
			this.ensCases[y][x].setNumSysteme(numSysteme);
			return true;
		}
		
		
		if ( x < 0 || x >= this.nbColonnes ) return false;
		if ( y < 0 || y >= this.nbLignes   ) return false;
		if ( numSysteme >  this.nbSysteme  ) return false;
		
		if ( numSysteme == this.nbSysteme )
		{
			this.nbSysteme++;
			this.ensCases[y][x].setNumSysteme(numSysteme);
			return true;
		}
		
		
		boolean estAdjascent = false;

		// en haut
		if ( y-1 >= 0                ) estAdjascent |= this.ensCases[y-1][x  ].getNumSysteme() == numSysteme;
		// en bas
		if ( y+1 < this.nbLignes   ) estAdjascent |= this.ensCases[y+1][x  ].getNumSysteme() == numSysteme;
		// à droite
		if ( x+1 < this.nbColonnes ) estAdjascent |= this.ensCases[y  ][x+1].getNumSysteme() == numSysteme;
		// à gauche
		if ( x-1 >= 0                ) estAdjascent |= this.ensCases[y  ][x-1].getNumSysteme() == numSysteme;


		
		if (estAdjascent) this.ensCases[y][x].setNumSysteme(numSysteme);
		else              return false;
		
		return true;
		
	}

	public boolean setEspece(String espese)
	{
		Case cTemp = null;
		// On parcours les lignes
		for (int lig = 0; lig < this.ensCases.length; lig++) 
		{
			// On parcours les colonnes
			for (int col = 0; col < this.ensCases[lig].length; col++) 
			{
				cTemp = this.ensCases[lig][col];

				// On regarde si la case à une planète
				if (!cTemp.estVide() )
				{
					// On regarde si la planète de cTemp est une base
					if (cTemp.getPlanete().estBase() )
					{
						// On regarde si les 2 planètes ont la même esp
						if (cTemp.getPlanete().getEspece().equals(espese) )
							return false;
					}
					
				}
			}
		}

		if (cTemp != null) cTemp.getPlanete().setEspece(espese);
		return true;
	}
	
	/* ---------------------------------- */
	/*           Autres méthodes          */
	/* ---------------------------------- */

	public boolean ajouterPlanete(int x, int y, Planete p) 
	{
		boolean baseExiste = false;

		if (!coordonneesValide(   x, y        ) ) return false;
		if (!planeteValide    (p, x, y        ) ) return false;
		if (!planeteExiste    (p.getSymbole() ) ) return false;

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

		//if (baseExiste) {System.out.println("base Existe");return false;}

		this.ensCases[y][x].setPlanete(p);

		return true;
	}

	public boolean retirerPlanete(int x, int y)
	{
		if (this.ensCases[y][x].getPlanete() == null) return false;

		this.ensCases[y][x].setPlanete(null);

		return true;
	}

	public boolean ajouterVoyage(Case source, Case destination)
	{
		if (source     .estVide()                  ) return false;
		if (destination.estVide()                  ) return false;
		if (this.voyageExiste(source, destination) ) return false;

		int dX =  Math.abs(destination.getPosX() - source.getPosX() );
		int dY =  Math.abs(destination.getPosY() - source.getPosY() );

		/*if (dX >=  3|| dY >= 3) 
		{
			//System.out.println(source);
			//System.out.println(destination);
			System.out.println(dY > 0 ); 

			System.out.println(source.getPosY() + ":" + dY );
			System.out.println(this.getCase(dY   , 3) );
			System.out.println(this.getCase(dY +1, 3) );
			System.out.println(this.getCase(dY +2, 3) );

		}*/

		//         Orthogonal      Diagonal
		//if ( dX == 0 ^ dY == 0  || dX == dY)

		//System.out.println(dY ); // Axe Horizontal
		//System.out.println(dX ); // Axe Vertical

		for (int cpt = 1; cpt < Math.max(dX, dY); cpt++) 
		{
			// même colonne                 // Case vide
			if (dX == 0  && !this.getCase(source.getPosY() + cpt , source.getPosX()      ).estVide() ) return false;
			if (dY == 0  && !this.getCase(source.getPosY() + cpt , source.getPosX()      ).estVide() ) return false;
			if (dX == dY && !this.getCase(source.getPosY() - cpt, source.getPosX() +  cpt).estVide() ) return false;
			if (dX == dY && !this.getCase(source.getPosY() + cpt, source.getPosX() -  cpt).estVide() ) return false;

		}
		

		
	
		

		this.lstVoyages.add(Voyage.creerVoyage(source, destination) );
		return true;
		
		

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
		boolean bPresente = true;

		// Regarede 
		for(String nomEspece : this.ensEspeces)
			if (!nomEspece.equals(espece) ) 
				
				bPresente = false;

		if (!bPresente) return false;
		// ...


		return false;
	}
	
	// Méthodes pour voir l'etat du plateau en CUI
	
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
