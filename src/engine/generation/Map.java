package engine.generation;

import java.util.HashMap;

import engine.tiles.Directions;
import engine.view.Scene;

import java.awt.Graphics;

public class Map{
    HashMap<Integer, Room> rooms = new HashMap<Integer, Room>();
    Room activeRoom;

    public Map() { }
    
    public void addRoom(Room room) {
        rooms.put(room.getId(), room);
    }   

    
    public Room getActiveRoom() {
        return activeRoom;
    }

    public Room getRoom(int id) {
        return rooms.get(id);
    }   

    public Room getAdjacentRoom(Directions dir) {
        return activeRoom.getNeighbors().get(dir);
    }

    public Room getUpRoom() {
        return activeRoom.getNeighbors().get(Directions.UP);
    }

    public Room getDownRoom() {
        return activeRoom.getNeighbors().get(Directions.DOWN);
    }

    public Room getLeftRoom() {
        return activeRoom.getNeighbors().get(Directions.LEFT);
    }

    public Room getRightRoom() {
        return activeRoom.getNeighbors().get(Directions.RIGHT);
    }
    
    public void setActiveRoom(int id) {
        activeRoom = rooms.get(id);
        
    }

    public void draw(Graphics g) {
        activeRoom.draw(g);
    }
    
    public int size(){
        return activeRoom.size();
    }
}
