package engine.physics;

import java.util.Timer;
import java.util.TimerTask;


/** State that the enemy can have */
enum State {
    ROAMING, IDLE, CHASING;
}

/** AI script of the enemy */
public class EnemyAI {
    /** Enemy controlled */
    Entity enemy;

    /** State of the enemy */
    State state;

    /** Zone in which the enemy can attack */
    private TriggerZone attackZone = new TriggerZone(20, 20);

    /** Whether or not the enemy can attack */
    boolean canAttack = true;

    

    /**
     * Creates an AI Script for a given enemy
     * @param enemy
     */
    public EnemyAI(Entity enemy) {
        this.enemy = enemy;
        state = State.ROAMING;
    }

    /** Update called every frame */
    public void update() {
        if(enemy.room != enemy.world.map.activeRoom) return;
        enemy.vx = move(enemy.px, enemy.world.player.px, 0.01);
        enemy.vy = move(enemy.py, enemy.world.player.py, 0.01);
        if(enemy.isInTriggerZone(enemy.world.player,attackZone) && canAttack) {
            canAttack = false;
            enemy.attack(enemy.world.player);
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    canAttack = true;
                }
            };
            Timer timer = new Timer("Timer");
            long delay = 3000L;
            timer.schedule(task, delay);
        }
    }

   

    /**
     * Move logic between two points
     * @param v1
     * @param v2
     * @param speed
     * @return
     */
    public double move(double v1, double v2, double speed) {
        return (speed * (v2 - v1)) % PhysicsEngine.SPEED;
    }

}
