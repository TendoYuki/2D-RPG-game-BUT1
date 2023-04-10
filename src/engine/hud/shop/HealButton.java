package engine.hud.shop;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import engine.physics.Player;
import engine.view.Sprite;

public class HealButton extends ShopButton {

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
                
            ), x, y, width, height
        );
        this.player = player;
    }

    @Override
    public void onClick() {
        int vie = player.getHealth();
        int maxVie = player.getMaxHealth();
        if (player.getCoins() >= 5){
            if(vie +15 <= maxVie) {
                player.setHealth(vie + 15);
                player.addCoins(-5);
            }
            else if(vie+15 < maxVie + 15){
                player.setHealth(maxVie);
                player.addCoins(-5);
            }
        }
    }
}
