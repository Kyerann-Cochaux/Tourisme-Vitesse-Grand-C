package source;

import source.metier.*;
import source.ihm.*;

public class AppliJeu
{
	private Metier        metier;
	private FrameJeu frameJeu;

	public AppliJeu()
	{
		this.metier        = new Metier           ();
		this.frameJeu = new FrameJeu(this);
	}

    public static void main(String[] args) 
	{
		new AppliJeu();
	}
}