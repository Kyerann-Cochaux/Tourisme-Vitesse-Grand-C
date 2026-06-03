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

	public static void main(String[] args) 
	{
		new Controleur();
	}
}