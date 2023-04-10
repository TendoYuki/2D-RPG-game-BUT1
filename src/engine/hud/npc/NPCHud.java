package engine.hud.npc;

import engine.hud.Hud;

public class NPCHud extends Hud{
    
    public NPCHud(int x, int y, int width, int height) {
        super();
        addElement(new NPCBackground(x, y, width, height));
        
    }
}
