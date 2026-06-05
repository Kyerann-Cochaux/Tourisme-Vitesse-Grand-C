package source.metier;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Scanner;

public class Metier
{
	private static final String CHEMIN_SAUVEGARDES    = "../source/metier/sauvegardes/"; // attention a ne pas oublier le / a la fin
	private static final String EXTENSION_SAUVEGARDES = ".data";
	
	private Plateau plateauJeu;
	private Pioche  pioche    ;
	
	public Metier()
	{
		this.plateauJeu = null;
		this.pioche     = new Pioche();
	}
	
	public void initialiserPlateau(int nbLignes, int nbColonnes, int nbFormes, int nbEspeces)
	{
		this.plateauJeu = Plateau.creerPlateau(nbLignes, nbColonnes, nbFormes, nbEspeces);
	}
	
	/* ---------------------------------- */
	/*               Accesseurs           */
	/* ---------------------------------- */
	
	public Plateau getPlateau() { return this.plateauJeu;}
	public Pioche  getPioche () { return this.pioche    ;}
	
	
	
	
	/* ---------------------------------- */
	/*          Autres méthodes           */
	/* ---------------------------------- */
	
	public boolean sauvegardeExiste(String nomSauvegarde)
	{
		String cheminSauvegarde = Metier.CHEMIN_SAUVEGARDES +
		                          nomSauvegarde + Metier.EXTENSION_SAUVEGARDES;
		
		try
		{
			Scanner sc = new Scanner ( new FileInputStream ( cheminSauvegarde ), "UTF8" );
			sc.close();
		}
		catch (Exception e)
		{
			return false;
		}
		return true;
	}
	
	
	public boolean chargerPlateau(String cheminSauvegarde)
	{
		int nbLignes   = 0;
		int nbColonnes = 0;
		int nbFormes   = 0;
		int nbEspeces  = 0;
		
		try
		{
			Scanner sc = new Scanner ( new FileInputStream ( cheminSauvegarde ), "UTF8" );
			
			
			String premiereLigne = sc.nextLine();
			
			String[] creationArguments = premiereLigne       .split(" ");
			String[] creationTaille    = creationArguments[0].split("x");
			
			nbLignes   = Integer.parseInt(creationTaille[0]);
			nbColonnes = Integer.parseInt(creationTaille[1]);
			
			nbFormes   = Integer.parseInt(creationArguments[1]);
			nbEspeces  = Integer.parseInt(creationArguments[2]);
			
			
			// initialisation du plateau
			this.initialiserPlateau(nbLignes, nbColonnes, nbFormes, nbEspeces);
			
			
			// placement des systèmes
			for (int numLig = 0; numLig < nbLignes; numLig++)
			{
				String ligne = sc.nextLine();
				
				for (int numCol = 0; numCol < nbColonnes; numCol++)
				{
					int numZone = Integer.parseInt( "" + ligne.charAt(numCol) );
					this.plateauJeu.setNumSysteme(numZone, numCol, numLig, true);
				}
			}
			
			
			// placement des planetes
			for (int numLig = 0; numLig < nbLignes; numLig++)
			{
				String ligne = sc.nextLine();
				
				for (int numCol = 0; numCol < nbColonnes; numCol++)
				{
					char sIndPlanete = ligne.charAt(numCol);
					
					if( sIndPlanete != '.' )
					{
						int indPlanete = Integer.parseInt("" + sIndPlanete);
						
						Planete tempPlanete = Planete.creerPlanete(Plateau.TAB_PLANETES[indPlanete].charAt(0), null);
						
						this.plateauJeu.getCase(numCol, numLig).setPlanete(tempPlanete);
					}
					
				}
			}
			
			
			// placement des bases
			for (int numLig = 0; numLig < nbLignes; numLig++)
			{
				String ligne = sc.nextLine();
				
				for (int numCol = 0; numCol < nbColonnes; numCol++)
				{
					
					char sIndEspece = ligne.charAt(numCol);
					
					if( sIndEspece != '-' )
					{
						int indEspece = Integer.parseInt("" + sIndEspece);
						String typeEspece = Plateau.TAB_ESPECES[indEspece];
						
						this.plateauJeu.getCase(numLig, numCol).getPlanete().setEspece(typeEspece);
					}
					
				}
			}
			
			// fermeture du scanner
			sc.close();
		}
		catch (Exception e)
		{
			System.out.println("Erreur lors du chargement du fichier sauvegardé.");
			this.initialiserPlateau(nbLignes, nbColonnes, nbFormes, nbEspeces);
			return false;
		}
		
		return true;
	}
	
