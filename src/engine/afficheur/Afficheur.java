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

import java.awt.*;
import java.awt.image.BufferStrategy;

import javax.swing.*;

import engine.hud.TestHud;
import engine.hud.player.PlayerHud;
import engine.hud.shop.Shop;
import engine.physique.*;
import engine.tiles.Atlas;
import engine.tiles.TileMap;

//permet d'affiche des objets

/**
 *
 * @author Pierre-Frederic Villard
 */
public class Afficheur extends JPanel {

	// le monde a affcher
	public Monde m;

	// l'afficheur de Decor
	public DecorFixe decor;

	// double buffering
	public BufferStrategy bs;
	PlayerHud hud;
	Shop shop;

	// creation d'un afficheur

	/**
	 *
	 * @param monde
	 */
	public Afficheur(Monde monde) {
		JFrame f = new JFrame();

		Atlas atlas = new Atlas(
            "assets/tiles/tilemap.png",
            16,
            1,
            4,
			2
        );
        TileMap tileMap = new TileMap(
            16,
            2,
            new int[][] {
                {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                {1,2,2,2,2,2,2,2,2,2,2,2,2,2,2,1},
                {1,2,3,3,3,3,3,3,3,3,3,3,3,3,2,1},
                {1,2,3,3,3,3,3,3,3,3,3,3,3,3,2,1},
                {1,2,3,3,3,3,3,3,3,3,3,3,3,3,2,1},
                {1,2,3,3,3,4,4,4,4,4,4,3,3,3,2,1},
                {1,2,3,3,3,4,4,4,4,4,4,3,3,3,2,1},
                {1,2,3,3,3,4,4,4,4,4,4,3,3,3,2,1},
                {1,2,3,3,3,4,4,4,4,4,4,3,3,3,2,1},
                {1,2,3,3,3,4,4,4,4,4,4,3,3,3,2,1},
                {1,2,3,3,3,4,4,4,4,4,4,3,3,3,2,1},
                {1,2,3,3,3,3,3,3,3,3,3,3,3,3,2,1},
                {1,2,3,3,3,3,3,3,3,3,3,3,3,3,2,1},
                {1,2,3,3,3,3,3,3,3,3,3,3,3,3,2,1},
                {1,2,2,2,2,2,2,2,2,2,2,2,2,2,2,1},
                {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
            },
            atlas
        );

		decor = new DecorFixe(tileMap);

		setPreferredSize(new Dimension(decor.size(),decor.size()));

		
		// setPreferredSize(new Dimension(800, 800));
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setContentPane(this);
		f.pack();
		f.setVisible(true);

		// ajouter pour eviter les repaint
		f.setIgnoreRepaint(true);

		// double buffering
		f.createBufferStrategy(2);
		bs = f.getBufferStrategy();
		this.setIgnoreRepaint(true);

		this.m = monde;
		shop = new Shop(monde.heros.get(0), f.getWidth()/2 - (int)(512/1.4)/2, f.getHeight()/2 - (int)(512/1.4)/2, (int)(512/1.4), (int)(512/1.4));
        shop.setIsShown(false);
        shop.setInteractable(false);
		hud = new PlayerHud(monde.heros.get(0),shop);
		this.addMouseListener(hud.getControleSouris());
		this.addMouseListener(shop.getControleSouris());
	}

	// permet de faire un afficheg

	// Renvoie le decor
	public DecorFixe getDecor() {
		return decor;
	}

	/**
	 *
	 */
	public void render() {
		Graphics2D g = (Graphics2D) bs.getDrawGraphics();

		g.setColor(Color.black);

		// Affiche le decor
		decor.affiche(g);

		// affiche les objets
		for (Objet obj : m.objets) {
			obj.draw(g);
		}

		// affiche les monstres
		for (Monstre monstre : m.monstres) {
			monstre.draw(g);
		}

		// affiche la balle
		Heros b = m.balle;
		b.draw(g);

		hud.draw(g);
		shop.draw(g);
		bs.show();
		Toolkit.getDefaultToolkit().sync();
		g.clearRect(0, 0, decor.size(), decor.size());
		g.dispose();

	}

}
