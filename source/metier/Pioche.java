package source.metier;

import java.util.ArrayList;
import java.util.List;

public class Pioche
{
	private ArrayList<Carte> pioche;
	
	public Pioche()
	{
		this.pioche = new ArrayList<Carte>();
		
		for(String esp : Plateau.TAB_FORMES)
		{
			this.pioche.add(Carte.creerCarte(esp, false));
			this.pioche.add(Carte.creerCarte(esp, true ));
		}
		this.pioche.add(Carte.creerCarte("Joker", false));
		this.pioche.add(Carte.creerCarte("Joker", true ));
	}
	
	/* ---------------------------------- */
	/*               Getters              */
	/* ---------------------------------- */
	
	public Carte getCarte(int indice)
	{
		if(indice >= this.pioche.size() || indice < 0){return null;}
		return this.pioche.get(indice);
	}

	/* ---------------------------------- */
	/*           Autres méthodes          */
	/* ---------------------------------- */
	
	public Boolean enleverCarte(String symbole, boolean premium)
	{
		int cpt = 0;
		
		for(Carte carte : this.pioche)
		{
			if(carte.getSymbole().equals(symbole) && 
			   carte.getPremium() == premium)
			{
				this.pioche.remove(cpt);
				return true;
			}
			cpt ++;
		}
		return false;
	}
	
	/* ---------------------------------- */
	/*          méthodes standard         */
	/* ---------------------------------- */
	
	public String toString()
	{
		String sRet = "";
		
		sRet += "Pioche :\n";
		for(Carte carte : this.pioche)
		{
			sRet += carte.toString() + "\n";
		}
		return sRet;
	}
}
