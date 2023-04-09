package engine.hud.shop;

import engine.hud.Hud;
import engine.physique.Heros;

public class Shop extends Hud{
	public Shop(Heros player, int x, int y, int width, int height) {
		super();
		try{
			addElement(new ShopBackground(x, y, width, height));
			addElement(new AttackDisplay(player, x, y + 50, width - 50, 50));
			addElement(new AddAttackButton(player, x + width - 165, y + 55,  50, 50));
			addElement(new DefenceDisplay(player, x, y + 120, width - 50, 50));
			addElement(new AddDefenceButton(player, x + width - 165, y + 125,  50, 50));
			addElement(new HealthDisplay(player, x, y + 190, width - 50, 50));
			addElement(new AddHealthButton(player, x + width - 165, y + 195,  50, 50));
		}
		catch(Exception e) {}
	}
}
