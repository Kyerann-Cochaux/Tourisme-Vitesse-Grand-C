package srcJeu.metier.manches;

import java.lang.classfile.instruction.TableSwitchInstruction;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import srcJeu.metier.Metier;

public class Pioche
{
	private static Carte[] TAB_CARTES_DEMO = {Carte.creerCarte("Joker", false) ,
											  Carte.creerCarte("Joker", true ) ,
											  Carte.creerCarte("Joker", false) ,
											  Carte.creerCarte("Joker", true ) ,
											  Carte.creerCarte("Joker", false) ,
											  Carte.creerCarte("Joker", true ) ,
											  Carte.creerCarte("Joker", false) ,
											  Carte.creerCarte("Joker", true ) ,
											  Carte.creerCarte("Joker", false) ,
											  Carte.creerCarte("Joker", true ) ,
											 };

	private List<Carte> pioche;

	public Pioche(Metier metier, boolean demo)
	{
		this.pioche = new ArrayList<Carte>();
		if(!demo)
		{

			for (int cpt = 0; cpt < metier.getPlateau().getNbPlanetes() ; cpt++) 
			{
				this.pioche.add(Carte.creerCarte(Metier.TAB_PLANETES[cpt], false) );
				this.pioche.add(Carte.creerCarte(Metier.TAB_PLANETES[cpt], true ) );
			}
				this.pioche.add(Carte.creerCarte("Joker", false) );
				this.pioche.add(Carte.creerCarte("Joker", true ) );
		}
		else
		{
			for (int cpt = 0; cpt < metier.getPlateau().getNbPlanetes() ; cpt++) 
			{
				this.pioche.add(TAB_CARTES_DEMO[cpt]);
			}
		}
	}
	
	/* ---------------------------------- */
	/*               Accesseurs           */
	/* ---------------------------------- */

	/*public Carte getCarteInit(int indice)
	{
		return Pioche.TAB_CARTES[indice];
	}*/
	
	public Carte getSommet()
	{
		// Si il n'y a plus de cartes dans la pioche on retourne null
		if ( this.pioche.size() > 0 )
		{
			return this.pioche.getFirst();
		}
		
		return null ;
	}

	public int   getTaillePioche()    { return this.pioche.size()      ; }
	public Carte getCarte(int indice) { return this.pioche.get(indice) ; }

	/* ---------------------------------- */
	/*           Autres méthodes          */
	/* ---------------------------------- */
	
	public boolean decouvrirCarte()
	{
		if ( ! this.pioche.isEmpty() )
		{
			this.pioche.removeFirst() ;
			return true ;
		}
		
		return false;
	}

	public boolean retirerCarte(String symbole, boolean premium)
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
		for ( Carte c : this.pioche )
		{
			if ( c.getPremium() == true ) { return true ; }
		}
		
		return false ;
	}
	
	public void melangerCarte()
	{
		Collections.shuffle(this.pioche) ;
	}

	/* ---------------------------------- */
	/*          Méthodes standard         */
	/* ---------------------------------- */
	
	public String toString()
	{
		String sRet = "Pioche :\n";

		for(Carte carte : this.pioche)
			sRet += carte + "\n";
		
		return sRet;
	}
}
