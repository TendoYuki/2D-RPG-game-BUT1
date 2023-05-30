package engine.hud.player;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

import engine.hud.HudElement;
import engine.physics.Player;
import engine.view.Coords;
/** GemsCount class */
public class EnemyCount extends HudElement{
    /** The player */
    Player player;
    /** Constructs the gems count hud
     * @param player
     * @param x
     * @param y
     * @param width
     * @param height
    */
    public EnemyCount(Coords origin, Player player, int x, int y, int width, int height) {
        super(origin, x, y, width, height);
        this.player = player;
    }

    @Override
    public void draw(Graphics g) {
        Font temp = g.getFont();
        g.setFont(temp.deriveFont(Font.BOLD).deriveFont(20F));

        String str = "Enemy count : " + player.world.map.enemiesCount();
        FontMetrics metrics = g.getFontMetrics(g.getFont());
        int xStr = getX() - metrics.stringWidth(str);

        g.drawString(str, xStr - 5, getY() );
        g.setFont(temp);
    }

    @Override
    public void onClick() {}
    
}
