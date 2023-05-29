package engine.generation;


import engine.tiles.Directions;
import engine.tiles.Grid;
import engine.tiles.GridCell;

import java.awt.Graphics;

/** Map class */
public class Map{
    /** Grid containing all existing rooms */
    public Grid<Room> rooms;
    /** The Active room */
    public Room activeRoom;
    /** The last room */
    public Room endRoom;
    /** The first room */
    public Room startRoom;
    /** Position x of the map */
    private int posX = 0;
    /** Position y of the map */
    private int posY = 0; 
    /** Returns the number of enemies in the map 
     * @return
    */
    public int enemiesCount() {
        int ct = 0;
        for(GridCell<Room> roomCell: rooms) {
            if(!roomCell.isEmpty()) {
                ct += roomCell.getContent().enemies.size();
            }
        }
        return ct;
    }
    /** Returns the position x of the map 
     * @return
    */
    public int getPosX() {
        return posX;
    }
    /** Updates the position x of the map 
     * @param posX
    */
    public void setPosX(int posX) {
        this.posX = posX;
    }
    /** Returns the position y of the map 
     * @return
    */
    public int getPosY() {
        return posY;
    }
    /** Updates the position y of the map 
     * @param posY
    */
    public void setPosY(int posY) {
        this.posY = posY;
    }
    /** Constructs the map 
     * @param posX
     * @param posY
     * @param xCount
     * @param yCount
    */
    public Map(int posX, int posY, int xCount, int yCount) { 
        this.posX = posX;
        this.posY = posY;
        rooms = new Grid<Room>(xCount, yCount);
    }
    /** Adds the given room to the grid 
     * @param room
     * @param x
     * @param y
    */
    public void addRoom(Room room, int x, int y) {
        rooms.setCell(x, y, room);
    }   
    /** Returns the active room 
     * @return
    */
    public Room getActiveRoom() {
        return activeRoom;
    }
    /** Returns the room having the given id 
     * @param id
     * @return
    */
    public Room getRoom(int id) {
        for(GridCell<Room> cell: rooms) {
            if(cell.getContent() != null && id == cell.getContent().getId()) return cell.getContent();
        }
        return null;
    }   
    /** Returns the room in the given direction from the active room 
     * @param dir
     * @return
    */
    public Room getAdjacentRoom(Directions dir) {
        return activeRoom.getNeighbors().get(dir);
    }
    /** Returns the upper room  from the active room 
     * @return
    */
    public Room getUpRoom() {
        return activeRoom.getNeighbors().get(Directions.UP);
    }
    /** Returns the room beneath the active room 
     * @return
    */
    public Room getDownRoom() {
        return activeRoom.getNeighbors().get(Directions.DOWN);
    }
    /** Returns the room to the left of the active room 
     * @return
    */
    public Room getLeftRoom() {
        return activeRoom.getNeighbors().get(Directions.LEFT);
    }
    /** Returns the room to the right of the active room 
     * @return
    */
    public Room getRightRoom() {
        return activeRoom.getNeighbors().get(Directions.RIGHT);
    }
    /** Updates the active room 
     * @param id
    */
    public void setActiveRoom(int id) {
        activeRoom = getRoom(id);
    }
    /** Draws the active room
     * @param g
     */
    public void draw(Graphics g) {
        activeRoom.setPosX(posX);
        activeRoom.setPosY(posY);
        activeRoom.draw(g);
    }
    /** Returns the size of the active room
     * @return
     */
    public int size(){
        return activeRoom.size();
    }
}

