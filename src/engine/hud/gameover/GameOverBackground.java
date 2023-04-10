package engine.hud.gameover;

import java.awt.Color;
import java.awt.Graphics;

import engine.hud.HudElement;

public class GameOverBackground extends HudElement{

    public GameOverBackground(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    @Override
    public void draw(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.gray);
        g.fillRect(getY(), getX(), getWidth(), getHeight());
        g.setColor(c);
        
    }

    @Override
    public void onClick() {}
    
}
