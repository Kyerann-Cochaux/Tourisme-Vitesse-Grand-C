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
		
		m.initialiserPlateau(11,11,3,2);

	
		p = m.getPlateau();

		for (int cpt = 0; cpt < p.getNbEspeces(); cpt++) 
			System.out.println(p.getNomEspece(cpt) );
		System.out.println();

		for (int cpt = 0; cpt < p.getNbPlanetes(); cpt++)
			System.out.println(p.getNomPlanete(cpt) );
			
		

		System.out.println("/* ---------------------------------- */");
		System.out.println("/*          Test des symboles         */");
		System.out.println("/* ---------------------------------- */");
		System.out.println();
		
		j = Planete.creerPlanete('A');
		System.out.println( (j == null) ? "OK" : "Erreur");
		
		j = Planete.creerPlanete('G');
		System.out.println( (j != null) ? "OK" : "Erreur");
		
		j = Planete.creerPlanete('o');
		System.out.println( (j != null) ? "OK" : "Erreur");
		
		j = Planete.creerPlanete('t');
		System.out.println( (j != null) ? "OK" : "Erreur");
		
		j = Planete.creerPlanete('v');
		System.out.println( (j != null) ? "OK" : "Erreur");

		System.out.println();
		System.out.println("/* ---------------------------------- */");
		System.out.println("/*          Test des espèces          */");
		System.out.println("/* ---------------------------------- */");
		System.out.println();

		j = Planete.creerPlanete('G' );
		System.out.println((j.getEspece() != null) ? "OK -> "+ j.getEspece() : "Erreur");
		j = Planete.creerPlanete('G' );
		System.out.println((j.getEspece() != null) ? "OK -> "+ j.getEspece() : "Erreur");
		j = Planete.creerPlanete('G' );
		System.out.println((j.getEspece() != null) ? "OK -> "+ j.getEspece() : "Erreur");
		j = Planete.creerPlanete('G' );
		System.out.println((j.getEspece() != null) ? "OK -> "+ j.getEspece() : "Erreur");

		try { j = Planete.creerPlanete('G');}
		catch (Exception e) {System.out.println("OK");}

		System.out.println("/* ------------------------------------------- */");
		System.out.println("/*   Ajout de planètes simples dans le plateau */");
		System.out.println("/* ------------------------------------------- */");
		System.out.println();
		System.out.println("/* ------- Ajouts impossibles ------- */");
		System.out.println();

		boolean b = false;

		b = p.ajouterPlanete(-1, 0, j);
		System.out.println("x négatif --> " + (!b ? " OK " : " Erreur ") );

		b = p.ajouterPlanete(0, -1, j);
		System.out.println( "y négatif --> " + (!b ? " OK " : " Erreur ") );
		b = p.ajouterPlanete(0, 0, null);
		System.out.println( "Planete null --> " + (!b ? " OK " : " Erreur ") );
		
		p.ajouterPlanete(0, 0, j);
		b = p.ajouterPlanete(0, 0, j);
		System.out.println("Planete déjà présent --> " + (!b ? " OK " : " Erreur ") );
		
		System.out.println(p.afficherPlanetes() );
		
		System.out.println("/* ---------------------------------- */");
		System.out.println("/*           Ajout de bases           */");
		System.out.println("/* ---------------------------------- */");
		
		j = Planete.creerPlanete('G');
		p.retirerPlanete(0, 0);
		
		p.ajouterPlanete(0, 0, j);
		p.ajouterPlanete(0, 1, Planete.creerPlanete('G') ); // Ajoute       : OK
		p.ajouterPlanete(0, 2, Planete.creerPlanete('V') ); // Ajoute       : OK
		p.ajouterPlanete(0, 3, Planete.creerPlanete('O') ); // Ajoute       : OK
		p.ajouterPlanete(0, 4, Planete.creerPlanete('T') ); // N'ajoute pas : OK
		
		p.ajouterPlanete(5, 5, Planete.creerPlanete('T') ); 
		p.ajouterPlanete(0, 0, Planete.creerPlanete('V') ); 
		
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

	
		System.out.println("/* ---------------------------------- */");
		System.out.println("/*       Suppression de Planetes      */");
		System.out.println("/* ---------------------------------- */");
		p.viderPlateau();

		System.out.println(p.ajouterPlanete(0, 0, Planete.creerPlanete('G') ) == true ? "OK" : "Erreur");
		System.out.println(p.afficherPlanetes() );
		System.out.println(p.retirerPlanete(0, 0)                                                   == true ? "OK" : "Erreur" );
		System.out.println(p.afficherPlanetes() );
		System.out.println(p.retirerPlanete(0, 0)                                                   == false ? "OK" : "Erreur" );


		System.out.println();
		System.out.println("/* ---------------------------------- */");
		System.out.println("/*          Test des liaisons         */");
		System.out.println("/* ---------------------------------- */");
		System.out.println();
		p.viderPlateau();
		//System.out.println("source inexistante : " + (p.ajouterVoyage(p.getCase(0, 0), p.getCase(5, 0) ) == false ? "OK" : "ERREUR") ); // la methode n'existe plus
		//System.out.println("dest   inexistante : " + (p.ajouterVoyage(p.getCase(5, 0), p.getCase(0, 0) ) == false ? "OK" : "ERREUR") );

		/* ---------------------------------- */
		/*         Ajout des planètes         */
		/* ---------------------------------- */

		// Planète centrale
		p.ajouterPlanete(5, 5, Planete.creerPlanete('G') );

		
		// Axe horizontal
		p.ajouterPlanete(4, 5, Planete.creerPlanete('G') );
		p.ajouterPlanete(6, 5, Planete.creerPlanete('G') );

		// Axe vertical
		p.ajouterPlanete(5, 4, Planete.creerPlanete('G') );
		p.ajouterPlanete(5, 6, Planete.creerPlanete('G') );

		// Axe diagonal SE / NO
		p.ajouterPlanete(6,4, Planete.creerPlanete('G') );
		p.ajouterPlanete(4, 6, Planete.creerPlanete('G') );

		// Axe diagonal SO / NE

		p.ajouterPlanete(4,4 , Planete.creerPlanete('G') );
		p.ajouterPlanete(6, 6, Planete.creerPlanete('G') );

		 /* -------- Axe Horizontal où dX > 1 -------- */

		// Axe horizontal

		p.ajouterPlanete(2, 5, Planete.creerPlanete('G') );
		p.ajouterPlanete(8, 5, Planete.creerPlanete('G') );

		// Axe vertical

		p.ajouterPlanete(5, 2, Planete.creerPlanete('G' ) );
		p.ajouterPlanete(5, 8, Planete.creerPlanete('G') );

		// Axe diagonal NE / SO


		p.ajouterPlanete(2, 2, Planete.creerPlanete('G') );
		p.ajouterPlanete(8, 2, Planete.creerPlanete('G') );


		// Axe diagonal NO / SE

		p.ajouterPlanete(2, 8, Planete.creerPlanete('G') );
		p.ajouterPlanete(8, 8, Planete.creerPlanete('G') );
		
		
		
		
		System.out.println("/* ---------------------------------- */");
		System.out.println("/*       Test Génération Plateau      */");
		System.out.println("/* ---------------------------------- */");

		Metier m2 = new Metier();
		TestMetier.genererPlateauTest(m2);

		System.out.println(m2.getPlateau() );


		
	}

	public static void genererPlateauTest(Metier metier)
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
			{'C',' ',' ',' ',' ',' ',' ',' ',' ',' '},
			{' ','S',' ',' ',' ',' ',' ','C',' ',' '},
			{' ',' ',' ',' ',' ','C',' ',' ','A',' '},
			{'F',' ',' ','S',' ',' ',' ',' ',' ',' '},
			{' ',' ','C',' ',' ','S',' ','A',' ',' '},
			{' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
			{' ','C',' ',' ','S',' ',' ',' ','A',' '},
		};
		
		
		metier.initialiserPlateau(tabSys.length, tabSys[0].length, 4, 4);
		
		// Parcours lignes
		for (int y = 0; y < tabSys.length; y++)
		{
			// Parcours colonnes
			for (int x = 0; x < tabSys[0].length; x++)
			{
				// Set du systeme à l'aide du tableau tabSys
				metier.getPlateau().setNumSysteme(tabSys[y][x], x, y);

				//Création de la planete à ajouter
				Planete planete = Planete.creerPlanete(tabPla[y][x]);
				
				//Ajout de la planète
				metier.getPlateau().ajouterPlanete(x, y, planete);
				
				
				for (int numEspece = 0; numEspece < Plateau.TAB_ESPECES.length; numEspece++)
					if ( Plateau.TAB_ESPECES[numEspece].charAt(0) == tabEsp[y][x] )
						metier.getPlateau().setEspece(planete, Plateau.TAB_ESPECES[numEspece]);
			}
		}	
	}
}