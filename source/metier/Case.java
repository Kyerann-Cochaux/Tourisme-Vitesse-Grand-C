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
	
	public boolean estVide()
	{
		return this.getJeton() == null;
	}
	
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
		return "Case : " + ( this.estVide() ? "Aucun" : this.getJeton().getSymbole() ) + " [" + this.posX + ":" + this.posY + "]";
	}
	
}