package source.metier;

public class Jeton
{
	private char   symbole;
	private String espece ;

	/*Factory pour verifier si le symbole du jeton fait partie des symbole autoriser 
	  et si l'espece n'est pas null ou vide*/
	public static Jeton creerJeton(char symbole, String espece)
	{
		symbole = Character.toUpperCase(symbole);

		if(!Jeton.parametresValide         (symbole     ) ) return null;	
		if (espece == null || espece.equals("") ) return new Jeton(symbole);

		return new Jeton(symbole, espece);

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
	
	protected static boolean parametresValide(char symbole)
	{
		return (symbole != 'G' && symbole != 'O' && symbole != 'T' && symbole != 'V');
	}
	
	/* ---------------------------------- */
	/*          méthodes standard         */
	/* ---------------------------------- */
	
	public String toString()
	{
		return "Symbole : " + this.symbole;
	}
}
