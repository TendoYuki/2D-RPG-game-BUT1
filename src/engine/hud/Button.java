package engine.hud;

import java.awt.Graphics;

import engine.view.Sprite;

public abstract class Button extends HudElement{

    Sprite buttonSprite;
    Sprite buttonActiveSprite;

    public Button(Sprite sprite, Sprite activeSprite, int x, int y, int width, int height) {
        super(x, y, width, height);
        buttonSprite = sprite;
        buttonActiveSprite = activeSprite;
    }

    @Override
    public void draw(Graphics g) {
        if(isPressed())
            buttonActiveSprite.draw(g, getX(), getY());
        else
            buttonSprite.draw(g, getX(), getY());
    }
}
