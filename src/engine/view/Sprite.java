package engine.view;

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
    private double scaleFactor;

    /** Size X of the sprite in px */
    private int sizeX;

    /** Size Y of the sprite in px */
    private int sizeY;

    /**
     * Creates a sprite
     * @param scaleFactor Scaling factor of the sprite
     * @param image Image
     * @param sizeX
     * @param sizeY
     */
    public Sprite(int sizeX, int sizeY, double scaleFactor, BufferedImage image) {
        this.sizeX = (int)(sizeX *scaleFactor);
        this.sizeY = (int)(sizeY*scaleFactor);
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
    /** Returns the buffered image 
     * @return
    */
    public BufferedImage getImage() {
        return image;
    }
    /** Returns the scale factor 
     * @return
    */
    public double getScaleFactor() {
        return scaleFactor;
    }
    /** Returns the x size  
     * @return
    */
    public int getSizeX() {
        return sizeX;
    }
    /** Returns the y size 
     * @return
    */
    public int getSizeY() {
        return sizeY;
    }
}
