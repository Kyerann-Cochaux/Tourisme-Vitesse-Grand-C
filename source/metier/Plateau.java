package source.metier;

import java.util.ArrayList;
import java.util.List;

public class Plateau
{
	
	private static final int TAILLE_MAX = 30;
	
	public static final String[] TAB_FORMES  = {"Gazeuze","Océan", "Tellurique", "Volcanique" };
	public static final String[] TAB_ESPECES = {"Chlorophite", "Felihoïd", "Azimae", "Silikon"};
	                                               // Marron        BLeu      Rouge     Vert
	
	private Case[][] ensCases;
	private String[] ensEspeces; // Nom des Espèces  utilisées dans le Plateau, entre 2 et 4
	private String[] ensFormes;  // Nom des Planètes utilisées dans le Plateau, entre 2 et 4

	private List<Voyage>  lstVoyages;
	private List<Integer> lstNumSystemes;
	
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
		this.lstVoyages     = new ArrayList<Voyage >();
		this.lstNumSystemes = new ArrayList<Integer>();
		
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

	public int getNbEspeces () { return this.ensEspeces.length;}
	public int getNbFormes  () { return this.ensFormes .length;}
	
	public String[] getNomFormes () {return this.ensFormes  ;}
	public String[] getNomEspeces() { return this.ensEspeces;}
	
	public Voyage getVoyage   (int indice) { return this.lstVoyages .get(indice);}
	public int    getNbVoyages()           { return this.lstVoyages.size()      ;}
	
	public Case     getCase    (int ligne, int colonne) {return this.ensCases[ligne][colonne];}
	public Case[][] getEnsCases()                       {return this.ensCases                ;}

	// Cette méthode retourne le nombre de système différents présents sur le plateau

	public int getNbSysteme()
	{
		boolean numExiste = false;


		// On parcours les lignes
		for (int lig = 0; lig < this.ensCases.length; lig++) 
		{
			// On parcours les colonnes
			for (int col = 0; col < this.ensCases[lig].length; col++) 
			{
				// On regarde si la case appartient à un système
				if (!this.ensCases[lig][col].estNeutre() )
				{
					int numSysteme = this.ensCases[lig][col].getNumSysteme();

					// Cette boucle vérifie si le numéro du système de la case testé est déjà dans la liste
					for (Integer numTemp : this.lstNumSystemes) 
						// Si le numéro existe déjà, on ne l'ajoute pas
						if (numSysteme == numTemp) numExiste = true;

					// S'il n'est pas présent dans la liste, on l'ajoute, et on remet le booléen à faux
					if (!numExiste ) 
					{
						this.lstNumSystemes.add(numSysteme);
						numExiste = false;
					}
				}	
			}	
		}

		return this.lstNumSystemes.size();
	}
	
	
	/* ---------------------------------- */
	/*         Modificateurs              */
	/* ---------------------------------- */

	/*
	 Cette méthode vérifie s'il est possible d'associer la case fournie en paramètre avec le numéro du système 
	 Si le numéro du système existe déjà et qu'aucune Case portant ce dit numéro n'est à proximité de la case en paramètre,
	 on ne peut pas attribuer ce numéro du système à la case en paramètre.
	 
	*/

	// TODO: Finir méthode setNumSysteme(), ne fonctionne pas entièrement selon la case en paramètre

	public boolean setNumSysteme(Case c, int numSysteme)
	{
		//System.out.println(c.getPosX() + " " + c.getPosY() );

		// Si la liste ne contient pas le numéro
		if (!this.lstNumSystemes.contains(numSysteme) )
		{
			if (c.estNeutre() )
			{
				c.setNumSysteme(numSysteme);
				this.lstNumSystemes.add(numSysteme);
				return true;
			}
		}
		// Si la liste contient le numéro...
		else
		{
			// ... On regarde les cases autour de la case en paramètre

			// sur l'axe horizontal

			System.out.println(c.getPosX() - 1 >= 0); // à gauche
			System.out.println(c.getPosX() + 1 >= 0); // à droite

			System.out.println(c.getPosY() - 1 <= 0); // en haut
			System.out.println(c.getPosY() + 1 <= 0); // en bas


			if (c.getPosY() -1 >= 0 &&    c.getPosY()                      < this.nbColonnes  && 
			    this.getCase(c.getPosX(), c.getPosY() - 1).getNumSysteme() == numSysteme        ||
			    this.getCase(c.getPosX(), c.getPosY() ).getNumSysteme() == numSysteme)
			{
				c.setNumSysteme(numSysteme);
				return true;
			}

			// Puis sur l'axe vertical

			/*if (c.getPosX() - 1 >= 0 && c.getPosX()  +1                    < this.nbLignes +1 &&
		       this.getCase(c.getPosX() + 1, c.getPosY() ).getNumSysteme() == numSysteme || 
			   this.getCase(c.getPosX() - 1, c.getPosY() ).getNumSysteme() == numSysteme)
			{
				c.setNumSysteme(numSysteme);
				return true;
			}*/


		}
				
		

		return false;

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
				sRet += String.format("%4d",this.ensCases[lig][col].getNumSysteme() );

			sRet += "\n";	
		}

		return sRet;
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


}
