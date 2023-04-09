package engine.hud.player;

import java.awt.Font;
import java.awt.Graphics;
import java.io.File;

import javax.imageio.ImageIO;

import engine.afficheur.Sprite;
import engine.hud.HudElement;
import engine.physique.Heros;

public class CoinsCount extends HudElement{

    Sprite coin;
    Heros player;

    public CoinsCount(Heros player, int x, int y, int width, int height) {
        super(x, y, width, height);
        this.player = player;
        try{
            coin = new Sprite(16, 16, 2, ImageIO.read(
                new File(
                    "assets/misc/coin.png"
                )
            ));
        }
        catch(Exception e) {}
    }

    @Override
    public void draw(Graphics g) {
        Font temp = g.getFont();
        g.setFont(temp.deriveFont(Font.BOLD).deriveFont(20F));

        coin.draw(g, getX(), getY());
        g.drawString("" + player.getPieces(), getX() - coin.getSizeX(), getY() + (int)(coin.getSizeY()/1.3));
        g.setFont(temp);
    }

    @Override
    public void onClick() {
        // TODO Auto-generated method stub
        
    }
    
}
