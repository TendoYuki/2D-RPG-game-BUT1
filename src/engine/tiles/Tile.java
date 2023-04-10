package engine.tiles;

import java.awt.Graphics;

import engine.view.Sprite;

/** Tile place on a tilemap */
public class Tile {

    private Sprite tileSprite;
    private int x;
    private int y;
    private int id;
    private int scaleFactor;

    /**
     * Creates a tile that holds a buffered image 
     * @param sprite Tile's image
     * @param x X position of the tile
     * @param y Y position of the tile
     * @param scaleFactor Scaling multiplicator of the tile
     */
    public Tile(Sprite sprite,int id, int x, int y, int scaleFactor) {
        this.x = x*scaleFactor;
        this.y = y*scaleFactor;
        this.id = id;
        this.scaleFactor = scaleFactor;
        tileSprite = sprite;
    }

    /**
     * Creates a tile that holds a buffered image 
     * @param sprite Tile's image
     * @param x X position of the tile
     * @param y Y position of the tile
     */
    public Tile(Sprite sprite,int id, int x, int y) {
        this(sprite, id, x, y, 1);
    }

    

    public int getId() {
        return id;
    }

    /**
     * Draws the tile of the tilemap
     * @param g Graphics
     */
    public void draw(Graphics g) {
        tileSprite.draw(g, x, y);
    } 
}