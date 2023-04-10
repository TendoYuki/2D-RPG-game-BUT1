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

import static engine.main.GamePhysics.*;

/**
 *
 * @author Pierre-Frederic Villard
 */
public class Collision {

    public static int typeOfCollision = 0;

    /**
     *
     */
    public static int LEFT = 0;

    /**
     *
     */
    public static int RIGHT = 1;

    /**
     *
     */
    public static int UP = 2;

    /**
     *
     */
    public static int DOWN = 3;

    // test de collision

    /**
     *
     * @param o
     * @param o2
     * @return
     */
    public static boolean collision(PhysicalObject o, PhysicalObject o2) {
        typeOfCollision = 0;
        if (o2 instanceof Enemy) {
            typeOfCollision = ENEMY;
        }
        if (o2 instanceof Player) {
            typeOfCollision = PLAYER;
        }
        if (o2 instanceof Wall) {
            typeOfCollision = DECORS;
        }

        if ((o.px >= o2.px + o2.width) || (o.px + o.width <= o2.px)
                || (o.py >= o2.py + o2.height) || (o.py + o.height <= o2.py)) {
            return false;
        }
        return true;
    }

    // permet de retourner la direction de collision

    /**
     *
     * @param o
     * @param o2
     * @return
     */
    public static int direction(PhysicalObject o, PhysicalObject o2) {

        throw new AssertionError("deprecated");
        // on regarde simplement les anciennes positions

        /*
         * // reprend chacune des conditions
         * if (o.opx >= o2.opx + o2.width) {
         * // o vient de la droite de o2
         * return (DROITE);
         * }
         * if (o.opx + o.width <= o2.opx) {
         * // o vient de la gauche de o2
         * return (GAUCHE);
         * }
         * if (o.opy >= o2.opy + o2.height) {
         * // o vient du haut
         * return (HAUT);
         * }
         * 
         * if (o.opy + o.height <= o2.opy) {
         * // o vient du bas
         * return (BAS);
         * }
         * System.out.println("oldx "+o.opx);
         * System.out.println("oldy "+o.opy);
         * System.out.println("width "+o.width);
         * System.out.println("height "+o.height);
         * 
         * System.out.println("oldx2 "+o2.opx);
         * System.out.println("oldy2 "+o2.opy);
         * System.out.println("width "+o2.width);
         * System.out.println("width "+o2.height);
         * 
         * 
         * throw new AssertionError("Collision bizarre");
         */
    }

    // permet de savoir si la collision vient de la gauche

    /**
     *
     * @param o
     * @param o2
     * @return
     */
    public static boolean collisionLeft(PhysicalObject o, PhysicalObject o2) {
        return (o2.opx + o2.width >= o.opx);
    }

    // permet de savoir si la collision vient de la droite

    /**
     *
     * @param o Reference object
     * @param o2 Object that collieds with the reference
     * @return
     */
    public static boolean collisionRight(PhysicalObject o, PhysicalObject o2) {
        return (o.opx + o.width <= o2.opx);
    }

    // permet de savoir si la collision vient du haut

    /**
     *
     * @param o
     * @param o2
     * @return
     */
    public static boolean collisionUp(PhysicalObject o, PhysicalObject o2) {
        return (o.opy >= o2.opy + o2.height);
    }

    // permet de savoir si la collision vient du bas

    /**
     *
     * @param o
     * @param o2
     * @return
     */
    public static boolean collisionDown(PhysicalObject o, PhysicalObject o2) {
        return (o.opy + o.height <= o2.opy);
    }

}
