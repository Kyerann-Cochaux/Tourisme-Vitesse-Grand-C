package source.metier;

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

	

	
}