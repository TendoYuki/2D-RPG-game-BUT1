package engine.physics;

import engine.generation.Room;

public abstract class Entity extends PhysicalObject {

	/** Entity's max health */
	private int maxHealth;

	/** Entity's health */
	private int health;

	/** Entity's attack points */
	private int attack;

	/** Entity's defense points */
	private int defence;

	private int level;

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	/** World */
	public World world;

	/** Room of the entity */
	public Room room;

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

	private void normalize() {
		defence = defence <= 0 ? 1 : defence;
		attack = attack <= 0 ? 1 : attack;
		health = health <= 0 ? 1 : health;
		maxHealth = health;
	}

	public int getMaxHealth() {
		return maxHealth;
	}

	public void setMaxHealth(int maxVie) {
		this.maxHealth = maxVie;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int vie) {
		this.health = vie;
		if(vie <= 0) {
			this.health = 0;
			handleDeath();
		}
	}

	public int getAttack() {
		return attack;
	}

	public void setAttack(int attaque) {
		this.attack = attaque;
	}

	public int getDefence() {
		return defence;
	}

	public void setDefence(int defense) {
		this.defence = defense;
		normalize();
	}

	public void attack(Entity entity) {
		entity.setHealth(entity.getHealth() - getAttack()/entity.getDefence());
	}

	public abstract void handleDeath();
}
