package source.metier;

public class Base extends Jeton
{
	private String espece;

	public static Base creerBase(char symbole, String espece)
	{
		if (!Jeton.parametresValide(symbole) ) return null;
		return new Base(symbole, espece);
	}
	
	private Base(char symbole, String espece)
	{
		super(symbole);
		this.espece = espece;
	}
	
	public String getEspece(){return this.espece;}
	
	public String toString()
	{
		return super.toString() + ", espece: " + this.espece;
	}
}
