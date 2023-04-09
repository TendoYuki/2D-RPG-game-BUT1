package engine.hud.shop;

import java.awt.Graphics;

import engine.hud.HudElement;

public abstract class StatsDisplay extends HudElement{

    int maxValue;

    public StatsDisplay(int maxValue, int x, int y, int width, int height) {
        super(x, y, width, height);
        this.maxValue = maxValue;
    }

    public abstract int getCurrentValue();

    @Override
    public void draw(Graphics g) {
        for(int i = 0; i < maxValue; i++) {
            int elementWidth = (getWidth()/2) / maxValue;
            if(i<getCurrentValue()){
                g.fillRect(10+i*(getX()+10+ elementWidth), getY(), elementWidth, getHeight() - 10);
            } else {
                g.drawRect(10+i*(getX()+10 + elementWidth), getY(), elementWidth, getHeight() - 10);
            }
        }
    }

    @Override
    public final void onClick() {}
    
}
