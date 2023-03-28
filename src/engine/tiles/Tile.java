package engine.tiles;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

/** Tile place on a tilemap */
public class Tile {

    BufferedImage tileImage;
    int x;
    int y;

    /**
     * Creates a tile that holds a buffered image 
     * @param image Tile's image
     * @param x X position of the tile
     * @param y Y position of the tile
     */
    public Tile(BufferedImage image, int x, int y) {
        this.x = x;
        this.y = y;
        tileImage = image;
    }

    /**
     * Draws the tile of the tilemap
     * @param g Graphics
     */
    public void draw(Graphics g) {
        g.drawImage(tileImage, y, x, null);
        // g.drawString("Z", y, x);
    } 
}