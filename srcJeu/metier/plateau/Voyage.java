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
		
		this.espece = espece;
		
		return true;
	}
	
	
	// retourne si ce voyage coupe un autre voyage qui as une espece
	public boolean coupe(Voyage voyage)
	{
		int xA = this.getPlaneteDestination().getPosX();
		int yA = this.getPlaneteDestination().getPosY();

		int xB = this.getPlaneteSource().getPosX();
		int yB = this.getPlaneteSource().getPosY();

		int xC = voyage.getPlaneteDestination().getPosX();
		int yC = voyage.getPlaneteDestination().getPosY();

		int xD = voyage.getPlaneteSource().getPosX();
		int yD = voyage.getPlaneteSource().getPosY();

		int ori1 = calculOrientation(yA, xA, yB, xB, yC, xC);
		int ori2 = calculOrientation(yA, xA, yB, xB, yD, xD);
		int ori3 = calculOrientation(yC, xC, yD, xD, yA, xA);
		int ori4 = calculOrientation(yC, xC, yD, xD, yB, xB);
		
		// Les segments se croisent si et seulement si :
		// - C et D sont de côtés opposés de la droite (AB) (l'un est horaire, l'autre anti-horaire)
		// - A et B sont de côtés opposés de la droite (CD)
		return ((ori1 == 1 && ori2 == 2) || (ori1 == 2 && ori2 == 1)) &&
		       ((ori3 == 1 && ori4 == 2) || (ori3 == 2 && ori4 == 1)) && 
		       voyage.getEspece() != null;
	}

	/**
	 * Détermine l'orientation de trois points (1 = Horaire, 2 = Anti-horaire, 0 = Alignés)
	 */
	private int calculOrientation(int lig1, int col1, int lig2, int col2, int lig3, int col3)
	{
		// Calcul du produit en croix (en considérant col comme X et lig comme Y)
		long sens = (long)(lig2 - lig1) * (col3 - col2) - (long)(col2 - col1) * (lig3 - lig2);
		
		if     (sens == 0) return 0; // Les points sont alignés
		return (sens >  0) ? 1 : 2; // 1 = Sens horaire, 2 = Sens anti-horaire
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
