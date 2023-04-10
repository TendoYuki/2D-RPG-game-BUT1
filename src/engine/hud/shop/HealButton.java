package engine.hud.shop;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import engine.afficheur.Sprite;
import engine.physique.Player;

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
        int vie = player.getVie();
        int maxVie = player.getMaxVie();
        if (player.getPieces() >= 5){
            if(vie +15 <= maxVie) {
                player.setVie(vie + 15);
                player.addPieces(-5);
            }
            else if(vie+15 < maxVie + 15){
                player.setVie(maxVie);
                player.addPieces(-5);
            }
        }
    }
}
