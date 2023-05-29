package engine.tiles;

import java.util.Map.Entry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Grid of a certain scale with a determined x and y count 
 * and contains value of a given type
 */
public class Grid<T> implements Iterable<GridCell<T>>{
    
    /** All cells of the grid */
    private HashMap<Integer, GridCell<T>> cells;

    /** Index of all the cells that are non empty */
    private ArrayList<Integer> nonEmptyCellsIndexes = new ArrayList<Integer>();

    /** Count of cells on the x axis */
    private int xCount;

    /** Count of cells on the y axis */
    private int yCount;

    /**
     * Returns an array containing all non empty cells of the grid
     * @return
     */
    public ArrayList<GridCell<T>> getNonEmptyCells() {
        ArrayList<GridCell<T>> nonEmptyCells = new ArrayList<GridCell<T>>();
        for(int index: nonEmptyCellsIndexes) {
            nonEmptyCells.add(cells.get(index));
        }
        return nonEmptyCells;
    }

    /**
     * Creates a grid that holds cell that has y rows and x
     * columns
     * @param xCount Count of columns
     * @param yCount Count of rows
     */
    public Grid(int xCount, int yCount) {
        this.xCount = xCount;
        this.yCount = yCount;
        cells = new HashMap<Integer, GridCell<T>>();
        for(int y = 0; y < yCount; y++)
            for(int x = 0; x < xCount; x++)
                cells.put(y*xCount + x, new GridCell<T>(this, null, x, y, y*xCount + x));
    }

    /**
     * Gets a cell at a given position
     * @param x X coord
     * @param y Y coord
     * @return Cell at the x and y coord
     */
    public T getCellContent(int x, int y) {
        if(cells.get(y*xCount + x) != null)
            return cells.get(y*xCount + x).getContent();
        return null;
    }

    /**
     * Gets a cell at a given position
     * @param x X coord
     * @param y Y coord
     * @return Cell at the x and y coord
     */
    public GridCell<T> getCell(int x, int y) {
        return cells.get(y*xCount + x);
    }
    
    /**
     * Returns an array of the addresses of the cell [x,y]
     * @param cell
     * @return [x,y]
     */
    public int[] getCellPos(T cell) {
        int index = -1;
        for (Entry<Integer, GridCell<T>> entry : cells.entrySet()) {
            if (cell.equals(entry.getValue().getContent())) {
                index = entry.getKey();
            }
        }
        int x = index%xCount;
        int y = (index-x)/yCount;
        return new int[] {x,y};
    }

    /**
     * Returns an array of the addresses of the cell [x,y]
     * @param cell
     * @return [x,y]
     */
    public int[] getCellPos(GridCell<T> cell) {
        return cell.getCoords();
    }

    /**
     * Gets a hashmap of all the cells of the grid adjacent to the one passed
     * as parameter
     * @param x Xpos 
     * @param y Ypos
     * @return Hashmap of the possible directions
     */
    public HashMap<Directions, GridCell<T>> getAdjacentCells(int x, int y) {
        HashMap<Directions, GridCell<T>> adjacentCells = new HashMap<Directions, GridCell<T>>();
        if(y-1 >= 0){
            adjacentCells.put(
                Directions.UP,
                cells.get((y-1)*xCount + x)
            );
        }
        else{
            adjacentCells.put(Directions.UP, null);
        }
        if(y+1 < yCount){
            adjacentCells.put(
                Directions.DOWN,
                cells.get((y+1)*xCount + x)
            );
        }
        else{
            adjacentCells.put(Directions.DOWN, null);
        }
        if(x + 1 < xCount)
            adjacentCells.put(
                Directions.RIGHT,
                cells.get(y*xCount + x + 1)
            );
        else {
            adjacentCells.put(Directions.RIGHT, null);
        }
        if(x - 1 >= 0)
            adjacentCells.put(
                Directions.LEFT,
                cells.get(y*xCount + x - 1)
            );
        else {
            adjacentCells.put(Directions.LEFT, null);
        }
        return adjacentCells;
    }

    /**
     * Returns all cells that are adjacent to the given cell content
     * @param cellContent
     * @return Hashmap containing directions and cells relative to the cell
     */
    public HashMap<Directions, GridCell<T>> getAdjacentCells(T cellContent){
        int[] pos = getCellPos(cellContent);
        return getAdjacentCells(pos[0], pos[1]);
    }

    /**
     * Returns all cells that are adjacent to the given cell
     * @param cell
     * @return Hashmap containing directions and cells relative to the cell
     */
    public HashMap<Directions, GridCell<T>> getAdjacentCells(GridCell<T> cell){
        int[] pos = getCellPos(cell);
        return getAdjacentCells(pos[0], pos[1]);
    }

    /**
     * Changes the value of a cell
     * @param x X coord
     * @param y Y coord
     * @param cell New value
     */
    public void setCell(int x, int y, T cell) {
        int index = y*xCount + x;
        setCell(index, cell);
    }

    /**
     * Changes the value of a cell
     * @param cell gridcell to modify
     * @param cellContent New content
     */
    public void setCell(GridCell<T> cell, T cellContent) {
        int index = cell.getIndex();
        setCell(index, cellContent);
    }

    public boolean isIndexValid(int index) {
        return index < xCount * yCount && index >= 0;
    }

    /**
     * Changes the value of a cell
     * @param index index
     * @param cell
     */
    public void setCell(int index, T cell) {
        if(!isIndexValid(index))
            return;
        cells.get(index).setContent(cell);
        if(!nonEmptyCellsIndexes.contains(index))
            nonEmptyCellsIndexes.add(index);
    }

    /**
     * Returns the number of cell on the x axis
     * @return
     */
    public int getxCount() {
        return xCount;
    }

    /**
     * Returns the number of cell on the y axis
     * @return
     */
    public int getyCount() {
        return yCount;
    }    

    @Override
    public Iterator<GridCell<T>> iterator() {
        Iterator<GridCell<T>> it = new Iterator<GridCell<T>>() {

            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < xCount * yCount;
            }

            @Override
            public GridCell<T> next() {
                return cells.get(currentIndex++);
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
        return it;
    }
}