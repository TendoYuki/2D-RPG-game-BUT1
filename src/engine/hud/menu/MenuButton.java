package engine.hud.menu;

import java.awt.Graphics;

import engine.afficheur.Sprite;
import engine.hud.HudElement;

public abstract class MenuButton extends HudElement{

    Sprite buttonSprite;

    public MenuButton(Sprite sprite, int x, int y, int width, int height) {
        super(x, y, width, height);
        buttonSprite = sprite;
    }

    @Override
    public void draw(Graphics g) {
        buttonSprite.draw(g, getX(), getY());
    }
}
