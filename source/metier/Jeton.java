package source.metier;

public class Jeton
{
	private char   symbole;
	private String espece ;

	/*Factory pour vérifier si le symbole du jeton fait partie des symbole autorisés */

	public static Jeton creerJeton(char symbole, String espece)
	{

		if(!Jeton.parametresValide(symbole) ) return null;	

		// L'espèce est null, donc le Jeton ne possède qu'un symbole
		if (espece == null || espece.equals("") ) return new Jeton(symbole); 

		// L'espèce est non null, le Jeton est donc une base
		return new Jeton(Character.toUpperCase(symbole), espece);

	}

	private Jeton(char symbole, String espece)
	{
		this.symbole = symbole;
		this.espece  = espece;
	}

	private Jeton(char symbole)
	{
		this(symbole, null);
	}

	/* ---------------------------------- */
	/*               Getters              */
	/* ---------------------------------- */
	
	public char   getSymbole() {return this.symbole;}
	public String getEspece () {return this.espece ;}

	/* ---------------------------------- */
	/*           Autres méthodes          */
	/* ---------------------------------- */
	
	private static boolean parametresValide(char symbole)
	{
		return (symbole != 'g' && symbole != 'o' && symbole != 't' && symbole != 'v');
	}

	public boolean estBase() { return this.espece != null && !this.espece.equals("");}

	/* ---------------------------------- */
	/*          méthodes standard         */
	/* ---------------------------------- */

	public String toString()
	{
		return "Symbole : " + this.symbole + ( (this.estBase() ) ? "\n" + 
			   "Base    :"  + this.espece : "");
	}
}
