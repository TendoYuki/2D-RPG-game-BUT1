package engine.physics;

import engine.generation.Room;

/** Boss class */
public class Boss extends Entity {
    /**
     * Constructs a boss
     * @param w
     * @param r
     * @param level
     */
    public Boss(World w, Room r, int level){
        super(w, r, level);
    }
   
    @Override
    public void handleDeath() {
        world.bossDefeated = true;
    }   
}