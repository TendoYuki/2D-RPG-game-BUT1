package engine.hud.player;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import engine.hud.HudElement;
import engine.physics.Entity;
import engine.view.Coords;
import engine.view.Sprite;
/** HealthBar class */
public class HealthBar extends HudElement{
    /** The entity */
    private Entity entity; 
    /** Splash of the background */
    private Sprite fullHeart;
    /** Splash of the background */
    private Sprite halfHeart;
    /** Splash of the background */
    private Sprite emptyHeart;
    /** Wether or not it has decoration */
    private boolean hasDecoration;
    /** Wether or not it displays the level */
    private boolean displayLevel;
    /** Constructs a health bar 
     * @param entity
     * @param x
     * @param y 
     * @param width
     * @param height
     * @param hasDecoration
     * @param displayLevel
    */
    public HealthBar(Coords origin, Entity entity, int x, int y, int width, int height, boolean hasDecoration, boolean displayLevel) {
        super(origin, x, y, width, height);
        this.entity = entity;
        this.hasDecoration = hasDecoration;
        this.displayLevel = displayLevel;
        try{
            
            fullHeart = new Sprite(x, y, 2, ImageIO.read(new File(
                "assets/misc/heart_full.png"
            )));
            halfHeart = new Sprite(x, y, 2, ImageIO.read(new File(
                "assets/misc/heart_half.png"
            )));
            emptyHeart = new Sprite(x, y, 2, ImageIO.read(new File(
                "assets/misc/heart_empty.png"
            )));
        }
        catch(Exception e){}
    }
    /**
     * Calculates the fill of the healthbar in px according to the entity's
     * actual health
     * @return
     */
    private int calcFill() {
        return entity.getHealth() * getWidth() / entity.getMaxHealth();
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D)g;
        Color temp = g.getColor();
        g.setColor(Color.black);

        if(hasDecoration) {
            int offset = getX();
            int heartCount = (int)Math.floor(entity.getMaxHealth() / 30);

            int fullHeartCount = (int)Math.floor(entity.getHealth() / 30);

            int halfHeartCount = (int)Math.floor((entity.getHealth() / 15 - fullHeartCount * 2));

            int emptyHeartCount = heartCount - fullHeartCount - halfHeartCount;

            if(fullHeartCount==0 && halfHeartCount ==0) halfHeartCount++;
            for(int i = 0; i < fullHeartCount; i++) {
                fullHeart.draw(g2d, getX() + offset, getY());
                offset+=32;
            }
            for(int i = 0; i < halfHeartCount && Math.ceil((fullHeartCount * 2 + halfHeartCount) / 2) < heartCount; i++) {
                halfHeart.draw(g2d, getX() + offset, getY());
                offset+=32;
            }
            for(int i = 0; i < emptyHeartCount; i++) {
                emptyHeart.draw(g2d, getX() + offset, getY());
                offset+=32;
            }

        } else {
            if(displayLevel)
                g.drawString("" + entity.getLevel(), getX()+4, getY()+4);
            g.setColor(Color.RED);
            g.fillRect(getX()+4, getY()+6, calcFill(), getHeight());
            g.setColor(temp);
        }
    }
    
    @Override
    public void onClick() {  }
    
}
