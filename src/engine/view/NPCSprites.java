package engine.view;

import java.io.IOException;
import java.util.HashMap;

import engine.physics.NPC;
import engine.tiles.Atlas;

public class NPCSprites extends Sprites {

	NPC npc;
	String imageFile = "assets/char/February.png";
	Atlas spriteAtlas;

	// constructeur de table de sprites

	/**
	 *
	 * @throws IOException
	 */
	public NPCSprites(NPC b) throws IOException {
		this.npc = b;
		spriteAtlas = new Atlas(imageFile, 16, 8, 15, 2);
		activity = "down";
		sprites = new HashMap<String, Sprite>();
		sprites.put("fixe", spriteAtlas.get(73));
		sprites.put("fixe0", spriteAtlas.get(73));
		sprites.put("fixe1", spriteAtlas.get(73));
		sprites.put("fixe2", spriteAtlas.get(73));

		sprites.put("right0", spriteAtlas.get(103));
		sprites.put("right1", spriteAtlas.get(104));
		sprites.put("right2", spriteAtlas.get(105));

		sprites.put("left0", spriteAtlas.get(88));
		sprites.put("left1", spriteAtlas.get(89));
		sprites.put("left2", spriteAtlas.get(90));

		sprites.put("down0", spriteAtlas.get(73));
		sprites.put("down1", spriteAtlas.get(74));
		sprites.put("down2", spriteAtlas.get(75));
		
		sprites.put("down-right0", spriteAtlas.get(73));
		sprites.put("down-right1", spriteAtlas.get(74));
		sprites.put("down-right2", spriteAtlas.get(75));
		
		sprites.put("down-left0", spriteAtlas.get(73));
		sprites.put("down-left1", spriteAtlas.get(74));
		sprites.put("down-left2", spriteAtlas.get(75));
		
		sprites.put("up0", spriteAtlas.get(118));
		sprites.put("up1", spriteAtlas.get(119));
		sprites.put("up2", spriteAtlas.get(120));

		sprites.put("up-right0", spriteAtlas.get(118));
		sprites.put("up-right1", spriteAtlas.get(119));
		sprites.put("up-right2", spriteAtlas.get(120));

		sprites.put("up-left0", spriteAtlas.get(118));
		sprites.put("up-left1", spriteAtlas.get(119));
		sprites.put("up-left2", spriteAtlas.get(120));
	}

	@Override
	public void animate() {
		iteration++;
		int itCount = 2;
			if (iteration > itCount) {
				num++;
				iteration = 0;
			}
			if (num > itCount)
				num = 0;
	}

}
