package source.metier;

public class Liaison
{
	private Case planeteSource;
	private Case planeteDestination;
	
	private String espece;
	
	/*Factory pour verifier si les parametres ne sont pas null et si la planete de destination n'est pas egale a celle de départ */

	public static Liaison creerLiaison(Case planeteSource, Case planeteDestination, String espece)
	{
		if (planeteDestination == null         ) return null;
		if (planeteSource      == null         ) return null; 
		if (planeteDestination == planeteSource) return null;

		return new Liaison(planeteSource,  planeteDestination, espece);
	}

	public Liaison(Case planeteSource, Case planeteDestination, String espece)
	{
		this.planeteSource      = planeteSource;
		this.planeteDestination = planeteDestination;
		this.espece             = espece;
	}
	
	/* ---------------------------------- */
	/*               Accesseurs           */
	/* ---------------------------------- */
	
	public Case getPlaneteSource     () {return this.planeteSource     ;}
	public Case getPlaneteDestination() {return this.planeteDestination;}
	public String getEspece          () {return this.espece            ;}
	
	/* ---------------------------------- */
	/*               Modificateurs        */
	/* ---------------------------------- */
	
	public boolean setEspece(String espece)
	{
		if( espece == null || espece.equals("") ) return false;

		this.espece = espece;
		return true;
	}
	
	/* ---------------------------------- */
	/*          Méthodes standard         */
	/* ---------------------------------- */
	
	public String toString()
	{
		return "Planete source      : " + this.planeteSource      +
		       "Planete destination : " + this.planeteDestination +
		       "Espece :              " + this.espece;
	}
}
