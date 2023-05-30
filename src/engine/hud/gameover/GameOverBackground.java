package engine.hud.gameover;

import java.awt.Color;
import java.awt.Graphics;
import java.io.File;

import javax.imageio.ImageIO;

import engine.hud.HudElement;
import engine.view.Coords;
import engine.view.Sprite;
/** GameOverBackground class */
public class GameOverBackground extends HudElement{
    /** Splash image of the background */
    Sprite sprite;
    /** Label */
    Sprite label;
    /** Constructs the background 
     * @param x
     * @param y
     * @param width
     * @param height
    */
    public GameOverBackground(Coords origin,int x, int y, int width, int height) {
        super(origin,x, y, width, height);
        try{
            sprite = new Sprite(500, 250, 1, ImageIO.read(
                new File(
                    "assets/misc/GameOver.png"
                )
            ));
            label = new Sprite(288, 48, 1, ImageIO.read(
                new File(
                    "assets/misc/game_over.png"
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
        label.draw(g, getWidth()/2 - label.getSizeX()/2, getHeight()/2);
    }

    @Override
    public void onClick() {}
    
}
