package engine.hud.player;

import engine.hud.Hud;
import engine.hud.shop.Shop;
import engine.physique.Heros;

public class PlayerHud extends Hud{
    
    public PlayerHud(Heros player, Shop shop) {
        super();
        addElement(new HealthBar(player, 10, 10, 200, 20));
        addElement(new OpenShop(shop, 505-32, 505 - 32, 50, 50));
        addElement(new CoinsCount(player, 505-32, 505 - 70, 50, 50));
    }
}
