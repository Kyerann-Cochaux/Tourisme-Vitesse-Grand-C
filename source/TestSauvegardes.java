package source;

import source.metier.*;

public class TestSauvegardes
{
	private static int nbTests;
	
	public static void main(String[] args)
	{
		
		Metier metier = new Metier();
		
		metier.chargerPlateau("../source/metier/sauvegardes/sauvegarde-000.data");
		
		Plateau plateau = metier.getPlateau();
		
		
		
		System.out.println("Chargement d'un plateau sans les fix des zones");
		System.out.println();
		
		System.out.println(plateau);
		
		
		System.out.println("Sauvegarde et chargement de plateaux");
		
		metier.sauvegarderPlateau("testSave");
		
		metier.initialiserPlateau(7, 10, 2, 2);
		plateau = metier.getPlateau();
		
		metier.chargerPlateau("../source/metier/sauvegardes/testSave.data");
		
		plateau = metier.getPlateau();
		
		System.out.println(plateau);
		
		
		
	}
	
	private static void test(String resultat, String resultatAttendu)
	{
		System.out.println( "Test n°" + (TestSauvegardes.nbTests++)                      +
		                    " Resultat : " + resultat + " = " + resultatAttendu + " => " +
		                  ( resultat.equals(resultatAttendu) ? "OK" : "ERREUR" ) );
	}
	
	
}