public class Jeton
{
	char symbole;
	
	private Jeton(char symbole)
	{
		this.symbole = symbole;
	}
	
	public static creeJetons(char symbole)
	{
		char temp = Character.toUpperCase(symbole);
		if(temp == 'G' || temp == 'O' || temp == 'T' || temp == 'V')
		{
			return new Jeton(temp);
		}
		return null;
	}
	
	public char getSymbole(){return this.symbole;}
	
	public String toString()
	{
		return "Symbole: " + this.symbole;
	}
}
