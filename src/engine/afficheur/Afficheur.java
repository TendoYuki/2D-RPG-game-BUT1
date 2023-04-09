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
            4
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
		for (ObjetMonstre monstre : m.monstres) {
			monstre.draw(g);
		}

		// affiche la balle
		ObjetHeros b = m.balle;
		b.draw(g);

		bs.show();
		Toolkit.getDefaultToolkit().sync();
		g.clearRect(0, 0, decor.size(), decor.size());
		g.dispose();

	}

}
