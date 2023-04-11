package engine.hud.gameover;

import java.awt.Graphics;

import engine.hud.Hud;
import engine.view.Display;

public class GameOver extends Hud{

    GameOverBackground gameOverBackground;
    GOQuitButton goQuitButton;

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

    public void draw(Graphics g) {
        super.draw(g);
        int topInset = getTopInset();
        gameOverBackground.setY(getY() + topInset);
        goQuitButton.setY(getHeight() - 100 + topInset);
        this.setOffsetY(topInset);
    }
}