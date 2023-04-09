package engine.hud;

import java.awt.Graphics;
import java.util.ArrayList;

import engine.controle.ControleSouris;

public abstract class Hud{
    ControleSouris cs;
    ArrayList<HudElement> elements = new ArrayList<HudElement>();

    public Hud() {
        this.cs = new ControleSouris();
    }

    public ControleSouris getControleSouris() {
        return cs;
    }

    public void addElement(HudElement el) {
        elements.add(el);
        cs.register(el);
    }
    
    public void draw(Graphics g) {
        elements.forEach(element -> {
            element.draw(g);
        });
    }
}
