package engine.hud.menu;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import engine.controller.KeyboardController;
import engine.hud.Button;
import engine.view.Sprite;
/** QuitButton class */
public class QuitButton extends Button {

    /** Constructs a quit button 
     * @param x 
     * @param y
     * @param width
     * @param height
    */
    public QuitButton(int x, int y, int width, int height) throws IOException{
        super(
            new Sprite(
                x,
                y,
                2,
                ImageIO.read(
                    new File("assets/misc/Exit.png")
                )
                
            ),
            new Sprite(
                x,
                y,
                2,
                ImageIO.read(
                    new File("assets/misc/Exit_active.png")
                )
                
            ), x, y, width, height
        );
    }

    @Override
    public void onClick() {
        KeyboardController.fin = true;
    }
}
