package engine.physics;

import java.awt.Color;
import java.awt.Graphics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import engine.controller.Control;
import engine.generation.Room;
import engine.hud.Hud;
import engine.view.CoordinateSystem;
import engine.view.Coords;
import engine.view.PlayerSprites;

/** Player class */
public class Player extends Entity{

	/** Sprites of the player */
	public PlayerSprites sprites;

	/** Controller of the player */
	Control c;

	/** Unit of the multiplicator of the health */
	public final int HEALTH_MUL_UNIT = 25;
	/** Unit of the multiplicator of the attack */
	public final int ATTACK_MUL_UNIT = 5;
	/** Unit of the multiplicator of the defence */
	public final int DEFENCE_MUL_UNIT = 1;

	/** Default health */
	private final int START_HEALTH;
	/** Default defence */
	private final int START_DEFENCE = 2;
	/** Default attack */
	private final int START_ATTACK = 5;

	/** Health multiplicator */
	private int healthMultiplicator = 0;

	/** Defence multiplicator */
	private int defenceMultiplicator = 0;

	/** Attack multiplicator */
	private int attackMultiplicator = 0;

	/** Gems count of the player */
	private int gems;

	/** Whether or not the player can attack */
	private boolean canAttack = true;

	/** Wheter or not the player has the boss loot */
	public boolean hasBossLoot = false;

    /** Items */
    public ArrayList<Item> inventory = new ArrayList<Item>();

	/** Zone in which the player can attack other entities */
	private TriggerZone attackZone = new TriggerZone(25, 25);

	/**
	 * Creates a new player
	 * @throws IOException
	 */
	public Player(World world, Room r, int x, int y, int gems) throws IOException {
		super(world, r, 1);
		START_HEALTH = 100;
		this.gems = gems;
		super.setHealth(START_HEALTH);
		
		sprites = new PlayerSprites(this);
		height = 10;
		width = 10;
		height = sprites.sprites.get("fixe").getSizeY();
		width = sprites.sprites.get("fixe").getSizeX();
		px = x;
		py = y;
		vx = 0;
		vy = 0;
		ax = 0;
		ay = 0;
	}

	/** Updates the player */
	public void update() {
		super.update();
		System.out.println(getHealth());
		if(world.map.enemiesCount()==0) addgems(30);
		ArrayList<Enemy> cpyEnemies = new ArrayList<Enemy>(world.map.activeRoom.enemies);

		for(Enemy enemy: cpyEnemies) {
			if(isInTriggerZone(enemy,attackZone) && canAttack) {
				canAttack = false;
				attack(enemy);
				TimerTask task = new TimerTask() {
					@Override
					public void run() {
						canAttack = true;
					}
				};
				Timer timer = new Timer("Timer");
				long delay = 2500L;
				timer.schedule(task, delay);
			}
		}

		ArrayList<Boss> cpyBosses = new ArrayList<Boss>(world.map.activeRoom.bosses);

		for(Boss boss: cpyBosses) {
			if(isInTriggerZone(boss,attackZone) && canAttack) {
				canAttack = false;
				attack(boss);
				TimerTask task = new TimerTask() {
					@Override
					public void run() {
						canAttack = true;
					}
				};
				Timer timer = new Timer("Timer");
				long delay = 2500L;
				timer.schedule(task, delay);
			}
		}
	}

	/**
	 * The player has the loot
	 */
	public void hasLoot(){
		hasBossLoot = true;
	}

	/**  Adds gems to the player
	 * @param gems 
	 */
	public void addgems(int gems){
		this.gems += gems;
	}

	/**  Adds gems to the player
	 * @param gems 
	 */
	public void addToInventory(Item item){
		inventory.add(item);
	}

	/** Changes player's gems count
	 * @param gems 
	 */
	public void setgems(int gems){
		this.gems = gems;
	}

	/** Returns actual gem count of the player
	 * @return
	 */
	public int getGems(){
		return gems;
	}

	/** Player's death logic */
	public void handleDeath() {
		world.huds.values().forEach(hud -> {
			hud.setInteractable(false);
			hud.setIsShown(false);
		});

		Hud gO = world.huds.get("gameOver");

		gO.setInteractable(true);
		gO.setIsShown(true);
		PhysicsEngine.endGame = true;
	}


	/**
	 * Returns the health multiplicator
	 * @return
	 */
	public int getHealthMultiplicator() {
		return healthMultiplicator;
	}

	/**
	 * Sets the health multiplicator
	 * @param healthMultiplicator
	 */
	public void setHealthMultiplicator(int healthMultiplicator) {
		this.healthMultiplicator = healthMultiplicator;
	}

	/**
	 * Returns the defence multiplicator
	 * @return
	 */
	public int getDefenceMultiplicator() {
		return defenceMultiplicator;
	}

	/**
	 * Sets the defence multiplicator
	 * @param defenceMultiplicator
	 */
	public void setDefenceMultiplicator(int defenceMultiplicator) {
		this.defenceMultiplicator = defenceMultiplicator;
	}

	/**
	 * Returns the attack multiplicator
	 * @return
	 */
	public int getAttackMultiplicator() {
		return attackMultiplicator;
	}

	/**
	 * Sets the attack multiplicator
	 * @param attackMultiplicator
	 */
	public void setAttackMultiplicator(int attackMultiplicator) {
		this.attackMultiplicator = attackMultiplicator;
	}

	/**
	 * Return the maximum health
	 */
	@Override
	public int getMaxHealth() {
		return START_HEALTH + HEALTH_MUL_UNIT * getHealthMultiplicator();
	}

	/**
	 * Return the maximum defence
	 */
	@Override
	public int getDefence() {
		return START_DEFENCE + DEFENCE_MUL_UNIT * getDefenceMultiplicator();
	}

	/**
	 * Return the maximum attack
	 */
	@Override
	public int getAttack() {
		return START_ATTACK + ATTACK_MUL_UNIT * getAttackMultiplicator();
	}
	
	/**
	 * Draws the player on the screen
	 * @param g
	 */
	@Override
	public void draw(Graphics g) {
		g.setColor(Color.black);

		// change de repere
		Coords coords = CoordinateSystem.changeCS(this, world.map.getPosX(), world.map.getPosY());
		sprites.draw(coords.getX(), coords.getY(), g);
		sprites.animate();
	}
}
