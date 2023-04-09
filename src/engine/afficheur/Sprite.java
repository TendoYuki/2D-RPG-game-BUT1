package engine.afficheur;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

/**
 * Class representing a sprite
 */
public class Sprite {

    /** Image of the sprite */
    private BufferedImage image;

    /** Scaling factor of the sprite */
    private int scaleFactor;

    /** Size X of the sprite in px */
    private int sizeX;

    /** Size Y of the sprite in px */
    private int sizeY;

    /**
     * Creates a sprite
     * @param scaleFactor Scaling factor of the sprite
     * @param image Image
     */
    public Sprite(int sizeX, int sizeY, int scaleFactor, BufferedImage image) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.scaleFactor = scaleFactor;
        this.image = image;
    }

    /**
     * Draws the sprite on a canvas
     * @param g Graphics
     * @param x Xpos
     * @param y Ypos
     */
    public void draw(Graphics g, int x, int y) {
        AffineTransform at = AffineTransform.getTranslateInstance(x, y);
        at.scale(scaleFactor, scaleFactor);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(image, at, null);
    }

    public BufferedImage getImage() {
        return image;
    }

    public int getScaleFactor() {
        return scaleFactor;
    }

    public int getSizeX() {
        return sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }
}
