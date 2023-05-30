package engine.view;

/** Coords class */
public class Coords {
    /** x value of the coordinates */
    private int x;
    /** y value of the coordinates */
    private int y;
    /** Returns the x value 
     * @return
    */
    public int getX() {
        return x;
    }
    /** Updates the x value 
     * @param x
    */
    public void setX(int x) {
        this.x = x;
    }
    /** Returns the y value 
     * @return
    */
    public int getY() {
        return y;
    }
    /** Updates the y value 
     * @param y
    */
    public void setY(int y) {
        this.y = y;
    }
    /** Contructs coordinates 
     * @param x
     * @param y
    */
    public Coords(int x, int y) {
        this.x = x;
        this.y = y;
    }
}