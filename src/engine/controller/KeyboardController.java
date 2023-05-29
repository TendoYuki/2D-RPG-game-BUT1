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

/**
 *
 * @author Pierre-Frederic Villard
 */
public class KeyboardController implements KeyListener {

	/**
	 * end of the game
	 */
	public static boolean fin = false;

	// afficheur
	boolean affiche = false;

	/** the player can move or not */
	public static boolean canMove = true;

	// la variable de controle

	/**
	 * The controller
	 */
	public Control c;

	/** opens the map*/
	public static boolean map = false;

	/** Shows the next dialog */
	public static boolean nextDialog = false;

	/** Wheter or not the shop must be closed */
	public static boolean closeShop = true;

	/** 
	 * @param e
	 */
	// constructeur avec affichage du controleur ou non.

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	/**
	 * keyboard controller
	 * @param affiche
	 */
	public KeyboardController(boolean affiche) {
		c = new Control();
		this.affiche = affiche;
	}
	
	
	@Override
	public void keyPressed(KeyEvent e) {
		if(canMove){
			// left key
			if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				c.gauche = true;
			}
			// right key
			if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				c.droite = true;
			}
			// up key
			if (e.getKeyCode() == KeyEvent.VK_UP) {
				c.haut = true;
			}
			// down key
			if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				c.bas = true;
			}
			// Q key
			if (e.getKeyCode() == KeyEvent.VK_Q) {
				fin = true;
			}
			
		}
		// M key
		if (e.getKeyCode() == KeyEvent.VK_M) {
			map = true;
		}
		// F key
		if (e.getKeyCode() == KeyEvent.VK_F){
			nextDialog = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// left key
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			c.gauche = false;
		}
		// right key
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			c.droite = false;
		}
		// up key
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			c.haut = false;
		}
		// down key
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			c.bas = false;
		}
		// M key
		if (e.getKeyCode() == KeyEvent.VK_M) {
			map = false;
		}
		// F key
		if (e.getKeyCode() == KeyEvent.VK_F){
			nextDialog = false;
		}
		// Escape key
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE){
			closeShop = true;
			canMove = true;
		}	
	}

}
