package engine.hud;

import java.awt.Graphics;

public abstract class HudElement extends Clickable{
    public HudElement(int x, int y, int width, int height) {
        super(x, y, width, height);
    }
    public abstract void draw(Graphics g);
}
