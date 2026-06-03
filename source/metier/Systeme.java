package source.metier;

import java.util.List;
import java.util.ArrayList;

public class Systeme
{
	private static int nbSysteme;
	private int numSysteme;
	private List<Case> ensCase;

	public Systeme()
	{
		this.numSysteme = this.nbSysteme++;
		this.ensCase = new ArrayList<Case>();
	}
	
	/* ---------------------------------- */
	/*               Accesseurs           */
	/* ---------------------------------- */
	
	public Case       getCase    (int indice) { return this.ensCase.get(indice);                  }
	public Case       getCase    (Case c)     { return this.ensCase.get(this.ensCase.indexOf(c) );}
	public List<Case> getEnsCases()           { return new ArrayList<Case>(this.ensCase);         }
	
	/* ---------------------------------- */
	/*           Autres Méthodes          */
	/* ---------------------------------- */
	
	
	// Vérifier que les cases sélectionnées soient adjacentes entre-elles
	
	public boolean ajouterCase(Case c) 
	{
		if (c == null || this.ensCase.contains(c) ) return false;
		this.ensCase.add(c);
		
		return true;
		
	}
	
	public boolean supprimerCase(Case c)
	{
		if (!this.ensCase.contains(c) ) return false;
		this.ensCase.remove(c);

		return true;
	}

	/* ---------------------------------- */
	/*          Méthodes standard         */
	/* ---------------------------------- */

	public String toString()
	{
		String sRet = "";
		
		sRet += "Système :\n";
		for(Case c : this.ensCase)
			sRet += c.toString() + "\n";
		
		return sRet;
	}
}