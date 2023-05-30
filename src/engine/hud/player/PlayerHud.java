package engine.hud.player;

import java.awt.Graphics;

import engine.hud.Hud;
import engine.hud.shop.Shop;
import engine.physics.Player;
import engine.view.Coords;
import engine.view.Display;
/** PlayerHud class */
public class PlayerHud extends Hud{
    /** The shop button */
    OpenShop openShop;
    /** The players gems */
    GemsCount gemsCount;
    /** The enemy count */
    EnemyCount enemyCount;
    /** The attack cooldown */
    AttackCooldown attackCooldown;
    /** Constructs the player hud 
     * @param display 
     * @param player
     * @param shop
    */
    public PlayerHud(Display display, Player player, Shop shop) {
        super(display, 0, 0, display.getWidth(), display.getHeight());
        Coords origin = new Coords(getX(), getY());
        Coords origin1 = new Coords(0, 0);
        addElement(new HealthBar(origin1,player, 10,display.getHeight()-30, 200, 20, true, false));

        openShop = new OpenShop(origin, shop, getWidth()-42, 10, 50, 32);
        gemsCount = new GemsCount(origin, player, getWidth()-42, 50, 50, 32);
        enemyCount = new EnemyCount(origin, player, getWidth()-42, getHeight()-70, 50, 32);
        attackCooldown = new AttackCooldown(origin, player, 10, 10, 50, 64);

        addElement(openShop);
        addElement(gemsCount);
        addElement(enemyCount);
        addElement(attackCooldown);
    }
    /** Draws the player hud 
     * @param g
    */
    public void draw(Graphics g) {
        int topInset = getTopInset();
        this.setOffsetY(topInset);

        super.draw(g);
    }
}
