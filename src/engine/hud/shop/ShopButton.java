package engine.hud.shop;

import java.awt.Graphics;

import engine.hud.HudElement;
import engine.view.Sprite;

public abstract class ShopButton extends HudElement{

    Sprite buttonSprite;

    public ShopButton(Sprite sprite, int x, int y, int width, int height) {
        super(x, y, width, height);
        buttonSprite = sprite;
    }

    @Override
    public void draw(Graphics g) {
        buttonSprite.draw(g, getX(), getY());
    }
}
