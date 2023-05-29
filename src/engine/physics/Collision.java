package engine.physics;

/** Collison class */
public class Collision {

    /** Objects involved in the collison */
    public PhysicalObject o, o2;

    /** Angle of the collsion */
    public double collisionAngle;

    /** Value necessary for exiting a colliding state */
    public double correctionValue;
    
    /**
     * Constructs a collision
     * @param typeOfCollision
     * @param o
     * @param o2
     * @param collisionAngle
     * @param correctionValue
     */
    private Collision(PhysicalObject o, PhysicalObject o2,
            double collisionAngle, double correctionValue) {
        this.o = o;
        this.o2 = o2;
        this.collisionAngle = collisionAngle;
        this.correctionValue = correctionValue;
    }

    /**
     * Constructs a collision
     * @param o
     * @param o2
     * @return
     */
    public static Collision collision(PhysicalObject o, PhysicalObject o2) {
        // Checks if collision
        if ((o.px >= o2.px + o2.width) || (o.px + o.width <= o2.px)
                || (o.py >= o2.py + o2.height) || (o.py + o.height <= o2.py)) {
            return null;
        }

        double posX = o.px + o.width/2;
        double posY = o.py + o.height/2;

        double posX1 = o2.px + o2.width/2;
        double posY1 = o2.py + o2.height/2;

        double relX = posX - posX1;
        double relY = posY - posY1;

        double dist  = Math.sqrt(Math.pow(posX1-posX, 2) + Math.pow(posY1-posY, 2));
        
        double colAngle = Math.atan2(relY, relX);

        double minDist = o.width + o2.width;
		double overlap = minDist - dist;
		double correctionValue = overlap / 25;

        return new Collision(
            o,
            o2,
            colAngle,
            correctionValue
        );
    }
}