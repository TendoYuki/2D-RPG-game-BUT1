package engine.tiles;

/** Directions (UP, RIGHT, DOWN, LEFT) */
public enum Directions {
    UP    (0),
    RIGHT (1),
    DOWN  (2),
    LEFT  (3);

    private final int dir; 

    /**
     * Creates a new direction
     * @param dir
     */
    private Directions(final int dir) { this.dir = dir; }

    /**
     * Returns the opposite of the direction
     * @return
     */
    public Directions opposite() {
        return Directions.values()[(dir+2)%4];
    }
}