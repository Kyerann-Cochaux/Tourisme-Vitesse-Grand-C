package source.metier;

public class Jeton
{
	private char   symbole;
	private String espece ;

	// Ajout d'un paramètre boolean base pour savoir si le jeton est une base ou non
	
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

	
	public char getSymbole() {return this.symbole;}

	protected static boolean parametresValide(char symbole)
	{
		return (symbole != 'G' && symbole != 'O' && symbole != 'T' && symbole != 'V');
	}
	
	
	public String toString()
	{
		return "Symbole : " + this.symbole;
	}
}
