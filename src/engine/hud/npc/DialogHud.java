package engine.hud.npc;

import engine.hud.Hud;
import engine.view.Coords;
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
        Coords origin = new Coords(0, 0);
        dialogDisplay = new DialogDisplay(origin, x, y, width, height);
        addElement(dialogDisplay);
        
    }
}
