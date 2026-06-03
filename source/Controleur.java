package source;

import source.metier.*;
import source.ihm.*;

public class Controleur
{
	private Metier    metier;
	private FrameCreation frameCreation;

	public Controleur()
	{
<<<<<<< HEAD
		this.metier    = new Metier   ();
=======
		this.metier        = new Metier   ();
>>>>>>> 75d3aac3fb39ae1831f4120a316ad555dc16c07e
		this.frameCreation = new FrameCreation(this);
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