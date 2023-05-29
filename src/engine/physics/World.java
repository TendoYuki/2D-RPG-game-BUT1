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
import engine.controller.KeyboardController;
import engine.generation.Map;
import engine.hud.Hud;
import engine.hud.map.MapHud;
import engine.tiles.TileMap;
import engine.trigger.TriggerMap;
import engine.view.CoordinateSystem;

/** World class */
public class World {

    /** Controller */
    public Control c;

    /** World border */
    private WorldBorder worldBorder;

    /** World triggers */
    public ArrayList<TriggerMap> triggerMaps = new ArrayList<TriggerMap>();
    
    /** Huds */
    public HashMap<String, Hud> huds = new HashMap<String, Hud>();

    /** Actual trigger */
    public TriggerMap worldTrigger;

    /** Player */
    public Player player;

    /** Active map */
    public Map map;

    /** Hud displaying the map */
    public MapHud mapHud;

    /**
     * Creates a world
     * @throws IOException
     */
    public World() throws IOException {
        player = new Player(this, null, 0, 0, 100);
    }

    /**
     * Adds a hud
     * @param hudName
     * @param hud
     */
    public void addHud(String hudName, Hud hud) {
        huds.put(hudName, hud);
    }

    /**
     * Adds a trigger map
     * @param triggerMap
     */
    public void addTriggerMap(TriggerMap triggerMap) {
        this.triggerMaps.add(triggerMap);
    }

    /**
     * Sets the active map to a new one
     * @param map
     */
    public void setMap(Map map) {
        this.map = map;
    }

    /**
     * Sets the active trigger map to a new one
     * @param tm
     */
    public void setTriggerMap(TriggerMap tm) {
        worldTrigger = tm;
    }

    /**
     * Changes the tile map binded to the active trigger map
     * @param map
     */
    public void setTriggerMapTileMap(TileMap map) {
        worldTrigger.setTileMap(map);
    }

    /**
     * Changes the active world border
     * @param worldBorder
     */
    public void setWorldBorder(WorldBorder worldBorder) {
        this.worldBorder = worldBorder;
    }

    /**
     * Returns the world border
     * @return
     */
    public WorldBorder getWorldBorder() {
        return this.worldBorder;
    }

    /** Whether or not the boss is defeated */
    public boolean bossDefeated = false;

    /** Updates the world */
    public void update() {
        mapHud.setIsShown(KeyboardController.map);
        if(map.enemiesCount() == 0) {
            map.endRoom.unlockRoom();
        }
        if(map.activeRoom.getId()==1 && !bossDefeated){
            map.endRoom.lockRoom();
        }
        else if (bossDefeated){
            map.endRoom.unlockRoom();
        }
    }

    /**
     * Set the player to a new one
     * 
     * @param vx
     * @param vy
     * @param px
     * @param py
     * @throws java.io.IOException
     */
    public void setPlayer(double vx, double vy, int px, int py, int health, int gems) throws IOException {
        player = new Player(this, map.activeRoom, px, py, gems);
        CoordinateSystem.h = player;
        player.index = 1;
    }
}
