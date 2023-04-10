package engine.hud.gameover;

import engine.hud.Hud;

public class GameOver extends Hud{
    public GameOver(int x, int y, int width, int height) {
        super();
        try{
            addElement(new GameOverBackground(x, y, width, height));
            addElement(new GOQuitButton(width/2 - 32, height - 100, 64, 50));
        }
        catch(Exception e) {}
    }
}