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

package engine.physique;

import java.awt.Color;
import java.awt.Graphics;

import engine.afficheur.CoordinateSystem;

//un objet de type mur

/**
 *
 * @author Pierre-Frederic Villard
 */
public class Wall extends Object {

	/**
	 *
	 */
	public Wall() {
		// taille de mur diff�rente
		height = 50;
		width = 50;
		px = 100;
		py = 20;
		sauveAnterieur();
	}

	/**
	 *
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 */
	public Wall(int x, int y, int w, int h) {
		// taille de mur diff�rente
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
		g.setColor(new Color(0,0,0,0));
		int[] tab = CoordinateSystem.changeCS(this);
		g.fillRect(tab[0], tab[1], tab[2], tab[3]);
	}

}
