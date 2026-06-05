package source;

import source.metier.Metier;
import source.metier.Plateau;

public class TestZone
{
	private static int nbTests;
	
	
	public static void main(String[] args) 
	{
		
		Metier m = new Metier();
		Plateau p = null;
		
		m.initialiserPlateau(7,10,3,2);
		
		
		p = m.getPlateau();
		
		System.out.println("/* ---------------------------------- */");
		System.out.println("/*           Ajout des Zones          */");
		System.out.println("/* ---------------------------------- */");
		
		
		
		
		// zone 0
		
		TestZone.test(p.setNumSysteme(0, 0, 0 ),  "true" );
		TestZone.test(p.setNumSysteme(0, 1, 0 ),  "true" );
		
		// zone 0 non adjascente
		TestZone.test(p.setNumSysteme(0, 2, 2 ),  "false");
		
		
		
		
		
		// zone 1
		
		TestZone.test(p.setNumSysteme(1, 3, 0 )   , "true" );
		
		//Zone 1 Non adjacent : 
		TestZone.test(p.setNumSysteme(1, 5, 0 ), "false" );
		
		TestZone.test(p.setNumSysteme(1, 3, 1 )   , "true" );
		TestZone.test(p.setNumSysteme(1, 4, 1 )   , "true" );
		TestZone.test(p.setNumSysteme(1, 5, 1 )   , "true" );
		TestZone.test(p.setNumSysteme(1, 5, 0 )   , "true" );
		
		// y negatif
		TestZone.test(p.setNumSysteme(1, 5, -1  ), "false" );
		
		
		
		
		TestZone.test(p.setNumSysteme(0, 0, 1 ),  "true" );
		
		// zone 2
		
		TestZone.test(p.setNumSysteme(2, 0, 4 )   , "true" );
		TestZone.test(p.setNumSysteme(2, 0, 3 )   , "true" );
		TestZone.test(p.setNumSysteme(2, 1, 3 )   , "true" );
		
		
		
		// zone 1 isolée
		TestZone.test(p.setNumSysteme(1, 1, 4 ),   "false" );
		
		// x negatif
		TestZone.test(p.setNumSysteme(2, -1, 4 ) , "false" );
		
		
		
		
		
		// zone 3
		TestZone.test(p.setNumSysteme(3, 0, 6 )   , "true" );
		TestZone.test(p.setNumSysteme(3, 1, 6 )   , "true" );
		TestZone.test(p.setNumSysteme(3, 0, 5 )   , "true" );
		TestZone.test(p.setNumSysteme(3, 0, 5 )   , "true" );
		
		
		
		
		
		// zone 4
		TestZone.test(p.setNumSysteme(4, 9, 5 )   , "true" );
		TestZone.test(p.setNumSysteme(4, 9, 6 )   , "true" );
		TestZone.test(p.setNumSysteme(4, 8, 6 )   , "true" );
		
		
		
		
		
		// zone 5
		TestZone.test(p.setNumSysteme(5, 9, 3 )   , "true" );
		TestZone.test(p.setNumSysteme(5, 8, 3 )   , "true" );
		
		//x trop grand
		TestZone.test(p.setNumSysteme(5, 500, 3 ), "false" );
		
		// zone 1 deja prise
		TestZone.test(p.setNumSysteme(1, 8  , 2 ), "false" );
		
		// zone 1 deja prise a gauche
		TestZone.test(p.setNumSysteme(1, 7  , 3 ), "false" );
		
		// zone 1 deja prise en bas
		TestZone.test(p.setNumSysteme(1, 7  , 4 ), "false" );
		
		
		
		
		
		// zone 6
		TestZone.test(p.setNumSysteme(6,  9, 0 )   , "true" );
		TestZone.test(p.setNumSysteme(6,  8, 0 )   , "true" );
		TestZone.test(p.setNumSysteme(6,  7, 0 )   , "true" );
		TestZone.test(p.setNumSysteme(6,  6, 0 )   , "true" );
		TestZone.test(p.setNumSysteme(6,  5, 0 )   , "true" );
		TestZone.test(p.setNumSysteme(6,  7, 1 )   , "true" );
		
		
		
		
		
		// zone 7
		TestZone.test(p.setNumSysteme(7, 3, 6 ), "true" );
		// y trop grand
		TestZone.test(p.setNumSysteme(7, 3, 700 ), "false");
		System.out.println();
		System.out.println(p);
		
		
	}
	
	
	private static void test(String resultat, String resultatAttendu)
	{
		String sEgalite = String.format("%15s = %-15s", resultat, resultatAttendu);
		String sNumTest = String.format("Test %3d", ++TestZone.nbTests);
		
		System.out.println( sNumTest                      +
		                    " Resultat : " + sEgalite + " => " +
		                  ( resultat.equals(resultatAttendu) ? "OK" : "ERREUR" ) );
	}
	
	
	private static void test(boolean resultat, String resultatAttendu)
	{
		TestZone.test("" + resultat, resultatAttendu);
	}
	
	
}
