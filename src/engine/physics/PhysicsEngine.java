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

import java.io.IOException;
import java.util.ArrayList;
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
	public static boolean endGame = false;

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
		if(!endGame) {
			world.update();
			world.player.collision = 0;
			// mise a jour des objets
			for (PhysicalObject o : world.objects) {
				o.update();
				o.collision = 0;
			}
	
			for (Entry<Directions, Wall> wall : world.getWorldBorder().getBorderWalls().entrySet()) {
				wall.getValue().update();
				wall.getValue().collision = 0;
			}
	
			// mise a jour des monstres
			for (Enemy enemy : world.map.activeRoom.enemies) {
				enemy.update();
			}
	
			// gestion du controleur
			if (world.c.droite) {
				if (world.player.ovx == 0) {
				}
				world.player.ax = 0.1;
				if (world.player.vx > SPEED)
					world.player.vx = SPEED;
	
			} else if (world.c.gauche) {
				if (world.player.ovx == 0) {
				}
				world.player.ax = -0.1;
				if (world.player.vx < -SPEED)
					world.player.vx = -SPEED;
	
			} else {
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
				if (world.c.gauche) {
					world.player.vx = -SPEED * 0.75;
					world.player.vy = SPEED * 0.75;
				} else if (world.c.droite) {
					world.player.vx = SPEED * 0.75;
					world.player.vy = SPEED * 0.75;
				} else {
					world.player.vy = SPEED;
				}
			}
			if (world.c.bas) {
				if (world.c.gauche) {
					world.player.vx = -SPEED * 0.75;
					world.player.vy = -SPEED * 0.75;
				} else if (world.c.droite) {
					world.player.vx = SPEED * 0.75;
					world.player.vy = -SPEED * 0.75;
				} else {
					world.player.vy = -SPEED;
				}
			}
	
			if (world.player.vx > 0) {
				if (world.player.vy > 0) {
					world.player.sprites.changeActivity("up-right");
				} else if (world.player.vy < 0) {
					world.player.sprites.changeActivity("down-right");
				} else {
					world.player.sprites.changeActivity("right");
				}
			} else if (world.player.vx < 0) {
				if (world.player.vy > 0) {
					world.player.sprites.changeActivity("up-left");
				} else if (world.player.vy < 0) {
					world.player.sprites.changeActivity("down-left");
				} else {
					world.player.sprites.changeActivity("left");
				}
			} else if (world.player.vy > 0) {
				world.player.sprites.changeActivity("up");
			} else if (world.player.vy < 0) {
				world.player.sprites.changeActivity("down");
			}
			// mise a jour de la balle
			world.player.update();
	
			// test de collision pour chaque mur
			for (Entry<Directions, Wall> wall : world.getWorldBorder().getBorderWalls().entrySet()) {
				Collision col = Collision.collision(world.player, wall.getValue());
				if (col != null) {
					if(world.player.px+world.player.width > world.getWorldBorder().width) {
						world.player.px = world.getWorldBorder().width-world.player.width-2;
						world.player.vx = world.player.vx * -0.9;
					}
					if(world.player.px <= 0) {
						world.player.px = 2;
						world.player.vx = world.player.vx * -0.9;
					}
					if(world.player.py+world.player.height > world.getWorldBorder().height) {
						world.player.py = world.getWorldBorder().height-world.player.height-2;
						world.player.vy = world.player.vy * -0.9;
					}
					if(world.player.py <= 0) {
						world.player.py = 2;
						world.player.vy = world.player.vy * -0.9;
					}
				}
	
				for (Enemy enemy : world.map.activeRoom.enemies) {
					Collision col1 = Collision.collision(enemy, wall.getValue());
					if (col1 != null) {
						if(enemy.px+enemy.width > world.getWorldBorder().width) {
							enemy.px = world.getWorldBorder().width-enemy.width-2;
							enemy.vx = enemy.vx * -0.9;
						}
						if(enemy.px <= 0) {
							enemy.px = 2;
							enemy.vx = enemy.vx * -0.9;
						}
						if(enemy.py+enemy.height > world.getWorldBorder().height) {
							enemy.py = world.getWorldBorder().height-enemy.height-2;
							enemy.vy = enemy.vy * -0.9;
						}
						if(enemy.py <= 0) {
							enemy.py = 2;
							enemy.vy = enemy.vy * -0.9;
						}
					}
				}
	
			}
			for (Enemy enemy : world.map.activeRoom.enemies) {
				Collision col = Collision.collision(enemy, world.player);
				if (Collision.collision(enemy, world.player) != null) {
					world.player.px = world.player.px - (col.correctionValue * Math.cos(col.collisionAngle));
					world.player.py = world.player.py - (col.correctionValue * Math.sin(col.collisionAngle));
	
					enemy.px = enemy.px + (col.correctionValue * Math.cos(col.collisionAngle));
					enemy.py = enemy.py + (col.correctionValue * Math.sin(col.collisionAngle));
				}
				
				for (Enemy enemy1 : world.map.activeRoom.enemies) {
					Collision col1 = Collision.collision(enemy, enemy1);
					if (!enemy.equals(enemy1) && Collision.collision(enemy, enemy1) != null) {
						enemy.px = enemy.px + (col1.correctionValue * Math.cos(col1.collisionAngle));
						enemy.py = enemy.py + (col1.correctionValue * Math.sin(col1.collisionAngle));
	
						enemy1.px = enemy1.px - (col1.correctionValue * Math.cos(col1.collisionAngle));
						enemy1.py = enemy1.py - (col1.correctionValue * Math.sin(col1.collisionAngle));
					}
				}
			}
			for (NPC npc : world.map.activeRoom.npcs) {
				Collision col = Collision.collision(npc, world.player);
				if (col != null) {
					world.player.px = world.player.px - (col.correctionValue * Math.cos(col.collisionAngle));
					world.player.py = world.player.py - (col.correctionValue * Math.sin(col.collisionAngle));
				}
	
			}
			for (TriggerMap triggerMap : world.triggerMaps) {
				triggerMap.update();
			}

			ArrayList<Item> cpy = new ArrayList<Item>(world.map.activeRoom.items);
			for (Item item : cpy) {
				item.update();
			}
	
			boolean isPlayerInteracting = false;
			for (NPC npc : world.map.activeRoom.npcs) {
				if (npc.isInTriggerZone()) {
					npc.interact();
					isPlayerInteracting = true;
					break;
				} else
					isPlayerInteracting = false;
			}
			if (!isPlayerInteracting) {
				world.huds.get("npc").setIsShown(false);
				world.huds.get("npc").setInteractable(false);
			}
		}
	}
}
