package engine.physics;

import java.util.ArrayList;

import engine.generation.Room;
import engine.tiles.GridCell;
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
				
			// mise a jour des npcs
			for (NPC npc : world.map.activeRoom.npcs) {
				npc.update();
			}

			// mise a jour des boss
			for (Boss boss : world.map.activeRoom.bosses) {
				boss.update();
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
			// gestion de l'activite du player
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
			// mise a jour du balle
			world.player.update();
	
			/** Checks for collision with the world border */
			if(world.player.px+world.player.width+32 > world.getWorldBorder().width+64) {
				world.player.px = world.getWorldBorder().width-world.player.width-2+32 ;
				world.player.vx = world.player.vx * -0.9;
			}
			if(world.player.px <= 32) {
				world.player.px = 32+2;
				world.player.vx = world.player.vx * -0.9;
			}
			if(world.player.py+world.player.height > world.getWorldBorder().height+32) {
				world.player.py = world.getWorldBorder().height-world.player.height-2+32 ;
				world.player.vy = world.player.vy * -0.9;
			}
			if(world.player.py <= 32) {
				world.player.py = 32+2;
				world.player.vy = world.player.vy * -0.9;
			}
	
			/** Checks for collision with the enemies and the world border */
			for (Enemy enemy : world.map.activeRoom.enemies) {
				if(enemy.px+enemy.width+32  > world.getWorldBorder().width+64) {
					enemy.px = world.getWorldBorder().width-enemy.width-2+32;
					enemy.vx = enemy.vx * -0.9;
				}
				if(enemy.px <= 32) {
					enemy.px = 32+2;
					enemy.vx = enemy.vx * -0.9;
				}
				if(enemy.py+enemy.height > world.getWorldBorder().height+64) {
					enemy.py = world.getWorldBorder().height-enemy.height-2+32;
					enemy.vy = enemy.vy * -0.9;
				}
				if(enemy.py <= 32) {
					enemy.py = 32+2;
					enemy.vy = enemy.vy * -0.9;
				}
	
				// gestion de l'activite de l'enemy
				if (enemy.vx > 0) {
					if (enemy.vy > 0.5) {
						enemy.sprite.changeActivity("up-right");
					} else if (enemy.vy < -0.5) {
						enemy.sprite.changeActivity("down-right");
					} else {
						enemy.sprite.changeActivity("right");
					}
				} else if (enemy.vx < 0) {
					if (enemy.vy > 0.5) {
						enemy.sprite.changeActivity("up-left");
					} else if (enemy.vy < -0.5) {
						enemy.sprite.changeActivity("down-left");
					} else {
						enemy.sprite.changeActivity("left");
					}
				} else if (enemy.vy > 0) {
					enemy.sprite.changeActivity("up");
				} else if (enemy.vy < 0) {
					enemy.sprite.changeActivity("down");
				}
			}
			/** Checks for collision with the bosses and the world border */
			for (Boss boss : world.map.activeRoom.bosses) {
				if(boss.px+boss.width+32 > world.getWorldBorder().width+64) {
					boss.px = world.getWorldBorder().width-boss.width-2+32;
					boss.vx = boss.vx * -0.9;
				}
				if(boss.px <= 32) {
					boss.px = 32+2;
					boss.vx = boss.vx * -0.9;
				}
				if(boss.py+boss.height > world.getWorldBorder().height+32) {
					boss.py = world.getWorldBorder().height-boss.height-2+32;
					boss.vy = boss.vy * -0.9;
				}
				if(boss.py <= 32) {
					boss.py = 32+2;
					boss.vy = boss.vy * -0.9;
				}
	
				// Boss activity
				if (boss.vx > 0) {
					if (boss.vy > 0.5) {
						boss.sprite.changeActivity("up-right");
					} else if (boss.vy < -0.5) {
						boss.sprite.changeActivity("down-right");
					} else {
						boss.sprite.changeActivity("right");
					}
				} else if (boss.vx < 0) {
					if (boss.vy > 0.5) {
						boss.sprite.changeActivity("up-left");
					} else if (boss.vy < -0.5) {
						boss.sprite.changeActivity("down-left");
					} else {
						boss.sprite.changeActivity("left");
					}
				} else if (boss.vy > 0) {
					boss.sprite.changeActivity("up");
				} else if (boss.vy < 0) {
					boss.sprite.changeActivity("down");
				}
			}
			
			for (Enemy enemy : world.map.activeRoom.enemies) {
				Collision col = Collision.collision(enemy, world.player);
				/** Checks for collision with the enemy and the player */
				if (Collision.collision(enemy, world.player) != null) {
					world.player.px = world.player.px - (col.correctionValue * Math.cos(col.collisionAngle));
					world.player.py = world.player.py - (col.correctionValue * Math.sin(col.collisionAngle));
	
					enemy.px = enemy.px + (col.correctionValue * Math.cos(col.collisionAngle));
					enemy.py = enemy.py + (col.correctionValue * Math.sin(col.collisionAngle));
				}
				
			/** Checks for collision with the enemies and the enemy */
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
			
			for (Boss boss : world.map.activeRoom.bosses) {
				Collision col = Collision.collision(boss, world.player);
				/** Checks for collision with the boss and the player */
				if (Collision.collision(boss, world.player) != null) {
					world.player.px = world.player.px - (col.correctionValue * Math.cos(col.collisionAngle));
					world.player.py = world.player.py - (col.correctionValue * Math.sin(col.collisionAngle));
	
					boss.px = boss.px + (col.correctionValue * Math.cos(col.collisionAngle));
					boss.py = boss.py + (col.correctionValue * Math.sin(col.collisionAngle));
				}
			}
			
			for (NPC npc : world.map.activeRoom.npcs) {
				Collision col = Collision.collision(npc, world.player);
				if (col != null) {
					world.player.px = world.player.px - (col.correctionValue * Math.cos(col.collisionAngle));
					world.player.py = world.player.py - (col.correctionValue * Math.sin(col.collisionAngle));
				}
	
			}
			for (GridCell<Room> roomCell : world.map.rooms) {
				if(!roomCell.isEmpty())
					roomCell.getContent().update();
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
