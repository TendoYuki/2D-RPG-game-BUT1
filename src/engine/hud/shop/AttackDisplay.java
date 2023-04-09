package engine.hud.shop;

import engine.physique.Heros;

public class AttackDisplay extends StatsDisplay {

    Heros player;

    public AttackDisplay(Heros player, int x, int y, int width, int height) {
        super(10, x, y, width, height);
        this.player = player;
    }

    @Override
    public int getCurrentValue() {
        return player.getMultiplicatorAtt();
    }
    
}
