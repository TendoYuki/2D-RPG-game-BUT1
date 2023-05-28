package engine.hud.shop;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import engine.hud.Button;
import engine.physics.Player;
import engine.view.Sprite;

public class HealButton extends Button {

    Player player;

    public HealButton(Player player, int x, int y, int width, int height) throws IOException{
        super(
            new Sprite(
                x,
                y,
                2,
                ImageIO.read(
                    new File("assets/misc/HealthVial.png")
                )
                
            ),
            new Sprite(
                x,
                y,
                2,
                ImageIO.read(
                    new File("assets/misc/HealthVial.png")
                )
                
            ), x, y, width, height
        );
        this.player = player;
    }

    @Override
    public void onClick() {
        int vie = player.getHealth();
        int maxVie = player.getMaxHealth();
        if (player.getgems() >= 5){
            if(vie +40 <= maxVie) {
                player.setHealth(vie + 40);
                player.addgems(-5);
            }
            else if(vie+40 < maxVie + 40){
                player.setHealth(maxVie);
                player.addgems(-5);
            }
        }
    }
}
