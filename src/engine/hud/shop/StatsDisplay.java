package engine.hud.shop;

import java.awt.Font;
import java.awt.Graphics;

import engine.hud.HudElement;
import engine.view.Coords;
/** StatsDisplay class */
public abstract class StatsDisplay extends HudElement{
    /** The max value */
    int maxValue;
    /** The display string */
    protected String displayString = "";
    /** Constructs a stats display hud 
     * @param maxValue
     * @param x
     * @param y
     * @param width
     * @param height
    */
    public StatsDisplay(Coords origin, int maxValue, int x, int y, int width, int height) {
        super(origin, x, y, width, height);
        this.maxValue = maxValue;
    }
    /** Returns the current value 
     * @return
    */
    public abstract int getCurrentValue();

    @Override
    public void draw(Graphics g) {
        int elementWidth = (getWidth()/2) / maxValue;
        Font temp = g.getFont();
        g.setFont(temp.deriveFont(Font.BOLD).deriveFont(16F));
        g.drawString(displayString,getX() + 10,getY()-5);
        g.setFont(temp);
        for(int i = 0; i < maxValue; i++) {
            
            if(i<getCurrentValue()){
                g.fillRect(getX() + 10 + i * (10+ elementWidth), getY(), elementWidth, getHeight() - 10);
            } else {
                g.drawRect(getX() + 10 + i * (10 + elementWidth), getY(), elementWidth, getHeight() - 10);
            }
        }
        int totalSize = 10 + 10 * (10+ elementWidth);
        g.setFont(temp.deriveFont(Font.BOLD).deriveFont(18F));
        g.drawString("5 x ",getX()+ totalSize-2,getY()+35);
        g.setFont(temp);
    }

    @Override
    public final void onClick() {}
    
}
