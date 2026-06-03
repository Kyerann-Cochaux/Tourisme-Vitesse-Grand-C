package source.metier;

public class Planete
{
	private char   symbole;
	private String espece ;

	/*Factory pour vérifier si le symbole de la planète fait partie des symboles autorisés */

	public static Planete creerPlanete(char symbole, String espece)
	{
		symbole = Character.toUpperCase(symbole);

		// Test symbole invalide
		if(!Planete.symboleValide(symbole) ) return null;

		// Test espèce invalide
		if (espece != null && !espece.equals("") )
		{
			boolean estEspeceValide = false;

			for (int cpt = 0; cpt < Plateau.TAB_ESPECES.length; cpt++)
			{
				if ( Plateau.TAB_ESPECES[cpt].equals(espece) )
					estEspeceValide = true;
			}
			if ( !estEspeceValide ) return null;
		}
		
		
		
		// L'espèce est null, donc le Jeton ne possède qu'un symbole
		if (espece == null || espece.equals("") ) return new Planete(symbole); 
		
		// L'espèce est non null, le Jeton est donc une base
		return new Planete(symbole, espece);
		
	}
	 //############## \\
	// Constructeurs  \\
	
	private Planete(char symbole, String espece)
	{
		this.symbole = symbole;
		this.espece  = espece;
	}

	private Planete(char symbole)
	{
		this(symbole, null);
	}

	/* ---------------------------------- */
	/*               Accesseurs           */
	/* ---------------------------------- */
	
	public char   getSymbole() {return this.symbole;}
	public String getEspece () {return this.espece ;}

	/* ---------------------------------- */
	/*           Autres méthodes          */
	/* ---------------------------------- */
	
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
