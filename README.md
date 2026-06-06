# Tourisme à Vitesse Grand C

Liste de chose a faire:

[Appli Création]

 Metier :
 
| Plateau | (Kyerann)

méthode remplirZone(numZone,caseDep)

méthode remplirZone(caseDep)

méthode zoneScindee(numZone)

 IHM :
 
| PanelEdition |

initialiser le plateau à partir d'un fichier

MenuBar dans le Frame quand PanelEdition est afficher (Clément)

Ajout des Planètes sur le Plateau

Render des Liens sur le Plateau

Ajout des Zones sur le Plateau

Ajout des Bases sur les Planètes

Ajout d'un bouton sauvegarder le plateau <-- MenuBar


Liste des changements fonctionnels effectués le 05/06/2026 par Clément :

• Simplification de FrameCreation pour l'ajout des différents panel
	Se sert d'une méthode ayant un entier en paramètre pour savoir quoi afficher et quand
• Centrage de la Frame en fonction de la taille de l'écran

• Nettoyage de code dans les classes FrameCreation et PanelCreation 



"En cours" : Optimisation dans PanelPlateau, panelMenu et panelInit.
	|
	|---> les classes IHM ne doivent pas stocker les attributs Métier ! Elles doivent seulement les utiliser.