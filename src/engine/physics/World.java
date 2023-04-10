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
import java.util.HashMap;

import engine.controller.Control;
import engine.hud.Hud;
import engine.trigger.TriggerMap;
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
     * Enemies
     */
    public ArrayList<Enemy> enemies = new ArrayList<Enemy>();

    /**
     * NPCs
     */
    public ArrayList<NPC> npcs = new ArrayList<NPC>();
    /**
     * NPCs
     */
    public ArrayList<TriggerMap> triggerMaps = new ArrayList<TriggerMap>();

    
    public HashMap<String, Hud> huds = new HashMap<String, Hud>();

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
        player = new Player(this, 0, 0 ,100, 10);

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
        Enemy enemy = new Enemy(20,10,5);

        enemy.m = this;

        enemy.vx = vx;
        enemy.vy = vy;
        enemy.px = px;
        enemy.py = py;
        enemy.index = enemies.size();

        enemies.add(enemy);
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
    public void addNPC(double vx, double vy, int px, int py) throws IOException {
        NPC npc = new NPC(20,10,5);

        npc.m = this;
        npc.vx = vx;
        npc.vy = vy;
        npc.px = px;
        npc.py = py;
        npc.index = npcs.size();

        npcs.add(npc);
    }

    public void addObject(PhysicalObject o) {
        objects.add(o);
    }

    public void addHud(String hudName, Hud hud) {
        huds.put(hudName, hud);
    }

    public void addTriggerMap(TriggerMap triggerMap) {
        this.triggerMaps.add(triggerMap);
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
        player = new Player(this, px, py, health, coins);
        player.vx = vx;
        player.vy = vy;
        player.px = px;
        player.py = py;
        player.index = 1;
    }
}
