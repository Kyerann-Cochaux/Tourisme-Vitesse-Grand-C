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

	public boolean    estExtremite(int col, int lig)
	{
		if(this.lstCases.getFirst().getPosX() == col && this.lstCases.getFirst().getPosY() == lig ||
	       this.lstCases.getLast().getPosX()  == col && this.lstCases.getLast().getPosY() == lig)
		   return true;
		return false;
	}

	/* ---------------------------------- */
	/*           Autres Méthodes          */
	/* ---------------------------------- */

	public int calculerScore()
	{
		return -1;
	}

	public boolean estMancheFinie()
	{
		if(this.pioche.resteCartePremium())
			return false;
		return true;
	}

	public boolean decouvrirCarte()
	{
		return this.pioche.decouvrirCarte();
	}

	public boolean ajouterCase(Case c)
	{
		if(c == null)
			return false;
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
