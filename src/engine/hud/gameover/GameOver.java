package engine.hud.gameover;
import engine.hud.Hud;
import engine.view.Coords;
import engine.view.Display;
/** GameOver class */
public class GameOver extends Hud{
    /** Background of the hud */
    GameOverBackground gameOverBackground;
    /** Quit button */
    GOQuitButton goQuitButton;
    /** Constructs a GameOver hud 
     * @param display
     * @param x
     * @param y
     * @param width
     * @param height
    */
    public GameOver(Display display, int x, int y, int width, int height) {
        super(display, x, y, width, height);
        Coords origin = new Coords(x, y);
        try{
            gameOverBackground = new GameOverBackground(origin,x, y, width, height);
            goQuitButton = new GOQuitButton(origin,width/2 - 32, 50, 64, 50);
            addElement(gameOverBackground);
            addElement(goQuitButton);
        }
        catch(Exception e) {}
    }
}