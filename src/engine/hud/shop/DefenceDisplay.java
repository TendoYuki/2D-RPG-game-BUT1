package engine.hud.shop;

import engine.physics.Player;

public class DefenceDisplay extends StatsDisplay {

    Player player;

    public DefenceDisplay(Player player, int x, int y, int width, int height) {
        super(10, x, y, width, height);
        this.player = player;
        displayString = "Defence";
    }

    @Override
    public int getCurrentValue() {
        return player.getDefenceMultiplicator();
    }
    
}
