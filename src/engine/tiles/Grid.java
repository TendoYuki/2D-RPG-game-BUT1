package engine.tiles;

import java.util.Map.Entry;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Grid of a certain scale with a determined x and y count 
 * and contains value of a given type
 */
public class Grid<T> implements Iterable<GridCell<T>>{
    
    private HashMap<Integer, GridCell<T>> cells;

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
        cells = new HashMap<Integer, GridCell<T>>();
        for(int y = 0; y < yCount; y++)
            for(int x = 0; x < xCount; x++)
                cells.put(y*xCount + x, new GridCell<T>(null, x, y, y*xCount + x));
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
    
    public int[] getCellPos(T cell) {
        int index = -1;
        for (Entry<Integer, GridCell<T>> entry : cells.entrySet()) {
            if (cell.equals(entry.getValue())) {
                index = entry.getKey();
            }
        }
        int x = index%xCount;
        int y = index%yCount;
        return new int[] {x,y};
    }

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
        adjacentCells.put(
            Directions.UP,
            cells.get(y*xCount + x - xCount)
        );
        adjacentCells.put(
            Directions.DOWN,
            cells.get(y*xCount + x + xCount)
        );
        if(x + 1 < xCount)
            adjacentCells.put(
                Directions.RIGHT,
                cells.get(y*xCount + x + 1)
            );
        else {
            adjacentCells.put(Directions.RIGHT, null);
        }
        if(x - 1 > 0)
            adjacentCells.put(
                Directions.LEFT,
                cells.get(y*xCount + x - 1)
            );
        else {
            adjacentCells.put(Directions.LEFT, null);
        }
        return adjacentCells;
    }

    public HashMap<Directions, GridCell<T>> getAdjacentCells(T cell){
        int[] pos = getCellPos(cell);
        return getAdjacentCells(pos[0], pos[1]);
    }

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
        cells.put(y*xCount + x, new GridCell<T>(cell, x, y, y*xCount + x));
    }
    /**
     * Changes the value of a cell
     * @param x X coord
     * @param y Y coord
     * @param cell New value
     */
    public void setCell(int index, T cell) {
        cells.put(index, new GridCell<T>(cell, index%xCount, index%yCount, index));
    }
    public int getxCount() {
        return xCount;
    }

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