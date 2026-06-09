package srcEdition;

import srcEdition.metier.*;

public class TestSauvegardes
{
	private static int nbTests;
	
	public static void main(String[] args)
	{
		
		Metier metier = new Metier();
		
		TestMetier.genererPlateauTest(metier);
		
		System.out.println(metier.getPlateau());
		
		
		System.out.println();
		System.out.println();
		System.out.println("--------");
		System.out.println("---TEST sauvegarderPlateau(String nomSauvegarde)");
		System.out.println();
		
		// sauvegarde d'un plateau avec le même nom
		TestSauvegardes.test(metier.sauvegarderPlateau("test"  ), "true");
		TestSauvegardes.test(metier.sauvegarderPlateau("test"  ), "true");
		TestSauvegardes.test(metier.sauvegarderPlateau("test"  ), "true");
		TestSauvegardes.test(metier.sauvegarderPlateau("test-0"), "true");
		TestSauvegardes.test(metier.sauvegarderPlateau("test-0"), "true");
		TestSauvegardes.test(metier.sauvegarderPlateau("test-0"), "true");
		
		
		
		
		
		System.out.println();
		System.out.println();
		System.out.println("--------");
		System.out.println("---TEST chargerPlateau(String cheminSauvegarde)");
		System.out.println();
		
		TestSauvegardes.test(metier.chargerPlateau("../sauvegardes/test.data"   ), "true");
		TestSauvegardes.test(metier.chargerPlateau("../sauvegardes/test-0.data" ), "true");
		TestSauvegardes.test(metier.chargerPlateau("../sauvegardes/asterix.data"), "false");
		
		
		
		
		System.out.println(metier.getPlateau());
		
		
		
	}
	
	private static void test(String resultat, String resultatAttendu)
	{
		String sEgalite = String.format("%15s = %-15s", resultat, resultatAttendu);
		String sNumTest = String.format("Test %3d", ++TestSauvegardes.nbTests);
		
		System.out.println( sNumTest                      +
		                    " Resultat : " + sEgalite + " => " +
		                  ( resultat.equals(resultatAttendu) ? "OK" : "ERREUR" ) );
	}
	
	
	private static void test(boolean resultat, String resultatAttendu)
	{
		TestSauvegardes.test("" + resultat, resultatAttendu);
	}
	
}