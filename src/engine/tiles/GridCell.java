package engine.tiles;

import java.util.HashMap;

/**
 * GridCell
 */
public class GridCell<T> {

    /** x position of the cell */
    private int x;

    /** y position of the cell */
    private int y;

    /** Index of the cell within the grid */
    private int index;
    /** Content of the cell */
    private T content; 
    /** Grid that contains the cell */
    private Grid<T> grid;

    /**
     * Creates a new cell
     * @param grid
     * @param content
     * @param x
     * @param y
     * @param index
     */
    public GridCell(Grid<T> grid, T content, int x, int y, int index) {
        this.grid = grid;
        this.x = x;
        this.y = y;
        this.index = index;
        this.content = content;
    }
    
    /**
     * Returns the content of the cell
     * @return
     */
    public T getContent() {
        return content;
    }

    /**
     * Set the content of the cell
     * @param content
     */
    void setContent(T content) {
        this.content = content;
    }

    /**
     * Returns the coords [x,y] of the cell
     * @return
     */
    public int[] getCoords() {
        return new int[] {x, y};
    }

    /**
     * Returns the index of the cell within the grid
     * @return
     */
    public int getIndex() {
        return index;
    }

    /**
     * Whether or not the cell does not have any content
     * @return
     */
    public boolean isEmpty() {
        return content == null;
    }

    /**
     * Returns the cells that are adjacent to the cell
     * @return
     */
    public HashMap<Directions, GridCell<T>> getAdjacentCells() { 
        return grid.getAdjacentCells(this);
    }
}