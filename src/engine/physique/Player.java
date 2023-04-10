package engine.physique;

import java.awt.Color;
import java.awt.Graphics;
import java.io.IOException;

import engine.afficheur.CoordinateSystem;
import engine.afficheur.Sprites;
import engine.afficheur.PlayerSprites;
import engine.controle.Control;

public class Player extends Entity{

	// distributeur de sprite
	public Sprites sprites;

	// lien vers son controleur
	Control c;

	public final int MUL_VIE_UNIT = 10;
	public final int MUL_ATT_UNIT = 5;
	public final int MUL_DEF_UNIT = 1;

	private final int VIE_DEPART;
	private final int DEF_DEPART = 2;
	private final int ATT_DEPART = 5;
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
	public Player(int x, int y, int vie, int pieces) throws IOException {
		super(vie, 5, 2);
		VIE_DEPART = vie;
		this.pieces = pieces;
		sprites = new PlayerSprites(this);
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
	public Player() throws IOException {
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
	@Override
	public int getMaxVie() {
		return VIE_DEPART + MUL_VIE_UNIT * getMultiplicatorVie();
	}
	@Override
	public int getDefense() {
		return DEF_DEPART + MUL_DEF_UNIT * getMultiplicatorDef();
	}
	@Override
	public int getAttaque() {
		return ATT_DEPART + MUL_ATT_UNIT * getMultiplicatorAtt();
	}
	/**
	 *
	 * @param g
	 */
	@Override
	public void draw(Graphics g) {
		g.setColor(Color.black);

		// change de repere
		int[] tab = CoordinateSystem.changeCS(this);
		sprites.draw(tab[0], tab[1], g);
		sprites.animate();
	}
}
