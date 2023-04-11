package engine.hud.player;

import java.awt.Graphics;

import engine.hud.Hud;
import engine.hud.shop.Shop;
import engine.physics.Player;
import engine.view.Display;

public class PlayerHud extends Hud{
    
    Display display;

    OpenShop openShop;
    CoinsCount counsCount;

    public PlayerHud(Display display, Player player, Shop shop) {
        super();
        this.display = display;
        addElement(new HealthBar(player, 10, 10, 200, 20));

        openShop = new OpenShop(shop, display.getWidth()-32, display.getHeight()-32, 50, 50);
        counsCount = new CoinsCount(player, display.getWidth()-32, display.getHeight()-70, 50, 50);

        addElement(openShop);
        addElement(counsCount);
    }

    public void draw(Graphics g) {
        openShop.setX(display.getWidth()-32);
        openShop.setY(display.getHeight()-32);

        counsCount.setX(display.getWidth()-32);
        counsCount.setY(display.getHeight()-70);

        super.draw(g);
    }
}
