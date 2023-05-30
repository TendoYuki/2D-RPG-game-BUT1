package engine.hud.menu;

import engine.hud.Hud;
import engine.hud.player.PlayerHud;
import engine.view.Coords;
import engine.view.Display;
/** Menu class */
public class Menu extends Hud{
	/** Player hud */
	private PlayerHud pHud;
	/** Background of the menu */
	MenuBackground menuBackground;
	/** Start button */
	StartButton startButton;
	/** Quit button */
	QuitButton quitButton;
	/** Constructs a menu 
	 * @param display
	 * @param pHud
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	*/
	public Menu(Display display, PlayerHud pHud, int x, int y, int width, int height) {
		super(display, x, y, width, height);
		this.pHud = pHud;
		Coords origin = new Coords(x, y);
		try{
			menuBackground = new MenuBackground(origin,x, y, width, height);
			startButton = new StartButton(origin,this, width/2 - 32, 120, 64,50 );
			quitButton = new QuitButton(origin,width/2 - 32, 50, 64, 50);

			addElement(menuBackground);
            addElement(startButton);
            addElement(quitButton);
		}
		catch(Exception e) {}
	}
	/** Returns the player hud 
	 * @return
	*/
	public PlayerHud getpHud() {
		return pHud;
	}
	
}
