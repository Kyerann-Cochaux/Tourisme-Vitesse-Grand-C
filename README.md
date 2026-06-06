# Tourisme à Vitesse Grand C

Liste de chose a faire:

[Appli Création]

 Metier :
 
| Plateau | (Kyerann)
[AJOUTÉ] privée corrigerDecoupeZone(x, y)
[MODIFIÉ] boolean setNumSysteme(int numSysteme, int x, int y, boolean forcerPlacement)
[AJOUTÉ] ArrayList<Case> parcoursZone(Case caseDep)

[AJOUTE] int tailleZone(int numZone)

[ FAIT ] boolean remplirZone(int numZone, Case caseDep)

[ANULLE] remplirZone(caseDep)

[ FAIT ] boolean estZoneScindee(int numZone)

[------] une méthode pour remplir les zones a -1

 IHM :
 
| PanelEdition |

MenuBar dans le Frame quand PanelEdition est afficher (Clément)

Ajout des Planètes sur le Plateau

Render des Liens sur le Plateau

Ajout des Zones sur le Plateau

Ajout d'un bouton sauvegarder le plateau <-- MenuBar


Liste des changements fonctionnels effectués le 05/06/2026 par Clément :

• Simplification de FrameCreation pour l'ajout des différents panel
	Se sert d'une méthode ayant un entier en paramètre pour savoir quoi afficher et quand
• Centrage de la Frame en fonction de la taille de l'écran

• Nettoyage de code dans les classes FrameCreation et PanelCreation 

• Passerelle entre IHM et Métier pour le chargement d'un fichier et l'initialisation d'un plateau
	Le chargement ne fonctionne pas tout le temps (voir précédent commit) (problème coté Métier)


"En cours" : Optimisation dans PanelPlateau, panelMenu et panelInit.
	|
	|---> les classes IHM ne doivent pas stocker les attributs Métier ! Elles doivent seulement les utiliser.