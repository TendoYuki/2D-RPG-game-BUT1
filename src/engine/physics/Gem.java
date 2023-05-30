package engine.physics;

import java.awt.Graphics;
import java.io.File;

import javax.imageio.ImageIO;

import engine.view.CoordinateSystem;
import engine.view.Coords;
import engine.view.Sprite;

/** Gem class */
public class Gem extends Item {
    
    /**
     * Creates a new gem item
     * @param w
     * @param px
     * @param py
     */
    public Gem(World w, int px, int py){
        super(w);
        this.px = px;
        this.py = py;
    }
    /** Pickup logic of the item */
    public void pickup(){
        // faire disparaitre l'objet
        world.player.addgems(5);
        world.map.activeRoom.items.remove(this);
    }
    
    @Override
	public void draw(Graphics g) {
		Coords coords = CoordinateSystem.changeCS(this, world.map.getPosX(), world.map.getPosY());
        try {
            Sprite s = new Sprite(16, 16, 2, ImageIO.read(
                new File("assets/misc/gem.png")
            ));
            s.draw(g, coords.getX(), coords.getY());
        } catch (Exception e) { }
	}
}
