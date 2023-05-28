package engine.physics;

import java.awt.Color;
import java.awt.Graphics;
import java.io.IOException;

import engine.generation.Room;
import engine.view.CoordinateSystem;
import engine.view.EnemySprites;

public class Enemy extends Entity {
	EnemyAI ai;

	EnemySprites sprite;

	
	/**
	 *
	 * @throws IOException
	 */
	public Enemy(World w, Room r, int vie,int attaque,int defense) throws IOException {
		super(w, r,vie, attaque, defense);
		ax = 0;
		ay = 0;
		px = 0;
		py = 0;
		vx = 0;
		vy = 0;
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

		// change de repere
		int[] tab = CoordinateSystem.changeCS(this, world.map.getPosX(), world.map.getPosY());

		sprite.draw(tab[0], tab[1], g);
		sprite.animate();
	}

	@Override
	public void handleDeath() {
		// TODO Auto-generated method stub
		world.map.activeRoom.enemies.remove(world.map.activeRoom.enemies.indexOf(this));
	}

	public void update() {
		super.update();
		ai.update();
	}

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Enemy)) return false;
		if(((Enemy)obj).index == index) return true;
		else return false;
    }
}
