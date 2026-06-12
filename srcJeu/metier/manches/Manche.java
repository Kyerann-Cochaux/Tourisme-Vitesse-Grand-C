package srcJeu.metier.manches;

import srcJeu.metier.Metier;
import srcJeu.metier.plateau.Case;

import java.util.List;
import java.util.ArrayList;


public class Manche
{
	private Pioche pioche;
	private String espece;
	private List<Case> lstCases;

	/*Factory pour vérifier si l'espèce en paramètre est une espèce valide  */
	public static Manche creerManche(String espece, Case c)
	{
		for (int cpt = 0; cpt < Metier.TAB_ESPECES.length; cpt++)
		{
			if ( Metier.TAB_ESPECES[cpt].equals(espece) )
				return new Manche(espece, c);
		}
		
		return null;
	}

	private Manche(String espece, Case c)
	{
		this.espece   = espece;
		this.pioche   = new Pioche();
		this.lstCases = new ArrayList<Case>();
		this.lstCases.add(c);
	}

	/* ---------------------------------- */
	/*            Accesseurs              */
	/* ---------------------------------- */

	public String     getEspece   ()           {return this.espece;                 }
	public Pioche     getPioche   ()           {return this.pioche;                 }
	public Carte      getSommet   ()           {return this.pioche.getSommet();     }
	public Carte      getCarteInit(int indice) {return this.pioche.getCarteInit(indice);}
	public List<Case> getlstCases ()           {return this.lstCases;               }
	public Case       getPremier  ()           {return this.lstCases.getFirst();    }
	public Case       getDernier  ()           {return this.lstCases.getLast() ;    }

	public boolean estExtremite(int col, int lig)
	{
		if( this.lstCases.getFirst().getPosX() == col && this.lstCases.getFirst().getPosY() == lig ||
		    this.lstCases.getLast() .getPosX() == col && this.lstCases.getLast() .getPosY() == lig    )
			return true;
		
		return false;
	}

	/* ---------------------------------- */
	/*           Autres Méthodes          */
	/* ---------------------------------- */

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

	public boolean estMancheFinie() { return (this.pioche.resteCartePremium() );}
	public boolean decouvrirCarte() { return this.pioche.decouvrirCarte();}

	public boolean ajouterCase(Case c)
	{
		if( c == null ) { return false ; }
		this.lstCases.add(c);
		return true;
	}

	public int nbPremiumRestant()
	{
		int cartePremium = 0;
		for(int cpt = 0; cpt < this.pioche.getTaillePioche(); cpt ++)
		{
			if(this.pioche.getCarte(cpt).getPremium() )
					cartePremium++;
		}
		return 5 - cartePremium;
	}

	public int nbStandartRestant()
	{
		int carteStandard = 0;

		for(int cpt = 0; cpt < this.pioche.getTaillePioche(); cpt ++)
		{
			if(!this.pioche.getCarte(cpt).getPremium() )
				carteStandard++;
		}
		return 5 - carteStandard;
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
