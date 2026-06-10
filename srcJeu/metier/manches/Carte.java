package srcJeu.metier.manches;

import srcJeu.metier.Metier;

public class Carte
{
	private String symbole;
	private boolean premium;
	
	/*Factory pour verifier si le symbole en paramètre fait partie des symbole
	  du tableau TAB_FORMES*/
	  
	public static Carte creerCarte(String symbole, boolean premium)
	{
		if (symbole.equals("Joker")) 
			return new Carte(symbole, premium);

		for(String esp : Metier.TAB_PLANETES)
		{
			if(esp.equals(symbole) )
				return new Carte(symbole, premium);
			
		}
		
		return null;
	}
	
	private Carte(String symbole, boolean premium)
	{
		this.symbole = symbole;
		this.premium = premium;
	}
	
	/* ---------------------------------- */
	/*            Accesseurs              */
	/* ---------------------------------- */
	
	public String  getSymbole() {return this.symbole;}
	public boolean getPremium () {return this.premium;}
	
	/* ---------------------------------- */
	/*          Méthodes standard         */
	/* ---------------------------------- */
	
	public String toString()
	{
		return "Symbole : " + this.symbole + ", premium : " + this.premium;
	}
}
