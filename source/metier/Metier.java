package source.metier;

public class Metier
{
	private Plateau plateauJeu;
	// private Pioche piocheFerme;

	public Metier()
	{
		this.plateauJeu = null;
	}

	public void initialiserPlateau(int nbLignes, int nbColonnes, int nbFormes, int nbEspeces)
	{
		this.plateauJeu = Plateau.creerPlateau(nbLignes, nbColonnes, nbFormes, nbEspeces);
	}
}