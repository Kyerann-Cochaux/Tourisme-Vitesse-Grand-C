package source;

import source.metier.*;
import source.ihm.*;

public class AppliCreation
{
	private Metier        metier;
	private FrameCreation frameCreation;

	public AppliCreation()
	{
		this.metier        = new Metier();
		this.frameCreation = new FrameCreation(this);
	}
	
	public void initialiserPlateau(int nbLignes, int nbColonnes, int nbFormes, int nbEspeces)
	{
		this.metier.initialiserPlateau(nbLignes, nbColonnes, nbFormes, nbEspeces);
	}
	
	public Plateau getPlateau()
	{
		return this.metier.getPlateau();
	}
	
	public static void main(String[] args) 
	{
		new AppliCreation();
	}
}
