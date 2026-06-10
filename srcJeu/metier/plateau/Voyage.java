package srcJeu.metier;

public class Voyage
{
	private Case planeteSource;
	private Case planeteDestination;
	
	private String espece;
	
	/*Factory pour vérifier si les parametres ne sont pas null et si la planete de destination n'est pas égale a celle de départ */

	public static Voyage creerVoyage(Case planeteSource, Case planeteDestination)
	{
		if (planeteSource      == null              ) return null; 
		if (planeteDestination == null              ) return null;
		if (planeteSource      == planeteDestination) return null;

		return new Voyage(planeteSource,  planeteDestination);
	}

	private Voyage(Case planeteSource, Case planeteDestination)
	{
		this.planeteSource      = planeteSource;
		this.planeteDestination = planeteDestination;
		this.espece             = null;	
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
		return "Planete source      en [" + this.planeteSource     .getPosY() +":"+ this.planeteSource     .getPosX() + "]\n"+
		       "Planete destination en [" + this.planeteDestination.getPosY() +":"+ this.planeteDestination.getPosX() + "]\n"+
		       "Espece              : "   + this.espece                                                               + "\n";
	}
}
