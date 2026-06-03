package source;

import source.metier.*;
import source.ihm.*;

public class Controleur
{
	private Metier    metier;
	private FrameInit frameInit;

	public Controleur()
	{
		this.metier    = new Metier   ();
		this.frameInit = new FrameInit(this);
	}

	public void initialiserPlateau(int nbLignes, int nbColonnes, int nbFormes, int nbEspeces)
	{
		this.metier.creerPlateau(nbLignes, nbColonnes, nbFormes, nbEspeces);
	}

	public static void main(String[] args) 
	{
		new Controleur();
	}
}