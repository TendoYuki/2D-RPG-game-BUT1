package engine.physics;

import java.awt.Color;
import java.awt.Graphics;

import engine.view.CoordinateSystem;
import engine.view.Coords;

public class PhysicalObject {

    /**
     * Position of the object on the x axis
     */
    public double px = 0;

    /**
     * Position of the object on the y axis
     */
    public double py = 0;

    /**
     * Speed of the object on the x axis
     */
    public double vx = 0;
    
    /**
     * Speed of the object on the y axis
     */
    public double vy = 0;

    /**
     * Acceleration of the object on the x axis
     */
    public double ax = 0;

    /**
     * Acceleration of the object on the x axis
     */
    public double ay = 0;

    /**
     * Width of the physical object
     */
    public double width = 0;

    /**
     * Height of the physical object
     */
    public double height = 0;

    /** Index of the object within its type */
    public int index;

    /** World in which the object is in */
    public World world;

    /**
     * Constructs a new physical object
     * @param w
     */
    public PhysicalObject(World w) {
        world = w;
    }

    /**
     * Returns the active world
     * @return
     */
    public World getWorld() {
        return world;
    }

    /**
     * Sets the world to the given one
     * @param world
     */
    public void setWorld(World world) {
        this.world = world;
    }

    /**
     * Run at each frame
     */
    public void update() {
        px = px + vx;
        py = py + vy;
        vx = vx + ax;
        vy = vy + ay;
    }

    /**
     * Draws the object
     * @param g
     */
    public void draw(Graphics g) {
        g.setColor(Color.green);
        Coords coords = CoordinateSystem.changeCS(this, world.map.getPosX(), world.map.getPosY());
        g.fillRect(coords.getX(), coords.getY(), (int)width, (int)height);
    }

    /**
     * Returns the x position of the object
     * @return
     */
    public double getPx() {
        return px;
    }

    /**
     * Returns the y position of the object
     * @return
     */
    public double getPy() {
        return py;
    }

    /**
     * Checks whether or not another physical object is within the given trigger zone
     * @param po
     * @param tz
     * @return
     */
    public boolean isInTriggerZone(PhysicalObject po, TriggerZone tz){
		return (
			px+width > po.px- tz.getWidth()/2 &&
			px < po.px + po.width + tz.getWidth()/2
		) &&
		( 
			py+height> po.py - tz.getHeight()/2 &&
			py < po.py + po.height + tz.getHeight()/2
		);
	}
}
