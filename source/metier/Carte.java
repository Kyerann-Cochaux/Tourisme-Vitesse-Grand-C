package source.metier;

public class Carte
{
	private String symbole;
	private boolean premium;
	
	public static Carte creerCarte(String symbole, boolean premium)
	{
		for(String esp : Plateau.TAB_FORMES)
		{
			if(esp.equals(symbole) )
				return new Carte(symbole, premium);
			
		}
		
		return null;
	}
	
	public Carte(String symbole, boolean premium)
	{
		this.symbole = symbole;
		this.premium = premium;
	}
	
	public String  getSymbole() {return this.symbole;}
	public boolean getpremium() {return this.premium;}
	
	public String toString()
	{
		return "Symbole : " + this.symbole + ", premium : " + this.premium;
	}
}
