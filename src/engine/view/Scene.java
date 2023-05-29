package engine.view;

import java.awt.Graphics;
import engine.tiles.TileMap;
/** Scene class */
public class Scene {
	/** The tilemap */
	private TileMap tileMap;
	/** The position x of the scene*/
    private int posX = 0;
	/** The position y of the scene*/
    private int posY = 0; 
	/** Returns the position x 
	 * @return
	*/
    public int getPosX() {
        return posX;
    }
	/** Updates the position x 
	 * @param posX
	*/
    public void setPosX(int posX) {
        this.posX = posX;
		tileMap.setPosX(posX);
    }
	/** Returns the position y 
	 * @return
	*/
    public int 	getPosY() {
        return posY;
    }
	/** Updates the position y 
	 * @param posY
	 */
    public void setPosY(int posY) {
        this.posY = posY;
		tileMap.setPosY(posY);
    }
	/** Constructs a scene 
	 * @param tileMap
	*/
	public Scene(TileMap tileMap) {
		this.tileMap = tileMap;
	}
	/** Constructs an empty scene */
	public Scene() { 
		this(null);
	}
	/** Updates the tilemap 
	 * @param tileMap
	*/
	public void setTileMap(TileMap tileMap) {
		this.tileMap = tileMap;
	}
	/** Return the size of the tilemap 
	 * @return
	*/
	public int size() {
		if(tileMap != null)
			return tileMap.size();
		else return 0;
	}
	/** Draws the scene 
	 * @param g
	*/
	public void draw(Graphics g) {
		if(tileMap != null) tileMap.draw(g);
	}
	/** Returns the tilemap 
	 * @return
	*/
	public TileMap getTileMap() {
		return tileMap;
	}

}
