package source;

import source.metier.*;
import java.util.List;
import java.io.File;
import java.util.ArrayList;

public class TestSauvegardes
{
	private static int nbTests;
	
	public static void main(String[] args) 
	{
		
		Metier metier = new Metier();

		File f = new File("../../source/metier/sauvegardes/sauvegarde-000.data");
		System.out.println( f.getAbsolutePath() );
		
		metier.chargerPlateau("./../../../source/metier/sauvegardes/sauvegarde-000.data");
		
		Plateau plateau = metier.getPlateau();
		
		
		
		System.out.println("/* ---------------------------------- */");
		System.out.println("/*          Test des sauvegardes      */");
		System.out.println("/* ---------------------------------- */");
		
		System.out.println();
		
		System.out.println(plateau);
		
		TestSauvegardes.test("oui", "false");
		
	}
	
	private static void test(String resultat, String resultatAttendu)
	{
		System.out.println( "Test n°" + (TestSauvegardes.nbTests++)                      +
		                    " Resultat : " + resultat + " = " + resultatAttendu + " => " +
		                  ( resultat.equals(resultatAttendu) ? "OK" : "ERREUR" ) );
	}
}