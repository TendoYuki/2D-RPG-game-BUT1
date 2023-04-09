package engine.hud.shop;

import java.awt.Color;
import java.awt.Graphics;

import engine.hud.HudElement;
import engine.physique.Heros;

public class ShopBackground extends HudElement{

    public ShopBackground(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    @Override
    public void draw(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.cyan);
        g.fillRect(getY(), getX(), getWidth(), getHeight());
        g.setColor(c);
        
    }

    @Override
    public void onClick() {}
    
}
