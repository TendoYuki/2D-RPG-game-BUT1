package engine.hud.menu;

import java.awt.Color;
import java.awt.Graphics;
import java.io.File;

import javax.imageio.ImageIO;

import engine.hud.HudElement;
import engine.view.Coords;
import engine.view.Sprite;
/** MenuBackground class */
public class MenuBackground extends HudElement{
    /** Splash of the background */
    Sprite sprite;

    /** Label */
    Sprite label;
    /** Constructs a menu background 
     * @param x
     * @param y
     * @param width
     * @param height
    */
    public MenuBackground(Coords origin, int x, int y, int width, int height) {
        super(origin, x, y, width, height);
        try{
            label = new Sprite(144, 64, 4, ImageIO.read(
                new File(
                    "assets/misc/menu_sprite.png"
                )
            ));
        }
        catch(Exception e) {};
    }

    @Override
    public void draw(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.gray);
        g.fillRect(getX(), getY(), getWidth(), getHeight());
        g.setColor(c);
        label.draw(g, getWidth()/2 - label.getSizeX()/2, getHeight()/3 - label.getSizeY()/2);
    }

    @Override
    public void onClick() {}
    
}
