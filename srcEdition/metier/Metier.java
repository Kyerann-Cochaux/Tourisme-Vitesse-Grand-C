package srcEdition.metier;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Scanner;

public class Metier
{
	private static final String CHEMIN_SAUVEGARDES    = "../sauvegardes/"; // attention a ne pas oublier le / a la fin
	private static final String EXTENSION_SAUVEGARDES = ".data";
	
	private Plateau plateauJeu;
	
	
	public Metier()
	{
		this.plateauJeu = null;
	}
	
	public void initialiserPlateau(int nbLignes, int nbColonnes, int nbFormes, int nbEspeces)
	{
		this.plateauJeu = Plateau.creerPlateau(nbLignes, nbColonnes, nbFormes, nbEspeces);
	}
	
	/* ---------------------------------- */
	/*               Accesseurs           */
	/* ---------------------------------- */
	
	public Plateau getPlateau() { return this.plateauJeu;}
	
	
	
	
	/* ---------------------------------- */
	/*          Autres méthodes           */
	/* ---------------------------------- */

	public int getNbEspeces()
	{
		return plateauJeu.getNbEspeces();
	}

	public int getNbEspecesPosees()
	{
		return plateauJeu.getNbEspecesPosees ();
	}
	
	
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
				String   ligne  = sc.nextLine();
				String[] numSys = ligne.split("\t");
				
				for (int numCol = 0; numCol < nbColonnes; numCol++)
				{
					int numZone = Integer.parseInt( numSys[numCol] );
					this.plateauJeu.setNumSysteme(numZone, numCol, numLig, true);
				}
			}
			
			
			// placement des planetes
			for (int numLig = 0; numLig < nbLignes; numLig++)
			{
				String   ligne   = sc.nextLine();
				String[] numPlan = ligne.split("\t");
				
				for (int numCol = 0; numCol < nbColonnes; numCol++)
				{
					String sIndPlanete = numPlan[numCol];
					
					if( !sIndPlanete.equals(".") )
					{
						int indPlanete = Integer.parseInt("" + sIndPlanete);
						
						Planete tempPlanete = Planete.creerPlanete(Plateau.TAB_PLANETES[indPlanete].charAt(0) );
						
						this.plateauJeu.getCase(numCol, numLig).setPlanete(tempPlanete);
						// System.out.println(tempPlanete.getSymbole() + " : numCol -> " + numCol + "\n" + "    numLig -> " + numLig );
						// System.out.println();
						//this.plateauJeu.ajouterPlanete(numCol, numLig, tempPlanete);
					}
					
				}
			}
			
			
			// placement des bases
			for (int numLig = 0; numLig < nbLignes; numLig++)
			{
				String ligne     = sc.nextLine();
				String[] numBase = ligne.split("\t");
				
				for (int numCol = 0; numCol < nbColonnes; numCol++)
				{
					
					String sIndEspece = numBase[numCol];
					
					if( !sIndEspece.equals("-") )
					{
						int indEspece = Integer.parseInt("" + sIndEspece);
						String typeEspece = Plateau.TAB_ESPECES[indEspece];
						
						this.plateauJeu.getCase(numCol, numLig).getPlanete().setEspece(typeEspece);
					}
					
				}
			}
			
			// fermeture du scanner
			sc.close();
		}
		catch (Exception e)
		{
			// System.out.println("Erreur lors du chargement du fichier sauvegardé.");
			e.printStackTrace();
			
			this.initialiserPlateau(nbLignes, nbColonnes, nbFormes, nbEspeces);
			return false;
		}
		
		this.plateauJeu.actualiserVoyages();
		
		return true;
	}

	public boolean sauvegarderPlateau(String nomSauvegarde)
	{
		return this.sauvegarderPlateau(nomSauvegarde, true);
	}
	

	public boolean sauvegarderPlateau(String nomSauvegarde, boolean reecrire)
	{
		
		this.plateauJeu.remplirZoneVide();
		
		if ( this.getPlateau() == null ) return false;
		
		if ( !reecrire )
		{
			while ( this.sauvegardeExiste(nomSauvegarde) )
			{
				String[] nomSaveDiv = nomSauvegarde.split("-");
				
				// System.out.println("nomSaveDiv.length : " +  nomSaveDiv[nomSaveDiv.length-1]);
				
				if ( nomSaveDiv.length == 1 )
					nomSauvegarde += "-1";
				else
				{
					String sNumApparition = nomSaveDiv[nomSaveDiv.length-1];
					int     numApparition = Integer.parseInt(sNumApparition)+1;
					
					nomSaveDiv[nomSaveDiv.length-1] = "" + numApparition;
					
					
					
					nomSauvegarde = nomSaveDiv[0];
					
					for (int numDiv = 1; numDiv < nomSaveDiv.length; numDiv++)
					{
						nomSauvegarde += "-" + nomSaveDiv[numDiv];
					}
				}
				// System.out.println(nomSauvegarde);
			}
		}
		
		
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
			
			pw.println ( "" + this.plateauJeu.getNbLignes  () + "x" + this.plateauJeu.getNbColonnes() + " " +
			                  this.plateauJeu.getNbPlanetes() + " " + this.plateauJeu.getNbEspeces ()         );
			
			
			// sauvegarde des systèmes
			for (int numLig = 0; numLig < this.getPlateau().getNbLignes(); numLig++)
			{
				String ligne = "";
				
				for (int numCol = 0; numCol < this.getPlateau().getNbColonnes(); numCol++)
					ligne += this.getPlateau().getCase(numCol, numLig).getNumSysteme() + "\t";
				
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
					
					ligne += symolePlanete + "\t";
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
					
					ligne += symoleEspece + "\t";
				}
				
				pw.println ( ligne );
			}
			
			
			// fermeture du writer
			pw.close();
		}
		catch (Exception e)
		{
			// System.out.println("Erreur lors de la sauvegarde du plateau.");
			e.printStackTrace();
			
			return false;
		}
		
		return true;
	}
}
