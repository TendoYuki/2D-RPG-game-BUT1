package engine.physics;

import java.awt.Color;
import java.awt.Graphics;

import engine.view.CoordinateSystem;
import engine.view.Coords;
import engine.view.Display;

/** Wall class */
public class Wall extends PhysicalObject {
	/**
	 * Constructs a wall
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 */
	public Wall(World world, int x, int y, int w, int h) {
		super(world);
		height = h;
		width = w;
		px = x;
		py = y;
	}

	/**
	 * Draws the wall
	 * @param g
	 */
	public void draw(Graphics g) {
		g.setColor(new Color(255,0,0,255));
		Coords coords = CoordinateSystem.changeCS(this, getWorld().map.getPosX(), getWorld().map.getPosY());
		g.fillRect(coords.getX(), coords.getY(), (int)width, (int)height);
	}

}
