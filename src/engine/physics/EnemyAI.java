package engine.physics;

import java.util.Timer;
import java.util.TimerTask;

enum State {
    ROAMING, IDLE, CHASING;
}

public class EnemyAI {
    Enemy enemy;

    State state;

    int attackZoneWidth = 20;
    int attackZoneHeight = 20;

    boolean canAttack = true;

    public EnemyAI(Enemy enemy) {
        this.enemy = enemy;
        state = State.ROAMING;
    }

    public void update() {
        if(enemy.room != enemy.world.map.activeRoom) return;
        enemy.vx = lerp(enemy.px, enemy.world.player.px, 0.01);
        enemy.vy = lerp(enemy.py, enemy.world.player.py, 0.01);
        if(isInTriggerZone() && canAttack) {
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

    public boolean isInTriggerZone(){
		return (
			enemy.world.player.px+enemy.world.player.width > enemy.px- attackZoneWidth/2 &&
			enemy.world.player.px < enemy.px + enemy.width + attackZoneWidth/2
		) &&
		( 
			enemy.world.player.py+enemy.world.player.height > enemy.py - attackZoneHeight/2 &&
			enemy.world.player.py < enemy.py + enemy.height + attackZoneHeight/2
		);
	}

    /**
     * Linear interpolation between a and b 
     * @param v1
     * @param v2
     * @param speed
     * @return
     */
    public double lerp(double v1, double v2, double speed) {
        return (speed * (v2 - v1)) % PhysicsEngine.SPEED;
    }

}
