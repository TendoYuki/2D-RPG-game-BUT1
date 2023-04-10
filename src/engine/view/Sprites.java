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

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

//distributeur de sprites

/**
 *
 * @author Pierre-Frederic Villard
 */
public abstract class Sprites {

    // l'activit�
    String activity;
    // compteur interne
    int iteration;
    // numero de la frame

    /**
     *
     */
    public int num;

    // lie chaine et sprite

    /**
     *
     */
    public HashMap<String, Sprite> sprites;

    // construit le sprite

    /**
     *
     * @return
     */
    public String chain() {
        return (activity + num);
    }

    // afficheur de sprite

    /**
     *
     * @param x
     * @param y
     * @param g
     */
    public void draw(int x, int y, Graphics g) {
        // Sprite s=sprites.get(chaine());
        Sprite s = sprites.get("fixe");
        if (s == null)
            s = sprites.get("erreur");
        s.draw(g, x, y);
    }

    // permet de changer le type de sprite

    /**
     *
     * @param n
     */
    public void changeActivity(String n) {
        if(!activity.equals(n)) {
            activity = n;
            iteration = 0;
            num = 0;
        }
    }

    public String getActivity() {
        return activity;
    }

    /**
     *
    */
    public abstract void animate();
}
