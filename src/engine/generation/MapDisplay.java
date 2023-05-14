package engine.generation;

import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Map.Entry;

import javax.swing.JPanel;

import engine.tiles.Directions;
import engine.tiles.GridCell;

public class MapDisplay extends JPanel {
    Map map;
    public MapDisplay(Map map) {
        super();
        this.map = map;
        this.setSize(new Dimension(250,250));
    }
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        for(GridCell<Room> roomCell: map.rooms) {
            if(roomCell.getContent() != null) {
                int x = 100+ roomCell.getCoords()[0]*80;
                int y = 100+ roomCell.getCoords()[1]*80;
                g.drawRect(
                    x,
                    y,
                    50,
                    50
                );
                g.drawString(
                    "" +roomCell.getContent().getId(),
                    x +25,
                    y +25
                );
                for(Entry<Directions, Room> nRoom: roomCell.getContent().getNeighbors().entrySet()) {
                    if(nRoom.getValue() != null)
                        switch (nRoom.getKey()) {
                            case DOWN:
                                g.drawLine(x+25, y+50, x+25, y+50+ 30);
                                break;
                            case UP:
                                g.drawLine(x+25, y, x+25, y - 30);
                                break;
                            case LEFT:
                                g.drawLine(x, y+25, x - 30, y+25);
                                break;
                            case RIGHT:
                                g.drawLine(x+50, y+25, x + 50 + 30, y+25);
                                break;
                        }
                }
            }
        }
    }
}
