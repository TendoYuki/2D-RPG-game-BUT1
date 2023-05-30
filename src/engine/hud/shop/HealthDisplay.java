package engine.hud.shop;

import engine.physics.Player;
import engine.view.Coords;
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
    public HealthDisplay(Coords origin, Player player, int x, int y, int width, int height) {
        super(origin, 10, x, y, width, height);
        this.player = player;
        displayString = "Health";
    }

    @Override
    public int getCurrentValue() {
        return player.getHealthMultiplicator();
    }
    
}
