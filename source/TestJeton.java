package source;

import source.metier.Jeton;

public class TestJeton
{
	public static void main(String[] args) 
	{
		
		Jeton j;
		
		j = Jeton.creerJeton('A',null);
		System.out.println( (j == null) ? "OK" : "Erreur");
		
		j = Jeton.creerJeton('G',null);
		System.out.println( (j != null) ? "OK" : "Erreur");
		System.out.println(j);
		
		j = Jeton.creerJeton('O',null);
		System.out.println( (j != null) ? "OK" : "Erreur");
		
		j = Jeton.creerJeton('T',null);
		System.out.println( (j != null) ? "OK" : "Erreur");
		
		j = Jeton.creerJeton('V',null);
		System.out.println( (j != null) ? "OK" : "Erreur");
		
	}
	
	
}