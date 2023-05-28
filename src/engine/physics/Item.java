package engine.physics;

public abstract class Item extends PhysicalObject{

    private static final TriggerZone pickupZone = new TriggerZone(30, 30);
    public Item(World w){
        super(w);
    }

    public void update() {
		super.update();
        if(isInTriggerZone(world.player, pickupZone)){
            pickup();
        }
		
	}

    public abstract void pickup();
}
