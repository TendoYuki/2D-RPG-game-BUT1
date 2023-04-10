package engine.hud.shop;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import engine.afficheur.Sprite;
import engine.physique.Heros;

public class AddAttackButton extends ShopButton {

    Heros player;

    public AddAttackButton(Heros player, int x, int y, int width, int height) throws IOException{
        super(
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
        int curr = player.getMultiplicatorAtt()+1;
        if(curr <= 10 && player.getPieces() >= 5) {
            player.setMultiplicatorAtt(curr);
            player.addPieces(-5);
        }
    }
}
