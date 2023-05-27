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

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import engine.controller.Control;
import engine.generation.Map;
import engine.hud.Hud;
import engine.tiles.TileMap;
import engine.trigger.TriggerMap;
import engine.view.CoordinateSystem;

//gere les objets du monde

/**
 *
 * @author Pierre-Frederic Villard
 */
public class World {

    /**
     * le controleur
     */
    public Control c;

    private WorldBorder worldBorder;

    /**
     * les murs
     */
    public ArrayList<PhysicalObject> objects = new ArrayList<PhysicalObject>();

    /**
     * Triggers
     */
    public ArrayList<TriggerMap> triggerMaps = new ArrayList<TriggerMap>();
    
    public HashMap<String, Hud> huds = new HashMap<String, Hud>();

    public TriggerMap worldTrigger;

    /**
     * les heros
     */
    public Player player;

    public Map map;

    /**
     * un monde par defaut
     * 
     * @throws java.io.IOException
     */
    public World() throws IOException {
        player = new Player(this, null, 0, 0, 100, 30);
    }

    /**
     * Ajouter un mur
     * 
     * @param x
     * @param y
     * @param dx
     * @param dy
     */
    public void addWall(int x, int y, int dx, int dy) {
        objects.add(new Wall(this, x, y, dx, dy));
        objects.get(objects.size()).index = objects.size();
    }

    public void addHud(String hudName, Hud hud) {
        huds.put(hudName, hud);
    }

    public void addTriggerMap(TriggerMap triggerMap) {
        this.triggerMaps.add(triggerMap);
    }

    
    public void setMap(Map map) {
        this.map = map;
    }
    public void setTriggerMap(TriggerMap tm) {
        worldTrigger = tm;
    }
    public void setTriggerMapTileMap(TileMap map) {
        worldTrigger.setTileMap(map);
    }
    public void setWorldBorder(WorldBorder worldBorder) {
        this.worldBorder = worldBorder;
    }
    public WorldBorder getWorldBorder() {
        return this.worldBorder;
    }
    /**
     * ajouter heros
     * 
     * @param vx
     * @param vy
     * @param px
     * @param py
     * @throws java.io.IOException
     */
    public void setPlayer(double vx, double vy, int px, int py, int health, int coins) throws IOException {
        player = new Player(this, map.activeRoom, px, py, health, coins);
        CoordinateSystem.h = player;
        player.index = 1;
    }
}
