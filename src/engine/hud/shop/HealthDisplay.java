package engine.hud.shop;

import engine.physique.Heros;

public class HealthDisplay extends StatsDisplay {

    Heros player;

    public HealthDisplay(Heros player, int x, int y, int width, int height) {
        super(10, x, y, width, height);
        this.player = player;
    }

    @Override
    public int getCurrentValue() {
        return player.getMultiplicatorVie();
    }
    
}
