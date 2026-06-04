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
		
		m.initialiserPlateau(7,10,3,3);
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
		System.out.println("/*   Ajout de planètes simples dans le plateau */");
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
		
		System.out.println(p.afficherPlanetes() );
		
		System.out.println("/* ---------------------------------- */");
		System.out.println("/*           Ajout de bases           */");
		System.out.println("/* ---------------------------------- */");
		
		j = Planete.creerPlanete('G', Plateau.TAB_ESPECES[3] );
		p.retirerForme(0, 0);
		
		p.ajouterForme(0, 0, j);
		p.ajouterForme(0, 1, Planete.creerPlanete('G', Plateau.TAB_ESPECES[0] ) ); // Ajoute       : OK
		p.ajouterForme(0, 2, Planete.creerPlanete('V', Plateau.TAB_ESPECES[1] ) ); // Ajoute       : OK
		p.ajouterForme(0, 3, Planete.creerPlanete('O', Plateau.TAB_ESPECES[2] ) ); // Ajoute       : OK
		p.ajouterForme(0, 4, Planete.creerPlanete('T', Plateau.TAB_ESPECES[3] ) ); // N'ajoute pas : OK
		
		p.ajouterForme(5, 5, Planete.creerPlanete('T',  null) ); 
		p.ajouterForme(0, 0, Planete.creerPlanete('V',  null) ); 
		
		System.out.println(p.afficherPlanetes() );
		
		System.out.println("/* ---------------------------------- */");
		System.out.println("/*           Ajout des Zones          */");
		System.out.println("/* ---------------------------------- */");
	
		// zone 0

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

		System.out.println();
		System.out.println("/* ---------------------------------- */");
		System.out.println("/*          Test des liaisons         */");
		System.out.println("/* ---------------------------------- */");
		System.out.println();

		System.out.println("source inexistante : " + (p.ajouterVoyage(p.getCase(0, 0), p.getCase(5, 0) ) == false ? "OK" : "ERREUR") );
		System.out.println("dest   inexistante : " + (p.ajouterVoyage(p.getCase(5, 0), p.getCase(0, 0) ) == false ? "OK" : "ERREUR") );

		System.out.println( (p.ajouterVoyage(p.getCase(0, 0), p.getCase(1, 0) ) == true ? "OK" : "ERREUR") );


		System.out.println(p.afficherPlanetes() );

		
		
	}
	
	
}