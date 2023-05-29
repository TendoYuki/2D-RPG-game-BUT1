package engine.hud;
/** Clickable class */
public abstract class Clickable {
    /** Position x of the element*/
    private int x;
    /** Position y of the element */
    private int y; 
    /** Width of the elemnt */
    private int width;
    /** Height of the element */
    private int height;
    /** Wether or not the element is pressed */
    private boolean isPressed = false;
    /** Constructs a clickable 
     * @param x
     * @param y
     * @param width
     * @param height
    */
    public Clickable(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    /** Returns the position x 
     * @return
    */
    public int getX() {
        return x;
    }
    /** Returns the position y 
     * @return
    */
    public int getY() {
        return y;
    }
    /** Updates the position x 
     * @param x
    */
    public void setX(int x) {
        this.x = x;
    }
    /** Updates the position y 
     * @param y
    */
    public void setY(int y) {
        this.y = y;
    }
    /** Returns the width 
     * @return
    */
    public int getWidth() {
        return width;
    }
    /** Returns the height 
     * @return
    */
    public int getHeight() {
        return height;
    }
    /** Return wether or not the element is pressed 
     * @return
    */
    public boolean isPressed() {
        return isPressed;
    }   
    /** Updates wether or not the element is pressed 
     * @param isPressed
    */
    public void setPressed(boolean isPressed) {
        this.isPressed = isPressed;
    }
    /** Do something when clicked */
    public abstract void onClick();
    /** Do something when pressed */
    public void onPressed() {};
}
