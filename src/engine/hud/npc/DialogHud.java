package engine.hud.npc;

import java.awt.Graphics;

import engine.hud.Hud;
import engine.view.Display;
/** DialogHud class */
public class DialogHud extends Hud{
    /** The dialog display */
    DialogDisplay dialogDisplay;
    /** Constructs the dialog hud 
     * @param display
     * @param x
     * @param y
     * @param width
     * @param height
    */
    public DialogHud(Display display, int x, int y, int width, int height) {
        super(display, x, y, width, height);
        dialogDisplay = new DialogDisplay(x, y, width, height);
        addElement(dialogDisplay);
        
    }
    /** Draws the dialog hud 
     * @param g
     */
    public void draw(Graphics g) {
        super.draw(g);
        int topInset = getTopInset();
        dialogDisplay.setY(getY() + topInset);
        this.setOffsetY(topInset);
    }
}
