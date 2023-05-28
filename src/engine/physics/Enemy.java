package engine.physics;

import java.awt.Graphics;
import java.io.IOException;

import engine.generation.Room;
import engine.hud.player.HealthBar;
import engine.view.CoordinateSystem;
import engine.view.EnemySprites;

public class Enemy extends Entity {
	EnemyAI ai;

	EnemySprites sprite;

	public HealthBar healthBar;
	
	/**
	 *
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
		healthBar = new HealthBar(this, (int)px, (int)py, 25, 3, false, true);
		height = 30;
		width = 20;
		sprite = new EnemySprites(this);
		ai = new EnemyAI(this);
	}

	/**
	 *
	 * @param g
	 */
	public void draw(Graphics g) {
		int[] tab = CoordinateSystem.changeCS(this, world.map.getPosX(), world.map.getPosY());

		sprite.draw(tab[0], tab[1], g);
		sprite.animate();
	}

	@Override
	public void handleDeath() {
		world.map.activeRoom.enemies.remove(world.map.activeRoom.enemies.indexOf(this));
		world.player.addgems(3);
		world.huds.get("hud").removeElement(healthBar);
	}

	public void update() {
		super.update();
		ai.update();
		int[] c = CoordinateSystem.changeCS(this, world.map.getPosX(), world.map.getPosY());
		healthBar.setX(c[0]);
		healthBar.setY(c[1]-20);
	}

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Enemy)) return false;
		if(((Enemy)obj).index == index) return true;
		else return false;
    }
}
