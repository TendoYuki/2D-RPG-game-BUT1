package engine.physics;

import java.util.HashMap;

import engine.tiles.Directions;

public class WorldBorder {

    int width;
    int height;
    private int BORDER_WIDTH = 2;

    private Wall tBorder;
    private Wall rBorder;
    private Wall bBorder;
    private Wall lBorder;

    private World world;

    public WorldBorder(World world, int width, int height) {
        this.world = world;
        this.width = width;
        this.height = height;
        updateBorders();
    }

    private void updateBorders() {
        // Top Border
        tBorder = new Wall(
            world,
            0 - BORDER_WIDTH,
            height,
            width + 2 * BORDER_WIDTH,
            BORDER_WIDTH);
        // Left Border
        lBorder = new Wall(
            world,
            0 - BORDER_WIDTH,
            0,
            BORDER_WIDTH,
            height);
        // Bottom Border
        bBorder = new Wall(
            world,
            0 - BORDER_WIDTH,
            0 - BORDER_WIDTH,
            width + 2 * BORDER_WIDTH,
            BORDER_WIDTH);
        // Right Border
        rBorder = new Wall(
            world,
            width,
            0,
            BORDER_WIDTH,
            height);
    }
    
    public HashMap<Directions, Wall> getBorderWalls() {
        HashMap<Directions, Wall> walls = new HashMap<Directions, Wall>();
        walls.put(Directions.UP, tBorder);
        walls.put(Directions.RIGHT, rBorder);
        walls.put(Directions.DOWN, bBorder);
        walls.put(Directions.LEFT, lBorder);
        return walls;
    }
}