package engine.physics;

import java.awt.Color;
import java.awt.Graphics;
import java.io.IOException;

import engine.controller.Control;
import engine.generation.Room;
import engine.hud.Hud;
import engine.view.CoordinateSystem;
import engine.view.PlayerSprites;
import engine.view.Sprites;

public class Player extends Entity{

	// distributeur de sprite
	public PlayerSprites sprites;

	// lien vers son controleur
	Control c;

	public final int HEALTH_MUL_UNIT = 10;
	public final int ATTACK_MUL_UNIT = 5;
	public final int DEFENCE_MUL_UNIT = 1;

	private final int START_HEALTH;
	private final int START_DEFENCE = 2;
	private final int START_ATTACK = 5;
	/** Multiplicator de la vie */
	private int healthMultiplicator = 0;

	/** Multiplicator de la defense */
	private int defenceMultiplicator = 0;

	/** Multiplicator de l'attaque */
	private int attackMultiplicator = 0;

	// pieces du heros
	private int coins;

	/**
	 *
	 * @throws IOException
	 */
	public Player(World world, Room r, int x, int y, int health, int coins) throws IOException {
		super(world, r, health, 5, 2);
		START_HEALTH = health;
		this.coins = coins;
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

	/**  ajoute des pieces au heros
	 * @param coins les pieces a ajouter
	 */
	public void addCoins(int coins){
		this.coins += coins;
	}

	/** change les pieces du heros
	 * @param coins nouvelles pieces du heros
	 */
	public void setCoins(int coins){
		this.coins = coins;
	}

	/** Retourne les pieces du heros
	 * @return les pieces du heros
	 */
	public int getCoins(){
		return coins;
	}

	/** Mort du heros */
	public void handleDeath() {
		world.huds.values().forEach(hud -> {
			hud.setInteractable(false);
			hud.setIsShown(false);
		});;

		Hud gO = world.huds.get("gameOver");

		gO.setInteractable(true);
		gO.setIsShown(true);
	}


	
	public int getHealthMultiplicator() {
		return healthMultiplicator;
	}

	public void setHealthMultiplicator(int multiplicatorVie) {
		this.healthMultiplicator = multiplicatorVie;
	}

	public int getDefenceMultiplicator() {
		return defenceMultiplicator;
	}

	public void setDefenceMultiplicator(int multiplicatorDef) {
		this.defenceMultiplicator = multiplicatorDef;
	}

	public int getAttackMultiplicator() {
		return attackMultiplicator;
	}

	public void setAttackMultiplicator(int multiplicatorAtt) {
		this.attackMultiplicator = multiplicatorAtt;
	}
	@Override
	public int getMaxHealth() {
		return START_HEALTH + HEALTH_MUL_UNIT * getHealthMultiplicator();
	}
	@Override
	public int getDefence() {
		return START_DEFENCE + DEFENCE_MUL_UNIT * getDefenceMultiplicator();
	}
	@Override
	public int getAttack() {
		return START_ATTACK + ATTACK_MUL_UNIT * getAttackMultiplicator();
	}
	
	/**
	 *
	 * @param g
	 */
	@Override
	public void draw(Graphics g) {
		g.setColor(Color.black);

		// change de repere
		int[] tab = CoordinateSystem.changeCS(this, world.map.getPosX(), world.map.getPosY());
		sprites.draw(tab[0], tab[1], g);
		sprites.animate();
	}
}
