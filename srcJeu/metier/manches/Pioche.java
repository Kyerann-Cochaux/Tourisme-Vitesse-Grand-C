package srcJeu.metier.manches;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import srcJeu.metier.Metier;

public class Pioche
{
	private static final Carte[] TAB_CARTES = { Carte.creerCarte(Metier.TAB_PLANETES[0],true),
	                                            Carte.creerCarte(Metier.TAB_PLANETES[1],true),
	                                            Carte.creerCarte(Metier.TAB_PLANETES[2],true),
	                                            Carte.creerCarte(Metier.TAB_PLANETES[3],true),
	                                            Carte.creerCarte("Joker"       ,true),
	                                            Carte.creerCarte(Metier.TAB_PLANETES[0],false),
	                                            Carte.creerCarte(Metier.TAB_PLANETES[1],false),
	                                            Carte.creerCarte(Metier.TAB_PLANETES[2],false),
	                                            Carte.creerCarte(Metier.TAB_PLANETES[3],false),
	                                            Carte.creerCarte("Joker"       ,false),
	                                        };

	private List<Carte> pioche;
	
	public Pioche()
	{
		this.pioche = new ArrayList<Carte>();
		
		for (int cpt = 0; cpt < Pioche.TAB_CARTES.length; cpt++) 
		{
			this.pioche.add(Pioche.TAB_CARTES[cpt] );
		}


		//Collections.shuffle(this.pioche);
	}
	
	/* ---------------------------------- */
	/*               Accesseurs           */
	/* ---------------------------------- */

	public Carte getCarte(int indice)
	{
		return this.pioche.get(indice);
	}
	
	public Carte getSommet()
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
		if(this.pioche.size() == 1 ) {return false;}
		if(!this.resteCartePremium()){return false;}
		
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
