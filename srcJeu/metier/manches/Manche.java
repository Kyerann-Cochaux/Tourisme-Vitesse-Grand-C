package srcJeu.metier.manches;

import srcJeu.metier.Metier;
import srcJeu.metier.plateau.*;

import java.util.List;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;


public class Manche
{
	private Pioche pioche;
	private String espece;
	private List<Case> lstCases;
	private Metier metier;

	/*Factory pour vérifier si l'espèce en paramètre est une espèce valide  */
	// Case ici doit être le départ de l'espèce de la manche actuelle
	public static Manche creerManche(String espece, Case c, Metier metier, boolean demo) 
	{
		for (int cpt = 0; cpt < metier.getPlateau().getNbEspeces() ; cpt++)
		{
			if (  metier.getPlateau().getNomEspece(cpt).equals(espece) )
				return new Manche(espece, c, metier, demo);
		}
		
		return null;
	}

	private Manche(String espece, Case c, Metier metier, boolean demo)
	{
		this.espece   = espece;
		this.pioche   = new Pioche(metier, demo);
		if (!demo) this.pioche.melangerCarte(); // On mélange la pioche ici
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
	public List<Case> getlstCases ()           {return this.lstCases;               }
	public Case       getPremier  ()           {return this.lstCases.getFirst();    }
	public Case       getDernier  ()           {return this.lstCases.getLast() ;    }
	
	/* ---------------------------------- */
	/*           Autres Méthodes          */
	/* ---------------------------------- */
	
	public boolean estMancheFinie() { return this.pioche.resteCartePremium() || this.getPioche().getTaillePioche() == 0 ; }
	
	public int calculerScore()
	{

		int[] tabCase = new int[11];

		int nbSystemes   = 1;
		int nbPlanetes   = 1;
		int nbPlaneteMax = 1;

		// Init à -1
		for (int cpt = 0; cpt < tabCase.length; cpt++) 
			tabCase[cpt] = -1;

		// On ajoute le numéro de la zone au tabCases

		for (Case caseVisitee : this.lstCases)
			tabCase[this.lstCases.indexOf(caseVisitee) ] = caseVisitee.getNumSysteme();

		// Tri des systèmes
		ArrayList<Integer> lstCases = new ArrayList<Integer>();

		for (int cpt = 0; cpt < tabCase.length; cpt++) 
			if (tabCase[cpt] != -1)
				lstCases.add(tabCase[cpt]);

		Collections.sort(lstCases);

		for (int cpt = 0; cpt < lstCases.size(); cpt++) 
			tabCase[cpt] = lstCases.get(cpt);


		for (int cpt = 1; cpt < tabCase.length; cpt++)
		{
			if (tabCase[cpt ] != -1)
			{

				if (tabCase[cpt] != tabCase[cpt-1])
				{
					nbSystemes++;
					nbPlaneteMax = Math.max(nbPlaneteMax, nbPlanetes);
					nbPlanetes   = 1;
				}
				else nbPlanetes++;
			}
			}

		nbPlaneteMax = Math.max(nbPlaneteMax, nbPlanetes);

		return (nbSystemes * nbPlaneteMax) ;
	}
	
	
	public boolean decouvrirCarte() 
	{
		return this.pioche.decouvrirCarte();
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
				if (  voyageTemp.getPlaneteSource     ().getPosX() == cDep.getPosX() &&
					  voyageTemp.getPlaneteSource     ().getPosY() == cDep.getPosY() ||
					  voyageTemp.getPlaneteDestination().getPosX() == cDep.getPosX() &&
					  voyageTemp.getPlaneteDestination().getPosY() == cDep.getPosY() 
					)
				{
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
