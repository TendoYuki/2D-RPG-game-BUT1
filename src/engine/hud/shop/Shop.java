package engine.hud.shop;

import engine.hud.Hud;
import engine.physics.Player;

public class Shop extends Hud{
	public Shop(Player player, int x, int y, int width, int height) {
		super();
		try{
			addElement(new ShopBackground(x, y, width, height));
			addElement(new AttackDisplay(player, x + 10, y + 50, width - 50, 50));
			addElement(new AddAttackButton(player, x + 50 + 10 * (10+ (((width - 50)/2) / 10)), y + 55,  50, 50));
			addElement(new DefenceDisplay(player, x + 10, y + 120, width - 50, 50));
			addElement(new AddDefenceButton(player, x + 50 + 10 * (10+ (((width - 50)/2) / 10)), y + 125,  50, 50));
			addElement(new HealthDisplay(player, x + 10, y + 190, width - 50, 50));
			addElement(new AddHealthButton(player, x + 50 + 10* (10+ (((width - 50)/2) / 10)), y + 195,  50, 50));
			addElement(new HealButton(player, x + 25, y + height-110,  64, 64));
			addElement(new HealLabel(x + 30, y + height-20,  64, 50));
			addElement(new CloseButton(this, x + width/2-32, y + height-60,  64, 50));
		}
		catch(Exception e) {}
	}
}
