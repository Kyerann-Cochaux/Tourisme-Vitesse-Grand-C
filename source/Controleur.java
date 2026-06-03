package source;

import source.metier.*;
import source.ihm.*;

public class Controleur
{
	private Metier    metier;
	private FrameCreation frameCreation;

	public Controleur()
	{
		this.metier    = new Metier   ();
		this.frameCreation = new frameCreation(this);
	}

	public void initialiserPlateau(int nbLignes, int nbColonnes, int nbFormes, int nbEspeces)
	{
		this.metier.initialiserPlateau(nbLignes, nbColonnes, nbFormes, nbEspeces);
	}

	public static void main(String[] args) 
	{
		new Controleur();
	}
}