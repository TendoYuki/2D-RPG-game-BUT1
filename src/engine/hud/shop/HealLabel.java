package engine.hud.shop;

import java.awt.Font;
import java.awt.Graphics;
import java.io.File;

import javax.imageio.ImageIO;

import engine.hud.HudElement;
import engine.physics.Player;
import engine.view.Coords;
import engine.view.Sprite;
/** HealLabel class */
public class HealLabel extends HudElement{
    /** Splash of the background */
    Sprite coin;

    /** Player */
    Player player;
    /** Constructs the heal label 
     * @param x
     * @param y
     * @param width
     * @param height
    */
    public HealLabel(Coords origin,Player player, int x, int y, int width, int height) {
        super(origin, x, y, width, height);
        
        try {
                
            this.player = player;
            coin = new Sprite(
                x,
                y,
                2,
                ImageIO.read(
                    new File("assets/misc/gem.png")
                )
                
            );

        } catch (Exception e) {}
    }

    @Override
    public void draw(Graphics g) {
        Font tempFont = g.getFont();
        g.setFont(tempFont.deriveFont(Font.BOLD).deriveFont(14F));
        g.drawString("5 x ", getX(), getY()+25);
        g.setFont(tempFont);
        coin.draw(g, getX() + 30, getY());
    }

    @Override
    public void onClick() {
        int vie = player.getHealth();
        int maxVie = player.getMaxHealth();
        if (player.getGems() >= 5){
            if(vie +40 <= maxVie) {
                player.setHealth(vie + 40);
                player.addgems(-5);
            }
            else if(vie+40 < maxVie + 40){
                player.setHealth(maxVie);
                player.addgems(-5);
            }
        }
    }
    
}
