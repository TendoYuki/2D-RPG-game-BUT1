package engine.generation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import engine.tiles.Directions;
import engine.tiles.GridCell;
import engine.view.Scene;

public class Room extends Scene{
    /** Directions in which the room can have neighbors */
    private ArrayList<Directions> roomConstraints = new ArrayList<Directions>();

    /** Rooms that are neighbors of the room */
    private HashMap<Directions,Room> neighboringRooms = new HashMap<Directions,Room>();

    /** Count of all existing rooms */
    private static int count = 0;

    /** Id of the room */
    private int id;    

    /** Creates a new unique room
     * @param freeDirections Directions in which the room can have neighbors
     */
    public Room(ArrayList<Directions> freeDirections) {
        id = count++;
        setRoomPossibleDirections(freeDirections);
    }

    /** Creates a new unique room
     * @param freeDirections Directions in which the room can have neighbors
     */
    public Room(Directions[] freeDirections) {
        id = count++;
        setRoomPossibleDirections(freeDirections);
    }

    /**
     * @return Returns all directions which are not occupied by a room
     */
    public List<Directions> getAvailableDirections() {
        ArrayList<Directions> availableDirections = new ArrayList<Directions>();

        for(Directions dir: Directions.values())
            if (
                roomConstraints.contains(dir) &&
                neighboringRooms.get(dir) == null
            ) availableDirections.add(dir);

        return availableDirections;
    }

    /**
     * Adds a new neighbor to the room
     * @param direction Direction of the new neighbor
     * @param room New neighbor
     */
    private void addNeighbor(Directions direction, Room room) {
        neighboringRooms.put(direction, room);
    }

    /**
     * @return List of all the possible directions of the room
     */
    public List<Directions> getRoomConstraints() {
        return roomConstraints;
    }

    private void updateNeighboringRooms() {
        for(Directions dir: Directions.values()) {
            if(roomConstraints.contains(dir)) {
                neighboringRooms.put(dir, null);
            }
        }
    }

    /**
     * Sets the possible directions of the room 
     * @param freeDirections New free directions of the room
     */
    public void setRoomPossibleDirections(List<Directions> freeDirections) {
        this.roomConstraints = new ArrayList<Directions>(freeDirections);
        updateNeighboringRooms();
    }

    /**
     * Sets the possible directions of the room 
     * @param freeDirections New free directions of the room
     */
    public void setRoomPossibleDirections(Directions[] freeDirections) {
        setRoomPossibleDirections(Arrays.asList(freeDirections));
    }

    /**
     * @return Room's neighboring room
     */
    public HashMap<Directions,Room> getNeighbors() {
        return neighboringRooms;
    }

    /**
     * @return Id of the room
     */
    public int getId(){
        return id;
    }

    /**
     * @return String representation of the room
     */
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

    /**
     * Links the room to the target room in the given direction
     * @param target Target room
     * @param dir Direction of the link
     */
    public void linkRoom(Room target, Directions dir) {
        addNeighbor(dir, target);
        target.addNeighbor(dir.opposite(), this);
    }
}
