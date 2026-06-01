package source;


import source.metier.Jeton;

public class TestJeton
{
	public static void main(String[] args) 
	{

		Jeton j;

		j = Jeton.creerJetons('A');
		System.out.println( (j == null) ? "OK" : "Erreur");
		
		j = Jeton.creerJetons('G');
		System.out.println( (j != null) ? "OK" : "Erreur");

		j = Jeton.creerJetons('O');
		System.out.println( (j != null) ? "OK" : "Erreur");

		j = Jeton.creerJetons('T');
		System.out.println( (j != null) ? "OK" : "Erreur");

		j = Jeton.creerJetons('V');
		System.out.println( (j != null) ? "OK" : "Erreur");


	}
}