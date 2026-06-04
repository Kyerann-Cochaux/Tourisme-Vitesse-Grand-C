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
	private String[] ensEspeces; // Nom des Espèces  utilisées dans le Plateau, entre 2 et 4
	private String[] ensFormes;  // Nom des Planètes utilisées dans le Plateau, entre 2 et 4

	private List<Voyage>  lstVoyages;
	
	private int nbLignes, nbColonnes;
	private int nbSysteme;
	
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
		this.lstVoyages     = new ArrayList<Voyage >();
		//this.lstNumSystemes = new ArrayList<Integer>();
		
		this.nbLignes   = nbLignes;
		this.nbColonnes = nbColonnes;
		this.nbSysteme  = 0;
		
		this.ensFormes  = new String[nbFormes ];
		this.ensEspeces = new String[nbEspeces];
		
		/* Ces tableaux permettent de savoir quelles valeurs sont utilisés lors d'une partie 
			
			Si il y a 3 formes, on prends les 3 premières dans le tableau de constantes.
			S'il y a 4 couleurs, on prends les 4 couleurs dans le tableau de constantes.
		*/
		
		for (int cpt = 0; cpt < this.ensFormes.length; cpt++)
			this.ensFormes[cpt] = Plateau.TAB_PLANETES[cpt];
		
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
	
	public int getNbLignes  () { return this.nbLignes         ;}
	public int getNbColonnes() { return this.nbColonnes       ;}
	public int getNbSysteme () { return this.nbSysteme        ;}

	public int getNbEspeces () { return this.ensEspeces.length;}
	public int getNbFormes  () { return this.ensFormes .length;}
	
	public String[] getNomFormes () {return this.ensFormes  ;}
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
			for (int lig = 0; lig < this.ensCases.length; lig++) 
			{
				// On parcours les colonnes
				for (int col = 0; col < this.ensCases[lig].length; col++) 
				{
					Case cTemp = this.ensCases[lig][col];

					if (!cTemp.estVide() )
					{

						if (cTemp.getPlanete().estBase()                            &&
							j.getEspece().equals(cTemp.getPlanete().getEspece() ) )
						
							baseExiste = true;
					}

					
					
				}
				
			}
		}

		if (baseExiste) return false;

		this.ensCases[posX][posY].setPlanete(j);

		return true;
	}

	public boolean retirerForme(int posX, int posY)
	{
		if (this.ensCases[posX][posY].getPlanete() == null) return false;

		this.ensCases[posX][posY].setPlanete(null);

		return true;
	}

	public boolean ajouterVoyage(Case source, Case destination)
	{

		if (source     .estVide() ) return false;
		if (destination.estVide() ) return false;

		int dX =  Math.abs(destination.getPosX() - source.getPosX() );
		int dY =  Math.abs(destination.getPosY() - source.getPosY() );

			//    Orthogonal      Diagonal
		if ( dX == 0 ^ dY == 0 || dX == dY)

			for (int cpt = 0; cpt < Math.max(dX, dY); cpt++) 
			{
				if (this.getCase(dX, dY).getPlanete() != null)
					this.lstVoyages.add(Voyage.creerVoyage(source, destination));
			}
		
	
		System.out.println( );
		System.out.println(source.getPosX() + " <==> " + destination.getPosX() );

		System.out.println(source);
		System.out.println(destination);



		return true;
	}

	private boolean coordonneesValide(int posX, int  posY)
	{
		return (posX >= 0 && posX < this.ensCases      .length ) && 
		       (posY >= 0 && posY < this.ensCases[posX].length ); 
	}

	private boolean planeteValide(Planete j, int posX, int posY)
	{
		return j != null && j != this.ensCases [posX][posY].getPlanete()  &&
		                         this.ensCases [posX][posY].getPlanete() == null ;
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
