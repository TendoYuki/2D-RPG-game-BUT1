package engine.hud.shop;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import engine.hud.Button;
import engine.physics.Player;
import engine.view.Coords;
import engine.view.Sprite;
/** HealButton class */
public class HealButton extends Button {
    /** The player */
    Player player;
    /** Constructs the healing button 
     * @param player
     * @param x
     * @param y
     * @param width
     * @param height
    */
    public HealButton(Coords origin, Player player, int x, int y, int width, int height) throws IOException{
        super(
            origin,
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
        if (player.getGems() >= 5){
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
