package source.metier;

public class Jeton
{
	private char   symbole;
	private String espece ;

	/*Factory pour vérifier si le symbole du jeton fait partie des symbole autorisés */

	public static Jeton creerJeton(char symbole, String espece)
	{
		symbole = Character.toUpperCase(symbole);

		// Test symbole invalide
		if(!Jeton.symboleValide(symbole) ) return null;

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
		if (espece == null || espece.equals("") ) return new Jeton(symbole); 
		
		// L'espèce est non null, le Jeton est donc une base
		return new Jeton(symbole, espece);
		
	}
	 //##############\\
	// Constructeurs  \\
	
	private Jeton(char symbole, String espece)
	{
		this.symbole = symbole;
		this.espece  = espece;
	}

	private Jeton(char symbole)
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
