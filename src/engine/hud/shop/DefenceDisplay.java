package engine.hud.shop;

import engine.physique.Heros;

public class DefenceDisplay extends StatsDisplay {

    Heros player;

    public DefenceDisplay(Heros player, int x, int y, int width, int height) {
        super(10, x, y, width, height);
        this.player = player;
        displayString = "Defence";
    }

    @Override
    public int getCurrentValue() {
        return player.getMultiplicatorDef();
    }
    
}
