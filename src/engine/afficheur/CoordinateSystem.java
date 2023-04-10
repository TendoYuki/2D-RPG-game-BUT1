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

package engine.afficheur;

import engine.physique.Object;
import engine.physique.Player;

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
     *
     * @param o
     * @return
     */
    public static int[] changeCS(Object o) {
        int res[] = new int[4];
        // res[1]= 370 - (int)o.py - (int)(o.height);
        res[0] = (int) o.px;
        res[1] = windowHeight - (int) o.py - (int) (o.height);
        res[2] = (int) o.width;
        res[3] = (int) o.height;
        return (res);
    }

}
