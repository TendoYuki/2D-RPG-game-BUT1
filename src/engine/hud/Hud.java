package engine.hud;

import java.awt.Graphics;
import java.util.ArrayList;

import engine.controller.MouseController;
import engine.view.Display;
/** Hud class */
public abstract class Hud{
    /** The mouse controller */
    MouseController mc;
    /** The hud elements */
    ArrayList<HudElement> elements = new ArrayList<HudElement>();
    /** Whether or not the hud is intereactable */
    boolean isInteractable = true;
    /** Whether or not the hud is shown */
	private boolean isShown = true;
    /** Position x of the hud */
    private int x;
    /** Position y of the hud */
    private int y;
    /** Width of the hud */
    private int width;
    /** Height of the hud */
    private int height;
    /** Coordinate offset */
    int offsetY;
    /** The display  */
    private Display display;
    /** Constructs a hud 
     * @param display
     * @param offsetY
     * @param x
     * @param y
     * @param width
     * @param height
    */
    public Hud(Display display,int offsetY, int x, int y, int width, int height) {
        this.width =width;
        this.height = height;
        this.x = x;
        this.y = y;
        this.mc = new MouseController(offsetY);
        this.offsetY = offsetY;
        this.display = display;
        changeListenerState();
    }
    /** Constructs a hud 
     * @param display
     * @param x
     * @param y
     * @param width
     * @param height
    */
    public Hud(Display display, int x, int y, int width, int height) {
        this(display, 0, x, y, width, height);
    }
    /** Updates the offset 
     * @param offsetY
    */
    public void setOffsetY(int offsetY) {
        this.offsetY = offsetY;
        this.mc.setOffsetY(offsetY);
    }
    /** Returns the inset of the frame  
     * @return
    */
    public int getTopInset() {
        return display.getFrame().getInsets().top;
    }
    /** Returns the mouse controller  
     * @return
    */
    public MouseController getMouseController() {
        return mc;
    }
    /** Adds an element to the hud 
     * @param el
    */
    public void addElement(HudElement el) {
        elements.add(el);
        mc.register(el);
    }
    /** Removes an element to the hud 
     * @param el
    */
    public void removeElement(HudElement el) {
        elements.remove(el);
        mc.unRegister(el);
    }
    /** Changes the listeners state 
    */
    private void changeListenerState() {
        if(isInteractable) {
            mc.listen();
        }else {
            mc.stopListening();
        }
    }
    /** Returns whether or not the hud is interactable
     * @return
    */
    public boolean isInteractable() {
        return isInteractable;
    }
    /**  Updates whether or not the hud is interactable
     * @param isInteractable
    */
    public void setInteractable(boolean isInteractable) {
        this.isInteractable = isInteractable;
        changeListenerState();
    }
    /** Return whether or not the hud is shown
     * @return
     */
	public boolean isShown() {
		return isShown;
	}
    /** Updates whether or not the hud is shown 
     * @param isShown
    */
	public void setIsShown(boolean isShown) {
		 this.isShown = isShown;
	}
    /** Draws the hud 
     * @param g
    */
    public void draw(Graphics g) {
        if(isShown)
            elements.forEach(element -> {
                element.draw(g);
            });
    }
    /** Updates the position x 
     * @param x
    */
    public void setX(int x){
        this.x = x;
    }
    /** Updates the position y 
     * @param y
    */
    public void setY(int y){
        this.y = y;
    }
    /** Returns the position x 
     * @return
    */
    public int getX(){
        return x;
    }
    /** Returns the position y 
     * @return
    */
    public int getY(){
        return y;
    }
    /** Returns the width 
     * @return
    */
    public int getWidth() {
        return width;
    }
    /** Updates the width 
     * @param width
    */
    public void setWidth(int width) {
        this.width = width;
    }
    /** Returns the height
     * @return
     */
    public int getHeight() {
        return height;
    }
    /** Updates the height 
     * @param height
    */
    public void setHeight(int height) {
        this.height = height;
    }
    /** Returns the display 
     * @return
    */
    public Display getDisplay() {
        return display;
    }
    
    
}
