package engine.tiles;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

/** Tile place on a tilemap */
public class Tile {

    BufferedImage tileImage;
    int x;
    int y;
    int scaleFactor;

    /**
     * Creates a tile that holds a buffered image 
     * @param image Tile's image
     * @param x X position of the tile
     * @param y Y position of the tile
     * @param scaleFactor Scaling multiplicator of the tile
     */
    public Tile(BufferedImage image, int x, int y, int scaleFactor) {
        this.x = x*scaleFactor;
        this.y = y*scaleFactor;
        this.scaleFactor = scaleFactor;
        tileImage = image;
    }

    /**
     * Creates a tile that holds a buffered image 
     * @param image Tile's image
     * @param x X position of the tile
     * @param y Y position of the tile
     */
    public Tile(BufferedImage image, int x, int y) {
        this(image, x, y, 1);
    }

    /**
     * Draws the tile of the tilemap
     * @param g Graphics
     */
    public void draw(Graphics g) {
        AffineTransform at = AffineTransform.getTranslateInstance(y, x);
        at.scale(scaleFactor, scaleFactor);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(tileImage, at, null);
    } 
}