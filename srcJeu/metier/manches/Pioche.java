package srcJeu.metier.manches;

import java.util.ArrayList;
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
	}
	
	/* ---------------------------------- */
	/*               Accesseurs           */
	/* ---------------------------------- */
	
	public Carte getCarte(int indice)
	{
		if (indice >= this.pioche.size() || indice < 0) return null;
		return this.pioche.get(indice);
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
