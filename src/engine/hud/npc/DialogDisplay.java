package engine.hud.npc;

import java.awt.Color;
import java.awt.Graphics;

import engine.dialog.DialogController;
import engine.hud.HudElement;
/** DialogDisplay class */
public class DialogDisplay extends HudElement{
    /** Constructs a dialog display 
     * @param x
     * @param y
     * @param width
     * @param height
    */
    public DialogDisplay(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    @Override
    public void draw(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.lightGray);
        g.fillRect(getX(), getY(), getWidth(), getHeight());
        g.setColor(c);
        DialogController.getCurrentDialog().draw(g, getX(), getY());
    }

    @Override
    public void onClick() {
        DialogController.getCurrentDialog().nextLine();
    }
    
}
