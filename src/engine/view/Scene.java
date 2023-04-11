package engine.view;

import java.awt.Graphics;
import engine.tiles.TileMap;

public class Scene {

	private TileMap tileMap;


    private int posX = 0;
    private int posY = 0; 

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
		tileMap.setPosX(posX);
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
		tileMap.setPosY(posY);
    }

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
