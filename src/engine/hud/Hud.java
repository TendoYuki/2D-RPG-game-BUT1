package engine.hud;

import java.awt.Graphics;
import java.util.ArrayList;

import engine.controle.ControleSouris;

public abstract class Hud{
    ControleSouris cs;
    ArrayList<HudElement> elements = new ArrayList<HudElement>();
    boolean isInteractable = true;
	private boolean isShown = true;

    public Hud() {
        this.cs = new ControleSouris();
        changeListenerState();
    }

    public ControleSouris getControleSouris() {
        return cs;
    }

    public void addElement(HudElement el) {
        elements.add(el);
        cs.register(el);
    }
    
    private void changeListenerState() {
        if(isInteractable) {
            cs.listen();
        }else {
            cs.stopListening();
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
}
