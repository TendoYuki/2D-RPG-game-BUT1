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
 *
 * @author Pierre-Frederic Villard
 */
public class CoordinateSystem {

    private static int windowHeight;

    public static void setWindowHeight(int windowHeight) {
        CoordinateSystem.windowHeight = windowHeight;
    }

    /**
     *
     */
    public static Player h;

    /**
     * Changes the corordinate system from y top -> bottom to y bottom -> top
     * @return
     */
    public static int[] changeCS(PhysicalObject o, int x0, int y0) {
        int res[] = new int[4];
        res[0] = (int) o.px + x0;
        res[1] = (windowHeight - (int) o.py - (int) (o.height)) + y0;
        res[2] = (int) o.width;
        res[3] = (int) o.height;
        return (res);
    }

}
