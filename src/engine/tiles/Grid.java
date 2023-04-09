package engine.tiles;

import java.util.HashMap;

/**
 * Grid of a certain scale with a determined x and y count 
 * and contains value of a given type
 */
public class Grid<T>{
    
    private HashMap<Integer, T> cells;

    private int xCount;
    private int yCount;

    /**
     * Creates a grid that holds cell that has y rows and x
     * columns
     * @param xCount Count of columns
     * @param yCount Count of rows
     */
    public Grid(int xCount, int yCount) {
        this.xCount = xCount;
        this.yCount = yCount;
        cells = new HashMap<Integer, T>();
    }


    // public Grid(T[][] initArray) {
    //     this(initArray[0].length, initArray.length);
    //     for(int y = 0; y < initArray.length; y++) {
    //         for(int x = 0; x < initArray[0].length; x++) {
    //             setCell(x,y,initArray[y][x]);
    //         }
    //     }
    // }   

    /**
     * Gets a cell at a given position
     * @param x X coord
     * @param y Y coord
     * @return Cell at the x and y coord
     */
    public T getCell(int x, int y) {
        return cells.get(y*xCount + x);
    }

    /**
     * Changes the value of a cell
     * @param x X coord
     * @param y Y coord
     * @param cell New value
     */
    public void setCell(int x, int y, T cell) {
        cells.put(y*xCount + x, cell);
    }

    public int getxCount() {
        return xCount;
    }

    public int getyCount() {
        return yCount;
    }    
}