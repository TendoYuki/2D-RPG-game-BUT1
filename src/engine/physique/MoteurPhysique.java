/* ========================================================== */
/*                  Bibliotheque MoteurDeJeu                  */
/* --------------------------------------------               */
/* Bibliotheque pour aider la création de jeu video comme :   */
/* - Jeux de role                                             */
/* - Jeux de plateforme                                       */
/* - Jeux de combat                                           */
/* - Jeux de course                                           */
/* - Ancien jeu d'arcade (Pac-Man, Space Invider, Snake, ...) */
/* ========================================================== */

package engine.physique;

import static engine.main.JeuPhysique.*;

import java.io.IOException;
import java.util.ArrayList;

import engine.controle.Controle;

//permet de g�rer la physique

/**
 *
 * @author Pierre-Frederic Villard
 */
public class MoteurPhysique {

	// la liste des objets dans le monde

	/**
	 *
	 */
	public Monde monde;

	public boolean gravity = false;

	public float gravityValue = -0.04f;

	public int current_monster_index = 0;
	public int current_wall_index = 0;

	public static double SPEED = 1.5;

	/**
	 * Construit un moteur par defaut
	 * 
	 * @throws IOException
	 */
	public MoteurPhysique() {
	}

	// met a jour le monde

	/**
	 *
	 */
	public void update() {

		monde.balle.collision = 0;
		// mise a jour des objets
		for (Objet o : monde.objets) {
			o.update();
			o.collision = 0;
		}

		// mise a jour des monstres
		for (ObjetMonstre monstre : monde.monstres) {
			monstre.evolue();
			if (Collision.typeOfCollision == MONSTRE) {
				monde.balle.collision = MONSTRE;
				current_monster_index = monstre.index;
			}
		}

		// gestion du controleur
		if (monde.c.droite) {
			if (monde.balle.ovx == 0) {
			}
			monde.balle.ax = 0.1;
			if (monde.balle.vx > SPEED)
				monde.balle.vx = SPEED;

		} 
		else if (monde.c.gauche) {
			if (monde.balle.ovx == 0) {
			}
			monde.balle.ax = -0.1;
			if (monde.balle.vx < -SPEED)
				monde.balle.vx = -SPEED;

		}
		else {
			if ((monde.balle.vx < 0.2) && (monde.balle.vx > -0.2)) {
				monde.balle.vx = 0;
				monde.balle.ax = 0;
			} else if (monde.balle.vx > 0)
				monde.balle.ax = -0.1;
			else if (monde.balle.vx < 0)
				monde.balle.ax = +0.1;
		}

		// gestion des deplacement verticaux
		monde.balle.ay = 0;
		monde.balle.vy = 0;
		if (monde.c.haut) {
			if(monde.c.gauche) {
				monde.balle.vx = -SPEED*0.75;
				monde.balle.vy = SPEED*0.75;
			}
			else if(monde.c.droite) {
				monde.balle.vx = SPEED*0.75;
				monde.balle.vy = SPEED*0.75;
			}
			else {
				monde.balle.vy = SPEED;
			}
		}
		if (monde.c.bas) {
			if(monde.c.gauche) {
				monde.balle.vx = -SPEED*0.75;
				monde.balle.vy = -SPEED*0.75;
			}
			else if(monde.c.droite) {
				monde.balle.vx = SPEED*0.75;
				monde.balle.vy = -SPEED*0.75;
			}
			else {
				monde.balle.vy = -SPEED;
			}
		}

		if (monde.balle.vx > 0) {
			if (monde.balle.vy > 0){
				monde.balle.sprites.changeEtape("up-right");
			}
			else if (monde.balle.vy < 0) {
				monde.balle.sprites.changeEtape("down-right");
			}
			else {
				monde.balle.sprites.changeEtape("right");
			}
		}
		else if (monde.balle.vx < 0){
			if (monde.balle.vy > 0){
				monde.balle.sprites.changeEtape("up-left");
			}
			else if (monde.balle.vy < 0) {
				monde.balle.sprites.changeEtape("down-left");
			}
			else {
				monde.balle.sprites.changeEtape("left");
			}
		}
		else if (monde.balle.vy > 0){
			monde.balle.sprites.changeEtape("up");
		}
		else if (monde.balle.vy < 0){
			monde.balle.sprites.changeEtape("down");
		}
		// mise a jour de la balle
		monde.balle.update();

		// test de collision pour chaque mur
		for (Objet obj : monde.objets) {

			if (Collision.collision(monde.balle, obj)) {
				// si collision vient du haut
				if (Collision.collisionHaut(monde.balle, obj)) {
					monde.balle.py = monde.balle.py - monde.balle.vy;
					monde.balle.vy = -1;
					if (monde.c.enAir) {
						monde.c.enAir = false;
						if (monde.balle.vx == 0) {
							monde.balle.sprites.changeEtape("fixe");
						} else
							monde.balle.sprites.changeEtape("course");
					}

				}

				// si collision vient du Bas
				if (Collision.collisionBas(monde.balle, obj)) {

					monde.balle.py = monde.balle.py - monde.balle.vy;
					monde.balle.vy = -monde.balle.vy;
					;
				}

				// si collision vient de la gauche ou droite
				if (Collision.collisionGauche(monde.balle, obj)
						|| (Collision.collisionDroite(monde.balle, obj))) {
					monde.balle.px = monde.balle.px - monde.balle.vx;
					if (monde.balle.vx > 0)
						monde.balle.vx = -monde.balle.vx * monde.balle.ax - 0.5;
					else
						monde.balle.vx = -monde.balle.vx * monde.balle.ax + 0.5;
				}
				current_wall_index = obj.index;
			}

		}
		// Assign the last collision type if not a monster
		if (monde.balle.collision == 0)
			monde.balle.collision = Collision.typeOfCollision;

	}

}
