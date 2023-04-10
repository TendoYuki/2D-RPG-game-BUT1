package engine.hud.shop;

import engine.physics.Player;

public class AttackDisplay extends StatsDisplay {

    Player player;

    public AttackDisplay(Player player, int x, int y, int width, int height) {
        super(10, x, y, width, height);
        this.player = player;
        displayString = "Attack";
    }

    @Override
    public int getCurrentValue() {
        return player.getAttackMultiplicator();
    }
    
}
