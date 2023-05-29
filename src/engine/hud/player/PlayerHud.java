package engine.hud.player;

import java.awt.Graphics;

import engine.hud.Hud;
import engine.hud.shop.Shop;
import engine.physics.Player;
import engine.view.Display;
/** PlayerHud class */
public class PlayerHud extends Hud{
    /** The shop button */
    OpenShop openShop;
    /** The players gems */
    GemsCount gemsCount;
    /** Constructs the player hud 
     * @param display 
     * @param player
     * @param shop
    */
    public PlayerHud(Display display, Player player, Shop shop) {
        super(display, 0, 0, display.getWidth(), display.getHeight());
        int topInset = getTopInset();
        addElement(new HealthBar(player, 10, 10+topInset, 200, 20, true, false));

        openShop = new OpenShop(shop, display.getWidth()-32, display.getHeight()-32, 50, 50);
        gemsCount = new GemsCount(player, display.getWidth()-32, display.getHeight()-70, 50, 50);

        addElement(openShop);
        addElement(gemsCount);
    }
    /** Draws the player hud 
     * @param g
    */
    public void draw(Graphics g) {
        int topInset = getTopInset();
        this.setOffsetY(topInset);
        openShop.setX(getDisplay().getWidth()-32);
        openShop.setY(getDisplay().getHeight()-32 + topInset);

        gemsCount.setX(getDisplay().getWidth()-32);
        gemsCount.setY(getDisplay().getHeight()-70 + topInset);

        super.draw(g);
    }
}
