package engine.physics;

import java.awt.Color;
import java.awt.Graphics;
import java.io.IOException;
import java.util.HashMap;

import engine.dialog.Dialog;
import engine.view.CoordinateSystem;
import engine.view.NPCSprites;

public class NPC extends Entity {

	NPCSprites sprite;

	// lien vers le monde
	World m;

	private int triggerZoneWidth = 25;
	private int triggerZoneHeight = 25;

	private HashMap<String, Dialog> dialogs = new HashMap<String, Dialog>();

	private Dialog activeDialog;

	/**
	 *
	 * @throws IOException
	 */
	public NPC(int vie,int attaque,int defense) throws IOException {
		super(vie, attaque, defense);
		activeDialog = new Dialog();
		activeDialog.addLine("");
		sprite = new NPCSprites(this);
		height = sprite.sprites.get("fixe").getSizeY();
		width  = sprite.sprites.get("fixe").getSizeX();
	}

	public void addDialog(String dialogName, Dialog dialog) {
		dialogs.put(dialogName, dialog);
	}

	public void setActiveDialog(String dialogName) {
		activeDialog = dialogs.get(dialogName);
	}

	/**
	 *
	 * @param g
	 */
	public void draw(Graphics g) {

		// change de repere
		g.setColor(Color.black);
		int[] tab = CoordinateSystem.changeCS(this);

		sprite.draw(tab[0], tab[1], g);
		sprite.animate();
	}

	/** the player is near the NPC */
	public boolean isInTriggerZone(){
		return (
			m.player.px+m.player.width > this.px- triggerZoneWidth/2 &&
			m.player.px < this.px + width + triggerZoneWidth/2
		) &&
		( 
			m.player.py+m.player.height > this.py - triggerZoneHeight/2 &&
			m.player.py < this.py + height + triggerZoneHeight/2
		);
	}
	/**
	 *
	 */
	public void interact() {
		m.huds.get("npc").setIsShown(true);
		m.huds.get("npc").setInteractable(true);
	}

	/** */
	public void noInteract(){
		m.huds.get("npc").setIsShown(false);
		m.huds.get("npc").setInteractable(false);
	}


	@Override
	public void handleDeath() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'handleDeath'");
	}

}
