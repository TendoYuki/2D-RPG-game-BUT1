package engine.physics;

public abstract class Item extends PhysicalObject{

    /** Zone in which the item is picked up */
    private static final TriggerZone pickupZone = new TriggerZone(30, 30);
    /**
     * Creates a new item
     * @param w
     */
    public Item(World w){
        super(w);
    }

    /** Updates at each frame */
    public void update() {
		super.update();
        if(isInTriggerZone(world.player, pickupZone)){
            pickup();
        }
		
	}
    /** Pickup logic run when player is in trigger zone */
    public abstract void pickup();
}
