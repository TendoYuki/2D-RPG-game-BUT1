package engine.hud.end;

import engine.hud.Hud;
import engine.view.Coords;
import engine.view.Display;
/** GameOver class */
public class EndMenu extends Hud{
    /** Background of the hud */
    EndMenuBackground endMenuBackground;
    /** Quit button */
    EMQuitButton emQuitButton;
    /** Constructs a GameOver hud 
     * @param display
     * @param x
     * @param y
     * @param width
     * @param height
    */
    public EndMenu(Display display, int x, int y, int width, int height) {
        super(display, x, y, width, height);
        Coords origin = new Coords(x, y);
        try{
            endMenuBackground = new EndMenuBackground(origin,x, y, width, height);
            emQuitButton = new EMQuitButton(origin,width/2 - 32, 50, 64, 50);
            addElement(endMenuBackground);
            addElement(emQuitButton);
        }
        catch(Exception e) {}
    }
}