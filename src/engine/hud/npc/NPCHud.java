package engine.hud.npc;

import java.awt.Graphics;

import engine.hud.Hud;
import engine.view.Display;

public class NPCHud extends Hud{
    
    DialogDisplay dialogDisplay;

    public NPCHud(Display display, int x, int y, int width, int height) {
        super(display, x, y, width, height);
        dialogDisplay = new DialogDisplay(x, y, width, height);
        addElement(dialogDisplay);
        
    }

    public void draw(Graphics g) {
        super.draw(g);
        int topInset = getTopInset();
        dialogDisplay.setY(getY() + topInset);
        this.setOffsetY(topInset);
    }
}
