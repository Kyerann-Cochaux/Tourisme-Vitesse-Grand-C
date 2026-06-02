package source.metier;

import java.util.List;
import java.util.ArrayList;

public class Zone
{
	private List<Case> ensCase;

	public Zone()
	{
		this.ensCase = new ArrayList<Case>();
	}

	/* ---------------------------------- */
	/*               Getters              */
	/* ---------------------------------- */

	public Case       getCase    (int indice) { return this.ensCase.get(indice);                  }
    public Case       getCase    (Case c)     { return this.ensCase.get(this.ensCase.indexOf(c) );}
	public List<Case> getEnsCases()           { return new ArrayList<Case>(this.ensCase);         }

	/* ---------------------------------- */
	/*           Autres Méthodes          */
	/* ---------------------------------- */

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
	/*          méthodes standard         */
	/* ---------------------------------- */

    public String toString()
    {
        String sRet = "";
		
		sRet += "Zone :\n";
		for(Case c : this.ensCase)
			sRet += c.toString() + "\n";
		
		return sRet;
    }
}