package engine.view;

import java.awt.Graphics;
import engine.tiles.TileMap;

public class Scene {

	private TileMap tileMap;

	public Scene(TileMap tileMap) {
		this.tileMap = tileMap;
	}

	public Scene() { 
		this(null);
	}

	public void setTileMap(TileMap tileMap) {
		this.tileMap = tileMap;
	}

	public int size() {
		if(tileMap != null)
			return tileMap.size();
		else return 0;
	}

	public void draw(Graphics g) {
		if(tileMap != null) tileMap.draw(g);
	}

	public TileMap getTileMap() {
		return tileMap;
	}

}
