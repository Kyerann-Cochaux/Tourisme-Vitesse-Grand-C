package srcJeu.metier.manches;

public class Manche
{
	private Pioche pioche;
	private String espece;

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
	}

	/* ---------------------------------- */
	/*            Accesseurs              */
	/* ---------------------------------- */

	public String getEspece(){return this.espece;           }
	public Pioche getPioche(){return this.pioche;           }
	public Carte  getCarte (){return this.pioche.getCarte();}

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

	/* ---------------------------------- */
	/*          Méthodes standard         */
	/* ---------------------------------- */

	public String toString()
	{
		return "Espece : " + this.espece + "\n" +
			   this.pioche;
	}
}
