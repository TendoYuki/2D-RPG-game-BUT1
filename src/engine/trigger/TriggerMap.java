package engine.trigger;

import java.util.ArrayList;
import java.util.HashMap;

import engine.physics.Entity;
import engine.tiles.TileMap;

public class TriggerMap {
    private TileMap tileMap;
    private Entity entity;
    private HashMap<Integer, ArrayList<Trigger>> triggers = 
        new HashMap<Integer, ArrayList<Trigger>>();

    public TriggerMap(Entity reference, TileMap tileMap) {
        entity = reference;
        this.tileMap = tileMap;
        for(int i = 1; i <= tileMap.tileCount(); i++) 
            triggers.put(i, new ArrayList<Trigger>());
    }

    public void setTileMap(TileMap tileMap) {
        this.tileMap= tileMap;
    }

    public void addTrigger(int triggeringTileId, Trigger trigger) {
        ArrayList<Trigger> trig = triggers.get(triggeringTileId);
        trig.add(trigger);
        triggers.put(triggeringTileId, trig);
    }
    
    public void update() {
        int currentTile = tileMap.getTileID(
            (int)(entity.px + entity.width/2),
            (int)(entity.py + entity.height/2)
        );
        System.out.println(currentTile);
        triggers.get(currentTile).forEach(trigger -> {
           trigger.onTriggered();
        });
    }
}
