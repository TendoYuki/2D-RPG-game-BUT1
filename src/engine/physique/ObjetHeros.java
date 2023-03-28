/* ========================================================== */
/*                  Bibliotheque MoteurDeJeu                  */
/* --------------------------------------------               */
/* Bibliotheque pour aider la création de jeu video comme :   */
/* - Jeux de role                                             */
/* - Jeux de plateforme                                       */
/* - Jeux de combat                                           */
/* - Jeux de course                                           */
/* - Ancien jeu d'arcade (Pac-Man, Space Invider, Snake, ...) */
/* ========================================================== */

package engine.physique;

import java.awt.Color;
import java.awt.Graphics;
import java.io.IOException;

import engine.afficheur.Repere;
import engine.afficheur.Sprites;
import engine.afficheur.SpritesHeros;
import engine.controle.Controle;

//un objet de type balle

/**
 *
 * @author Pierre-Frederic Villard
 */
public class ObjetHeros extends Objet{

	
	//distributeur de sprite
	public Sprites sprites;
	
	//lien  vers son controleur
	Controle c;
	
	//fait une balle par d�faut

    /**
     *
     * @throws IOException
     */
	public ObjetHeros() throws IOException
	{
		
	
		
		sprites=new SpritesHeros(this);
		height=10;
		width=10;
		height=sprites.sprites.get("fixe").ty;
		width=sprites.sprites.get("fixe").tx;
		vx=1;
		vy=3;
		ax=0;
		ay=-0.04;
	}
    /**
     *
     * @throws IOException
     */
	public ObjetHeros(int x, int y) throws IOException
	{
		sprites=new SpritesHeros(this);
		height=10;
		width=10;
		height=sprites.sprites.get("fixe").ty;
		width=sprites.sprites.get("fixe").tx;
                px=x;
                py=y;
		vx=1;
		vy=3;
		ax=0;
		ay=-0.04;
	}
    /**
     *
     * @param g
     */
    @Override
	public void draw(Graphics g) {
		g.setColor(Color.black);
		
		//change de repere
		int[]tab=Repere.changeRepere(this);
		sprites.affiche(tab[0],tab[1],g);
		sprites.anime();
	}
	
	
}
