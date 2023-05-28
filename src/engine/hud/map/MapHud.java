package engine.hud.map;

import java.awt.Graphics;
import engine.generation.Map;
import engine.hud.Hud;
import engine.view.Display;

public class MapHud extends Hud{


	public MapHud(Display display, Map map, int x, int y, int width, int height) {
		super(display, 0,x, y, width, height);
		try{
			addElement(new MapHudBackground(map, x, y, width, height));
		}
		catch(Exception e) {}
	}

    public void draw(Graphics g) {
        super.draw(g);
        int topInset = getDisplay().getFrame().getInsets().top;
        this.setOffsetY(topInset);
    }
}
