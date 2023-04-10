package engine.hud.shop;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import engine.afficheur.Sprite;
import engine.physique.Player;

public class AddHealthButton extends ShopButton {

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
                
            ), x, y, width, height
        );
        this.player = player;
    }

    @Override
    public void onClick() {
        int curr = player.getMultiplicatorVie()+1;
        if(curr <= 10 && player.getPieces() >= 5) {
            player.setMultiplicatorVie(curr);
            player.setVie(player.getVie() + player.MUL_VIE_UNIT);
            player.addPieces(-5);
        }
    }
}
