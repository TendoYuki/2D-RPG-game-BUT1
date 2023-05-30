package engine.hud.shop;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import engine.hud.Button;
import engine.physics.Player;
import engine.view.Coords;
import engine.view.Sprite;
/** AddAttackButton class */
public class AddAttackButton extends Button {
    /** The player  */
    Player player;
    /** Constructs the button to buy attack stats 
     * @param player
     * @param x
     * @param y
     * @param width
     * @param height
    */
    public AddAttackButton(Coords origin, Player player, int x, int y, int width, int height) throws IOException{
        super(
            origin,
            new Sprite(
                x,
                y,
                2,
                ImageIO.read(
                    new File("assets/misc/gem.png")
                )
                
            ),
            new Sprite(
                x,
                y,
                2,
                ImageIO.read(
                    new File("assets/misc/gem.png")
                )
                
            ), x, y, width, height
        );
        this.player = player;
    }

    @Override
    public void onClick() {
        int curr = (int)(player.getAttackMultiplicator()+1.5);
        if(curr <= 10 && player.getGems() >= 5) {
            player.setAttackMultiplicator(curr);
            player.addgems(-5);
        }
    }
}
