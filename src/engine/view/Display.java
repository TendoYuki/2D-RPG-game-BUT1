package engine.view;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.Map.Entry;

import javax.swing.*;

import engine.physics.*;
import engine.tiles.Directions;
import engine.generation.Map;
import engine.hud.Hud;

public class Display extends JPanel {

	// le monde a affcher
	public World m;

	// l'afficheur de Decor
	// public Scene decor;

	Map map;

	// double buffering
	public BufferStrategy bs;

	private JFrame frame;


	// creation d'un afficheur

	/**
	 *
	 * @param monde
	 */
	public Display(World monde, Map map) {
		JFrame f = new JFrame();

		this.map = map;

		// setPreferredSize(new Dimension(map.size(),map.size()));
		// setPreferredSize(new Dimension(map.size() + 200,map.size() + 200));
		
		// setPreferredSize(new Dimension(800, 800));
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setPreferredSize(new Dimension(map.size() + 200,map.size() + 200));
		f.getContentPane().add(this);
		f.setResizable(false);

		f.pack();
		f.setVisible(true);
		
		// ajouter pour eviter les repaint
		f.setIgnoreRepaint(true);

		// double buffering
		f.createBufferStrategy(2);
		bs = f.getBufferStrategy();
		this.setIgnoreRepaint(true);

		this.m = monde;
		this.frame = f;
	}

	// permet de faire un affichage

	// Renvoie le decor
	// public Scene getDecor() {
	// 	return decor;
	// }

	// public void setDecor(Scene decor) {
	// 	this.decor = decor;
	// }

	

	/**
	 *
	 */
	public void render() {
		Graphics2D g = (Graphics2D) bs.getDrawGraphics();

		g.setColor(Color.black);

		// Affiche le decor
		// decor.draw(g);
		map.draw(g);

		// affiche les objets
		// for (PhysicalObject obj : m.objects) {
		// 	obj.draw(g);
		// }

		// Draws walls
		for(Entry<Directions, Wall> wall: m.getWorldBorder().getBorderWalls().entrySet()){
			wall.getValue().draw(g);
		}

		// affiche les monstres
		for (Enemy enemy : m.map.activeRoom.enemies) {
			enemy.draw(g);
		}

		// draws npcs
		for (NPC npc : m.map.activeRoom.npcs) {
			npc.draw(g);
		}

		// affiche la balle
		Player b = m.player;
		b.draw(g);

		for (Hud hud : m.huds.values()) {
			hud.draw(g);
		}

		bs.show();
		Toolkit.getDefaultToolkit().sync();
		g.clearRect(0, 0, this.getWidth(), this.getHeight()+frame.getInsets().top);
		g.dispose();


	}

	public JFrame getFrame() {
		return frame;
	}

	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
	}

}
