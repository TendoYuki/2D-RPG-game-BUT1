package engine.physics;

import java.io.IOException;
import java.awt.Graphics;

import engine.generation.Room;
import engine.hud.player.HealthBar;
import engine.view.CoordinateSystem;
import engine.view.EnemySprites;

/** Boss class */
public class Boss extends Entity {
    /** AI Script of the enemy */
	EnemyAI ai;

	/** Sprites of the enemy */
	EnemySprites sprite;


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
		healthBar = new HealthBar(this, (int)px, (int)py, 40, 3, false, true);
		height = 30;
		width = 20;
		sprite = new EnemySprites(this);
		ai = new EnemyAI(this);
    }
   
   /**
	 * Draws a boss
	 * @param g
	 */
	public void draw(Graphics g) {
		int[] tab = CoordinateSystem.changeCS(this, world.map.getPosX(), world.map.getPosY());

		sprite.draw(tab[0], tab[1], g);
		sprite.animate();
	}

    @Override
    public void handleDeath() {
        world.bossDefeated = true;
		world.map.activeRoom.bosses.remove(world.map.activeRoom.bosses.indexOf(this));
		world.huds.get("hud").removeElement(healthBar);
    }   

    /** Update called every frame */
	public void update() {
		super.update();
		ai.update();
		int[] c = CoordinateSystem.changeCS(this, world.map.getPosX(), world.map.getPosY());
		healthBar.setX(c[0]);
		healthBar.setY(c[1]-20);
		System.out.println("Vie du boss : "+ super.getHealth());
	}


	/** Checks if the given enemy is equal to the enemy */
    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Boss)) return false;
		if(((Boss)obj).index == index) return true;
		else return false;
    }
}