package engine.physics;

import java.io.IOException;
import java.awt.Graphics;

import engine.generation.Room;
import engine.hud.player.HealthBar;
import engine.view.BossSprites;
import engine.view.CoordinateSystem;
import engine.view.Coords;

/** Boss class */
public class Boss extends Entity {
    /** AI Script of the enemy */
	EnemyAI ai;

	/** Sprites of the enemy */
	BossSprites sprite;


	/** HealthBar of the enemy */
	public HealthBar healthBar;
    /**
     * Constructs a boss
     * @param w
     * @param r
     * @param level
     * @throws IOException
     */
    public Boss(World w, Room r, int level) throws IOException {
        super(w, r, level);
		ax = 0;
		ay = 0;
		px = 0;
		py = 0;
		vx = 0;
		vy = 0;
		healthBar = new HealthBar(new Coords(w.map.getPosX(), w.map.getPosY()), this, (int)px, (int)py, 40, 3, false, true);
		height = 64;
		width = 64;
		sprite = new BossSprites(this);
		ai = new EnemyAI(this);
    }
   
   /**
	 * Draws a boss
	 * @param g
	 */
	public void draw(Graphics g) {
		Coords coords = CoordinateSystem.changeCS(this, world.map.getPosX(), world.map.getPosY());

		sprite.draw(coords.getX(), coords.getY(), g);
		sprite.animate();
	}

    @Override
    public void handleDeath() {
        world.bossDefeated = true;
		world.map.activeRoom.bosses.remove(world.map.activeRoom.bosses.indexOf(this));
		world.huds.get("hud").removeElement(healthBar);
		world.player.hasLoot();
    }   

    /** Update called every frame */
	public void update() {
		super.update();
		ai.update();
		healthBar.setX((int)px);
		healthBar.setY((int)py+62);
	}


	/** Checks if the given enemy is equal to the enemy */
    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Boss)) return false;
		if(((Boss)obj).index == index) return true;
		else return false;
    }
}