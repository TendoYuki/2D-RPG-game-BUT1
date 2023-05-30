package engine.hud.shop;

import java.awt.Color;
import java.awt.Graphics;
import java.io.File;

import javax.imageio.ImageIO;

import engine.generation.Map;
import engine.hud.HudElement;
import engine.view.CoordinateSystem;
import engine.view.Coords;
import engine.view.Sprite;
/** ShopBackground class */
public class ShopBackground extends HudElement{
    /** The map */
    Map map;
    /** Constructs a shop background 
     * @param x
     * @param y
     * @param width
     * @param height
    */
    public ShopBackground(Coords origin, int x, int y, int width, int height, Map map) {
        super(origin, x, y, width, height);
        this.map = map;
    }

    @Override
    public void draw(Graphics g) {
        try{
            new Sprite(
                getWidth(),
                getHeight(),
                1,
                ImageIO.read(
                    new File("assets/misc/MenuBackground.png")
                )
                
            ).draw(g, getX(), getY());
        } catch (Exception e) { }
    }

    @Override
    public void onClick() {}
    
}
