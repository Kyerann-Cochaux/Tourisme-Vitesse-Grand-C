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
		
		m.initialiserPlateau(11,11,3,2);
		
		
		p = m.getPlateau();
		
		System.out.println("/* ---------------------------------- */");
		System.out.println("/*           Ajout des Zones          */");
		System.out.println("/* ---------------------------------- */");
		
		// zone 0
		
		System.out.println("--------");
		System.out.println(p.setNumSysteme(0, 0, 1 )                              == true  ? "OK" : "ERREUR"  );
		System.out.println(p.setNumSysteme(0, 0, 0 )                              == true  ? "OK" : "ERREUR"  );
		System.out.println(p.setNumSysteme(0, 1, 0 )                              == true  ? "OK" : "ERREUR"  );
		System.out.println("Zone 0 Non adjacent : " + ( p.setNumSysteme(0, 2, 2 ) == false ? "OK" : "ERREUR") );
		
		
		// zone 1
		
		System.out.println(p.setNumSysteme(1, 3, 0 )                             == true  ? "OK" : "ERREUR"   );
		System.out.println("Zone 1 Non adjacent : " + (p.setNumSysteme(1, 5, 0 ) == false ? "OK" : "ERREUR" ) );
		System.out.println(p.setNumSysteme(1, 3, 1 )                             == true  ? "OK" : "ERREUR"   );
		System.out.println(p.setNumSysteme(1, 4, 1 )                             == true  ? "OK" : "ERREUR"   );
		System.out.println(p.setNumSysteme(1, 5, 1 )                             == true  ? "OK" : "ERREUR"   );
		System.out.println(p.setNumSysteme(1, 5, 0 )                             == true  ? "OK" : "ERREUR"   );
		System.out.println("y négatif : "          + ( p.setNumSysteme(1, 5, -1  ) == false ? "OK" : "ERREUR"  ) );
		
		
		// zone 2
		
		System.out.println(p.setNumSysteme(2, 0, 4 )                            == true  ? "OK" : "ERREUR"   );
		System.out.println(p.setNumSysteme(2, 0, 3 )                            == true  ? "OK" : "ERREUR"   );
		System.out.println(p.setNumSysteme(2, 1, 3 )                            == true  ? "OK" : "ERREUR"   );
		System.out.println("zone 1 isolé : "+ (p.setNumSysteme(1, 1, 4 )        == false ? "OK" : "ERREUR"  ) );
		System.out.println("x négatif    : "+ (p.setNumSysteme(2, -1, 4 )         == false ? "OK" : "ERREUR"  ) );
		
		// zone 3
		
		System.out.println(p.setNumSysteme(3, 0, 6 )                            == true  ? "OK" : "ERREUR"   );
		System.out.println(p.setNumSysteme(3, 1, 6 )                            == true  ? "OK" : "ERREUR"   );
		System.out.println(p.setNumSysteme(3, 0, 5 )                            == true  ? "OK" : "ERREUR"   );
		System.out.println(p.setNumSysteme(3, 0, 5 )                            == true  ? "OK" : "ERREUR"   );
		
		// zone 4
		System.out.println(p.setNumSysteme(4, 9, 5 )                            == true  ? "OK" : "ERREUR"   );
		System.out.println(p.setNumSysteme(4, 9, 6 )                            == true  ? "OK" : "ERREUR"   );
		System.out.println(p.setNumSysteme(4, 8, 6 )                            == true  ? "OK" : "ERREUR"   );
		
		// zone 5
		
		System.out.println(p.setNumSysteme(5, 9, 3 )                                 == true  ? "OK" : "ERREUR"   );
		System.out.println(p.setNumSysteme(5, 8, 3 )                                 == true  ? "OK" : "ERREUR"   );
		System.out.println("x trop grand : "           + (p.setNumSysteme(5, 10, 3 ) == false ? "OK" : "ERREUR" ) );
		System.out.println("zone 1 déjà prise (top) : "+ (p.setNumSysteme(1, 8 , 2 ) == false ? "OK" : "ERREUR" ) );
		System.out.println("zone 1 déjà prise (left): "+ (p.setNumSysteme(1, 7 , 3 ) == false ? "OK" : "ERREUR" ) );
		System.out.println("zone 1 déjà prise (down): "+ (p.setNumSysteme(1, 7 , 4 ) == false ? "OK" : "ERREUR" ) );
		
		// zone 6
		
		System.out.println(p.setNumSysteme(6, 9, 1 )                                 == true  ? "OK" : "ERREUR"   );
		System.out.println(p.setNumSysteme(6, 9, 0 )                                 == true  ? "OK" : "ERREUR"   );
		System.out.println(p.setNumSysteme(6, 8, 0 )                                 == true  ? "OK" : "ERREUR"   );
		System.out.println(p.setNumSysteme(6, 7, 0 )                                 == true  ? "OK" : "ERREUR"   );
		System.out.println(p.setNumSysteme(6, 6, 0 )                                 == true  ? "OK" : "ERREUR"   );
		System.out.println(p.setNumSysteme(6, 5, 0 )                                 == true  ? "OK" : "ERREUR"   );
		
		// zone 7
		
		System.out.println(p.setNumSysteme(7, 3, 6 )                                 == true  ? "OK" : "ERREUR"     );
		System.out.println("y trop grand : " + (p.setNumSysteme(7, 3, 7 )            == false ? "OK" : "ERREUR"   ) );
		System.out.println();
		System.out.println(p.afficherSystemes() );
		
		
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