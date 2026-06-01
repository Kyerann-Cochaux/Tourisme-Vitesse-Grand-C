package metier;

public class Base extends Jeton
{
	private String espece;
	
	public Base(char symbole, String espece)
	{
		super(symbole);
		this.espece = espece;
	}
	
	public String getEspece(){return this.espece;}
	
	public string toString()
	{
		return super.toString() + ", espece: " + this.espece;
	}
}
