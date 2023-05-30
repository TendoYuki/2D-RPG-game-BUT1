package engine.hud.npc;

import java.awt.Graphics;
import java.io.File;

import javax.imageio.ImageIO;

import engine.dialog.DialogController;
import engine.hud.HudElement;
import engine.view.Coords;
import engine.view.Sprite;
/** DialogDisplay class */
public class DialogDisplay extends HudElement{
    /** Constructs a dialog display 
     * @param x
     * @param y
     * @param width
     * @param height
    */
    public DialogDisplay(Coords origin, int x, int y, int width, int height) {
        super(origin, x, y, width, height);
    }

    @Override
    public void draw(Graphics g) {
        try{
            new Sprite(
                getX(),
                getY(),
                1,
                ImageIO.read(
                    new File("assets/misc/text_bg.png")
                )
            ).draw(g, getX(), getY());
        } catch (Exception e) { }
        DialogController.getCurrentDialog().draw(g, getX(), getY());
    }

    @Override
    public void onClick() {
        DialogController.getCurrentDialog().nextLine();
    }
    
}
