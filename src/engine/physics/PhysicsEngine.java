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

package engine.physics;

import static engine.main.GamePhysics.*;

import java.io.IOException;
import java.util.Map.Entry;

import engine.tiles.Directions;
import engine.trigger.TriggerMap;

//permet de g�rer la physique

/**
 *
 * @author Pierre-Frederic Villard
 */
public class PhysicsEngine {

	// la liste des objets dans le monde

	/**
	 *
	 */
	public World world;

	public boolean gravity = false;

	public float gravityValue = -0.04f;

	public int current_monster_index = 0;
	public int current_wall_index = 0;

	public static double SPEED = 2;

	/**
	 * Construit un moteur par defaut
	 * 
	 * @throws IOException
	 */
	public PhysicsEngine() {
	}

	// met a jour le monde

	/**
	 *
	 */
	public void update() {

		world.player.collision = 0;
		// mise a jour des objets
		for (PhysicalObject o : world.objects) {
			o.update();
			o.collision = 0;
		}

		for(Entry<Directions, Wall> wall: world.getWorldBorder().getBorderWalls().entrySet()){
			wall.getValue().update();
			wall.getValue().collision = 0;
		}

		// mise a jour des monstres
		for (Enemy enemy : world.map.activeRoom.enemies) {
			enemy.evolue();
			if (Collision.typeOfCollision == ENEMY) {
				world.player.collision = ENEMY;
				current_monster_index = enemy.index;
			}
		}

		// gestion du controleur
		if (world.c.droite) {
			if (world.player.ovx == 0) {
			}
			world.player.ax = 0.1;
			if (world.player.vx > SPEED)
				world.player.vx = SPEED;

		} 
		else if (world.c.gauche) {
			if (world.player.ovx == 0) {
			}
			world.player.ax = -0.1;
			if (world.player.vx < -SPEED)
				world.player.vx = -SPEED;

		}
		else {
			if ((world.player.vx < 0.2) && (world.player.vx > -0.2)) {
				world.player.vx = 0;
				world.player.ax = 0;
			} else if (world.player.vx > 0)
				world.player.ax = -0.1;
			else if (world.player.vx < 0)
				world.player.ax = +0.1;
		}

		// gestion des deplacement verticaux
		world.player.ay = 0;
		world.player.vy = 0;
		if (world.c.haut) {
			if(world.c.gauche) {
				world.player.vx = -SPEED*0.75;
				world.player.vy = SPEED*0.75;
			}
			else if(world.c.droite) {
				world.player.vx = SPEED*0.75;
				world.player.vy = SPEED*0.75;
			}
			else {
				world.player.vy = SPEED;
			}
		}
		if (world.c.bas) {
			if(world.c.gauche) {
				world.player.vx = -SPEED*0.75;
				world.player.vy = -SPEED*0.75;
			}
			else if(world.c.droite) {
				world.player.vx = SPEED*0.75;
				world.player.vy = -SPEED*0.75;
			}
			else {
				world.player.vy = -SPEED;
			}
		}

		if (world.player.vx > 0) {
			if (world.player.vy > 0){
				world.player.sprites.changeActivity("up-right");
			}
			else if (world.player.vy < 0) {
				world.player.sprites.changeActivity("down-right");
			}
			else {
				world.player.sprites.changeActivity("right");
			}
		}
		else if (world.player.vx < 0){
			if (world.player.vy > 0){
				world.player.sprites.changeActivity("up-left");
			}
			else if (world.player.vy < 0) {
				world.player.sprites.changeActivity("down-left");
			}
			else {
				world.player.sprites.changeActivity("left");
			}
		}
		else if (world.player.vy > 0){
			world.player.sprites.changeActivity("up");
		}
		else if (world.player.vy < 0){
			world.player.sprites.changeActivity("down");
		}
		// mise a jour de la balle
		world.player.update();

		// test de collision pour chaque mur
		for (Entry<Directions, Wall> wall: world.getWorldBorder().getBorderWalls().entrySet()) {

			if (Collision.collision(world.player, wall.getValue())) {
				world.player.py = world.player.py - 1.1*world.player.vy;
				world.player.vy = 0;
				world.player.px = world.player.px - 1.1*world.player.vx;
				world.player.vx = 0;
			}

		}
		for (Enemy monstre : world.map.activeRoom.enemies) {
			if(Collision.collision(monstre, world.player)) {
				world.player.py = world.player.py - 1.1*world.player.vy;
				world.player.vy = 0;
				world.player.px = world.player.px - 1.1*world.player.vx;
				world.player.vx = 0;
			}

		}
		for (NPC npc : world.map.activeRoom.npcs) {
			if(Collision.collision(npc, world.player)) {
				world.player.py = world.player.py - 1.1*world.player.vy;
				world.player.vy = 0;
				world.player.px = world.player.px - 1.1*world.player.vx;
				world.player.vx = 0;
			}

		}
		for (TriggerMap triggerMap : world.triggerMaps) {
			triggerMap.update();
		}
		// Assign the last collision type if not a monster
		if (world.player.collision == 0)
			world.player.collision = Collision.typeOfCollision;

		boolean isPlayerInteracting = false;
		for (NPC npc : world.map.activeRoom.npcs){
			if (npc.isInTriggerZone()) {
				npc.interact();
				isPlayerInteracting = true;
				break;
			}
			else isPlayerInteracting = false;
		}
		if(!isPlayerInteracting) {
			world.huds.get("npc").setIsShown(false);
			world.huds.get("npc").setInteractable(false);
		}
	}

}
