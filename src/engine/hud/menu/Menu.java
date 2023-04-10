package engine.hud.menu;

import engine.hud.Hud;

public class Menu extends Hud{
	public Menu(int x, int y, int width, int height) {
		super();
		try{
			addElement(new MenuBackground(x, y, width, height));
            addElement(new StartButton(this, width/2 - 32, height/2 - 25, 64,50 ));
            addElement(new QuitButton(width/2 - 32, height/2 - 100, 64, 50));
		}
		catch(Exception e) {}
	}
}
