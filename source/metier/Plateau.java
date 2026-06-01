package source.metier;

import java.util.ArrayList;
import java.util.List;

public class Plateau
{
	List<Jeton>   lstJetons;
	List<Liaison> lstLiaisons;
	
	public Plateau()
	{
		this.lstJetons   = new ArrayList<Jeton  >();
		this.lstLiaisons = new ArrayList<Liaison>();

	}
}
