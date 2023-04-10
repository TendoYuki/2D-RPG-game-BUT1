package engine.main;

import java.io.IOException;

import engine.physics.PhysicsEngine;
import engine.view.Display;

public class GamePhysics {

	// le moteur physique
	public PhysicsEngine physicsEngine;

	// le rendu
	public Display display;

	public final static short ENEMY = 1;
	public final static short PLAYER = 2;
	public final static short DECORS = 3;

	int i = 0;

	// separation vue affichage
	public GamePhysics() throws IOException {
		// on creer le moteur physique
		// moteurPhys=new MoteurPhysique();
		// on creer l'afficheur du monde
		// affiche=new Afficheur(moteurPhys.monde);

	}

	public void update() {
		physicsEngine.update();
	}

	public void render() {
		display.render();

	}
}