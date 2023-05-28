package engine.physics;

enum State {
    ROAMING, IDLE, CHASING;
}

public class EnemyAI {
    Enemy enemy;

    State state;

    public EnemyAI(Enemy enemy) {
        this.enemy = enemy;
        state = State.ROAMING;
    }

    public void update() {
        if(enemy.room != enemy.world.map.activeRoom) return;
        enemy.vx = lerp(enemy.px, enemy.world.player.px, 0.01);
        enemy.vy = lerp(enemy.py, enemy.world.player.py, 0.01);
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
