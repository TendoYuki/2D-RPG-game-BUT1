package engine.tiles;

public enum Directions {
    UP    (0),
    RIGHT (1),
    DOWN  (2),
    LEFT  (3);

    private final int dir; 

    private Directions(final int dir) { this.dir = dir; }

    public Directions opposite() {
        return Directions.values()[(dir+2)%4];
    }
}