package engine.physics;

import java.util.ArrayList;
import java.util.Map.Entry;

import engine.tiles.Directions;
import engine.trigger.TriggerMap;

public class PhysicsEngine {

	/** Active world */
	public World world;

	/** Whether or not the game has ended */
	public static boolean endGame = false;

	/** Speed of the entities */
	public static double SPEED = 2;

	/**
	 * Updates each object and calculates physics
	 */
	public void update() {
		if(!endGame) {
			world.update();
			
			// mise a jour des monstres
			for (Enemy enemy : world.map.activeRoom.enemies) {
				enemy.update();
			}
	
			// gestion du controleur
			if (world.c.droite) {
				world.player.ax = 0.1;
				if (world.player.vx > SPEED)
					world.player.vx = SPEED;
	
			} else if (world.c.gauche) {
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
	
			/** Checks for collision with the world border */
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
	
			/** Checks for collision with the enemies and the world border */
			for (Enemy enemy : world.map.activeRoom.enemies) {
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
