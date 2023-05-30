package engine.physics;

import java.awt.Color;
import java.awt.Graphics;
import java.io.IOException;
import java.util.HashMap;

import engine.dialog.Dialog;
import engine.dialog.DialogController;
import engine.generation.Room;
import engine.hud.Hud;
import engine.view.CoordinateSystem;
import engine.view.Coords;
import engine.view.NPCSprites;
import java.util.Timer;
import java.util.TimerTask;

/** NPC Class */
public class NPC extends Entity {

	/** Sprites of the npc */
	NPCSprites sprite;

	/** Whether or not he npc is interacting */
	public static boolean interacting = false;

	/** Zone in which the player interacts with the npc */
	private TriggerZone interactZone = new TriggerZone(25, 25);

	/** Dialogs of the npc */
	private HashMap<String, Dialog> dialogs = new HashMap<String, Dialog>();

	/** Active dialog of the npc */
	private Dialog activeDialog;
	/** Active dialog name */
	private String dialogName;

	/** Whether or not the end menu is displayed */
	public boolean displayEndMenu = false;

	/**
	 * Creates a new npc
	 * @throws IOException
	 */
	public NPC(World w, Room r) throws IOException {
		super(w, r, 0);
		activeDialog = new Dialog();
		activeDialog.addLine("");
		sprite = new NPCSprites(this);
		height = sprite.sprites.get("fixe0").getSizeY();
		width  = sprite.sprites.get("fixe0").getSizeX();
		sprite.changeActivity("fixe");
	}

	/**
	 * Adds a new dialog to the npc
	 * @param dialogName
	 * @param dialog
	 */
	public void addDialog(String dialogName, Dialog dialog) {
		dialogs.put(dialogName, dialog);
	}

	/**
	 * Sets the active dialog to the corresponding one given in parameter
	 * @param dialogName
	 */
	public void setActiveDialog(String dialogName) {
		this.dialogName = dialogName;
		activeDialog = dialogs.get(dialogName);
	}

	/**
	 * Draws the npc
	 * @param g
	 */
	public void draw(Graphics g) {

		// change de repere
		g.setColor(Color.black);
		Coords coords = CoordinateSystem.changeCS(this, world.map.getPosX(), world.map.getPosY());

		sprite.draw(coords.getX(), coords.getY(), g);
		sprite.animate();
	}

	/** Checks if the player is near the NPC */
	public boolean isInTriggerZone(){
		return (
			world.player.px+world.player.width > this.px- interactZone.getWidth()/2 &&
			world.player.px < this.px + width + interactZone.getWidth()/2
		) &&
		( 
			world.player.py+world.player.height > this.py - interactZone.getHeight()/2 &&
			world.player.py < this.py + height + interactZone.getHeight()/2
		);
	}

	/** Update */
	public void update(){
		if(displayEndMenu){
			world.huds.values().forEach(hud -> {
				hud.setInteractable(false);
				hud.setIsShown(false);
			});
			Hud gO = world.huds.get("endMenu");
			gO.setInteractable(true);
			gO.setIsShown(true);
			PhysicsEngine.endGame = true;
		}
		else if(dialogName == "end" && !activeDialog.hasNextLine()){
			TimerTask task = new TimerTask() {
					@Override
					public void run() {	
						displayEndMenu = true;
					}
				};
				Timer timer = new Timer("Timer");
				long delay = 5000L;
				timer.schedule(task, delay);
		}
	}
	/**
	 * Interaction logic
	 */
	public void interact() {
		if(world.player.hasBossLoot)
			setActiveDialog("end");
		else
			setActiveDialog("start");
		world.huds.get("npc").setIsShown(true);
		world.huds.get("npc").setInteractable(true);
		interacting = true;
		DialogController.setCurrentDialog(activeDialog);
	}

	@Override
	public void handleDeath() {
		throw new UnsupportedOperationException("Unimplemented method 'handleDeath'");
	}

}
