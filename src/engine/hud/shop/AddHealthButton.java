package engine.hud.shop;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import engine.hud.Button;
import engine.physics.Player;
import engine.view.Sprite;

public class AddHealthButton extends Button {

    Player player;

    public AddHealthButton(Player player, int x, int y, int width, int height) throws IOException{
        super(
            new Sprite(
                x,
                y,
                2,
                ImageIO.read(
                    new File("assets/misc/coin.png")
                )
                
            ),
            new Sprite(
                x,
                y,
                2,
                ImageIO.read(
                    new File("assets/misc/coin.png")
                )
                
            ), x, y, width, height
        );
        this.player = player;
    }

    @Override
    public void onClick() {
        int curr = player.getHealthMultiplicator()+1;
        if(curr <= 10 && player.getCoins() >= 5) {
            player.setHealthMultiplicator(curr);
            player.setHealth(player.getHealth() + player.HEALTH_MUL_UNIT);
            player.addCoins(-5);
        }
    }
}
