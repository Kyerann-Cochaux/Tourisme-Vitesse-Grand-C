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
		
		
		
		System.out.println("----------------------------------------------------------");
		System.out.println("------CHARGEMENT D'UN PLATEAU SANS LES FIX DES ZONES------");
		System.out.println();
		
		System.out.println(plateau);
		
		
		System.out.println("------------------------------------------------");
		System.out.println("------SAUVEGARDE ET CHARGEMENT DE PLATEAUX------");
		
		metier.sauvegarderPlateau("sauvegarde-000");
		
		System.out.println(plateau);
		
		
		
	}
	
	private static void test(String resultat, String resultatAttendu)
	{
		System.out.println( "Test n°" + (TestSauvegardes.nbTests++)                      +
		                    " Resultat : " + resultat + " = " + resultatAttendu + " => " +
		                  ( resultat.equals(resultatAttendu) ? "OK" : "ERREUR" ) );
	}
	
	
}