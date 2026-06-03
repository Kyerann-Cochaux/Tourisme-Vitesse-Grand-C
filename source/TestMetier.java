package source;

import source.metier.*;
import java.util.List;
import java.util.ArrayList;

public class TestMetier
{
	public static void main(String[] args) 
	{
		
		Planete j;

		Metier m = new Metier();
		Plateau p = null;
		
		m.initialiserPlateau(10,10,3,3);
		p = m.getPlateau();

		System.out.println("/* ---------------------------------- */");
		System.out.println("/*          Test des symboles         */");
		System.out.println("/* ---------------------------------- */");
		System.out.println();
		
		j = Planete.creerPlanete('A',null);
		System.out.println( (j == null) ? "OK" : "Erreur");
		
		j = Planete.creerPlanete('G',null);
		System.out.println( (j != null) ? "OK" : "Erreur");
		
		j = Planete.creerPlanete('o',null);
		System.out.println( (j != null) ? "OK" : "Erreur");
		
		j = Planete.creerPlanete('t',null);
		System.out.println( (j != null) ? "OK" : "Erreur");
		
		j = Planete.creerPlanete('v',null);
		System.out.println( (j != null) ? "OK" : "Erreur");

		System.out.println();
		System.out.println("/* ---------------------------------- */");
		System.out.println("/*          Test des espèces          */");
		System.out.println("/* ---------------------------------- */");
		System.out.println();

		j = Planete.creerPlanete('G', Plateau.TAB_ESPECES[0] );
		System.out.println((j.getEspece() != null) ? "OK -> "+ j.getEspece() : "Erreur");
		j = Planete.creerPlanete('G', Plateau.TAB_ESPECES[1] );
		System.out.println((j.getEspece() != null) ? "OK -> "+ j.getEspece() : "Erreur");
		j = Planete.creerPlanete('G', Plateau.TAB_ESPECES[2] );
		System.out.println((j.getEspece() != null) ? "OK -> "+ j.getEspece() : "Erreur");
		j = Planete.creerPlanete('G', Plateau.TAB_ESPECES[3] );
		System.out.println((j.getEspece() != null) ? "OK -> "+ j.getEspece() : "Erreur");

		try { j = Planete.creerPlanete('G', null );}
		catch (Exception e) {System.out.println("OK");}

		System.out.println("/* ------------------------------------------- */");
		System.out.println("/*   Ajout de Planetes simples dans le plateau   */");
		System.out.println("/* ------------------------------------------- */");
		System.out.println();
		System.out.println("/* ------- Ajouts impossibles ------- */");
		System.out.println();

		boolean b = false;

		b = p.ajouterForme(-1, 0, j);
		System.out.println("x négatif --> " + (!b ? " OK " : " Erreur ") );

		b = p.ajouterForme(0, -1, j);
		System.out.println( "y négatif --> " + (!b ? " OK " : " Erreur ") );
		b = p.ajouterForme(0, 0, null);
		System.out.println( "Forme null --> " + (!b ? " OK " : " Erreur ") );

		p.ajouterForme(0, 0, j);
		b = p.ajouterForme(0, 0, j);
		System.out.println("Forme déjà présent --> " + (!b ? " OK " : " Erreur ") );

		System.out.println(p);

		System.out.println("/* ---------------------------------- */");
		System.out.println("/*           Ajout de bases           */");
		System.out.println("/* ---------------------------------- */");

		j = Planete.creerPlanete('G', Plateau.TAB_ESPECES[3] );
		p.retirerForme(0, 0);
		
		p.ajouterForme(0, 0, j);
		p.ajouterForme(0, 1, j);
		p.ajouterForme(5, 5, Planete.creerPlanete('T', null) );

		System.out.println(p);

		



		
		
	}
	
	
}