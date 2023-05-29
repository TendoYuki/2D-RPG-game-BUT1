package engine.hud.gameover;

import java.awt.Graphics;

import engine.hud.Hud;
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
        try{
            gameOverBackground = new GameOverBackground(x, y, width, height);
            goQuitButton = new GOQuitButton(width/2 - 32, height - 100, 64, 50);
            addElement(gameOverBackground);
            addElement(goQuitButton);
        }
        catch(Exception e) {}
    }
    /** Draws the hud 
     * @param g
    */
    public void draw(Graphics g) {
        super.draw(g);
        int topInset = getTopInset();
        gameOverBackground.setY(getY() + topInset);
        goQuitButton.setY(getHeight() - 100 + topInset);
        this.setOffsetY(topInset);
    }
}