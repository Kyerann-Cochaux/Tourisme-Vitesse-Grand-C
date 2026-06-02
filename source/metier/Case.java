package source.metier;

public class Case 
{
	private Jeton jeton;
	private int   posX, posY;

	public Case(int posX, int posY, Jeton jeton)
	{
		this.jeton = jeton;
	}

	public Case(int posX, int posY)
	{
		this(posX, posY, null);
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