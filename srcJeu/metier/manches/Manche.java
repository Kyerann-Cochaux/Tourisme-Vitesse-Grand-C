package srcJeu.metier.manches;

import java.util.ArrayList;
import srcJeu.metier.plateau.Case;
import java.util.List;

public class Manche
{
	private Pioche pioche;
	private String espece;
	private List<Case> lstCases;

	/*Factory pour verifier si l'espece en parametre est une espece valide  */
	public static Manche creerManche(String espece)
	{
		if(!espece.equals("Chlorophite") && !espece.equals("Felinoid") && 
		   !espece.equals("Azimae")      && !espece.equals("Silikon")    )
			return null;
		return new Manche(espece);
	}

	private Manche(String espece)
	{
		this.espece = espece;
		this.pioche = new Pioche();
		this.lstCases = new ArrayList<Case>();
	}

	/* ---------------------------------- */
	/*            Accesseurs              */
	/* ---------------------------------- */

	public String     getEspece  () {return this.espece;              }
	public Pioche     getPioche  () {return this.pioche;              }
	public Carte      getCarte   () {return this.pioche.getCarte();   }
	public List<Case> getlstCases() {return this.lstCases;            }
	public Case       getPremier () {return this.lstCases.getFirst(); }
	public Case       getDernier () {return this.lstCases.getLast() ; }

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

	/* ---------------------------------- */
	/*          Méthodes standard         */
	/* ---------------------------------- */

	public String toString()
	{
		return "Espece : " + this.espece + "\n" +
			   this.pioche;
	}
}
