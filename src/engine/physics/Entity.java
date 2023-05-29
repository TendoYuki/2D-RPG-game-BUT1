package engine.physics;

import engine.generation.Room;

/** Entity class */
public abstract class Entity extends PhysicalObject {

	/** Entity's max health */
	private int maxHealth;

	/** Entity's health */
	private int health;

	/** Entity's attack points */
	private int attack;

	/** Entity's defense points */
	private int defence;

	/** Entity's level */
	private int level;

	/**
	 * Returns the entity's level
	 * @return
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * Sets the entity's level
	 * @param level
	 */
	public void setLevel(int level) {
		this.level = level;
	}

	/** World */
	public World world;

	/** Room of the entity */
	public Room room;

	/**
	 * Creates a new entity
	 * @param w
	 * @param r
	 * @param level
	 */
	public Entity(World w, Room r, int level){
		super(w);
		this.level = level;
		this.room = r;
		this.world = w;
		defence = (int)(0.15 * level);
		attack = 2* level;
    	health = 10* level;
		maxHealth = health;
		normalize();
	}

	/** Normalizes the entity's stats */
	private void normalize() {
		defence = defence <= 0 ? 1 : defence;
		attack = attack <= 0 ? 1 : attack;
		health = health <= 0 ? 1 : health;
		maxHealth = health;
	}

	/**
	 * Returns the maximum health of the entity
	 * @return
	 */
	public int getMaxHealth() {
		return maxHealth;
	}

	/**
	 * Sets the max health of the entity
	 * @param maxVie
	 */
	public void setMaxHealth(int maxVie) {
		this.maxHealth = maxVie;
	}

	/**
	 * Returns the actual health of the entity
	 * @return
	 */
	public int getHealth() {
		return health;
	}

	/**
	 * Sets the actual health of the entity
	 * @param health
	 */
	public void setHealth(int health) {
		this.health = health;
		if(health <= 0) {
			this.health = 0;
			handleDeath();
		}
	}

	/**
	 * Returns the attack value of the entity
	 * @return
	 */
	public int getAttack() {
		return attack;
	}

	/**
	 * Set the attack value of the entity
	 * @param attackValue
	 */
	public void setAttack(int attackValue) {
		this.attack = attackValue;
	}

	/**
	 * Returns the defence value of the entity
	 * @return
	 */
	public int getDefence() {
		return defence;
	}

	/**
	 * Sets the defence value off the entity
	 * @param defense
	 */
	public void setDefence(int defense) {
		this.defence = defense;
		normalize();
	}

	/**
	 * Attacks another entity
	 * @param entity
	 */
	public void attack(Entity entity) {
		entity.setHealth(entity.getHealth() - getAttack()/entity.getDefence());
	}

	/** Runned when the enityt is out of health (Death Logic) */
	public abstract void handleDeath();
}
