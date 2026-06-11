package srcJeu;

import srcJeu.metier.manches.Manche;
import srcJeu.metier.manches.Carte;
import srcJeu.metier.plateau.Case;

public class TestManches
{
	public static void main(String[] args)
	{
		System.out.println("=================================================");
		System.out.println("        TEST COMPLET DU MODULE DES MANCHES       ");
		System.out.println("=================================================\n");

		// ---------------------------------------------------------------
		// ETAPE 1 : Création de la Manche (Validation de la Factory)
		// ---------------------------------------------------------------
		System.out.println("1. TEST DE LA CREATION DE LA MANCHE");
		
		Manche m = Manche.creerManche("Chlorophite");
		if (m != null) {
			System.out.println("   Succes : Manche initialisee pour l'espece : " + m.getEspece());
		} else {
			System.out.println("   Echec : Impossible de creer la manche.");
			return;
		}

		// ---------------------------------------------------------------
		// ETAPE 2 : Inspection de la pioche initiale
		// ---------------------------------------------------------------
		System.out.println("\n2. INSPECTION DE LA PIOCHE INITIALE");
		Carte carteDuDessus = m.getSommet();
		System.out.println("   Carte actuellement visible : [ " + carteDuDessus + " ]");
		System.out.println("   La manche est-elle finie ? " + (m.estMancheFinie() ? "Oui" : "Non (il reste des premiums)"));

		// ---------------------------------------------------------------
		// ETAPE 3 : Gestion et manipulation des Cases de la manche
		// ---------------------------------------------------------------
		System.out.println("\n3. ENREGISTREMENT DES CASES DANS LA MANCHE");
		Case c1 = new Case(2, 3);
		Case c2 = new Case(2, 4);
		Case c3 = new Case(3, 4);

		m.ajouterCase(c1);
		m.ajouterCase(c2);
		m.ajouterCase(c3);

		System.out.println("   Succes : Nombre de cases rattachees a la manche : " + m.getlstCases().size());
		System.out.println("   Premiere case posee : [X=" + m.getPremier().getPosX() + ", Y=" + m.getPremier().getPosY() + "]");
		System.out.println("   Derniere case posee : [X=" + m.getDernier().getPosX() + ", Y=" + m.getDernier().getPosY() + "]");

		// ---------------------------------------------------------------
		// ETAPE 4 : Simulation du défilement des cartes (Cycle de jeu)
		// ---------------------------------------------------------------
		System.out.println("\n4. SIMULATION DU DEFILEMENT DE LA PIOCHE");
		
		// Retrait manuel de toutes les cartes Premium pour debloquer decouvrirCarte()
		m.getPioche().enleverCarte("Gazeuze", true);
		m.getPioche().enleverCarte("Ocean", true);
		m.getPioche().enleverCarte("Tellurique", true);
		m.getPioche().enleverCarte("Volcanique", true);
		m.getPioche().enleverCarte("Joker", true);

		System.out.println("   [Apres retrait des Premium] La manche est-elle consideree comme finie ? " + 
		                   (m.estMancheFinie() ? "Oui (Plus de Premium dans le paquet)" : "Non"));

		System.out.println("\n   Defilement manuel des cartes normales restantes :");
		int cpt = 1;
		boolean piocheEncoreValide = true;

		while (piocheEncoreValide) 
		{
			System.out.println("      * Carte n°" + cpt + " visible : [ " + m.getSommet() + " ]");
			piocheEncoreValide = m.decouvrirCarte();
			if (piocheEncoreValide) {
				cpt++;
			} else {
				System.out.println("      Impossible de passer a la suivante (La pioche a atteint sa derniere carte).");
			}
		}

		// ---------------------------------------------------------------
		// ETAPE 5 : Validation finale des scores
		// ---------------------------------------------------------------
		System.out.println("\n5. CONTROLE DE FIN DE MANCHE & SCORE");
		System.out.println("   Carte finale figee dans la defausse : [ " + m.getSommet() + " ]");
		System.out.println("   Calcul du score de la manche : " + m.calculerScore());
	}
}