	public boolean sauvegarderPlateau(String nomSauvegarde)
	{
		if ( this.getPlateau() == null  ) return false;
		
		
		
		
		try
		{
			PrintWriter pw = new PrintWriter
				(
					new OutputStreamWriter
					(
						new FileOutputStream
						(
							Metier.CHEMIN_SAUVEGARDES + nomSauvegarde + Metier.EXTENSION_SAUVEGARDES
						), "UTF8"
					)
				);
			
			pw.println ( "" + this.plateauJeu.getNbLignes() + "x" + this.plateauJeu.getNbColonnes() + " " +
			                  this.plateauJeu.getNbFormes() + " " + this.plateauJeu.getNbEspeces ()         );
			
			
			// sauvegarde des systèmes
			for (int numLig = 0; numLig < this.getPlateau().getNbLignes(); numLig++)
			{
				String ligne = "";
				
				for (int numCol = 0; numCol < this.getPlateau().getNbColonnes(); numCol++)
					ligne += this.getPlateau().getCase(numCol, numLig).getNumSysteme();
				
				pw.println ( ligne );
			}
			
			
			
			// sauvegarde des planetes
			for (int numLig = 0; numLig < this.getPlateau().getNbLignes(); numLig++)
			{
				String ligne = "";
				
				for (int numCol = 0; numCol < this.getPlateau().getNbColonnes(); numCol++)
				{
					Planete planete = this.getPlateau().getCase(numCol, numLig).getPlanete();
					char symolePlanete = 'X';
					
					if ( planete == null )
						symolePlanete = '.';
					else
					{
						for (int numPlanete = 0; numPlanete < Plateau.TAB_PLANETES.length; numPlanete++)
						{
							char symoleATest = Plateau.TAB_PLANETES[numPlanete].charAt(0);
							
							if ( symoleATest == planete.getSymbole() )
								symolePlanete = (char)('0' + numPlanete);
						}
					}
					
					ligne += symolePlanete;
				}
				
				pw.println ( ligne );
			}
			
			
			
			// sauvegarde des bases des espèces
			for (int numLig = 0; numLig < this.getPlateau().getNbLignes(); numLig++)
			{
				String ligne = "";
				
				for (int numCol = 0; numCol < this.getPlateau().getNbColonnes(); numCol++)
				{
					Planete planete = this.getPlateau().getCase(numCol, numLig).getPlanete();
					char symoleEspece = 'X';
					
					if ( planete == null || planete.getEspece() == null )
						symoleEspece = '-';
					else
					{
						for (int numEspece = 0; numEspece < Plateau.TAB_ESPECES.length; numEspece++)
						{
							char symoleATest = Plateau.TAB_ESPECES[numEspece].charAt(0);
							
							if ( symoleATest == planete.getEspece().charAt(0) )
								symoleEspece = (char)('0' + numEspece);
						}
					}
					
					ligne += symoleEspece;
				}
				
				pw.println ( ligne );
			}
			
			
			// fermeture du writer
			pw.close();
		}
		catch (Exception e)
		{
			System.out.println("Erreur lors de la sauvegarde du plateau.");
			return false;
		}
		
		return true;
	}
}