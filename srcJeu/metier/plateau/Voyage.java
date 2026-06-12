package srcJeu.metier.plateau;

public class Voyage
{
	private Case planeteSource;
	private Case planeteDestination;
	
	private String espece;
	
	private double pente;
	private double hauteur;
	
	/*Factory pour vérifier si les parametres ne sont pas null et si la planete de destination n'est pas égale a celle de départ */
	
	public static Voyage creerVoyage(Case planeteSource, Case planeteDestination)
	{
		if (planeteSource      == null              ) return null; 
		if (planeteDestination == null              ) return null;
		if (planeteSource      == planeteDestination) return null;
		
		return new Voyage(planeteSource,  planeteDestination);
	}
	
	private Voyage(Case planeteSource, Case planeteDestination)
	{
		this.planeteSource      = planeteSource;
		this.planeteDestination = planeteDestination;
		
		this.espece = null;
		
		this.pente =
		(
			1.0 * 
			( this.planeteSource.getPosY() - this.planeteDestination.getPosY() )
			/
			( this.planeteSource.getPosX() - this.planeteDestination.getPosX() )
		);
		
		
		
		// y avais un - pour mettre au negatif car quand on monte on va dans le -tif
		this.hauteur = - 1.0 * (this.pente * (this.planeteDestination.getPosX()) - (this.planeteDestination.getPosY()));
		
	}
	
	/* ---------------------------------- */
	/*               Accesseurs           */
	/* ---------------------------------- */
	
	public Case getPlaneteSource     () { return this.planeteSource     ; }
	public Case getPlaneteDestination() { return this.planeteDestination; }
	public String getEspece          () { return this.espece            ; }
	
	private double getPente  () { return this.pente;   }
	private double getHauteur() { return this.hauteur; }
	
	/* ---------------------------------- */
	/*               Modificateurs        */
	/* ---------------------------------- */
	
	public boolean setEspece(String espece)
	{
		if( espece == null || espece.equals("") ) return false;
		//if( this.voyagesCoupe()                 ) return false;
		
		this.espece = espece;
		
		return true;
	}
	
	
	// retourne si ce voyage coupe un autre voyage qui as une espece
	public boolean coupe(Voyage voyage)
	{
		// si les deux voyages sont paralelles (memes pentes)
		if ( this.pente == voyage.getPente() ) return false;
		
		
		// si il y as une espece sur ce voyage
		if ( this.espece != null ) return false;
		
		
		// si les deux voyages n'ont pas d'espece ca ne sert a rien de tester le reste
		// if ( this.espece != null && voyage.getEspece() != null ) return false;
		
		double intersectionX =  1.0 * ( voyage.getHauteur() - this.hauteur ) / ( this.pente - voyage.getPente() );
		//double intersectionY = (1.0 * this.pente * intersectionX + this.hauteur);
		
		// si la pente n'es pas parfaitement verticale 
		if ( !Double.isInfinite(this.pente) )
		{
			if ( intersectionX > Math.min(this.planeteDestination.getPosX(), this.planeteSource.getPosX()) && 
				intersectionX < Math.max(this.planeteDestination.getPosX(), this.planeteSource.getPosX()) && 
				
				intersectionX > Math.min(voyage.getPlaneteDestination().getPosX(), voyage.getPlaneteSource().getPosX()) && 
				intersectionX < Math.max(voyage.getPlaneteDestination().getPosX(), voyage.getPlaneteSource().getPosX())
			) {
				return voyage.getEspece() != null;
			}
		}
		
		
		double x = voyage.getPlaneteDestination().getPosX() - Math.max(this.planeteDestination.getPosX(), this.planeteSource.getPosX());
		double y = voyage.getPlaneteDestination().getPosY() - Math.max(this.planeteDestination.getPosY(), this.planeteSource.getPosY());
		
		double hauteurRel = 1.0 * ( voyage.getPente() * ( x ) - ( y ) );
		
		
		return hauteurRel < Math.max(this.planeteDestination.getPosY(), this.planeteSource.getPosY()) -
		                    Math.min(this.planeteDestination.getPosY(), this.planeteSource.getPosY())   &&
		       hauteurRel > 0                                                                           &&
		       voyage.getEspece() != null;
	}
	
	
	/* ---------------------------------- */
	/*          Méthodes standard         */
	/* ---------------------------------- */

	public String toString()
	{
		return "Planete source      en [" + this.planeteSource     .getPosY() +":"+ this.planeteSource     .getPosX() + "]\n"+
		       "Planete destination en [" + this.planeteDestination.getPosY() +":"+ this.planeteDestination.getPosX() + "]\n"+
		       "Espece              : "   + this.espece                                                               +  "\n";
	}
}
