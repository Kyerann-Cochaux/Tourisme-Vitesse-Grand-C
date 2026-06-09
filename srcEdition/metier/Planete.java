package srcEdition.metier;

public class Planete
{
	private char   symbole;
	private String espece ;

	/*Factory pour vérifier si le symbole de la planète fait partie des symboles autorisés */

	public static Planete creerPlanete(char symbole)
	{
		symbole = Character.toUpperCase(symbole);
		
		// Test symbole invalide
		if(!Planete.symboleValide(symbole) ) return null;

		return new Planete(symbole);
	}

	/* ---------------------------------- */
	/*            Constructeurs           */
	/* ---------------------------------- */
	
	private Planete(char symbole)
	{
		this.symbole = symbole;
		this.espece  = null;
	}

	/* ---------------------------------- */
	/*               Accesseurs           */
	/* ---------------------------------- */
	
	public char   getSymbole() {return this.symbole;}
	public String getEspece () {return this.espece ;}

	/* ---------------------------------- */
	/*            Modificateurs           */
	/* ---------------------------------- */

	public boolean setEspece(String espece) 
	{
		if (espece == null) 
		{
			this.espece = null;
			return true;
		}

		if ( !especeValide(espece) )
		{
			System.out.println("Espece invalide : " + espece);
			return false;
		}
		// System.out.println("Espece valide : " + espece);

		this.espece = espece;
		return true;
	} 

	public boolean setSymbole(char symbole)
	{
		if (!symboleValide(symbole) ) return false;
		
		this.symbole = symbole;
		return true;
	}

	/* ---------------------------------- */
	/*           Autres méthodes          */
	/* ---------------------------------- */

	// Vérifie si le String est bien dans le tableau d'espèces disponible
	private static boolean especeValide(String espece)
	{
		for (int cpt = 0; cpt < Plateau.TAB_ESPECES.length; cpt++)
		{
			if ( Plateau.TAB_ESPECES[cpt].equals(espece) )
				return true;
		}
		
		return false;
	}
	
	private static boolean symboleValide(char symbole)
	{
		return (symbole == 'G' || symbole == 'O' || symbole == 'T' || symbole == 'V');
	}

	public boolean estBase() { return this.espece != null && !this.espece.equals("");}

	/* ---------------------------------- */
	/*          Méthodes standard         */
	/* ---------------------------------- */

	public String toString()
	{
		return "Symbole  : " + this.symbole + ( (this.estBase() ) ? "\n" + 
			   "Base     : " + this.espece : "");
	}
}
