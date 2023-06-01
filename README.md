# SAE2-1-tp-4-rigard-riviere-plateau

## Sujet 8 : Jeu de rôle
Objectif : concevoir et réaliser un jeu de rôle permettant à un personnage de se déplacer sur une carte comprenant
de l’interaction avec des personnages non jouable, des monstres, la gestion d’équipements, des points de vie, des
points de magie et de l’expérience.

## Commandes
- Q -> Quitter le jeu
- M -> Ouverture de la map
- Echap -> Fermer le shop
- F -> Passer au prochain script du dialogue
- Les mouvements du joueur sont régis par les flèches directionelles

## But du jeu
Au début du jeu, un npc nous attend afin de nous demander d'aller récupérer un objet lui appartenant.
Dans ce jeu, le but est de tuer tous les monstres présents dans le donjon afin d'ouvrir la salle du boss.
Ensuite, une fois la salle ouverte, il faut aller tuer le boss du donjon afin de récupérer l'objet du npc.
Une fois l'objet rendu au npc, le jeu est fini.

## Lancement du jeu
Afin de lancer le jeu, il est possible de le lancer via la commande suivante :
```bash
    java -jar --enable-preview sae2-1-tp-4-rigard-riviere-plateau.jar
```
Sinon, vscode permettra de compiler et lancer le jeu.