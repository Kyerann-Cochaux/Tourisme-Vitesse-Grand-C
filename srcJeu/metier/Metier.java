package srcJeu.metier;

import java.io.FileInputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import srcJeu.metier.manches.*;
import srcJeu.metier.plateau.*;

public class Metier
{
	// Attributs de Classe
	public static final String[] TAB_PLANETES = {"Gazeuze",     "Océan",    "Tellurique", "Volcanique" };
	public static final String[] TAB_ESPECES  = {"Chlorophite", "Felinoid", "Azimae",      "Silikon"   };
	                                           // Marron         Bleu        Rouge          Vert
	
	// Attributs d'Instance
	private Plateau      plateauJeu;
	private List<Manche> lstManches;

	private int mancheCourante;
	
	public Metier()
	{
		this.plateauJeu = null;
		this.lstManches = null;
	}
	
	public void initialiserPlateau(int nbLignes, int nbColonnes, int nbFormes, int nbEspeces)
	{
		this.plateauJeu     = Plateau.creerPlateau(nbLignes, nbColonnes, nbFormes, nbEspeces);
		this.lstManches     = new ArrayList<Manche>(nbEspeces);
		this.mancheCourante = 0;
	}
	
	/* ---------------------------------- */
	/*               Accesseurs           */
	/* ---------------------------------- */
	
	public Plateau getPlateau       () {return this.plateauJeu ;}
	public Manche  getMancheCourante()
	{
		return this.lstManches.get( this.mancheCourante );
	}
	public Carte        getSommet () { return this.getMancheCourante().getSommet(); }
	public List<Voyage> getVoyages() { return this.plateauJeu.getVoyages(); }
	
	public Carte getCarteInit(int indice) {return this.getMancheCourante().getCarteInit(indice);}
	
	
	/* ---------------------------------- */
	/*          Autres méthodes           */
	/* ---------------------------------- */

	public boolean decouvrirCarte(){return this.getMancheCourante().decouvrirCarte();}


	public boolean effectuerVoyage(int xDep, int yDep, int xFin, int yFin, String espece)
	{
		System.out.println("~~~ effectuer voyage ~~~~");
		System.out.println("Espèce de Départ de la Manche Actuelle : " + this.lstManches.get( this.mancheCourante ).getEspece());
		System.out.println("Planète on top : " + this.getMancheCourante().getSommet().getSymbole());
		
		// Check si c'est une Planète que l'on clique
		if ( this.plateauJeu.getCase(xFin, yFin).getPlanete() == null ) return false ;
		
		
		if ( this.plateauJeu.getCase(xFin, yFin).getPlanete().getSymbole() != this.getMancheCourante().getSommet().getSymbole().charAt(0) && 
		     this.getMancheCourante().getSommet().getSymbole().charAt(0)   != 'J'                                                            )
		{
			 System.out.println("Pas bonne planete : " + this.getMancheCourante().getSommet().getSymbole());
			return false;
		}
		
		if ( !this.getMancheCourante().estExtremite(xDep, yDep) )
		{
			System.out.println("Extremité pas bonne = " + this.getMancheCourante().getEspece() + " = " + xDep + ":" + yDep);
			return false;
		}
		
		System.out.println("C'est ok mec");
		
		// On colore le lien de la couleur de l'espèce
		if ( this.plateauJeu.setEspece(xDep, yDep, xFin, yFin, espece) )
		{
			return this.getMancheCourante().ajouterCase( this.plateauJeu.getCase(xDep, yDep), this.plateauJeu.getCase(xFin, yFin) );
		}

		// On ajoute la destionation à la liste des cases
		return false;
	}
	
	public boolean estExtremite (int col, int lig) { return this.getMancheCourante().estExtremite(col, lig); }
	
