package engine.hud.menu;

import engine.hud.Hud;
import engine.hud.player.PlayerHud;

public class Menu extends Hud{

	private PlayerHud pHud;

	public Menu(PlayerHud pHud, int x, int y, int width, int height) {
		super();
		this.pHud = pHud;
		try{
			addElement(new MenuBackground(x, y, width, height));
            addElement(new StartButton(this, width/2 - 32, height/2 - 25, 64,50 ));
            addElement(new QuitButton(width/2 - 32, height/2 - 100, 64, 50));
		}
		catch(Exception e) {}
	}

	public PlayerHud getpHud() {
		return pHud;
	}
	
}
