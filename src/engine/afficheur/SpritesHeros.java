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

package engine.afficheur;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

import engine.controle.Controle;
import engine.physique.ObjetHeros;
import engine.tiles.Atlas;


public class SpritesHeros extends Sprites {

	ObjetHeros heros;
	String imageFile = "assets/char/preview.png";
	Atlas spriteAtlas;
	// constructeur de table de sprites

	/**
	 *
	 * @param b
	 * @throws IOException
	 */
	public SpritesHeros(ObjetHeros b) throws IOException {
		this.heros = b;
		spriteAtlas = new Atlas(imageFile, 32, 3, 9, 1);
		activite = "down";
		sprites = new HashMap<String, Sprite>();
		sprites.put("fixe", spriteAtlas.get(1));
		sprites.put("fixe0", spriteAtlas.get(1));
		sprites.put("fixe1", spriteAtlas.get(1));
		sprites.put("fixe2", spriteAtlas.get(1));

		sprites.put("right0", spriteAtlas.get(1));
		sprites.put("right1", spriteAtlas.get(1));
		sprites.put("right2", spriteAtlas.get(1));

		sprites.put("up-right0", spriteAtlas.get(1));
		sprites.put("up-right1", spriteAtlas.get(1));
		sprites.put("up-right2", spriteAtlas.get(1));

		sprites.put("down0", spriteAtlas.get(1));
		sprites.put("down1", spriteAtlas.get(4));
		sprites.put("down2", spriteAtlas.get(7));
		
		sprites.put("down-right0", spriteAtlas.get(1));
		sprites.put("down-right1", spriteAtlas.get(4));
		sprites.put("down-right2", spriteAtlas.get(7));
		
		sprites.put("down-left0", spriteAtlas.get(1));
		sprites.put("down-left1", spriteAtlas.get(4));
		sprites.put("down-left2", spriteAtlas.get(7));
		
		sprites.put("up0", spriteAtlas.get(3));
		sprites.put("up1", spriteAtlas.get(6));
		sprites.put("up2", spriteAtlas.get(9));

		sprites.put("left0", spriteAtlas.get(2));
		sprites.put("left1", spriteAtlas.get(5));
		sprites.put("left2", spriteAtlas.get(8));

		sprites.put("up-left0", spriteAtlas.get(2));
		sprites.put("up-left1", spriteAtlas.get(5));
		sprites.put("up-left2", spriteAtlas.get(8));
	}

	// afficheur de sprite
	public void affiche(int x, int y, Graphics g) {
		Sprite s = sprites.get("fixe");
		if (s == null)
			s = sprites.get("erreur");
		s = sprites.get(chaine());
		// regarde la direction du personnage
		s.draw(g, x, y);
	}

	/**
	 *
	 */
	@Override
	public void anime() {
		iteration++;

		int itCount = 2;
		if(heros.vx != 0 || heros.vy !=0) {
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
