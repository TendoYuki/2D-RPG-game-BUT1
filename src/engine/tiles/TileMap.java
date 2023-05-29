package engine.tiles;

import java.awt.Graphics;

/** Scaled grid */
public class TileMap {
    /** The tile grid */
    Grid<Tile> tileGrid;
    /** The tile atlas */
    Atlas tileAtlas;
    /** The Keys of the tile atlas */
    int[][] tilesAtlasKey;
    /** The scale */
    int scale;
    /** The scale factor  */
    int scaleFactor;
    /** The position x of the tilemap */
    private int posX = 0;
    /** The position y of the tilemap */
    private int posY = 0; 
    /** Returns position x
     * @return
    */
    public int getPosX() {
        return posX;
    }
    /** Updates the position x 
     * @param posX
    */
    public void setPosX(int posX) {
        this.posX = posX;
        updateTilesPositons();
    }
    /** Returns the position y 
     * @return
    */
    public int getPosY() {
        return posY;
    }
    /** Updates the position y 
     * @param posY
    */
    public void setPosY(int posY) {
        this.posY = posY;
        updateTilesPositons();
    }
    /** Returns the size x of the tile grid 
     * @return
    */
    public int getCountX() {
        return tileGrid.getxCount();
    }
    /** Returns the size y of the tile grid 
     * @return
    */
    public int getCountY() {
        return tileGrid.getyCount();
    }

    /**
     * Creates a tilemap of a given scale(ex 16 -> 16x16px) 
     * with an array of keys that maps to the provided tileAtlas
     * @param tileSize Scale of the TileMap
     * @param scaleFactor Scaling multiplicator of the tiles
     * @param tilesAtlasKey Matrix of keys of the tilemap's tiles
     * @param tileAtlas Atlas mapping a key to an image
     */
    public TileMap(
            int tileSize,
            int scaleFactor,
            int[][]tilesAtlasKey,
            Atlas tileAtlas
        ) {

        this.scale = tileSize;
        this.scaleFactor = scaleFactor;
        this.tilesAtlasKey = tilesAtlasKey;
        tileGrid = new Grid<Tile>(
            tilesAtlasKey[0].length,
            tilesAtlasKey.length
        );
        for(int y = 0; y < tilesAtlasKey.length; y++) {
            for(int x = 0; x < tilesAtlasKey[0].length; x++) {
                int key = tilesAtlasKey[y][x];
                tileGrid.setCell(x, y, new Tile(
                    tileAtlas.get(key),
                    key, posX+x*tileSize, posY+y*tileSize, scaleFactor
                ));
            }
        }
        this.tileAtlas = tileAtlas;
    }
    /** Updates the tiles positions */
    public void updateTilesPositons() {
        for(int y = 0; y < tilesAtlasKey.length; y++) {
            for(int x = 0; x < tilesAtlasKey[0].length; x++) {
                GridCell<Tile> tile = tileGrid.getCell(x, y);
                tile.getContent().setX(posX+x*scale*scaleFactor);
                tile.getContent().setY(posY+y*scale*scaleFactor);
                tileGrid.setCell(tile.getIndex(), tile.getContent());
            }
        }
    }

    /**
     * Creates a tilemap of a given scale(ex 16 -> 16x16px) 
     * with an array of keys that maps to the provided tileAtlas
     * @param tileSize Scale of the TileMap
     * @param tilesAtlasKey Matrix of keys of the tilemap's tiles
     * @param tileAtlas Atlas mapping a key to an image
     */
    public TileMap(
            int tileSize,
            int[][]tilesAtlasKey,
            Atlas tileAtlas
        ) {
        this(tileSize,1, tilesAtlasKey, tileAtlas);
    }

    /** Draws the tile map
     * @param g Graphics object
     */
    public void draw(Graphics g) {
        for(int y = 0; y < tilesAtlasKey.length; y++) {
            for(int x = 0; x < tilesAtlasKey[0].length; x++) {
                tileGrid.getCellContent(x, y).draw(g);
            }
        }
    }
    /** Returns the size of the tilemap 
     * @return
    */
    public int size() {
        return tileGrid.getxCount() * tileAtlas.getSpriteSize() * scaleFactor;
    }
    /** Returns the number of tiles in the tilemap 
     * @return
    */
    public int tileCount() {
        return tileAtlas.getSpriteCount();
    }

    /**
     * Gets the tile at a given x and y coordinates
     * @param x X coord
     * @param y Y coord
     * @return id of the tile
     */
    public int getTileID(int x, int y) {
        int colNum = (x)/(size()/scale);
        int rowNum = scale-((y)/(size()/scale))-1;
        if(tileGrid.getCellContent(colNum, rowNum) != null)
            return tileGrid.getCellContent(colNum, rowNum).getId();
        return -1;
    }
}