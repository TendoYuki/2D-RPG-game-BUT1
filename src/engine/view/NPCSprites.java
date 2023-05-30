package engine.view;

import java.io.IOException;
import java.util.HashMap;

import engine.physics.NPC;
import engine.tiles.Atlas;
/** NPCSSprites class */
public class NPCSprites extends Sprites {
	/** The npc */
	NPC npc;
	/** The sprite path */
	String imageFile = "assets/char/npc.png";
	/** The atlas */
	Atlas spriteAtlas;

	/**
	 * Constructs a npc sprite
	 * @param b
	 * @throws IOException
	 */
	public NPCSprites(NPC b) throws IOException {
		this.npc = b;
		spriteAtlas = new Atlas(imageFile, 16, 8, 15, 2);
		activity = "down";
		sprites = new HashMap<String, Sprite>();
		sprites.put("fixe0", spriteAtlas.get(1));
		sprites.put("fixe1", spriteAtlas.get(1));
		sprites.put("fixe2", spriteAtlas.get(1));
		sprites.put("fixe3", spriteAtlas.get(2));
		sprites.put("fixe4", spriteAtlas.get(2));
		sprites.put("fixe5", spriteAtlas.get(2));
		sprites.put("fixe6", spriteAtlas.get(2));
		sprites.put("fixe7", spriteAtlas.get(1));

		sprites.put("right0", spriteAtlas.get(1));
		sprites.put("right1", spriteAtlas.get(1));
		sprites.put("right2", spriteAtlas.get(1));

		sprites.put("left0", spriteAtlas.get(1));
		sprites.put("left1", spriteAtlas.get(1));
		sprites.put("left2", spriteAtlas.get(1));

		sprites.put("down0", spriteAtlas.get(1));
		sprites.put("down1", spriteAtlas.get(1));
		sprites.put("down2", spriteAtlas.get(2));
		sprites.put("down3", spriteAtlas.get(2));
		
		sprites.put("down-right0", spriteAtlas.get(1));
		sprites.put("down-right1", spriteAtlas.get(1));
		sprites.put("down-right2", spriteAtlas.get(1));
		
		sprites.put("down-left0", spriteAtlas.get(1));
		sprites.put("down-left1", spriteAtlas.get(1));
		sprites.put("down-left2", spriteAtlas.get(1));
		
		sprites.put("up0", spriteAtlas.get(1));
		sprites.put("up1", spriteAtlas.get(1));
		sprites.put("up2", spriteAtlas.get(1));

		sprites.put("up-right0", spriteAtlas.get(1));
		sprites.put("up-right1", spriteAtlas.get(1));
		sprites.put("up-right2", spriteAtlas.get(1));

		sprites.put("up-left0", spriteAtlas.get(1));
		sprites.put("up-left1", spriteAtlas.get(1));
		sprites.put("up-left2", spriteAtlas.get(1));
	}

	@Override
	public void animate() {
		iteration++;
		int itCount = 7;
			if (iteration > itCount) {
				num++;
				iteration = 0;
			}
			if (num > itCount)
				num = 0;
	}

}
