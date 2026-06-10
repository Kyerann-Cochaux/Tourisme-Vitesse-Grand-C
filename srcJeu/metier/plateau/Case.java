package srcJeu.metier;

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
	
	public int     getPosX      () { return this.posX;}
	public int     getPosY      () { return this.posY;}
	public Planete getPlanete   () { return this.planete   ;}
	public int     getNumSysteme() { return this.numSysteme;}	
	
	/* ---------------------------------- */
	/*           Modificateurs            */
	/* ---------------------------------- */
	
	public void setPlanete   (Planete planete) { this.planete    = planete   ;}
	public void setNumSysteme(int  numSysteme) { this.numSysteme = numSysteme;}
	
	
	/* ---------------------------------- */
	/*           Autres Méthodes          */
	/* ---------------------------------- */
	public boolean estVide  () { return this.getPlanete() == null;}
	public boolean estNeutre() { return this.numSysteme   == -1  ;}
	
	public String toString()
	{
		return "Case : " + ( this.estVide() ? "Aucun" : this.getPlanete().getSymbole() ) + " [" + this.posX + ":" + this.posY + "]";
	}
	
}