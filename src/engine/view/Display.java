package engine.view;

import java.awt.*;
import java.awt.image.BufferStrategy;

import javax.swing.*;

import engine.physics.*;
import engine.generation.Map;
import engine.hud.Hud;
/** Display class */
public class Display extends JPanel {

	/** The world */
	public World m;
	/** The map */
	Map map;

	/** The buffer strategy */
	public BufferStrategy bs;
	/** The jframe */
	public static JFrame frame;


	/**
	 * Constructs a display
	 * @param map
	 * @param monde
	 */
	public Display(World monde, Map map) {
		JFrame f = new JFrame();

		this.map = map;

		// setPreferredSize(new Dimension(map.size(),map.size()));
		// setPreferredSize(new Dimension(map.size() + 200,map.size() + 200));
		
		// setPreferredSize(new Dimension(800, 800));
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setResizable(false);
		f.setUndecorated(true);
		this.setPreferredSize(new Dimension(map.size() + 200,map.size() + 200));
		f.getContentPane().add(this);

		f.pack();
		f.setLocationRelativeTo(null);
		f.setVisible(true);
		
		// ajouter pour eviter les repaint
		f.setIgnoreRepaint(true);

		// double buffering
		f.createBufferStrategy(2);
		bs = f.getBufferStrategy();
		this.setIgnoreRepaint(true);

		this.m = monde;
		Display.frame = f;
	}


	/**
	 * Renders the display
	 */
	public void render() {
		Graphics2D g = (Graphics2D) bs.getDrawGraphics();

		g.setColor(new Color(46, 38, 90, 255));
		g.fillRect(0, 0, 100000, 100000);
		g.setColor(Color.black);

		map.draw(g);

		
		// Draws the monsters
		for (Enemy enemy : m.map.activeRoom.enemies) {
			enemy.draw(g);
		}

		// Draws the boss
		for (Boss boss : m.map.activeRoom.bosses) {
			boss.draw(g);
		}

		// Draws the items
		for (Item item : m.map.activeRoom.items) {
			item.draw(g);
		}

		// Draws the npcs
		for (NPC npc : m.map.activeRoom.npcs) {
			npc.draw(g);
		}

		// Draws the player
		Player b = m.player;
		b.draw(g);

		for (Hud hud : m.huds.values()) {
			hud.draw(g);
		}
		m.huds.get("shop").draw(g);
		m.huds.get("doorClosed").draw(g);
		m.huds.get("npc").draw(g);
		m.huds.get("mapHud").draw(g);
		m.huds.get("menu").draw(g);
		m.huds.get("gameOver").draw(g);
		m.huds.get("endMenu").draw(g);

		bs.show();
		Toolkit.getDefaultToolkit().sync();
		g.clearRect(0, 0, this.getWidth(), this.getHeight()+frame.getInsets().top);
		g.dispose();


	}
	/** Returns the jframe 
	 * @return
	*/
	public JFrame getFrame() {
		return frame;
	}
	/** Returns the map 
	 * @return
	*/
	public Map getMap() {
		return map;
	}
	/** Updates the map 
	 * @param map
	*/
	public void setMap(Map map) {
		this.map = map;
	}

}
