package source.metier;

public class Case 
{
	private Planete planete;
	private int   posX, posY;

	public Case(int posX, int posY, Planete planete)
	{
		this.planete = planete;
	}

	public Case(int posX, int posY)
	{
		this(posX, posY, null);
	}
	
	
	/* ---------------------------------- */
	/*            Accesseurs              */
	/* ---------------------------------- */
	
	public Planete   getPlanete() {return this.planete;}
	public boolean estVide () { return this.getPlanete() == null;}
	
	/* ---------------------------------- */
	/*           Modificateurs            */
	/* ---------------------------------- */

	public void setPlanete(Planete planete) { this.planete = planete;}

	/* ---------------------------------- */
	/*          Méthodes standard         */
	/* ---------------------------------- */

	public String toString()
	{
		return "Case : " + ( this.estVide() ? "Aucun" : this.getPlanete().getSymbole() ) + " [" + this.posX + ":" + this.posY + "]";
	}
	
}