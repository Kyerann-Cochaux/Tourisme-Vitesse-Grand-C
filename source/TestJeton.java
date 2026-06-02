package source;


import source.metier.Jeton;

public class TestJeton
{
	public static void main(String[] args) 
	{

		Jeton j;

		j = Jeton.creerJetons('A',null);
		System.out.println( (j == null) ? "OK" : "Erreur");
		
		j = Jeton.creerJetons('G',null);
		System.out.println( (j != null) ? "OK" : "Erreur");

		j = Jeton.creerJetons('O',null);
		System.out.println( (j != null) ? "OK" : "Erreur");

		j = Jeton.creerJetons('T',null);
		System.out.println( (j != null) ? "OK" : "Erreur");

		j = Jeton.creerJetons('V',null);
		System.out.println( (j != null) ? "OK" : "Erreur");


	}
}