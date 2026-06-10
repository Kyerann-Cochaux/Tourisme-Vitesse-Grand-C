package srcJeu.metier.manches;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import srcJeu.metier.Metier;

public class Pioche
{
	private List<Carte> pioche;
	
	public Pioche()
	{
		this.pioche = new ArrayList<Carte>();
		
		for (int cpt = 0; cpt < Metier.TAB_PLANETES.length; cpt++) 
		{
			this.pioche.add(Carte.creerCarte(Metier.TAB_PLANETES[cpt], false) );
			this.pioche.add(Carte.creerCarte(Metier.TAB_PLANETES[cpt], true ) );
		}

		this.pioche.add(Carte.creerCarte("Joker", false) );
		this.pioche.add(Carte.creerCarte("Joker", true ) );

		Collections.shuffle(this.pioche);
	}
	
	/* ---------------------------------- */
	/*               Accesseurs           */
	/* ---------------------------------- */
	
	public Carte getCarte()
	{
		return this.pioche.getFirst();
	}

	/* ---------------------------------- */
	/*           Autres méthodes          */
	/* ---------------------------------- */
	
	public boolean enleverCarte(String symbole, boolean premium)
	{
		
		for(Carte carte : this.pioche)
		{
			if(carte.getSymbole().equals(symbole) && 
			   carte.getPremium() == premium)
			{
				this.pioche.remove(carte);
				return true;
			}
		}
		
		return false;
	}

	public boolean resteCartePremium()
	{
		for (Carte c : this.pioche)
		{
			if(c.getPremium ())
				return true;
		}
		return false;
	}

	public boolean decouvrirCarte()
	{
		if(this.pioche.size() == 1 ){return false;}
		if(this.resteCartePremium()){return false;}
		
		this.pioche.removeFirst();
		return true;
	}
	
	/* ---------------------------------- */
	/*          Méthodes standard         */
	/* ---------------------------------- */
	
	public String toString()
	{
		String sRet = "";
		
		sRet += "Pioche :\n";
		for(Carte carte : this.pioche)
			sRet += carte.toString() + "\n";
		
		return sRet;
	}
}
