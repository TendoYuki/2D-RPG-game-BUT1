package engine.hud.shop;

import engine.physique.Player;

public class HealthDisplay extends StatsDisplay {

    Player player;

    public HealthDisplay(Player player, int x, int y, int width, int height) {
        super(10, x, y, width, height);
        this.player = player;
        displayString = "Health";
    }

    @Override
    public int getCurrentValue() {
        return player.getMultiplicatorVie();
    }
    
}
