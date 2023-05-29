package engine.hud.shop;

import java.awt.Color;
import java.awt.Graphics;
import java.io.File;

import javax.imageio.ImageIO;

import engine.hud.HudElement;
import engine.view.Sprite;
/** ShopBackground class */
public class ShopBackground extends HudElement{
    /** Constructs a shop background 
     * @param x
     * @param y
     * @param width
     * @param height
    */
    public ShopBackground(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    @Override
    public void draw(Graphics g) {
        Color c = g.getColor();
        g.setColor(new Color(100, 100, 100, 0));
        
        g.fillRect(getY()  , getX() , getWidth(), getHeight());
        try{
            new Sprite(
                getX(),
                getY(),
                1,
                ImageIO.read(
                    new File("assets/misc/MenuBackground.png")
                )
                
            ).draw(g, getX(), getY());;
        } catch (Exception e) { }
        
        g.setColor(c);
    }

    @Override
    public void onClick() {}
    
}
