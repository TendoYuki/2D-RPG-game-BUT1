package engine.hud.shop;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import engine.hud.Button;
import engine.physics.Player;
import engine.view.Sprite;
/** AddHealthButton class */
public class AddHealthButton extends Button {
    /** The player */
    Player player;
    /** Constructs the button to buy health stats 
     * @param player
     * @param x
     * @param y
     * @param width
     * @param height
    */
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
        if(curr <= 10 && player.getgems() >= 5) {
            player.setHealthMultiplicator(curr);
            player.setHealth(player.getHealth() + player.HEALTH_MUL_UNIT);
            player.addgems(-5);
        }
    }
}
