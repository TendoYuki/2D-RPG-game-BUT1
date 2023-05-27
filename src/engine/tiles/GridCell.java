package engine.tiles;

import java.util.HashMap;

/**
 * GridCell
 */
public class GridCell<T> {
    private int x;
    private int y;
    private int index;
    private T content; 
    private Grid<T> grid;

    public GridCell(Grid<T> grid, T content, int x, int y, int index) {
        this.grid = grid;
        this.x = x;
        this.y = y;
        this.index = index;
        this.content = content;
    }
    
    public T getContent() {
        return content;
    }

    void setContent(T content) {
        this.content = content;
    }

    public int[] getCoords() {
        return new int[] {x, y};
    }

    public int getIndex() {
        return index;
    }

    public boolean isEmpty() {
        return content == null;
    }

    public HashMap<Directions, GridCell<T>> getAdjacentCells() { 
        return grid.getAdjacentCells(this);
    }
}