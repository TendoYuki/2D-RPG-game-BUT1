package engine.main;

import java.io.IOException;

import engine.afficheur.Afficheur;
import engine.physique.MoteurPhysique;

public class JeuPhysique {

	// le moteur physique
	public MoteurPhysique moteurPhys;

	// le rendu
	public Afficheur affiche;

	public final static short MONSTRE = 1;
	public final static short HERO = 2;
	public final static short DECORS = 3;

	int i = 0;

	// separation vue affichage
	public JeuPhysique() throws IOException {
		// on creer le moteur physique
		// moteurPhys=new MoteurPhysique();
		// on creer l'afficheur du monde
		// affiche=new Afficheur(moteurPhys.monde);

	}

	public void update() {
		moteurPhys.update();
	}

	public void render() {
		affiche.render();

	}
}