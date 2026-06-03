package source;

import source.metier.*;
import java.util.List;
import java.util.ArrayList;

public class TestMetier
{
	public static void main(String[] args) 
	{
		
		Jeton j;

		
		Metier m = new Metier();
		Plateau p = null;
		
		List<Case> lstCases = new ArrayList<Case>();
		
		m.initialiserPlateau(10,10,3,3);
		p = m.getPlateau();

		System.out.println("/* ---------------------------------- */");
		System.out.println("/*          Test des symboles         */");
		System.out.println("/* ---------------------------------- */");
		System.out.println();
		
		j = Jeton.creerJeton('A',null);
		System.out.println( (j == null) ? "OK" : "Erreur");
		
		j = Jeton.creerJeton('G',null);
		System.out.println( (j != null) ? "OK" : "Erreur");
		
		j = Jeton.creerJeton('o',null);
		System.out.println( (j != null) ? "OK" : "Erreur");
		
		j = Jeton.creerJeton('t',null);
		System.out.println( (j != null) ? "OK" : "Erreur");
		
		j = Jeton.creerJeton('v',null);
		System.out.println( (j != null) ? "OK" : "Erreur");

		System.out.println();
		System.out.println("/* ---------------------------------- */");
		System.out.println("/*          Test des espèces          */");
		System.out.println("/* ---------------------------------- */");
		System.out.println();

		j = Jeton.creerJeton('G', Plateau.TAB_ESPECES[0] );
		System.out.println((j.getEspece() != null) ? "OK -> "+ j.getEspece() : "Erreur");
		j = Jeton.creerJeton('G', Plateau.TAB_ESPECES[1] );
		System.out.println((j.getEspece() != null) ? "OK -> "+ j.getEspece() : "Erreur");
		j = Jeton.creerJeton('G', Plateau.TAB_ESPECES[2] );
		System.out.println((j.getEspece() != null) ? "OK -> "+ j.getEspece() : "Erreur");
		j = Jeton.creerJeton('G', Plateau.TAB_ESPECES[3] );
		System.out.println((j.getEspece() != null) ? "OK -> "+ j.getEspece() : "Erreur");

		try { j = Jeton.creerJeton('G', Plateau.TAB_ESPECES[4] );}
		catch (Exception e) {System.out.println("OK");}




	
	/*	lstCases.add(p.getCase(0, 0) );
		lstCases.add(p.getCase(1, 0) );
		lstCases.add(p.getCase(2, 0) );
		lstCases.add(p.getCase(3, 0) );
		
		System.out.println(p.afficherPlateau() );	*/
		
	}
	
	
}