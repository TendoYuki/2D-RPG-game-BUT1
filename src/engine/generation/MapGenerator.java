package engine.generation;

import java.util.ArrayList;
import java.util.Random;
import java.util.Map.Entry;

import engine.tiles.Atlas;
import engine.tiles.Directions;
import engine.tiles.Grid;
import engine.tiles.GridCell;
import engine.tiles.TileMap;

public class MapGenerator {

    public MapGenerator() {}

    private static void linkRooms(GridCell<Room> r, Entry<Directions, GridCell<Room>> entry, Directions dir) {
        r.getContent().addNeighbor(entry.getKey(), entry.getValue().getContent());
        entry.getValue().getContent().addNeighbor(dir, r.getContent());
    }

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
            ArrayList<GridCell<Room>> availableSpots = getAvailableSpots(rooms, roomsGrid);

            // Creates new room at a random available spot
            Random rand = new Random();
            GridCell<Room> newRoom = availableSpots.get(rand.nextInt(availableSpots.size()));
            newRoom.setContent(new Room(Directions.values()));

            // Links the new room
            linkRoom(roomsGrid, newRoom);
            
            //Register the new room
            rooms.add(newRoom);
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

    private static void linkRoom(Grid<Room> roomsGrid, GridCell<Room> newRoom) {
        for(Entry<Directions, GridCell<Room>> entry: roomsGrid.getAdjacentCells(newRoom).entrySet()) {
            if(entry.getValue() != null && entry.getValue().getContent() != null) {
                if(entry.getValue().getContent().getRoomPossibleDirections().contains(entry.getKey().opposite())) {
                    linkRooms(newRoom,entry, entry.getKey().opposite());
                }
            }
        }
    }

    private static ArrayList<GridCell<Room>> getAvailableSpots(ArrayList<GridCell<Room>> rooms, Grid<Room> roomsGrid) {
        ArrayList<GridCell<Room>> availableSpots = new ArrayList<GridCell<Room>>();
        rooms.forEach(room->{
            room.getContent().getAvailableDirections().forEach(direction -> {
                for(Entry<Directions, GridCell<Room>> entry: roomsGrid.getAdjacentCells(room).entrySet()) {
                    if(entry.getValue() != null && entry.getValue().getContent() == null && entry.getKey() == direction) {
                        availableSpots.add(entry.getValue());
                    }
                }
            });
        });
        System.out.println("Available Spots : \n[");
        availableSpots.forEach(spot -> {
            System.out.println("x: " + spot.getCoords()[0] + " ; y: " +  spot.getCoords()[1] + " : " + spot.getContent());
        });
        System.out.println("]");
        return availableSpots;
    }
}
