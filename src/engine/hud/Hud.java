package engine.hud;

import java.awt.Graphics;
import java.util.ArrayList;

import engine.controller.MouseController;

public abstract class Hud{
    MouseController mc;
    ArrayList<HudElement> elements = new ArrayList<HudElement>();
    boolean isInteractable = true;
	private boolean isShown = true;
    private int x;
    private int y;

    public Hud() {
        this.mc = new MouseController();
        changeListenerState();
    }

    public MouseController getMouseController() {
        return mc;
    }

    public void addElement(HudElement el) {
        elements.add(el);
        mc.register(el);
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
}
