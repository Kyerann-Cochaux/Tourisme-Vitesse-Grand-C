package source.metier;

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
	
	// le numero des sauvegardes commence a 0
	public boolean sauvegardeExiste(int numSauvegarde)
	{
		String chemin = String.format("../source/metier/sauvegardes/sauvegarde-%03d.data", numSauvegarde);
		
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
	
}