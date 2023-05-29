package engine.hud.shop;

import engine.physics.Player;
/** HealthDisplay class */
public class HealthDisplay extends StatsDisplay {
    /** The player */
    Player player;
    /** Constructs a bar for the heal stats 
     * @param player
     * @param x
     * @param y
     * @param width
     * @param height
    */
    public HealthDisplay(Player player, int x, int y, int width, int height) {
        super(10, x, y, width, height);
        this.player = player;
        displayString = "Health";
    }

    @Override
    public int getCurrentValue() {
        return player.getHealthMultiplicator();
    }
    
}