	public boolean chargerPlateau(String cheminSauvegarde)
	{
		int nbLignes   = 0;
		int nbColonnes = 0;
		int nbFormes   = 0;
		int nbEspeces  = 0;
		
		try
		{
			Scanner sc = new Scanner ( new FileInputStream ( cheminSauvegarde ), "UTF8" );
			
			
			String premiereLigne = sc.nextLine();
			
			String[] creationArguments = premiereLigne       .split(" ");
			String[] creationTaille    = creationArguments[0].split("x");
			
			nbLignes   = Integer.parseInt(creationTaille[0]);
			nbColonnes = Integer.parseInt(creationTaille[1]);

			nbFormes   = Integer.parseInt(creationArguments[1]);
			nbEspeces  = Integer.parseInt(creationArguments[2]);
			
			
			// initialisation du plateau
			this.initialiserPlateau(nbLignes, nbColonnes, nbFormes, nbEspeces);
			
			
			// placement des systèmes
			for (int numLig = 0; numLig < nbLignes; numLig++)
			{
				String   ligne  = sc.nextLine();
				String[] numSys = ligne.split("\t");
				
				for (int numCol = 0; numCol < nbColonnes; numCol++)
				{
					int numZone = Integer.parseInt( numSys[numCol] );
					this.plateauJeu.setNumSysteme(numZone, numCol, numLig, true);
				}
			}
			
			
			// placement des planetes
			for (int numLig = 0; numLig < nbLignes; numLig++)
			{
				String   ligne   = sc.nextLine();
				String[] numPlan = ligne.split("\t");
				
				for (int numCol = 0; numCol < nbColonnes; numCol++)
				{
					String sIndPlanete = numPlan[numCol];
					
					if( !sIndPlanete.equals(".") )
					{
						int indPlanete = Integer.parseInt("" + sIndPlanete);
						
						Planete tempPlanete = Planete.creerPlanete(Metier.TAB_PLANETES[indPlanete].charAt(0) );
						
						this.plateauJeu.getCase(numCol, numLig).setPlanete(tempPlanete);
						// System.out.println(tempPlanete.getSymbole() + " : numCol -> " + numCol + "\n" + "    numLig -> " + numLig );
						// System.out.println();
						//this.plateauJeu.ajouterPlanete(numCol, numLig, tempPlanete);
					}
					
				}
			}
			
			
			// placement des bases
			for (int numLig = 0; numLig < nbLignes; numLig++)
			{
				String ligne     = sc.nextLine();
				String[] numBase = ligne.split("\t");
				
				for (int numCol = 0; numCol < nbColonnes; numCol++)
				{
					
					String sIndEspece = numBase[numCol];
					
					if( !sIndEspece.equals("-") )
					{
						int indEspece = Integer.parseInt("" + sIndEspece);
						String typeEspece = Metier.TAB_ESPECES[indEspece];
						
						this.plateauJeu.getCase(numCol, numLig).getPlanete().setEspece(typeEspece);
					}
					
				}
			}
			
			
			// fermeture du scanner
			sc.close();
		}
		catch (Exception e)
		{
			// System.out.println("Erreur lors du chargement du fichier sauvegardé.");
			e.printStackTrace();
			
			this.initialiserPlateau(nbLignes, nbColonnes, nbFormes, nbEspeces);
			return false;
		}
		
		// Ajout de la base dans les manches
		for (int cpt = 0; cpt < this.plateauJeu.getNbEspeces(); cpt++) 
		{
			for(int col = 0; col < this.plateauJeu.getNbColonnes(); col++)
			{
				for(int lig = 0; lig < this.plateauJeu.getNbLignes(); lig++)
				{
					if(this.plateauJeu.getCase(col, lig).getPlanete() != null &&
					   this.plateauJeu.getCase(col, lig).getPlanete().getEspece() != null &&
					   this.plateauJeu.getCase(col, lig).getPlanete().getEspece().equals(this.plateauJeu.getNomEspece(cpt)))
					{
						Manche m = Manche.creerManche(this.plateauJeu.getNomEspece(cpt), this.plateauJeu.getCase(col, lig), this);
						
						System.out.println(m);
						lstManches.add(m);
						System.out.println("nbManches : " + lstManches.size());
					}
				}
			}
		}
		
		this.plateauJeu.actualiserVoyages();
		
		// melanges des manches
		Collections.shuffle( this.lstManches );
		
		return true;
	}
	
	
	public boolean mancheSuivante()
	{
		if(mancheCourante + 1 >= this.lstManches.size())
			return false;
		if(!this.getMancheCourante().estMancheFinie())
			return false;

		mancheCourante++;
		return true;
	}

	public boolean ajouterManche(Manche manche)
	{
		if(manche == null)
			return false;
		
		this.lstManches.add(manche);
		
		return true;
	}

	public int calculerScore()
	{
		return this.getMancheCourante().calculerScore();
	}

	public String toString()
	{
		return "Plateau : \n" + this.plateauJeu + "\n" +
		       "list Manche : " + this.lstManches;
	}
}
