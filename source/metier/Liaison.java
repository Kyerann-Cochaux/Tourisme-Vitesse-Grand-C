package source.metier;

public class Liaison
{
	private Jeton planeteSource;
	private Jeton planeteDestination;
	
	private String espece;
	
	public Liaison(Jeton planeteSource, Jeton planeteDestination, String espece)
	{
		this.planeteSource      = planeteSource;
		this.planeteDestination = planeteDestination;
		this.espece             = espece;
	}
	
	/* ---------------------------------- */
	/*               Getters              */
	/* ---------------------------------- */
	
	public Jeton getPlaneteSource     () {return this.planeteSource     ;}
	public Jeton getPlaneteDestination() {return this.planeteDestination;}
	public String getEspece           () {return this.espece            ;}
	
	/* ---------------------------------- */
	/*               Setters              */
	/* ---------------------------------- */
	
	public boolean setEspece(String espece)
	{
		if( espece == null || espece.equals("") ) return false;

		this.espece = espece;
		return true;
	}
	
	/* ---------------------------------- */
	/*          méthodes standard         */
	/* ---------------------------------- */
	
	public String toString()
	{
		return "Planete source : "      + this.planeteSource      +
		       "Planete destination : " + this.planeteDestination +
		       "Espece :              " + this.espece;
	}
}
