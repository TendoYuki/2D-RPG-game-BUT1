package engine.view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Map.Entry;

import javax.swing.JPanel;

import engine.generation.Map;
import engine.generation.Room;
import engine.tiles.Directions;
import engine.tiles.GridCell;

public class MapDisplay extends JPanel {
    Map map;
    public static MapDisplay instance;
    public MapDisplay(Map map) {
        super();
        this.map = map;
        this.setSize(new Dimension(250,250));
        instance = this;
    }
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        int roomsize = 250 / (map.rooms.getxCount());
        int interRoomOffset = roomsize;
        for(GridCell<Room> roomCell: map.rooms) {
            if(roomCell.getContent() != null) {
                int x = roomCell.getCoords()[0]*(roomsize + interRoomOffset) + 10;
                int y = roomCell.getCoords()[1]*(roomsize + interRoomOffset) + 10;
                if(map.activeRoom.equals(roomCell.getContent()))
                    g.fillOval(x+roomsize/4,y+roomsize/4,roomsize/2,roomsize/2);
                g.drawRect(
                    x,
                    y,
                    roomsize,
                    roomsize
                );
                g.drawString(
                    "" +roomCell.getContent().getId(),
                    x + roomsize/2,
                    y + roomsize/2
                );
                for(Entry<Directions, Room> nRoom: roomCell.getContent().getNeighbors().entrySet()) {
                    if(nRoom.getValue() != null)
                        switch (nRoom.getKey()) {
                            case DOWN:
                                g.drawLine(x+roomsize/2, y+roomsize, x+roomsize/2, y+roomsize + interRoomOffset);
                                break;
                            case UP:
                                g.drawLine(x+roomsize/2, y, x+roomsize/2, y - interRoomOffset);
                                break;
                            case LEFT:
                                g.drawLine(x, y+roomsize/2, x - interRoomOffset, y+roomsize/2);
                                break;
                            case RIGHT:
                                g.drawLine(x+roomsize, y+roomsize/2, x + roomsize + interRoomOffset, y+roomsize/2);
                                break;
                        }
                }
            }
        }
    }
}
