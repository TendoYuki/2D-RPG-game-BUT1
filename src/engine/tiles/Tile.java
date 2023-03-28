package engine.tiles;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

/** Tile place on a tilemap */
public class Tile {

    BufferedImage tileImage;
    int x;
    int y;

    public Tile(BufferedImage image, int x, int y) {
        this.x = x;
        this.y = y;
        tileImage = image;
    }

    public void draw(Graphics g) {
        g.drawImage(tileImage, x, y, null);
    } 
}