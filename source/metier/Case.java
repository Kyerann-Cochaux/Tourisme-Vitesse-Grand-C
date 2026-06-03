package source.metier;

public class Case 
{
	private Planete planete;
	private int     posX, posY;
	private int     numSysteme;

	public Case(int posX, int posY)
	{
		this.posX       = posX;
		this.posY       = posY;
		this.numSysteme = -1;
		this.planete    = null;
	}
	
	
	/* ---------------------------------- */
	/*            Accesseurs              */
	/* ---------------------------------- */
	
	public Planete getPlanete() {return this.planete;}
	public boolean estVide   () { return this.getPlanete() == null;}
	
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