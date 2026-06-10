package srcJeu;

import srcJeu.metier.Metier;
import srcJeu.metier.plateau.Plateau;

public class TestZone
{
	private static int nbTests;
	
	
	public static void main(String[] args) 
	{
		
		Metier metier = new Metier();
		Plateau plateau = null;
		
		metier.initialiserPlateau(7,10,3,2);
		
		
		plateau = metier.getPlateau();
		
		System.out.println("/* ---------------------------------- */");
		System.out.println("/*           Ajout des Zones          */");
		System.out.println("/* ---------------------------------- */");
		
		
		
		
		// zone 0
		
		TestZone.test(plateau.setNumSysteme(0, 0, 0 ),  "true" );
		TestZone.test(plateau.setNumSysteme(0, 1, 0 ),  "true" );
		
		// zone 0 non adjascente
		TestZone.test(plateau.setNumSysteme(0, 2, 2 ),  "false");
		
		
		
		
		
		// zone 1
		
		TestZone.test(plateau.setNumSysteme(1, 3, 0 )   , "true" );
		
		//Zone 1 Non adjacent : 
		TestZone.test(plateau.setNumSysteme(1, 5, 0 ), "false" );
		
		TestZone.test(plateau.setNumSysteme(1, 3, 1 )   , "true" );
		TestZone.test(plateau.setNumSysteme(1, 4, 1 )   , "true" );
		TestZone.test(plateau.setNumSysteme(1, 5, 1 )   , "true" );
		TestZone.test(plateau.setNumSysteme(1, 5, 0 )   , "true" );
		
		// y negatif
		TestZone.test(plateau.setNumSysteme(1, 5, -1  ), "false" );
		
		
		
		
		TestZone.test(plateau.setNumSysteme(0, 0, 1 ),  "true" );
		
		// zone 2
		
		TestZone.test(plateau.setNumSysteme(2, 0, 4 )   , "true" );
		TestZone.test(plateau.setNumSysteme(2, 0, 3 )   , "true" );
		TestZone.test(plateau.setNumSysteme(2, 1, 3 )   , "true" );
		
		
		
		// zone 1 isolée
		TestZone.test(plateau.setNumSysteme(1, 1, 4 ),   "false" );
		
		// x negatif
		TestZone.test(plateau.setNumSysteme(2, -1, 4 ) , "false" );
		
		
		
		
		
		// zone 3
		TestZone.test(plateau.setNumSysteme(3, 0, 6 )   , "true" );
		TestZone.test(plateau.setNumSysteme(3, 1, 6 )   , "true" );
		TestZone.test(plateau.setNumSysteme(3, 0, 5 )   , "true" );
		TestZone.test(plateau.setNumSysteme(3, 0, 5 )   , "true" );
		
		
		
		
		
		// zone 4
		TestZone.test(plateau.setNumSysteme(4, 9, 5 )   , "true" );
		TestZone.test(plateau.setNumSysteme(4, 9, 6 )   , "true" );
		TestZone.test(plateau.setNumSysteme(4, 8, 6 )   , "true" );
		
		
		
		
		
		// zone 5
		TestZone.test(plateau.setNumSysteme(5, 9, 3 )   , "true" );
		TestZone.test(plateau.setNumSysteme(5, 8, 3 )   , "true" );
		for (int x = 8; x >= 5; x--)
			TestZone.test(plateau.setNumSysteme(5, x, 4 )   , "true" );
		
		//x trop grand
		TestZone.test(plateau.setNumSysteme(5, 500, 3 ), "false" );
		
		// zone 1 deja prise
		TestZone.test(plateau.setNumSysteme(1, 8  , 2 ), "false" );
		
		// zone 1 deja prise a gauche
		TestZone.test(plateau.setNumSysteme(1, 7  , 3 ), "false" );
		
		// zone 1 deja prise en bas
		TestZone.test(plateau.setNumSysteme(1, 7  , 4 ), "false" );
		
		
		
		
		
		// zone 6
		TestZone.test(plateau.setNumSysteme(6,  9, 0 )   , "true" );
		TestZone.test(plateau.setNumSysteme(6,  8, 0 )   , "true" );
		TestZone.test(plateau.setNumSysteme(6,  7, 0 )   , "true" );
		TestZone.test(plateau.setNumSysteme(6,  6, 0 )   , "true" );
		TestZone.test(plateau.setNumSysteme(6,  5, 0 )   , "true" );
		TestZone.test(plateau.setNumSysteme(6,  7, 1 )   , "true" );
		TestZone.test(plateau.setNumSysteme(6,  4, 0 )   , "true" );
		
		
		
		
		// zone 7
		TestZone.test(plateau.setNumSysteme(7, 3, 6 ), "true" );
		for (int x = 3; x <= 8; x++)
			TestZone.test(plateau.setNumSysteme(7, x, 5 ), "true" );
		
		// y trop grand
		TestZone.test(plateau.setNumSysteme(7, 3, 700 ), "false");
		
		
		/**
		
		
		System.out.println();
		System.out.println("---");
		System.out.println("tests découpe des zones");
		
		// coupage de la zone 1
		plateau.setNumSysteme(6,  4, 1 );
		// zone 8 qui coupe la zone 6
		plateau.setNumSysteme(9, 7, 0);
		// zone 5 qui coupe la zone 7
		plateau.setNumSysteme(5, 6, 5);
		
		System.out.println(plateau.afficherSystemes());
		
		TestZone.test(plateau.estZoneScindee(1), "false");
		TestZone.test(plateau.estZoneScindee(6), "false");
		TestZone.test(plateau.estZoneScindee(7), "false");
		
		
		
		System.out.println();
		System.out.println("+++++");
		System.out.println("+++ Test tailleZone(numZone)");
		System.out.println(plateau.afficherSystemes());
		
		TestZone.test(plateau.tailleZone(1), "2");
		TestZone.test(plateau.tailleZone(6), "4");
		
		
		System.out.println();
		System.out.println("+++++");
		System.out.println("+++ Test parcoursZone(caseDep)");
		
		System.out.println(plateau.afficherSystemes());
		
		
		TestZone.test(plateau.parcoursZone(plateau.getCase(0, 0)).size(),  "3");
		TestZone.test(plateau.parcoursZone(plateau.getCase(3, 0)).size(),  "2");
		TestZone.test(plateau.parcoursZone(plateau.getCase(8, 3)).size(),  "7");
		TestZone.test(plateau.parcoursZone(plateau.getCase(5, 0)).size(),  "4");
		TestZone.test(plateau.parcoursZone(plateau.getCase(1, 1)).size(), "29");// 29 car 2 zones sont isolées
		
		
		
		System.out.println();
		System.out.println("+++++");
		System.out.println("+++ Test remplirZone(numZone, caseDep)");
		
		System.out.println(plateau.afficherSystemes());
		
		TestZone.test(plateau.remplirZone( 1, plateau.getCase(0, 0)), "false");
		TestZone.test(plateau.remplirZone( 2, plateau.getCase(3, 0)), "false");
		TestZone.test(plateau.remplirZone(12, plateau.getCase(3, 0)), "false");
		TestZone.test(plateau.remplirZone(13, plateau.getCase(4, 6)), "true" );
		TestZone.test(plateau.remplirZone( 3, plateau.getCase(0, 3)), "true" );
		TestZone.test(plateau.remplirZone(50, plateau.getCase(1, 1)), "false");
		
		System.out.println(plateau.afficherSystemes());
		
		System.out.println();
		System.out.println("+++++");
		System.out.println("+++ Test estZoneScindee(numZone)");
		
		System.out.println(plateau.afficherSystemes());
		
		test(plateau.estZoneScindee(-1), "true");
		test(plateau.estZoneScindee(1), "false");
		test(plateau.estZoneScindee(6), "false");
		test(plateau.estZoneScindee(5), "false");
		test(plateau.estZoneScindee(9), "false");
		
		System.out.println();
		System.out.println("+++++");
		System.out.println("+++ Test remplirZoneVide(numZone)");

		plateau.remplirZoneVide();

		System.out.println(plateau.afficherSystemes());
		test(plateau.estZoneScindee(-1), "false");

 */
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
	private static void test(int resultat, String resultatAttendu)
	{
		TestZone.test("" + resultat, resultatAttendu);
	}
	
	
}
