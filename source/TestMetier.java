package source;

import java.util.List;
import java.util.ArrayList;

import source.metier.*;

public class TestMetier
{
	public static void main(String[] args) 
	{

		Metier m = new Metier();
		Plateau p = null;

		List<Case> lstCases = new ArrayList<Case>();


		m.initialiserPlateau(10,10,3,3);
		p = m.getPlateau();

		lstCases.add(new Case(0, 0) );
		lstCases.add(new Case(1, 0) );
		lstCases.add(new Case(2, 0) );
		lstCases.add(p.getCase(0, 0) ) ;

		System.out.println(p.afficherPlateau() );

	

		

	}
}