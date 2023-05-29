package engine.hud.shop;

import java.awt.Font;
import java.awt.Graphics;
import java.io.File;

import javax.imageio.ImageIO;

import engine.hud.HudElement;
import engine.view.Sprite;
/** HealLabel class */
public class HealLabel extends HudElement{
    /** Splash of the background */
    Sprite coin;
    /** Constructs the heal label 
     * @param x
     * @param y
     * @param width
     * @param height
    */
    public HealLabel(int x, int y, int width, int height) {
        super(x, y, width, height);
        try {
            coin = new Sprite(
                x,
                y,
                2,
                ImageIO.read(
                    new File("assets/misc/coin.png")
                )
                
            );

        } catch (Exception e) {}
    }

    @Override
    public void draw(Graphics g) {
        Font tempFont = g.getFont();
        g.setFont(tempFont.deriveFont(Font.BOLD).deriveFont(14F));
        g.drawString("5 x ", getX(), getY());
        g.setFont(tempFont);
        coin.draw(g, getX() + 30, getY() - 25);
    }

    @Override
    public void onClick() {}
    
}
