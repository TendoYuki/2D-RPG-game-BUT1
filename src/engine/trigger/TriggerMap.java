package engine.trigger;

import java.util.ArrayList;
import java.util.HashMap;

import engine.physics.Entity;
import engine.tiles.TileMap;
/** TriggerMap class */
public class TriggerMap {
    /** The tilemap */
    private TileMap tileMap;
    /** The entity */
    private Entity entity;
    /** The previous tile */
    private int previousTile = -1;
    /** The triggers of the trigger map */
    private HashMap<Integer, ArrayList<Trigger>> triggers = 
        new HashMap<Integer, ArrayList<Trigger>>();
    /** Constructs a trigger map 
     * @param reference
     * @param tileMap
    */
    public TriggerMap(Entity reference, TileMap tileMap) {
        entity = reference;
        this.tileMap = tileMap;
        for(int i = 1; i <= tileMap.tileCount(); i++) 
            triggers.put(i, new ArrayList<Trigger>());
    }
    /** Updates the tilemap 
     * @param tileMap
    */
    public void setTileMap(TileMap tileMap) {
        this.tileMap= tileMap;
    }
    /** Adds a trigger to the triggers hashmap 
     * @param triggeringTileId
     * @param trigger
    */
    public void addTrigger(int triggeringTileId, Trigger trigger) {
        ArrayList<Trigger> trig = triggers.get(triggeringTileId);
        trig.add(trigger);
        triggers.put(triggeringTileId, trig);
    }
    /** Updates the trigger map */
    public void update() {
        int currentTile = tileMap.getTileID(
            (int)(entity.px + entity.width/2),
            (int)(entity.py + entity.height/2)
        );
        if(currentTile != -1) {
            triggers.get(currentTile).forEach(trigger -> {
                trigger.onTriggered();
            });
        }
        if(previousTile != -1) {
            triggers.get(previousTile).forEach(trigger -> {
                if(previousTile != currentTile)
                    trigger.onTriggerExit();
            });
        }
        previousTile = currentTile;
    }
}
