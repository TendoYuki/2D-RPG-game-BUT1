package engine.hud.gameover;

import java.awt.Color;
import java.awt.Graphics;
import java.io.File;

import javax.imageio.ImageIO;

import engine.hud.HudElement;
import engine.view.Sprite;
/** GameOverBackground class */
public class GameOverBackground extends HudElement{
    /** Splash image of the background */
    Sprite sprite;
    /** Constructs the background 
     * @param x
     * @param y
     * @param width
     * @param height
    */
    public GameOverBackground(int x, int y, int width, int height) {
        super(x, y, width, height);
        try{
            sprite = new Sprite(500, 250, 1, ImageIO.read(
                new File(
                    "assets/misc/GameOver.png"
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
        sprite.draw(g, getWidth()/2 - sprite.getSizeX()/2, getHeight()/8);
    }

    @Override
    public void onClick() {}
    
}
