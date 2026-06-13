package srcJeu.metier.manches;

import srcJeu.metier.Metier;
import srcJeu.metier.plateau.*;

import java.util.List;
import java.util.ArrayList;


public class Manche
{
	private Pioche pioche;
	private String espece;
	private List<Case> lstCases;
	private Metier metier;

	/*Factory pour vérifier si l'espèce en paramètre est une espèce valide  */
	public static Manche creerManche(String espece, Case c, Metier metier) // Case ici doit être le départ de l'espèce de la manche actuelle
	{
		for (int cpt = 0; cpt < metier.getPlateau().getNbEspeces() ; cpt++)
		{
			if (  metier.getPlateau().getNomEspece(cpt).equals(espece) )
				return new Manche(espece, c, metier);
		}
		
		return null;
	}

	private Manche(String espece, Case c, Metier metier)
	{
		this.espece   = espece;
		this.pioche   = new Pioche(metier);
		this.pioche.melangerCarte(); // On mélange la pioche ici
		this.lstCases = new ArrayList<Case>();
		this.metier = metier;
		this.lstCases.add(c);
	}

	/* ---------------------------------- */
	/*            Accesseurs              */
	/* ---------------------------------- */

	public String     getEspece   ()           {return this.espece;                 }
	public Pioche     getPioche   ()           {return this.pioche;                 }
	public Carte      getSommet   ()           {return this.pioche.getSommet();     }
//public Carte      getCarteInit(int indice) {return this.pioche.getCarteInit(indice);}
	public List<Case> getlstCases ()           {return this.lstCases;               }
	public Case       getPremier  ()           {return this.lstCases.getFirst();    }
	public Case       getDernier  ()           {return this.lstCases.getLast() ;    }
	
	/* ---------------------------------- */
	/*           Autres Méthodes          */
	/* ---------------------------------- */
	
	public boolean estMancheFinie() { return this.pioche.resteCartePremium() ; }
	
	public int calculerScore()
	{
		// Je met la valeur à 11 pour éviter un problème d'indice si on visite 10 
		int[] tabZoneDiff = new int[11];
		int  nbPlaneteMax = 0;
		int  nbZonesDiff  = 0;

		for (int cpt = 0; cpt < tabZoneDiff.length; cpt++) 
			// On initialise à 0, ce qui correspond à aucune valeur
			tabZoneDiff[cpt] = 0;
			
		// On parcours toute nos Case de notre croisière
		for (int cpt = 0; cpt < this.lstCases.size(); cpt++) 
		{
			// On regarde toutes les valeurs présentes dans le tableau d'entier
			for (int cptVal = 0; cptVal < tabZoneDiff.length; cptVal++)
			{

				// Si le numSysteme de la case est identique à la valeur au même indice (ex : numSysteme == 1 et nbZoneDiff[cptVal] == 1)
				if (this.lstCases.get(cpt).getNumSysteme() == tabZoneDiff[cptVal] )
					// On incrémente la valeur
					tabZoneDiff[cptVal ]++;

				else 
					// Sinon on met la valeur différente à l'indice suivant
					tabZoneDiff[this.lstCases.get(cpt).getNumSysteme()] = tabZoneDiff[cptVal++]++;
				
			}
		}

		// Ensuite... On regarde combien de zones ont été visité
		nbPlaneteMax = tabZoneDiff[0];

		for (int cpt = 0; cpt < tabZoneDiff.length; cpt++) 
		{
			if (tabZoneDiff[cpt] != 0) 
			{
				if (tabZoneDiff[cpt] > nbPlaneteMax) nbPlaneteMax = tabZoneDiff[cpt];
				nbZonesDiff++;
			}
		}

		return nbZonesDiff * nbPlaneteMax;
	}
	
	
	public boolean enleverCarte() 
	{
		return this.pioche.enleverCarte();
	}
	
	
	public boolean estCaseVisitee( int posCol, int posLig ) // Retourne vrai si la case a déjà été visité
	{
		for ( int cpt=0 ; cpt < this.lstCases.size() ; cpt++ )
		{
			if ( this.lstCases.get(cpt).getPosX() == posCol && this.lstCases.get(cpt).getPosY() == posLig )
			{
				return true ;
			}
		}
		
		return false ;
	}
	
	public boolean estExtremite(int col, int lig)
	{
		if(
		    (this.lstCases.getFirst().getPosX() == col && this.lstCases.getFirst().getPosY() == lig) ||
		    (this.lstCases.getLast() .getPosX() == col && this.lstCases.getLast() .getPosY() == lig) 
		  )
		{
			return true ;
		}
		
		return false;
	}
	
	public boolean ajouterCase(Case cDep, Case cFin)
	{
		if( cDep == null ) return false;
		if( cFin == null ) return false;
		
		System.out.println("\n~~~ ajouterCase() ~~~");
		System.out.println( "Nombre de Planètes visité : " + this.lstCases.size() );
		
		// On regarde si cette case as deja été visitée
		for (int indCase = 0; indCase < this.lstCases.size(); indCase++)
		{
			if ( this.lstCases.get(indCase).getPosX() == cFin.getPosX() && 
			     this.lstCases.get(indCase).getPosY() == cFin.getPosY()     )
			{
				return false;
			}
		}
		
		// parcours de tout les voyages du plateau
		for (int indVoyage = 0; indVoyage < this.metier.getVoyages().size(); indVoyage++)
		{
			Voyage voyageTemp = this.metier.getVoyages().get(indVoyage);
			
			// si le voyage es de la bonne espece
			if (voyageTemp.getEspece() != null && voyageTemp.getEspece().equals(this.espece) ||
			      this.lstCases.size() <= 1 )
			{
				System.out.println("Voyage " + indVoyage + " de l'espèce attendu" ); // anciennement "bonne SP"
				if (
					(voyageTemp.getPlaneteSource().getPosX()      == cDep.getPosX() &&
					 voyageTemp.getPlaneteSource().getPosY()      == cDep.getPosY() )   ||
					(voyageTemp.getPlaneteDestination().getPosX() == cDep.getPosX() &&
					 voyageTemp.getPlaneteDestination().getPosY() == cDep.getPosY() )
					)
				{
					System.out.println("Voyage " + indVoyage + " à la bonne position" ); // anciennement "bonne POs"
					if ( this.lstCases.getFirst().getPosX() == cDep.getPosX() && 
						this.lstCases.getFirst().getPosY() == cDep.getPosY()     )
					{
						this.lstCases.addFirst(cFin);
						return true;
					}
					
					
					if ( this.lstCases.getLast().getPosX() == cDep.getPosX() && 
						this.lstCases.getLast().getPosY() == cDep.getPosY()    )
					{
						this.lstCases.addLast(cFin);
						return true;
					}
					
				}
			}
		}
		
		System.out.println("ajouterCase : Case pas Ajouté");
		return false;
	}

	public int nbPremiumRestant()
	{
		int cartePremium = 0;
		for(int cpt = 0; cpt < this.pioche.getTaillePioche(); cpt ++)
		{
			if(this.pioche.getCarte(cpt).getPremium() )
					cartePremium++;
		}
		return cartePremium;
	}

	public int nbStandartRestant()
	{
		int carteStandard = 0;

		for(int cpt = 0; cpt < this.pioche.getTaillePioche(); cpt ++)
		{
			if(!this.pioche.getCarte(cpt).getPremium() )
				carteStandard++;
		}
		return carteStandard;
	}


	/* ---------------------------------- */
	/*          Méthodes standard         */
	/* ---------------------------------- */

	public String toString()
	{
		return "Espece : " + this.espece + "\n" +
			   this.pioche;
	}
}
