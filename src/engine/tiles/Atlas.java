package engine.tiles;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

/**
 * Atlas reading a spriteSheet and bining each sprite to an integer key
 */
public class Atlas {
    /** SriteAtlas */
    private HashMap<Integer, BufferedImage> spriteAtlas = new HashMap<Integer, BufferedImage>();
    /** Size of a sprite in the spriteSheet */
    private int spriteSize;
    /** Number of sprite in a row */
    private int rows;
    /** Number of sprite in a column */
    private int cols;

    /**
     * Creates an atlas that binds an integer to a sprite from a spriteSheet
     * 
     * @param spritesSheetURL Path to the sprite sheet
     * @param spritesSize     Size of a sprite in the spriteSheet
     * @param rows            Number of sprite in a row
     * @param cols            Number of sprite in a column
     */
    public Atlas(String spriteSheetURL, int spriteSize, int rows, int cols) {
        this.spriteSize = spriteSize;
        this.rows = rows;
        this.cols = cols;
        try {
            BufferedImage spritesSheet = ImageIO.read(new File(spriteSheetURL));
            for (int row = 0; row < rows; row++) {
                for (int col = 0; col < cols; col++) {
                    spriteAtlas.put(row * rows + col + 1,
                            spritesSheet.getSubimage(
                                    col * spriteSize,
                                    row * spriteSize,
                                    spriteSize,
                                    spriteSize
                            )
                    );
                }
            }
        } catch (Exception e) {
        }
    }

    /**
     * Returns a sprite from the atlas given its index
     * 
     * @param i Index of the sprite
     * @return Buffered image of the sprite
     */
    public BufferedImage get(int i) {
        return spriteAtlas.get(i);
    }

    /**
     * Returns the string representation of the atlas
     * 
     * @return String representation of the atlas
     */
    public String toString() {
        String ret = "";
        for (Map.Entry<Integer, BufferedImage> entry : spriteAtlas.entrySet()) {
            int key = entry.getKey();
            ret += key + " - ";
            BufferedImage value = entry.getValue();
            ret += value + "\n";
        }
        return ret;
    }
    
    public int getSpriteSize() {
        return spriteSize;
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }
    
    public int getSpriteCount() {
        return spriteAtlas.size();
    }
}