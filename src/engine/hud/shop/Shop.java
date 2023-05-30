package engine.hud.shop;

import java.awt.Graphics;

import engine.generation.Map;
import engine.hud.Hud;
import engine.physics.Player;
import engine.view.Coords;
import engine.view.Display;
/** Shop class */
public class Shop extends Hud{
	/** Constructs a shop 
	 * @param display
	 * @param player
	 * @param x
     * @param y
     * @param width
     * @param height
	*/
	public Shop(Display display, Player player, int x, int y, int width, int height, Map map) {
		super(display, 0,x, y, width, height);
		Coords origin = new Coords(x,y);
		try{
			addElement(new ShopBackground(origin, 0, 0, width, height,map));
			addElement(new AttackDisplay(origin, player, 10, 260, width - 50, 50));
			addElement(new AddAttackButton(origin, player, 50 + 10 * (10+ (((width - 50)/2) / 10)), 255,  50, 50));
			addElement(new DefenceDisplay(origin, player, 10, 200, width - 50, 50));
			addElement(new AddDefenceButton(origin, player, 50 + 10 * (10+ (((width - 50)/2) / 10)), 195,  50, 50));
			addElement(new HealthDisplay(origin, player, 10, 140, width - 50, 50));
			addElement(new AddHealthButton(origin, player, 50 + 10* (10+ (((width - 50)/2) / 10)), 135,  50, 50));
			addElement(new HealButton(origin, player, 25, 50,  64, 64));
			addElement(new HealLabel(origin,player, 30, 10,  64, 32));
			addElement(new CloseButton(origin, this, width/2-32, 30,  64, 50));
		}
		catch(Exception e) {}
	}
	/** Draws the shop 
	 * @param g
	*/
    public void draw(Graphics g) {
        super.draw(g);
        int topInset = getDisplay().getFrame().getInsets().top;
        this.setOffsetY(topInset);
    }
}
