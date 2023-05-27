package engine.tiles;

/**
 * GridCell
 */
public class GridCell<T> {
    private int x;
    private int y;
    private int index;
    private T content; 

    public GridCell(T content, int x, int y, int index) {
        this.x = x;
        this.y = y;
        this.index = index;
        this.content = content;
    }
    
    public T getContent() {
        return content;
    }

    public void setContent(T content) {
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
}