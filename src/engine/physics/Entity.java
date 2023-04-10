package engine.physics;

public abstract class Entity extends PhysicalObject {

	/** Entity's max health */
	private int maxHealth;

	/** Entity's health */
	private int health;

	/** Entity's attack points */
	private int attack;

	/** Entity's defense points */
	private int defence;

	public Entity(int health, int attack, int defence){
		this.health = health;
		this.maxHealth = health;
		this.attack = attack;
		this.defence = defence;
		normalize();
	}

	private void normalize() {
		defence = defence == 0 ? 1 : defence;
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
