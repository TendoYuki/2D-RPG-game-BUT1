package engine.hud;

import java.awt.Graphics;
import java.util.ArrayList;

import engine.controller.MouseController;
import engine.view.Display;

public abstract class Hud{
    MouseController mc;
    ArrayList<HudElement> elements = new ArrayList<HudElement>();
    boolean isInteractable = true;
	private boolean isShown = true;
    private int x;
    private int y;
    private int width;
    private int height;
    private int offsetY;
    private Display display;

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

    public Hud(Display display, int x, int y, int width, int height) {
        this(display, 0, x, y, width, height);
    }

    public void setOffsetY(int offsetY) {
        this.offsetY = offsetY;
        this.mc.setOffsetY(offsetY);
    }

    public int getTopInset() {
        return display.getFrame().getInsets().top;
    }

    public MouseController getMouseController() {
        return mc;
    }

    public void addElement(HudElement el) {
        elements.add(el);
        mc.register(el);
    }

    public void removeElement(HudElement el) {
        elements.remove(el);
        mc.unRegister(el);
    }
    
    private void changeListenerState() {
        if(isInteractable) {
            mc.listen();
        }else {
            mc.stopListening();
        }
    }

    public boolean isInteractable() {
        return isInteractable;
    }

    public void setInteractable(boolean isInteractable) {
        this.isInteractable = isInteractable;
        changeListenerState();
    }

	public boolean isShown() {
		return isShown;
	}

	public boolean setIsShown(boolean isShown) {
		return this.isShown = isShown;
	}

    public void draw(Graphics g) {
        if(isShown)
            elements.forEach(element -> {
                element.draw(g);
            });
    }
    
    public void setX(int x){
        this.x = x;
    }
    
    public void setY(int y){
        this.y = y;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Display getDisplay() {
        return display;
    }
    
    
}
