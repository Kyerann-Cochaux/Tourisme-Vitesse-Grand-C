package source.metier;

import java.io.FileInputStream;
import java.util.Scanner;

public class Metier
{
	private static final String CHEMIN_SAUVEGARDES    = "../source/metier/sauvegardes/"; // attention a ne pas oublier le / a la fin
	private static final String NOM_SAUVEGARDES       = "sauvegarde-";
	private static final String EXTENSION_SAUVEGARDES = ".data";
	
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
	
	// le numero des sauvegardes commence a 0
	public boolean sauvegardeExiste(int numSauvegarde)
	{
		String chemin = Metier.CHEMIN_SAUVEGARDES + 
		                Metier.NOM_SAUVEGARDES    + String.format("%03d", numSauvegarde) + Metier.EXTENSION_SAUVEGARDES;
		
		try
		{
			Scanner sc = new Scanner ( new FileInputStream ( chemin ), "UTF8" );
			sc.close();
		}
		catch (Exception e)
		{
			return false;
		}
		
		return true;
	}
	
	public boolean chargerPlateau(int numSauvegarde)
	{
		if ( !sauvegardeExiste(numSauvegarde) ) return false;
		
		
		String nomFichier = Metier.NOM_SAUVEGARDES    + String.format("%03d", numSauvegarde) + Metier.EXTENSION_SAUVEGARDES;
		String chemin = Metier.CHEMIN_SAUVEGARDES + nomFichier;
		
		
		int nbLignes   = 0;
		int nbColonnes = 0;
		int nbFormes   = 0;
		int nbEspeces  = 0;
		
		try
		{
			Scanner sc = new Scanner ( new FileInputStream ( chemin ), "UTF8" );
			
			
			String premiereLigne       = sc.nextLine();
			
			String[] creationArguments = premiereLigne.split(" ");
			String[] creationTaille    = creationArguments[0].split("*");
			
			
			nbLignes   = Integer.parseInt(creationTaille[0]);
			nbColonnes = Integer.parseInt(creationTaille[1]);
			
			nbFormes   = Integer.parseInt(creationArguments[1]);
			nbEspeces  = Integer.parseInt(creationArguments[2]);
			
			
			// initialisation du plateau
			this.initialiserPlateau(nbLignes, nbColonnes, nbFormes, nbEspeces);
			
			
			// ajout des systèmes solaires
			String  sLigne  = "";
			Systeme tempSys = new Systeme();
			
			int numZone = sLigne.charAt(0);
			
			for (int cptLig = 0; cptLig < creationTaille.length; cptLig++)
			{
				sLigne = sc.nextLine();
				for (int cptCol = 0; cptCol < creationTaille.length; cptCol++)
				{
					numZone = sLigne.charAt(cptCol);
					tempSys.ajouterCase(this.plateauJeu.getCase(cptLig, cptCol));
					
					
				}
			}
			
			this.plateauJeu.ajouterSysteme(tempSys);
			this.plateauJeu.getCase(nbLignes, nbColonnes).getPlanete();
			
			
			// fermeture du scanner
			sc.close();
		}
		catch (Exception e)
		{
			System.out.println("Erreur lors du chargement du plateau\""+ nomFichier +"\"");
			return false;
		}
		
		return true;
	}
}