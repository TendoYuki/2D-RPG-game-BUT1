package engine.generation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import engine.tiles.Directions;
import engine.view.Scene;

public class Room extends Scene{
    private ArrayList<Directions> roomPossibleDirections = new ArrayList<Directions>();

    private HashMap<Directions,Room> neighboringRooms = new HashMap<Directions,Room>();

    public Room(ArrayList<Directions> freeDirections) {
        setRoomPossibleDirections(freeDirections);
    }

    public Room(Directions[] freeDirections) {
        setRoomPossibleDirections(freeDirections);
    }

    public List<Directions> getAvailableDirections() {
        ArrayList<Directions> availableDirections = new ArrayList<Directions>();

        for(Directions dir: Directions.values()) {
            if(neighboringRooms.get(dir) == null && roomPossibleDirections.contains(dir))
                availableDirections.add(dir);
        }
        return availableDirections;
    }

    public void addNeighbor(Directions direction, Room room) {
        neighboringRooms.put(direction, room);
    }

    public List<Directions> getRoomPossibleDirections() {
        return roomPossibleDirections;
    }

    private void updateNeighboringRooms() {
        for(Directions dir: Directions.values()) {
            if(!roomPossibleDirections.contains(dir)) {
                neighboringRooms.put(dir, null);
            }
        }
    }

    public void setRoomPossibleDirections(ArrayList<Directions> freeDirections) {
        this.roomPossibleDirections = freeDirections;
        updateNeighboringRooms();
    }

    public void setRoomPossibleDirections(Directions[] freeDirections) {
        for(Directions dir: freeDirections) 
            this.roomPossibleDirections.add(dir);
        updateNeighboringRooms();
    }

    public HashMap<Directions,Room> getNeighbors() {
        return neighboringRooms;
    }
}
