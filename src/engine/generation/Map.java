package engine.generation;

import java.util.HashMap;
import java.util.Map.Entry;

import javax.swing.JFrame;
import javax.swing.JPanel;

import engine.tiles.Directions;
import engine.tiles.Grid;
import engine.tiles.GridCell;
import engine.view.Scene;

import java.awt.Dimension;
import java.awt.Graphics;

public class Map{
    public Grid<Room> rooms;
    
    public Room activeRoom;

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

    public Map(int posX, int posY, int xCount, int yCount) { 
        this.posX = posX;
        this.posY = posY;
        rooms = new Grid<Room>(xCount, yCount);
    }
    
    public void addRoom(Room room, int x, int y) {
        rooms.setCell(x, y, room);
    }   

    
    public Room getActiveRoom() {
        return activeRoom;
    }

    public Room getRoom(int id) {
        for(GridCell<Room> cell: rooms) {
            if(cell.getContent() != null && id == cell.getContent().getId()) return cell.getContent();
        }
        return null;
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
        activeRoom = getRoom(id);
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

