package engine.hud.shop;

import engine.physics.Player;
import engine.view.Coords;
/** AttackDisplay class */
public class AttackDisplay extends StatsDisplay {
    /** The player */
    Player player;
    /** Constructs a bar for the attack stats
     * @param player
     * @param x
     * @param y
     * @param width
     * @param height
    */
    public AttackDisplay(Coords origin, Player player, int x, int y, int width, int height) {
        super(origin, 10, x, y, width, height);
        this.player = player;
        displayString = "Attack";
    }

    @Override
    public int getCurrentValue() {
        return player.getAttackMultiplicator();
    }
    
}
