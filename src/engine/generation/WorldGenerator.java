package engine.generation;

import java.util.ArrayList;
import java.util.Map.Entry;

import engine.tiles.Directions;
import engine.tiles.Grid;

public class WorldGenerator {

    public WorldGenerator() {}

    public static void GenerateWorld(Room startRoom, Room endRoom,int totalRoomCount, int countX, int countY) {
        ArrayList<Room> rooms = new ArrayList<Room>();
        Grid<Room> roomsGrid = new Grid<Room>(countX, countY);
        startRoom.setRoomPossibleDirections(new Directions[] {Directions.UP});
        rooms.add(startRoom);
        roomsGrid.setCell(countX/2, countY/2, startRoom);

        for(int i = 0; i < totalRoomCount; i++) {
            rooms.forEach(room->{
                for(Entry<Directions, Room> entry: roomsGrid.getAdjacentCells(room).entrySet()) {
                    if(entry.getValue() != null) {
                        entry.getValue().getAvailableDirections();
                    }
                }
            });
        }
    }
}
