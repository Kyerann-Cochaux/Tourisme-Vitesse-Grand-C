package source.metier;

public class Jeton
{
	private char symbole;
	
	public static Jeton creerJetons(char symbole)
	{
		symbole = Character.toUpperCase(symbole);

		if(!Jeton.parametresValide(symbole) ) return null;	
		return new Jeton(symbole);
	}

	protected Jeton(char symbole)
	{
		this.symbole = symbole;
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
