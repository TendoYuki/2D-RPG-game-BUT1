package engine.generation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Map.Entry;

import engine.tiles.Atlas;
import engine.tiles.Directions;
import engine.tiles.Grid;
import engine.tiles.GridCell;
import engine.tiles.TileMap;

public class MapGenerator {

    public MapGenerator() {}

    private static TileMap generateTileMap(Room r) {
        Atlas atlas = new Atlas(
            "assets/tiles/tilemap.png",
            16,
            1,
            8,
			2
        );

        int[][] tm = new int[][] {
            {5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5},
            {5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5},
            {5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5},
            {5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5},
            {5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5},
            {5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5},
            {5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5},
            {5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5},
            {5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5},
            {5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5},
            {5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5},
            {5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5},
            {5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5},
            {5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5},
            {5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5},
            {5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5}
        };
        
        for(Entry<Directions, Room> entry: r.getNeighbors().entrySet()) {
            if(entry.getValue() != null)
            switch(entry.getKey()) {
                case UP:
                    if(tm[0].length%2 == 0) {
                        tm[0][tm[0].length/2] = 1;
                        tm[0][tm[0].length/2-1] =1;
                    }
                    else{
                        tm[0][tm[0].length/2] = 1;
                    }
                    break;
                case DOWN:
                    if(tm[0].length%2 == 0) {
                        tm[tm.length-1][tm[0].length/2] = 3;
                        tm[tm.length-1][tm[0].length/2-1] =3;
                    }
                    else{
                        tm[tm.length-1][tm[0].length/2] = 3;
                    }
                    break;
                case LEFT:
                    if(tm.length%2 == 0) {
                        tm[tm.length/2][0] = 2;
                        tm[tm.length/2 -1][0] =2;
                    }
                    else{
                        tm[tm.length/2][0] = 2;
                    }
                    break;
                case RIGHT:
                    if(tm.length%2 == 0) {
                        tm[tm.length/2][tm[0].length-1] = 4;
                        tm[tm.length/2-1][tm[0].length-1] =4;
                    }
                    else{
                        tm[tm.length/2][tm[0].length-1] = 4;
                    }
                    break;
            }
        }

        TileMap tileMap = new TileMap(
            16,
            2,
            tm,
            atlas
        );
        
        return tileMap;
    }

    /** 
     * @param startRoom
     * @param endRoom
     * @param totalRoomCount
     * @param countX
     * @param countY
    */
    public static Map GenerateMap(Room startRoom, Room endRoom,int totalRoomCount, int countX, int countY) {
        ArrayList<GridCell<Room>> rooms = new ArrayList<GridCell<Room>>();
        Grid<Room> roomsGrid = new Grid<Room>(countX, countY);
        startRoom.setRoomPossibleDirections(new Directions[] {Directions.UP});
        roomsGrid.setCell(countX/2, countY/2, startRoom);
        rooms.add(roomsGrid.getCell(countX/2, countY/2));

        for(int i = 0; i < totalRoomCount; i++) {
            //Searches for available spots
            ArrayList<Spot> availableSpots = getAvailableSpots(roomsGrid);

            // Creates new room at a random available spot
            Random rand = new Random();
            Spot newRoomSpot = availableSpots.get(rand.nextInt(availableSpots.size()));
            newRoomSpot.getSpot().setContent(new Room(Directions.values()));

            // Links the new room
            linkRoom(roomsGrid, newRoomSpot);
            
            //Register the new room
            rooms.add(newRoomSpot.getSpot());
        }

        Map map = generateMap(rooms, countX, countY);
        
        map.setActiveRoom(rooms.get(0).getContent().getId());
        return map;
    }

    private static Map generateMap(ArrayList<GridCell<Room>> rooms, int countX, int countY) {
        Map map = new Map(0,0, countX, countY);

        rooms.forEach(cell->{
            map.addRoom(cell.getContent(), cell.getCoords()[0], cell.getCoords()[1]);
            cell.getContent().setTileMap(generateTileMap(cell.getContent()));
            System.out.println(cell.getContent().toString());
        });
        return map;
    }

    private static void linkRoom(Grid<Room> roomsGrid, Spot newRoomSpot) {
        for(Entry<Directions, GridCell<Room>> entry: roomsGrid.getAdjacentCells(newRoomSpot.getSpot()).entrySet()) {
            // Checks if the current adjacent cell isn't null
            if(entry.getValue() != null && entry.getValue().getContent() != null) {
                // Checks if the current adjacent room can link in the opposite direction
                if(
                    entry.getValue().getContent().getRoomConstraints().contains(entry.getKey().opposite())
                ) {
                    newRoomSpot.getOrigin().getContent().linkRoom(
                        newRoomSpot.getSpot().getContent(), newRoomSpot.getDirection()
                    );
                    // Random rand = new Random();
                    // if(rand.nextInt(100)%2==0)
                    //     newRoomSpot.getSpot().getContent().linkRoom(
                    //         entry.getValue().getContent(), entry.getKey()
                    //     );
                }
            }
        }
    }

    private static ArrayList<Spot> getAvailableSpots(Grid<Room> grid) {
        ArrayList<Spot> availableSpots = new ArrayList<Spot>();
        grid.forEach(cell -> {
            if(!cell.isEmpty()) {
                HashMap<Directions, GridCell<Room>> adjacentCells = grid.getAdjacentCells(cell);
                cell.getContent().getAvailableDirections().forEach(direction->{
                    if(
                        adjacentCells.get(direction) != null && (
                            (adjacentCells.get(direction).getContent() != null &&
                            adjacentCells.get(direction).getContent().getAvailableDirections().contains(direction.opposite())) ||
                            adjacentCells.get(direction).getContent() == null
                        )
                    )
                        availableSpots.add(
                            new Spot(cell, adjacentCells.get(direction), direction)
                        );
                });
            }
        });
        return availableSpots;
    }
}

class Spot {
    private GridCell<Room> origin;
    private Directions direction;
    private GridCell<Room> spot;
    
    public GridCell<Room> getOrigin() {
        return origin;
    }
    public Directions getDirection() {
        return direction;
    }
    public GridCell<Room> getSpot() {
        return spot;
    }
    public Spot(GridCell<Room> origin, GridCell<Room> dest, Directions direction) {
        this.origin = origin;
        this.direction = direction;
        this.spot = dest;
    }
}