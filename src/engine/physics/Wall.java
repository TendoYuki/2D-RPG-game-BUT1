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

package engine.physics;

import java.awt.Color;
import java.awt.Graphics;

import engine.view.CoordinateSystem;

//un objet de type mur

/**
 *
 * @author Pierre-Frederic Villard
 */
public class Wall extends PhysicalObject {



	/**
	 *
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 */
	public Wall(World world, int x, int y, int w, int h) {
		super(world);
		height = h;
		width = w;
		px = x;
		py = y;
	}

	/**
	 *
	 * @param g
	 */
	public void draw(Graphics g) {
		g.setColor(new Color(255,0,0,255));
		int[] tab = CoordinateSystem.changeCS(this, getWorld().map.getPosX(), getWorld().map.getPosY());
		g.fillRect(tab[0], tab[1], tab[2], tab[3]);
		// g.fillRect((int)px, (int)py, (int)width, (int)height);
	}

}
