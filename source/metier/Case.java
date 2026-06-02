package source.metier;

public class Case 
{
	private Jeton jeton;

	public Case(Jeton jeton)
	{
		this.jeton = jeton;
	}

	public Case()
	{
		this(null);
	}

	/* ---------------------------------- */
	/*               Getters              */
	/* ---------------------------------- */

	public Jeton getJeton() {return this.jeton;}

	/* ---------------------------------- */
	/*          méthodes standard         */
	/* ---------------------------------- */

	public String toString()
	{
		return "Case : " + this.jeton.toString();
	}
	
}