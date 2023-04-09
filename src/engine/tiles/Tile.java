package engine.tiles;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import engine.afficheur.Sprite;

/** Tile place on a tilemap */
public class Tile {

    Sprite tileSprite;
    int x;
    int y;
    int scaleFactor;

    /**
     * Creates a tile that holds a buffered image 
     * @param sprite Tile's image
     * @param x X position of the tile
     * @param y Y position of the tile
     * @param scaleFactor Scaling multiplicator of the tile
     */
    public Tile(Sprite sprite, int x, int y, int scaleFactor) {
        this.x = x*scaleFactor;
        this.y = y*scaleFactor;
        this.scaleFactor = scaleFactor;
        tileSprite = sprite;
    }

    /**
     * Creates a tile that holds a buffered image 
     * @param sprite Tile's image
     * @param x X position of the tile
     * @param y Y position of the tile
     */
    public Tile(Sprite sprite, int x, int y) {
        this(sprite, x, y, 1);
    }

    /**
     * Draws the tile of the tilemap
     * @param g Graphics
     */
    public void draw(Graphics g) {
        tileSprite.draw(g, x, y);
    } 
}