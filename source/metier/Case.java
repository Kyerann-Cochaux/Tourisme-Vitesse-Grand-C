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
	/*            Accesseurs              */
	/* ---------------------------------- */
	
	public Jeton getJeton() {return this.jeton;}
	
	/* ---------------------------------- */
	/*           Modificateurs            */
	/* ---------------------------------- */

	public boolean setJeton(Jeton jeton)
	{
		if (jeton == null || jeton == this.jeton) return false;
		
		this.jeton = jeton;
		
		return true;
	}

	/* ---------------------------------- */
	/*          Méthodes standard         */
	/* ---------------------------------- */

	public String toString()
	{
		return "Case : " + ( this.getJeton() != null ? this.getJeton().getSymbole() : "Aucun") + " [" + this.posX + ":" + this.posY + "]";
	}
	
}