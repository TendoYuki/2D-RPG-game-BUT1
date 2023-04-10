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

package engine.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

//permet de faire un controleur de clavier

/**
 *
 * @author Pierre-Frederic Villard
 */
public class KeyboardController implements KeyListener {

	// fin du jeu

	/**
	 *
	 */
	public static boolean fin = false;

	// afficheur
	boolean affiche = false;

	/** the player can move or not */
	public static boolean canMove = true;

	// la variable de controle

	/**
	 *
	 */
	public Control c;

	// constructeur avec affichage du controleur ou non.

	/**
	 *
	 * @param affiche
	 */
	public KeyboardController(boolean affiche) {
		c = new Control();
		this.affiche = affiche;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// vide
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(canMove){
			// touche gauche
			if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				c.gauche = true;
			}
			// touche droite
			if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				c.droite = true;
			}
			// touche up
			if (e.getKeyCode() == KeyEvent.VK_UP) {
				c.haut = true;
			}
			// touche down
			if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				c.bas = true;
			}
			// touche up
			if (e.getKeyCode() == KeyEvent.VK_Q) {
				fin = true;
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// touche gauche
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			c.gauche = false;
		}
		// touche droite
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			c.droite = false;
		}
		// touche up
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			c.haut = false;
		}
		// touche down
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			c.bas = false;
		}

	}

}
