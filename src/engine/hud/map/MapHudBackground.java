package engine.hud.map;

import java.awt.Color;
import java.awt.Graphics;
import java.lang.reflect.WildcardType;

import engine.generation.Map;
import engine.hud.HudElement;
import engine.generation.Room;
import java.util.Map.Entry;
import java.awt.FontMetrics;
import engine.tiles.Directions;
import engine.tiles.GridCell;

public class MapHudBackground extends HudElement{

	private Map map;

    public MapHudBackground(Map map, int x, int y, int width, int height) {
        super(x, y, width, height);
        this.map = map;
    }

    @Override
    public void draw(Graphics g) {
        Color c = g.getColor();
        g.setColor(new Color(100, 100, 100, 150));
        
        g.fillRect(getY()  , getX() , getWidth(), getHeight());
        g.setColor(c);


		int roomsize = getWidth() / (map.rooms.getxCount()*2);
        int interRoomOffset = roomsize;
        for(GridCell<Room> roomCell: map.rooms) {
            if(roomCell.getContent() != null) {
                int x = getX() +10 + roomCell.getCoords()[0]*(roomsize + interRoomOffset) + 10;
                int y = getY() +10 + roomCell.getCoords()[1]*(roomsize + interRoomOffset) + 10;
                if(map.activeRoom.equals(roomCell.getContent()))
                    g.fillOval(x+roomsize/4,y+roomsize/4,roomsize/2,roomsize/2);
                g.drawRect(
                    x,
                    y,
                    roomsize,
                    roomsize
                );
                
                
                String str = "" +roomCell.getContent().getId();
                FontMetrics metrics = g.getFontMetrics(g.getFont());
                int xStr = x + (roomsize - metrics.stringWidth(str)) / 2;
                int yStr = y + ((roomsize - metrics.getHeight()) / 2) + metrics.getAscent();
                g.drawString(
                    str,
                    xStr,
                    yStr
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

    @Override
    public void onClick() {}
    
}
