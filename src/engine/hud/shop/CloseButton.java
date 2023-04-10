package engine.hud.shop;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import engine.controller.KeyboardController;
import engine.hud.Button;
import engine.view.Sprite;

public class CloseButton extends Button {

    Shop shop;

    public CloseButton(Shop shop, int x, int y, int width, int height) throws IOException{
        super(
            new Sprite(
                x,
                y,
                2,
                ImageIO.read(
                    new File("assets/misc/Back.png")
                )
                
            ),
            new Sprite(
                x,
                y,
                2,
                ImageIO.read(
                    new File("assets/misc/Back_active.png")
                )
                
            ), x, y, width, height
        );
        this.shop = shop;
    }

    @Override
    public void onClick() {
        shop.setInteractable(false);   
        shop.setIsShown(false);
        KeyboardController.canMove = true;
    }
}