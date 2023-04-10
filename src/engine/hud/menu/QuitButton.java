package engine.hud.menu;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import engine.controller.KeyboardController;
import engine.view.Sprite;

public class QuitButton extends MenuButton {


    public QuitButton(int x, int y, int width, int height) throws IOException{
        super(
            new Sprite(
                x,
                y,
                2,
                ImageIO.read(
                    new File("assets/misc/Exit.png")
                )
                
            ), x, y, width, height
        );
    }

    @Override
    public void onClick() {
        KeyboardController.fin = true;
    }
}
