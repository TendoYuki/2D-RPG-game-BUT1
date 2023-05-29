/* ========================================================== */
/*                  Bibliotheque MoteurDeJeu                  */
/* --------------------------------------------               */
/* Bibliotheque pour aider la création de jeu video comme :   */
/* - Jeux de role                                             */
/* - Jeux de plateforme                                       */
/* - Jeux de combat                                           */
/* - Jeux de course                                           */
/* - Ancien jeu d'arcade (Pac-Man, Space Invider, Snake, ...) */
/* ========================================================== */

package engine.view;

import java.awt.Graphics;
import java.io.IOException;
import java.util.HashMap;

import engine.physics.Player;
import engine.tiles.Atlas;

/** PlayerSprites class */
public class PlayerSprites extends Sprites {
	/** The player */
	Player player;
	/** The sprite path */
	String imageFile = "assets/char/February.png";
	/** The atlas */
	Atlas spriteAtlas;
	/**
	 * Constructs a player sprite
	 * @param b
	 * @throws IOException
	 */
	public PlayerSprites(Player b) throws IOException {
		this.player = b;
		spriteAtlas = new Atlas(imageFile, 16, 8, 15, 2);
		activity = "down";
		sprites = new HashMap<String, Sprite>();
		sprites.put("fixe", spriteAtlas.get(73));
		sprites.put("fixe0", spriteAtlas.get(73));
		sprites.put("fixe1", spriteAtlas.get(73));
		sprites.put("fixe2", spriteAtlas.get(73));

		sprites.put("right0", spriteAtlas.get(103));
		sprites.put("right1", spriteAtlas.get(104));
		sprites.put("right2", spriteAtlas.get(105));

		sprites.put("left0", spriteAtlas.get(88));
		sprites.put("left1", spriteAtlas.get(89));
		sprites.put("left2", spriteAtlas.get(90));

		sprites.put("down0", spriteAtlas.get(73));
		sprites.put("down1", spriteAtlas.get(74));
		sprites.put("down2", spriteAtlas.get(75));
		
		sprites.put("down-right0", spriteAtlas.get(73));
		sprites.put("down-right1", spriteAtlas.get(74));
		sprites.put("down-right2", spriteAtlas.get(75));
		
		sprites.put("down-left0", spriteAtlas.get(73));
		sprites.put("down-left1", spriteAtlas.get(74));
		sprites.put("down-left2", spriteAtlas.get(75));
		
		sprites.put("up0", spriteAtlas.get(118));
		sprites.put("up1", spriteAtlas.get(119));
		sprites.put("up2", spriteAtlas.get(120));

		sprites.put("up-right0", spriteAtlas.get(118));
		sprites.put("up-right1", spriteAtlas.get(119));
		sprites.put("up-right2", spriteAtlas.get(120));

		sprites.put("up-left0", spriteAtlas.get(118));
		sprites.put("up-left1", spriteAtlas.get(119));
		sprites.put("up-left2", spriteAtlas.get(120));
		// sprites.put("fixe", spriteAtlas.get(10));
		// sprites.put("fixe0", spriteAtlas.get(10));
		// sprites.put("fixe1", spriteAtlas.get(10));
		// sprites.put("fixe2", spriteAtlas.get(10));

		// sprites.put("right0", spriteAtlas.get(40));
		// sprites.put("right1", spriteAtlas.get(41));
		// sprites.put("right2", spriteAtlas.get(42));

		// sprites.put("left0", spriteAtlas.get(25));
		// sprites.put("left1", spriteAtlas.get(26));
		// sprites.put("left2", spriteAtlas.get(27));

		// sprites.put("down0", spriteAtlas.get(10));
		// sprites.put("down1", spriteAtlas.get(11));
		// sprites.put("down2", spriteAtlas.get(12));
		
		// sprites.put("down-right0", spriteAtlas.get(10));
		// sprites.put("down-right1", spriteAtlas.get(11));
		// sprites.put("down-right2", spriteAtlas.get(12));
		
		// sprites.put("down-left0", spriteAtlas.get(10));
		// sprites.put("down-left1", spriteAtlas.get(11));
		// sprites.put("down-left2", spriteAtlas.get(12));
		
		// sprites.put("up0", spriteAtlas.get(55));
		// sprites.put("up1", spriteAtlas.get(56));
		// sprites.put("up2", spriteAtlas.get(57));

		// sprites.put("up-right0", spriteAtlas.get(55));
		// sprites.put("up-right1", spriteAtlas.get(56));
		// sprites.put("up-right2", spriteAtlas.get(57));

		// sprites.put("up-left0", spriteAtlas.get(25));
		// sprites.put("up-left1", spriteAtlas.get(26));
		// sprites.put("up-left2", spriteAtlas.get(27));
	}

	/** Draws the player sprite 
	 * @param x
	 * @param y
	 * @param g
	*/
	public void draw(int x, int y, Graphics g) {
		Sprite s = sprites.get("fixe");
		if (s == null)
			s = sprites.get("erreur");
		s = sprites.get(chain());
		s.draw(g, x, y);
	}

	/**
	 *
	 */
	@Override
	public void animate() {
		iteration++;

		int itCount = 2;
		if(player.vx != 0 || player.vy !=0) {
			if (iteration > itCount) {
				num++;
				iteration = 0;
			}
	
			if (num > itCount)
				num = 0;
		} else {
			num = 0;
			iteration = 0;
		}
	}
}
