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

public class HealthBar extends HudElement{

    private Entity entity; 

    private BufferedImage leftPart;

    private BufferedImage centerPart;

    private BufferedImage rightPart;

    private boolean hasDecoration;

    private boolean displayLevel;

    private int offset;

    public HealthBar(Entity entity, int x, int y, int width, int height, boolean hasDecoration, boolean displayLevel) {
        super(x, y, width, height);
        this.entity = entity;
        this.hasDecoration = hasDecoration;
        this.displayLevel = displayLevel;
        try{
            leftPart = ImageIO.read(new File(
                "assets/misc/HealthContainerLeft.png"
            ));
            centerPart = ImageIO.read(new File(
                "assets/misc/HealthContainerTile.png"
            ));
            rightPart = ImageIO.read(new File(
                "assets/misc/HealthContainerRight.png"
            ));
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

    /**
     * Calculates the fill of the healthbar in px according to the entity's
     * actual health
     * @return
     */
    private int calcFillOffseted(int offset) {
        return entity.getHealth() * (getWidth() + offset) / entity.getMaxHealth();
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D)g;
        Color temp = g.getColor();
        g.setColor(Color.GRAY);

        if(hasDecoration) {
            offset = getX();
            AffineTransform at = AffineTransform.getTranslateInstance(offset, getY());
            at.scale(2, 2);
            g2d.drawImage(leftPart, at, null);
            offset += leftPart.getWidth();

            int totalCenterSize = 0;
            for(int i = 1; totalCenterSize < getWidth() - rightPart.getWidth()*2 ; i++) {
                at = AffineTransform.getTranslateInstance(
                    getX() + totalCenterSize + leftPart.getWidth(),
                    getY()
                );
                at.scale(2, 2);
                g2d.drawImage(
                    centerPart,
                    at,
                    null
                );
                totalCenterSize = i*centerPart.getWidth()*2;
            }

            offset += totalCenterSize;

            at = AffineTransform.getTranslateInstance(offset, getY());
            at.scale(2, 2);
            g2d.drawImage(rightPart, at, null);
            offset += rightPart.getWidth();

            g.setColor(Color.RED);
            g.fillRect(getX()+4, getY()+6, calcFillOffseted(30), getHeight());
            g.setColor(temp);

            Font tempFont = g.getFont();
            g.setFont(tempFont.deriveFont(Font.BOLD).deriveFont(14F));
            g.drawString("" + entity.getHealth() + " / " + entity.getMaxHealth(), getX() + leftPart.getWidth(), getY() + (int)(leftPart.getHeight()*1.3));
            g.setFont(tempFont);
        } else {
            if(displayLevel)
                g.drawString("" + entity.getLevel(), getX()+4, getY()+4);
            g.setColor(Color.RED);
            g.fillRect(getX()+4, getY()+6, calcFill(), getHeight());
            g.setColor(temp);
        }
    }
    
    @Override
    public void onClick() { 
        entity.setHealth(entity.getHealth() - 10/entity.getDefence());
    }
    
}
