package source.metier;

public class Jeton
{
	private char symbole;
	
	public static Jeton creerJetons(char symbole)
	{
		symbole = Character.toUpperCase(symbole);

		if(symbole != 'G' && symbole != 'O' && symbole != 'T' && symbole != 'V')
			return null;
		
		return new Jeton(symbole);
	}

	protected Jeton(char symbole)
	{
		this.symbole = symbole;
	}
	
	public char getSymbole() {return this.symbole;}
	
	public String toString()
	{
		return "Symbole : " + this.symbole;
	}
}
