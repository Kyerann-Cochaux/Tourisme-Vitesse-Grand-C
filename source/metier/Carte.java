package source.metier;

public class Carte
{
	private String symbole;
	private Boolean type;
	
	public static Carte creerCarte(String symbole, Boolean type)
	{
		for(elem : Plateau.TAB_FORMES)
		{
			if(elem.equals(symbole))
			{
				return Carte(symbole, type);
			}
		}
		return null;
	}
	
	public Carte(String symbole, String type)
	{
		this.symbole = symbole;
		this.type    = type;
	}
	
	public String  getSymbole(){return this.symbole;}
	public Boolean getType()   {return this.type;   }
	
	public String toString()
	{
		return "Symbole : " + this.symbole + ", type : " + this.type;
	}
}
