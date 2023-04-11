package engine.generation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import engine.tiles.Directions;
import engine.tiles.GridCell;
import engine.view.Scene;

public class Room extends Scene{
    private ArrayList<Directions> roomPossibleDirections = new ArrayList<Directions>();

    private HashMap<Directions,Room> neighboringRooms = new HashMap<Directions,Room>();

    private static int count = 0;

    private int id;

    public Room(ArrayList<Directions> freeDirections) {
        id = count++;
        setRoomPossibleDirections(freeDirections);
    }

    public Room(Directions[] freeDirections) {
        id = count++;
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
            if(roomPossibleDirections.contains(dir)) {
                neighboringRooms.put(dir, null);
            }
        }
    }

    public void setRoomPossibleDirections(ArrayList<Directions> freeDirections) {
        this.roomPossibleDirections = freeDirections;
        updateNeighboringRooms();
    }

    public void setRoomPossibleDirections(Directions[] freeDirections) {
        roomPossibleDirections = new ArrayList<Directions>();
        for(Directions dir: freeDirections) 
            roomPossibleDirections.add(dir);
        updateNeighboringRooms();
    }

    public HashMap<Directions,Room> getNeighbors() {
        return neighboringRooms;
    }

    public int getId(){
        return id;
    }

    public String toString() {
        String ret = "";
        ret += id + " : [  ";
        for(Entry<Directions, Room> entry: neighboringRooms.entrySet()) {
            if(entry.getValue() != null)
                ret+= entry.getKey() + ":" + entry.getValue().id + " ";
        }
        ret += " ]";
        return ret;
    }
}
