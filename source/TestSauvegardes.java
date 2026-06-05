package source;

import javax.swing.text.PlainDocument;

import source.metier.*;

public class TestSauvegardes
{
	private static int nbTests;
	
	public static void main(String[] args)
	{
		
		Metier metier = new Metier();
		
		TestSauvegardes.genererPLateauTest(metier);
		
		System.out.println(metier.getPlateau());
		
		
		System.out.println();
		System.out.println();
		System.out.println("--------");
		System.out.println("---TEST sauvegardeExiste(String nomSauvegarde)");
		System.out.println();
		
		metier.sauvegarderPlateau("oui");
		
		TestSauvegardes.test(metier.sauvegardeExiste("oui"), "true");
		TestSauvegardes.test(metier.sauvegardeExiste("non"), "false");
		
		System.out.println();
		System.out.println("Chargement d'un plateau...");
		metier.chargerPlateau("../source/metier/sauvegardes/test.data");
		
		System.out.println("Sauvegarde de ce plateau...");
		metier.sauvegarderPlateau("oui");
		
		TestSauvegardes.test(metier.sauvegardeExiste("oui"), "true");
		TestSauvegardes.test(metier.sauvegardeExiste("non"), "false");
		
		
		
		
		
		System.out.println();
		System.out.println();
		System.out.println("--------");
		System.out.println("---TEST sauvegarderPlateau(String nomSauvegarde)");
		System.out.println();
		
		// sauvegarde d'un plateau avec le même nom
		TestSauvegardes.test(metier.sauvegarderPlateau("test"), "true");
		TestSauvegardes.test(metier.sauvegarderPlateau("test"), "true");
		
		
		
		
		
		System.out.println();
		System.out.println();
		System.out.println("--------");
		System.out.println("---TEST chargerPlateau(String cheminSauvegarde)");
		System.out.println();
		
		test(metier.chargerPlateau("../source/metier/sauvegardes/test.data"), "true");
		test(metier.chargerPlateau("../source/metier/sauvegardes/test-0.data"), "true");
		test(metier.chargerPlateau("../source/metier/sauvegardes/asterix.data"), "false");
		
		
		
		
		System.out.println(metier.getPlateau());
		
		
		
	}
	
	
	private static void genererPLateauTest(Metier metier)
	{
		int[][] tabSys = new int[][]
		{
			{0,0,0,0,0,1,1,1,1,1},
			{0,0,0,0,0,1,1,1,1,1},
			{0,0,0,0,0,1,1,1,1,1},
			{0,0,0,0,0,1,1,1,1,1},
			{2,2,2,2,2,2,2,2,2,2},
			{2,2,2,2,2,2,2,2,2,2},
			{2,2,2,2,2,2,2,2,2,2},
		};
		
		char[][] tabPla = new char[][]
		{
			{'G',' ',' ',' ',' ',' ',' ',' ',' ',' '},
			{' ','G',' ',' ',' ',' ',' ','V',' ',' '},
			{' ',' ',' ',' ',' ','O',' ',' ','V',' '},
			{'T',' ',' ','G',' ',' ',' ',' ',' ',' '},
			{' ',' ','O',' ',' ','O',' ','G',' ',' '},
			{' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
			{' ','V',' ',' ','T',' ',' ',' ','G',' '},
		};
		
		char[][] tabEsp = new char[][]
		{
			{'F',' ',' ',' ',' ',' ',' ',' ',' ',' '},
			{' ','S',' ',' ',' ',' ',' ','C',' ',' '},
			{' ',' ',' ',' ',' ','C',' ',' ','F',' '},
			{'A',' ',' ','S',' ',' ',' ',' ',' ',' '},
			{' ',' ','C',' ',' ','S',' ','A',' ',' '},
			{' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
			{' ','C',' ',' ','S',' ',' ',' ','A',' '},
		};
		
		
		metier.initialiserPlateau(tabSys.length, tabSys[0].length, 2, 3);
		
		
		for (int y = 0; y < tabSys.length; y++)
		{
			for (int x = 0; x < tabSys[0].length; x++)
			{
				metier.getPlateau().setNumSysteme(tabSys[y][x], x, y);
				
				Planete planete = Planete.creerPlanete(tabPla[y][x], null);
				
				metier.getPlateau().ajouterPlanete(x, y, planete);
				
				for (int numEspece = 0; numEspece < Plateau.TAB_ESPECES.length; numEspece++)
				{
					Planete planeteTemp = metier.getPlateau()
					                            .getCase(x, y)
					                            .getPlanete();
					
					if ( planeteTemp != null && Plateau.TAB_ESPECES[numEspece].charAt(0) == tabEsp[y][x] )
						planete.setEspece(Plateau.TAB_ESPECES[numEspece]);
				}
			}
		}
		
	}
	
	private static void test(String resultat, String resultatAttendu)
	{
		String sEgalite = String.format("%15s = %-15s", resultat, resultatAttendu);
		
		System.out.println( "Test n°" + (TestSauvegardes.nbTests++)                      +
		                    " Resultat : " + sEgalite + " => " +
		                  ( resultat.equals(resultatAttendu) ? "OK" : "ERREUR" ) );
	}
	
	
	private static void test(boolean resultat, String resultatAttendu)
	{
		TestSauvegardes.test("" + resultat, resultatAttendu);
	}
	
}