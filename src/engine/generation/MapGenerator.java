package engine.generation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Map.Entry;

import engine.physics.World;
import engine.tiles.Atlas;
import engine.tiles.Directions;
import engine.tiles.Grid;
import engine.tiles.GridCell;
import engine.tiles.TileMap;

class Coords {
    private int x;
    private int y;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    Coords(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

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

    public static void populateMap(
        Map map,
        int minEnemyCount,
        int maxEnemyCount
    ) { 
        populateMap(map, minEnemyCount, maxEnemyCount, new ArrayList<Integer>());
    }

    public static void populateMap(
        Map map,
        int minEnemyCount,
        int maxEnemyCount,
        ArrayList<Integer> exclusionIndexes
    ) {
        int minOffset = 2;
        map.rooms.forEach(roomCell->{
            if(
                !roomCell.isEmpty() &&
                !exclusionIndexes.contains(roomCell.getContent().getId())
            ) {
                Random rand = new Random();
                int ct = rand.nextInt(minEnemyCount, maxEnemyCount);
                if(ct == 0 && rand.nextInt(minEnemyCount, maxEnemyCount)%2 == 0)
                    ct = rand.nextInt(minEnemyCount, maxEnemyCount);


                ArrayList<Coords> possiblePositions = new ArrayList<Coords>();

                int cX = roomCell.getContent().getTileMap().getCountX();
                int cY = roomCell.getContent().getTileMap().getCountY();

                // Inits all from zero to max coords
                for(int y = 1; y < cY-1; y++) {
                    for(int x = 1; x < cX-1; x++) {
                        if(!(
                            //Down
                            (
                                y < 3 &&
                                x > cX/2-4 && x < cX/2+3
                            ) ||
                            // Up
                            (
                                y > cY-4 &&
                                x > cX/2-4 && x < cX/2+3
                            ) ||
                            // Left
                            (
                                x < 4 &&
                                y > cY/2-4 && y < cY/2+3
                            ) ||
                            // Right
                            (
                                x > cX-4 &&
                                y > cY/2-4 && y < cY/2+3
                            ) 
                        ))
                            possiblePositions.add(new Coords(x, y));
                    }
                }


                for(int i = 0; i < ct ; i++) {
                    try{
                        Coords coords = possiblePositions.get(rand.nextInt(possiblePositions.size()));  
                        roomCell.getContent().addEnemy(
                            0,
                            0,
                            coords.getX()*roomCell.getContent().getTileMap().size()/cX,
                            coords.getY()*roomCell.getContent().getTileMap().size()/cY
                        );
                        for(int j = 0; j < possiblePositions.size(); j++) {
                            if(
                                possiblePositions.get(j).getX() < (coords.getX()+minOffset) &&
                                possiblePositions.get(j).getX() > (coords.getX()-minOffset) &&
                                possiblePositions.get(j).getY() < (coords.getY()+minOffset) &&
                                possiblePositions.get(j).getY() > (coords.getY()-minOffset))
                                {
                                    possiblePositions.remove(j);
                                }
                        }
                    }
                    catch (Exception e) {}        
                }
                    
            }
        });
    }

    /** 
     * @param startRoom
     * @param endRoom
     * @param totalRoomCount
     * @param countX
     * @param countY
    */
    public static Map GenerateMap(
        World w,
        Room startRoom,
        Room endRoom,
        int totalRoomCount,
        int countX,
        int countY
    ) {
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

            if(i == totalRoomCount-1) {
                roomsGrid.setCell(newRoomSpot.getSpot(), endRoom);

                // Links the last room
                newRoomSpot.getOrigin().getContent().linkRoom(
                    newRoomSpot.getSpot().getContent(), newRoomSpot.getDirection()
                );
            } else {
                roomsGrid.setCell(newRoomSpot.getSpot(), new Room(w, Directions.values()));

                // Links the new room
                newRoomSpot.getOrigin().getContent().linkRoom(
                    newRoomSpot.getSpot().getContent(), newRoomSpot.getDirection()
                );
                linkRoom(roomsGrid, newRoomSpot);
            }
            
            //Register the new room
            rooms.add(newRoomSpot.getSpot());
        }

        Map map = generateMap(rooms, countX, countY);
        map.startRoom = startRoom;
        map.endRoom = endRoom;
        
        map.setActiveRoom(rooms.get(0).getContent().getId());
        return map;
    }

    private static Map generateMap(ArrayList<GridCell<Room>> rooms, int countX, int countY) {
        Map map = new Map(0,0, countX, countY);

        rooms.forEach(cell->{
            map.addRoom(cell.getContent(), cell.getCoords()[0], cell.getCoords()[1]);
            cell.getContent().setTileMap(generateTileMap(cell.getContent()));
        });
        return map;
    }

    private static void linkRoom(Grid<Room> roomsGrid, Spot newRoomSpot) {
        for(Entry<Directions, GridCell<Room>> entry: newRoomSpot.getSpot().getAdjacentCells().entrySet()) {
            // Checks if the current adjacent cell isn't null
            if(entry.getValue() != null && entry.getValue().getContent() != null) {
                // Checks if the current adjacent room can link in the opposite direction
                if(
                    entry.getValue().getContent().getRoomConstraints().contains(entry.getKey().opposite())
                ) {
                    Random rand = new Random();
                    if(rand.nextInt(100)%3==0)
                        newRoomSpot.getSpot().getContent().linkRoom(
                            entry.getValue().getContent(), entry.getKey()
                        );
                }
            }
        }
    }

    private static ArrayList<Spot> getAvailableSpots(Grid<Room> grid) {
        ArrayList<Spot> availableSpots = new ArrayList<Spot>();

        grid.getNonEmptyCells().forEach(cell->{
            cell.getContent().getAvailableDirections(grid).forEach(direction->{
                boolean exists = false;
                for(Spot spot: availableSpots) {
                    if(spot.getSpot().getIndex()==cell.getIndex())
                        exists = true;
                };
                if(!exists)
                    availableSpots.add(
                        new Spot(cell, cell.getAdjacentCells().get(direction), direction)
                    );
            });
        });


        // grid.forEach(cell -> {
        //     if(!cell.isEmpty()) {
        //         HashMap<Directions, GridCell<Room>> adjacentCells = cell.getAdjacentCells();
        //         cell.getContent().getAvailableDirections(grid).forEach(direction->{
        //             if(
        //                 adjacentCells.get(direction) != null && (
        //                     (adjacentCells.get(direction).getContent() != null &&
        //                     adjacentCells.get(direction).getContent().getAvailableDirections().contains(direction.opposite())) ||
        //                     adjacentCells.get(direction).getContent() == null
        //                 )
        //             )
        //                 availableSpots.add(
        //                     new Spot(cell, adjacentCells.get(direction), direction)
        //                 );
        //         });
        //     }
        // });
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