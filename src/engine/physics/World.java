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

package engine.physics;

import java.io.IOException;
import java.util.ArrayList;

import engine.controller.Control;
import engine.view.CoordinateSystem;

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
    public ArrayList<PhysicalObject> objects = new ArrayList<PhysicalObject>();

    public int wallCount = 0;

    /**
     * les monstres
     */
    public ArrayList<Enemy> enemies = new ArrayList<Enemy>();

    /**
     * les heros
     */
    public Player player;

    public int enemiesCount = 0;

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
    public void addWall(int x, int y, int dx, int dy) {
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
    public void addEnemy(double vx, double vy, int px, int py) throws IOException {
        enemies.add(new Enemy(20,10,5));
        // penser a le lier au monde
        enemies.get(enemiesCount).m = this;
        // propriétés du monstre
        enemies.get(enemiesCount).vx = vx;
        enemies.get(enemiesCount).vy = vy;
        enemies.get(enemiesCount).px = px;
        enemies.get(enemiesCount).py = py;
        enemies.get(enemiesCount).index = enemiesCount;

        enemiesCount++;
    }

    public void addObject(PhysicalObject o) {
        objects.add(o);
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
    public void setPlayer(double vx, double vy, int px, int py, int health, int coins) throws IOException {
        player = new Player(px, py, health, coins);
        player.vx = vx;
        player.vy = vy;
        player.px = px;
        player.py = py;
        player.index = 1;
    }
}
