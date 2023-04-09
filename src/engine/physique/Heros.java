package engine.physique;

import java.awt.Color;
import java.awt.Graphics;
import java.io.IOException;

import engine.afficheur.Repere;
import engine.afficheur.Sprites;
import engine.afficheur.SpritesHeros;
import engine.controle.Controle;

public class Heros extends Entity{

	// distributeur de sprite
	public Sprites sprites;

	// lien vers son controleur
	Controle c;

	private final int STATS_VIE = 10;
	private final int STATS_ATT = 5;
	private final int STATS_DEF = 1;
	private final int VIE_DEPART;
	/** Multiplicator de la vie */
	private int multiplicatorVie = 0;

	/** Multiplicator de la defense */
	private int multiplicatorDef = 0;

	/** Multiplicator de l'attaque */
	private int multiplicatorAtt = 0;

	// pieces du heros
	int pieces;

	/**
	 *
	 * @throws IOException
	 */
	public Heros(int x, int y, int vie, int pieces) throws IOException {
		super(vie, 5, 2);
		VIE_DEPART = vie;
		this.pieces = pieces;
		sprites = new SpritesHeros(this);
		height = 10;
		width = 10;
		height = sprites.sprites.get("fixe").getSizeY();
		width = sprites.sprites.get("fixe").getSizeX();
		px = x;
		py = y;
		vx = 0;
		vy = 0;
		ax = 0;
		ay = 0;
	}

	/**
	 *
	 * @throws IOException
	 */
	public Heros() throws IOException {
		this(0,0,100,10);
	}

	/**  ajoute des pieces au heros
	 * @param pieces les pieces a ajouter
	 */
	public void addPieces(int pieces){
		this.pieces += pieces;
	}

	/** change les pieces du heros
	 * @param pieces nouvelles pieces du heros
	 */
	public void setPieces(int pieces){
		this.pieces = pieces;
	}

	/** Retourne les pieces du heros
	 * @return les pieces du heros
	 */
	public int getPieces(){
		return pieces;
	}

	/** Mort du heros */
	public void handleDeath() {
		System.out.println("Shawn il é mor");
	}


	
	public int getMultiplicatorVie() {
		return multiplicatorVie;
	}

	public void setMultiplicatorVie(int multiplicatorVie) {
		this.multiplicatorVie = multiplicatorVie;
	}

	public int getMultiplicatorDef() {
		return multiplicatorDef;
	}

	public void setMultiplicatorDef(int multiplicatorDef) {
		this.multiplicatorDef = multiplicatorDef;
	}

	public int getMultiplicatorAtt() {
		return multiplicatorAtt;
	}

	public void setMultiplicatorAtt(int multiplicatorAtt) {
		this.multiplicatorAtt = multiplicatorAtt;
	}

	/**
	 *
	 * @param g
	 */
	@Override
	public void draw(Graphics g) {
		g.setColor(Color.black);

		// change de repere
		int[] tab = Repere.changeRepere(this);
		sprites.affiche(tab[0], tab[1], g);
		sprites.anime();
	}
}
