package engine.physique;

public abstract class Entity extends Objet {

	/** Entity's max health */
	private int maxVie;

	/** Entity's health */
	private int vie;

	/** Entity's attack points */
	private int attaque;

	/** Entity's defense points */
	private int defense;

	public Entity(int vie, int attaque, int defense){
		this.vie = vie;
		this.maxVie = vie;
		this.attaque = attaque;
		this.defense = defense;
		normalize();
	}

	private void normalize() {
		defense = defense == 0 ? 1 : defense;
	}

	public int getMaxVie() {
		return maxVie;
	}

	public void setMaxVie(int maxVie) {
		this.maxVie = maxVie;
	}

	public int getVie() {
		return vie;
	}

	public void setVie(int vie) {
		this.vie = vie;
		if(vie <= 0) {
			this.vie = 0;
			handleDeath();
		}
	}

	public int getAttaque() {
		return attaque;
	}

	public void setAttaque(int attaque) {
		this.attaque = attaque;
	}

	public int getDefense() {
		return defense;
	}

	public void setDefense(int defense) {
		this.defense = defense;
		normalize();
	}

	public void attack(Entity entity) {
		entity.setVie(entity.getVie() - attaque/entity.getDefense());
	}

	public abstract void handleDeath();
}
