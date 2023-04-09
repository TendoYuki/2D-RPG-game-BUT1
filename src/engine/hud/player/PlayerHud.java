package engine.hud.player;

import engine.hud.Hud;
import engine.physique.Heros;

public class PlayerHud extends Hud{
    
    public PlayerHud(Heros player) {
        super();
        addElement(new HealthBar(player, 10, 10, 200, 20));
        addElement(new CoinsCount(player, 512-32, 512 - 32, 0, 0));
    }
}
