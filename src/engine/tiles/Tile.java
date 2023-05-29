package engine.tiles;

import java.awt.Graphics;

import engine.view.Sprite;

/** Tile place on a tilemap */
public class Tile {
    /** The sprite of the tile */
    private Sprite tileSprite;
    /** The position x of the tile */
    private int x;
    /** The position y of the tile */
    private int y;
    /** The id of the tile */
    private int id;
    /** The scale factor */
    private int scaleFactor;
    /** Returns the position x
     * @return
     */
    public int getX() {
        return x;
    }
    /** Updates the position x
     * @param x
     */
    public void setX(int x) {
        this.x = x;
    }
    /** Returns the position y
     * @return
     */
    public int getY() {
        return y;
    }
    /** Updates the position y
     * @param y
     */
    public void setY(int y) {
        this.y = y;
    }

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
     * @param id
     * @param x X position of the tile
     * @param y Y position of the tile
     */
    public Tile(Sprite sprite,int id, int x, int y) {
        this(sprite, id, x, y, 1);
    }
    /** Returns the id 
     * @return
    */
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