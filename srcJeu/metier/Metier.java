package srcJeu.metier;

import java.io.FileInputStream;
import java.util.Scanner;

public class Metier
{
	private Plateau plateauJeu;
	private Pioche  pioche    ;
	
	
	public Metier()
	{
		this.plateauJeu = null;
		this.pioche     = new Pioche();
	}
	
	public void initialiserPlateau(int nbLignes, int nbColonnes, int nbFormes, int nbEspeces)
	{
		this.plateauJeu = Plateau.creerPlateau(nbLignes, nbColonnes, nbFormes, nbEspeces);
	}
	
	/* ---------------------------------- */
	/*               Accesseurs           */
	/* ---------------------------------- */
	
	public Plateau getPlateau() { return this.plateauJeu;}
	public Pioche  getPioche () { return this.pioche    ;}
	
	
	
	
	/* ---------------------------------- */
	/*          Autres méthodes           */
	/* ---------------------------------- */
	
	public boolean chargerPlateau(String cheminSauvegarde)
	{
		int nbLignes   = 0;
		int nbColonnes = 0;
		int nbFormes   = 0;
		int nbEspeces  = 0;
		
		try
		{
			Scanner sc = new Scanner ( new FileInputStream ( cheminSauvegarde ), "UTF8" );
			
			
			String premiereLigne = sc.nextLine();
			
			String[] creationArguments = premiereLigne       .split(" ");
			String[] creationTaille    = creationArguments[0].split("x");
			
			nbLignes   = Integer.parseInt(creationTaille[0]);
			nbColonnes = Integer.parseInt(creationTaille[1]);
			
			nbFormes   = Integer.parseInt(creationArguments[1]);
			nbEspeces  = Integer.parseInt(creationArguments[2]);
			
			
			// initialisation du plateau
			this.initialiserPlateau(nbLignes, nbColonnes, nbFormes, nbEspeces);
			
			
			// placement des systèmes
			for (int numLig = 0; numLig < nbLignes; numLig++)
			{
				String ligne = sc.nextLine();
				
				for (int numCol = 0; numCol < nbColonnes; numCol++)
				{
					int numZone = Integer.parseInt( "" + ligne.charAt(numCol) );
					this.plateauJeu.setNumSysteme(numZone, numCol, numLig, true);
				}
			}
			
			
			// placement des planetes
			for (int numLig = 0; numLig < nbLignes; numLig++)
			{
				String ligne = sc.nextLine();
				
				for (int numCol = 0; numCol < nbColonnes; numCol++)
				{
					char sIndPlanete = ligne.charAt(numCol);
					
					if( sIndPlanete != '.' )
					{
						int indPlanete = Integer.parseInt("" + sIndPlanete);
						
						Planete tempPlanete = Planete.creerPlanete(Plateau.TAB_PLANETES[indPlanete].charAt(0) );
						
						this.plateauJeu.getCase(numCol, numLig).setPlanete(tempPlanete);
						// System.out.println(tempPlanete.getSymbole() + " : numCol -> " + numCol + "\n" + "    numLig -> " + numLig );
						// System.out.println();
						//this.plateauJeu.ajouterPlanete(numCol, numLig, tempPlanete);
					}
					
				}
			}
			
			
			// placement des bases
			for (int numLig = 0; numLig < nbLignes; numLig++)
			{
				String ligne = sc.nextLine();
				
				for (int numCol = 0; numCol < nbColonnes; numCol++)
				{
					
					char sIndEspece = ligne.charAt(numCol);
					
					if( sIndEspece != '-' )
					{
						int indEspece = Integer.parseInt("" + sIndEspece);
						String typeEspece = Plateau.TAB_ESPECES[indEspece];
						
						this.plateauJeu.getCase(numCol, numLig).getPlanete().setEspece(typeEspece);
					}
					
				}
			}
			
			// fermeture du scanner
			sc.close();
		}
		catch (Exception e)
		{
			// System.out.println("Erreur lors du chargement du fichier sauvegardé.");
			e.printStackTrace();
			
			this.initialiserPlateau(nbLignes, nbColonnes, nbFormes, nbEspeces);
			return false;
		}
		
		this.plateauJeu.actualiserVoyages();
		
		return true;
	}
	
}
