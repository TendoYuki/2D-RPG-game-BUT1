package engine.tiles;

import java.awt.Graphics;

/** Scaled grid */
public class TileMap {
    Grid<Tile> tileGrid;

    Atlas tileAtlas;

    int[][] tilesAtlasKey;

    int scale;

    int scaleFactor;

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
                    key, x*tileSize, y*tileSize, scaleFactor
                ));
            }
        }
        this.tileAtlas = tileAtlas;
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

    public int size() {
        return tileGrid.getxCount() * tileAtlas.getSpriteSize() * scaleFactor;
    }

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
        int colNum = x/(size()/scale);
        int rowNum = scale-(y/(size()/scale))-1;
        return tileGrid.getCellContent(colNum, rowNum).getId();
    }
}