---
author: PF Villard
title: Le moteur de jeu
logo: iut.png
---
<!-- pandoc -s -f markdown -t html5 --template=template.html -c mvp.css  -o MoteurDeJeu.html MoteurDeJeu.md -->


# Utilisation de la bibliothèque de moteur de jeu  <!-- omit in toc -->


Avant de lire ce document, il faut déjà comprendre l'[architecture du Moteur de jeu](./archiMoteurDeJeu.pdf).

![Example du jeu par défaut](logo.png)

# Sommaire  <!-- omit in toc -->

- [Utilisation basique](#utilisation-basique)
  - [Le Modèle](#le-modèle)
  - [La Vue](#la-vue)
  - [Le Contrôleur](#le-contrôleur)
  - [La méthode principale](#la-méthode-principale)
- [Utilisation avancée](#utilisation-avancée)
  - [Création de décors](#création-de-décors)
  - [Changement d'image](#changement-dimage)
  - [Changement d'image pendant une collision](#changement-dimage-pendant-une-collision)
  - [Changement de gravité](#changement-de-gravité)
  - [Vue subjective](#vue-subjective)
  - [Boucle de jeu](#boucle-de-jeu)
  - [Changement du contrôleur](#changement-du-contrôleur)
  - [Ajouter un joueur](#ajouter-un-joueur)

# Utilisation basique

Pour utiliser la bibliothèque de moteur de jeu, il faut adopter une architecture du type Modèle-Vue-Contrôleur. Il faut donc commencer par définir ces trois entités.

## Le Modèle

Il faut d'abord construire un **Monde** :
```
// Construction du monde
monMonde= new Monde();
```
Pour peupler ce monde, on a le choix entre des éléments de décors (`addMur()`), des monstres (`addMonstre()`) ou des personnages jouables (`addHero()`). Tous ces éléments sont paramétrisables (position, image, comportement, etc.)

Il faut alors rajouter un **moteur physique** pour gérer les règles du monde telles que les collisions, la gravité ou la façon de gérer la vitesse des éléments :
```
moteurPhys=new MoteurPhysique();
//On ajoute le monde au moteur
moteurPhys.monde=monMonde;
```
Là aussi, on peut changer les valeurs par défaut.

## La Vue

La vue correspond à l'affichage. C'est cette partie qui va gérer la fenêtre graphique :
```
affiche=new Afficheur(moteurPhys.monde);
```

## Le Contrôleur
Le contrôleur correspond dans notre cas à la gestion du clavier :
```
ControleurClavier cClavier=new ControleurClavier(true); 
```

## La méthode principale

La méthode principale comprend la boucle de jeu :
```
BouclePrincipale maBoucle= new BouclePrincipale();
// Ajout du Contrôleur à le fenêtre
maBoucle.cClavier=cClavier;
// Ajout de la Vue au jeu
maBoucle.jeuPhysique.affiche=affiche;
// Ajout du Modèle à la boucle
maBoucle.jeuPhysique.moteurPhys=moteurPhys;
```
Une fois que tous ces éléments ont été définis, le jeu pourra se lancer par :
```
maBoucle.lanceBouclePrincipale();
```

# Utilisation avancée

## Création de décors

Il y a deux façon de créer un décors :
- Soit en utilisant un objet de la classe `ObjetMur` en le rajoutant au **monde** par `addMur(int x, int y, int dx, int dy)` avec $(x,y)$ la position du coin supérieur gauche du mur de dimension $dx \times dy$
- Soit en créant une classe héritant de `ObjetMur` et en l'ajoutant au **monde** par `addObjet(Objet monObjet)`.

Les éléments de décors, comme toutes les classes héritant de `Objet` ont une méthode `update()` qui permet de rajouter un comportement dynamique (Cf `ObjetMurDynamique`).

## Changement d'image

Pour changer l'image associée aux monstres ou aux héros, il suffit d'appeler la méthode `assignNewImage(String fileName)` de l'attribut `sprites` de l'objet. Exemple :
```
monMonde.balle.sprites.assignNewImage("hero2.png");
```

![Changement de l'image du hero](fig/img2.png)

Il est également possible de changer l'image du decors. 
```
affiche.decor.changeImage("background2.jpg");
```

![Changement du background](fig/img1.png)

## Changement d'image pendant une collision

Pour changer l'aspect d'un héros, monstre ou mur lorsque une collision a lieu, il faut faire une classe fille (par exemple de `ObjetHeros`)  et ré-écrire la méthode `public void draw(Graphics g)`en rajoutant quelque chose du type :

```
// si en collision affiche carre
if (collision==1) {
    g.setColor(Color.red);
    g.fillRect(tab[0],tab[1],tab[2],tab[3]);
}
```

Pour changer l'aspect du hero, il faut aussi créer une classe héritant de `Monde` afin de d'assigner à la construction la bonne classe pour `balle`.

Exemple :

```
public class MonMondeCool extends Monde{
    	public MonMondeCool() throws IOException
	{
        balle=new HeroCarreQuandTouche();
		//gere la vision subjective
		Repere.h=balle;	
	}
}
```

![Rectangle rouge lorsque collision](fig/img3.png)

## Changement de gravité

Pour changer la gravité, il faut changer la valeur de l'attribut `gravityValue` dans `MoteurPhysique`. Exemple :
```
moteurPhys.gravityValue=-0.08f;
```
Pour annuler la gravité et pouvoir se déplacer en **X** et en **Y** de la même façon, il faut mettre l'attribut `gravity` à `false`. 

## Vue subjective

On parle de vue subjective lorsque l'on se place du point de vue du joueur. C'est-à-dire que si on se déplace, la géométrie du hero ne bouge pas, c'est le décors et les monstres qui bougent.  Si la vu n'est pas subjective, le décors est fixe et ceux sont les monstres et le hero qui bougent.
La vue subjective peut être activé en changeant la valeur du booléen `Repere.isSubjective`.

## Boucle de jeu

La boucle de jeu est créée par défaut par la classe `BoucleDeJeu`. Pour gérer les événements du jeu, il est conseillé de créer une classe qui hérite de `BoucleDeJeu` en re-créant la méthode `public void lanceBouclePrincipale() throws Exception`.

Exemple de code pour gérer les collisions en fonction du type

```			
switch (jeuPhysique.moteurPhys.monde.balle.collision)
{
    case MONSTRE: System.out.println("Aie avec monstre numéro "+jeuPhysique.moteurPhys.current_monster_index);break;
    case HERO: System.out.println("Copain !");break;
    case DECORS: System.out.println("Pas mal avec mur numéro "+jeuPhysique.moteurPhys.current_wall_index);break;
    default: break;
}
```

## Changement du contrôleur


Pour personnaliser son moyen de contrôler un joueur, plusieurs étapes sont nécessaires.

1. Il faut faire une classe fille de `ControleurClavier` afin d'attribuer d'autres valeurs pour les éléments de l'attribut `Controle c`.

Exemple :

```
public class ControleurClavier2 extends ControleurClavier{
    public ControleurClavier2(boolean affiche) {
        super(affiche);
    }
	public void keyPressed(KeyEvent e) {
		//touche gauche
		if (e.getKeyCode()==KeyEvent.VK_A)
		{
			c.gauche=true;			
		}
		...
	}
	public void keyReleased(KeyEvent e) {
		//touche gauche
		if (e.getKeyCode()==KeyEvent.VK_A)
		{
			c.gauche=false;
		}
		...		
	}
}
```

2. Il faut créer une classe fille de `Monde` pour rajouter l'attribut correspondant au nouveau contrôleur.

Exemple :
```
public class MondePourDeux extends Monde{
    public Controle c2;
    ...
}
```

3. Il faut créer une classe qui hérite de `BouclePrincipale` afin de pouvoir rajouter le contrôleur au monde et écouter les nouveaux événements clavier.

Exemple :
   
```
public void lanceBouclePrincipale() throws Exception {
    ...
	jeuPhysique.affiche.addKeyListener(cClavier2);
	...
    MondePourDeux monMonde=(MondePourDeux jeuPhysique.moteurPhys.monde;
    monMonde.c2=cClavier2.c;
    ...         
```   

4. Il faut créer une classe fille de `MoteurPhysique` pour rajouter l'attribut correspondant au nouveau monde.

```
public class MoteurPourDeux extends MoteurPhysique{
    public MondePourDeux monde;
    ...
```

5. Enfin, dans la méthode principale :
```
// Création d'un contrôleur
ControleurClavier2 cClavier2=new ControleurClavier2(true);
// Ajout du contrôleur à la boucle
maBoucle.cClavier2=cClavier2;
```

## Ajouter un joueur

Pour ajouter un joueur, il faut déjà faire une nouveau contrôleur pour le second joueur (voir section [Changement du contrôleur](#changement-du-contrôleur)).

Il faut aussi faire les étapes suivantes :

1. Créer une classe fille de `Afficheur` pour rajouter l'affichage du second personnage et dans la méthode `render()` mettre :
```
decor.affiche((int)m.heros.get(0).px,g);
decor.affiche((int)m.heros.get(1).px,g);
...                
ObjetHeros b1 = m.heros.get(0);
b1.draw(g);
ObjetHeros b2 = m.heros.get(1);
b2.draw(g);
...
```
2. Compléter la classe que vous aviez créée dans [Changement du contrôleur](#changement-du-contrôleur) en modifiant les actions suites aux événements clavier. Par exemple, pour contrôler le mouvement à gauche du second hero :

```
if (monde.c2.gauche)
{
    if (heros.get(1).ovx==0)
    {
        heros.get(1).sprites.changeEtape("course");
    }
    heros.get(1).ax = -0.1;
    if (hheros.get(1).vx<-2)
            heros.get(1).vx=-2;
}
```  
3. Enfin, dans la méthode principale, ajouter les héros :
```
monMonde.addHero(0,0,500,20);//hero 1
monMonde.addHero(0,0,80,20);//hero 2
// Changement d'image pour hero 2
monMonde.heros.get(1).sprites.assignNewImage("hero2.png");
```

![Mode deux joueurs](fig/img4.png)


