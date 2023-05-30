package engine.physics;

import java.awt.Graphics;
import java.io.IOException;

import engine.generation.Room;
import engine.hud.player.HealthBar;
import engine.view.CoordinateSystem;
import engine.view.Coords;
import engine.view.EnemySprites;

/** Enemy class */
public class Enemy extends Entity {
	/** AI Script of the enemy */
	EnemyAI ai;

	/** Sprites of the enemy */
	EnemySprites sprite;

	/** HealthBar of the enemy */
	public HealthBar healthBar;
	
	private Coords mapCoords;

	/**
	 * Constructs an enemy
	 * @throws IOException
	 */
	public Enemy(World w, Room r, int level) throws IOException {
		super(w, r, level);
		ax = 0;
		ay = 0;
		px = 0;
		py = 0;
		vx = 0;
		vy = 0;
		mapCoords = new Coords(w.map.getPosX(), w.map.getPosY());
		healthBar = new HealthBar(mapCoords, this, (int)px, (int)py, 25, 3, false, true);
		height = 30;
		width = 20;
		sprite = new EnemySprites(this);
		ai = new EnemyAI(this);
	}

	/**
	 * Draws an enenmy
	 * @param g
	 */
	public void draw(Graphics g) {
		Coords coords = CoordinateSystem.changeCS(this, world.map.getPosX(), world.map.getPosY());
		mapCoords = new Coords(getWorld().map.getPosX(), getWorld().map.getPosY());

		sprite.draw(coords.getX(), coords.getY(), g);
		sprite.animate();
	}

	@Override
	public void handleDeath() {
		world.map.activeRoom.enemies.remove(world.map.activeRoom.enemies.indexOf(this));
		world.player.addgems(3);
		world.huds.get("hud").removeElement(healthBar);
	}

	/** Update called every frame */
	public void update() {
		super.update();
		ai.update();
		healthBar.setX((int)px);
		healthBar.setY((int)py+30);
	}

	/** Checks if the given enemy is equal to the enemy */
    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Enemy)) return false;
		if(((Enemy)obj).index == index) return true;
		else return false;
    }
}
