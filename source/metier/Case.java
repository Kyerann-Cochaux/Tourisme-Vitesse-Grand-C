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
	/*               Setters              */
	/* ---------------------------------- */

	public boolean setJeton(Jeton jeton)
	{
		if (jeton == null && jeton == this.jeton) return false;
		
		this.jeton = jeton;

		return true;
	}

	/* ---------------------------------- */
	/*          méthodes standard         */
	/* ---------------------------------- */

	public String toString()
	{
		return "Case : " + this.jeton.toString();
	}
	
}