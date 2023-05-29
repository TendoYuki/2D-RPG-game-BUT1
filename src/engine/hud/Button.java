package engine.hud;

import java.awt.Graphics;

import engine.view.Sprite;
/** Button class */
public abstract class Button extends HudElement{
    /** The button sprite */
    Sprite buttonSprite;
    /** The active button sprite */
    Sprite buttonActiveSprite;
    /** Constructs a button 
     * @param sprite
     * @param activeSprite
     * @param x
     * @param y
     * @param width
     * @param height
    */
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
