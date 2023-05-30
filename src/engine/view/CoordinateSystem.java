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

package engine.view;

import engine.physics.PhysicalObject;
import engine.physics.Player;

/**
 * CoordinateSystem class
 * @author Pierre-Frederic Villard
 */
public class CoordinateSystem {



    /** The window height */
    public static int windowHeight;
    /** Updates the window height 
     * @param windowHeight
    */
    public static void setWindowHeight(int windowHeight) {
        CoordinateSystem.windowHeight = windowHeight;
    }

    /**
     * The player
     */
    public static Player h;

    /**
     * Changes the corordinate system from y top -> bottom to y bottom -> top
     * @param o
     * @param x0
     * @param y0
     * @return
     */
    public static Coords changeCS(PhysicalObject o, int x0, int y0) {
        return changeCS((int)o.px, (int)o.py, (int)o.width, (int)o.height, x0, y0);
    }
    /**
     * Changes the corordinate system from y top -> bottom to y bottom -> top
     * @param o
     * @param origin
     * @return
     */
    public static Coords changeCS(PhysicalObject o, Coords origin) {
        return changeCS((int)o.px, (int)o.py, (int)o.height, (int)o.width, origin.getX(), origin.getY());
    }
    /**
     * Changes the corordinate system from y top -> bottom to y bottom -> top
     * @param x
     * @param y
     * @param height
     * @param width
     * @param origin
     * @return
     */
    public static Coords changeCS(int x, int y, int height, int width, Coords origin) {
        return changeCS(x, y, height, width, origin.getX(), origin.getY());
    }
    /**
     * Changes the corordinate system from y top -> bottom to y bottom -> top
     * @param x
     * @param y
     * @param height
     * @param width
     * @param x0
     * @param y0
     * @return
     */
    public static Coords changeCS(int x, int y, int height, int width, int x0, int y0) {
        int xAbs = (int) x + x0;
        int yAbs = ((windowHeight - (int) y - ((int) (height)+ y0)) );
        Coords absC = new Coords(xAbs, yAbs);
        return (absC);
    }

}
