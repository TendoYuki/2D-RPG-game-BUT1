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

import java.io.IOException;
import java.util.ArrayList;

import engine.afficheur.CoordinateSystem;
import engine.controle.Control;

//gere les objets du monde

/**
 *
 * @author Pierre-Frederic Villard
 */
public class World {

    /**
     * le controleur
     */
    public Control c;

    /**
     * les murs
     */
    public ArrayList<Object> objects = new ArrayList<Object>();

    public int wallCount = 0;

    /**
     * les monstres
     */
    public ArrayList<Enemy> enemies = new ArrayList<Enemy>();

    /**
     * les heros
     */
    public Player player;

    public int nbMonstres = 0;

    /**
     *
     * @throws IOException
     */
    /**
     * un monde par defaut
     * 
     * @throws java.io.IOException
     */
    public World() throws IOException {
        // getsion du controleur
        player = new Player();

        // gere la vision subjective
        CoordinateSystem.h = player;

    }

    /**
     * Ajouter un mur
     * 
     * @param x
     * @param y
     * @param dx
     * @param dy
     */
    public void addMur(int x, int y, int dx, int dy) {
        objects.add(new Wall(x, y, dx, dy));
        objects.get(wallCount).index = wallCount;
        wallCount++;

    }

    /**
     * ajouter monstre
     * 
     * @param vx
     * @param vy
     * @param px
     * @param py
     * @throws java.io.IOException
     */
    public void addMonstre(double vx, double vy, int px, int py) throws IOException {
        enemies.add(new Enemy(20,10,5));
        // penser a le lier au monde
        enemies.get(nbMonstres).m = this;
        // propriétés du monstre
        enemies.get(nbMonstres).vx = vx;
        enemies.get(nbMonstres).vy = vy;
        enemies.get(nbMonstres).px = px;
        enemies.get(nbMonstres).py = py;
        enemies.get(nbMonstres).index = nbMonstres;

        nbMonstres++;
    }

    public void addObjet(Object monObjet) {
        objects.add(monObjet);
    }

    /**
     * ajouter heros
     * 
     * @param vx
     * @param vy
     * @param px
     * @param py
     * @throws java.io.IOException
     */
    public void setHero(double vx, double vy, int px, int py, int vie, int pieces) throws IOException {
        player = new Player(px, py, vie, pieces);
        player.vx = vx;
        player.vy = vy;
        player.px = px;
        player.py = py;
        player.index = 1;
    }
}
