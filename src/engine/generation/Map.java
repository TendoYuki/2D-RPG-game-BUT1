package engine.generation;

import java.util.HashMap;

import engine.tiles.Directions;
import engine.view.Scene;

import java.awt.Graphics;

public class Map{
    HashMap<Integer, Room> rooms = new HashMap<Integer, Room>();
    Room activeRoom;

    private int posX = 0;
    private int posY = 0; 


    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public Map(int posX, int posY) { 
        this.posX = posX;
        this.posY = posY;
    }

    public Map() { 
        this(0,0);
    }
    
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
        activeRoom.setPosX(posX);
        activeRoom.setPosY(posY);
        activeRoom.draw(g);
    }
    
    public int size(){
        return activeRoom.size();
    }
}
