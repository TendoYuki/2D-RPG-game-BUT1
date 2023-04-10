package engine.hud.menu;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import engine.hud.Button;
import engine.view.Sprite;

public class StartButton extends Button {

    private Menu menu;

    public StartButton(Menu menu, int x, int y, int width, int height) throws IOException{
        super(
            new Sprite(
                x,
                y,
                2,
                ImageIO.read(
                    new File("assets/misc/Play.png")
                )
                
            ),
            new Sprite(
                x,
                y,
                2,
                ImageIO.read(
                    new File("assets/misc/Play_active.png")
                )
                
            ), x, y, width, height
        );
        this.menu = menu;
    }

    @Override
    public void onClick() {
        menu.setInteractable(false);   
        menu.setIsShown(false);
        menu.getpHud().setInteractable(true);
        menu.getpHud().setIsShown(true);
    }
}
