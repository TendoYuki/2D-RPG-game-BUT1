package engine.hud;

import java.awt.Graphics;

import engine.view.Coords;
/** HudElement class */
public abstract class HudElement extends Clickable{
    /** Constructs a hud element 
     * @param x
     * @param y
     * @param width
     * @param height
    */
    public HudElement(Coords origin, int x, int y, int width, int height) {
        super(origin, x, y, width, height);
    }
    /** Draws the hud element 
     * @param g
    */
    public abstract void draw(Graphics g);
}
