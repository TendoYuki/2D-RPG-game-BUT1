package engine.hud.menu;

import java.awt.Graphics;

import engine.hud.Hud;
import engine.hud.player.PlayerHud;
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
        int topInset = getTopInset();
		try{
			menuBackground = new MenuBackground(x, y+topInset, width, height);
			startButton = new StartButton(this, width/2 - 32, height/2 +50, 64,50 );
			quitButton = new QuitButton(width/2 - 32, height/2 + 125, 64, 50);

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
	/** Draws the menu 
	 * @param g
	*/
    public void draw(Graphics g) {
        super.draw(g);
        int topInset = getTopInset();
        this.setOffsetY(topInset);
		menuBackground.setY(getY() + topInset);
		startButton.setY(getHeight()/2 +50);
		quitButton.setY(getHeight()/2 + 125);
    }
	
}
