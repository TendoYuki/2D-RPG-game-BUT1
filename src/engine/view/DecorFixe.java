package engine.view;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

import engine.tiles.Atlas;
import engine.tiles.TileMap;

//distributeur de sprites
public class DecorFixe {

	// IMage
	BufferedImage im;

	Atlas atlas;

	TileMap tileMap;

	private int spriteSize = 16;
	private int spriteScale = 2;

	// taille
	int wx, wy;

	// construit le sprite
	public DecorFixe(TileMap tileMap) {
		this.tileMap = tileMap;
	}

	public void changeImage(String image) {
		try {
			im = ImageIO.read(new File(image));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("erreur lecture background");
		}
		wx = im.getWidth();
		wy = im.getHeight();
	}

	public int size() {
		return tileMap.size();
	}

	// afficheur de sprite
	public void affiche(Graphics g) {
		tileMap.draw(g);
		// g.drawImage(im, 0, 0, wx, wy, 0, 0, wx, wy, null);
	}

}
