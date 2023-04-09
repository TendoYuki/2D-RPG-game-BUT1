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

/**
 *
 * @author Pierre-Frederic Villard
 */

import engine.main.BouclePrincipale;
import engine.afficheur.Afficheur;
import engine.controle.ControleurClavier;
import engine.main.JeuPhysique;
import engine.physique.Monde;
import engine.physique.MoteurPhysique;
import engine.afficheur.Repere;
import engine.physique.Monde;
import engine.physique.MoteurPhysique;
import engine.physique.ObjetHeros;
import engine.physique.ObjetMonstre;
import engine.physique.ObjetMur;

public class Game {
    public static void main(String[] args) throws Exception {

        // le moteur physique
        MoteurPhysique moteurPhys;
        // le rendu
        Afficheur affiche;
        // leS controlerS
        ControleurClavier cClavier1 = new ControleurClavier(true);
        // Le monde
        Monde monMonde;

        // Construction du monde
        monMonde = new Monde();

        // on creer le moteur physique
        moteurPhys = new MoteurPhysique();
        // On ajoute le monde au moteur
        moteurPhys.monde = monMonde;
        // on creer l'afficheur du monde
        affiche = new Afficheur(moteurPhys.monde);

        int imgWidth = affiche.getDecor().getImage().getWidth();
        int imgHeight = affiche.getDecor().getImage().getHeight();
        Repere.setWindowHeight(imgHeight);

        //////////////////////
        // Les MURS
        /////////////////////

        // Top Border
        monMonde.addMur(
                0,
                imgHeight,
                imgWidth,
                2
        );
        // Left Border
        monMonde.addMur(
                0,
                0,
                2,
                imgHeight
        );
        // Bottom Border
        monMonde.addMur(
                0,
                0,
                imgWidth,
                2
        );
        // Right Border
        monMonde.addMur(
                imgWidth - 2,
                0,
                2,
                imgHeight
        );

        //////////////////////
        // Le Hero
        /////////////////////
        monMonde.addHero(0, 0, 50, 20);

        monMonde.addHero(0, 0, 500, 20);
        monMonde.heros.get(1).sprites.assignNewImage("assets/misc/hero.png");
        // monMonde.heros.get(1).

        // Gestion de la boucle principale
        BouclePrincipale maBoucle = new BouclePrincipale();
        // Ajout du jeu physique
        JeuPhysique MonJeuPhysique = new JeuPhysique();
        maBoucle.jeuPhysique = MonJeuPhysique;
        // Ajout du controler a le fenetre
        maBoucle.cClavier = cClavier1;
        // Ajout de la vue au jeu
        maBoucle.jeuPhysique.affiche = affiche;
        // Ajout du jeu a la boucle
        maBoucle.jeuPhysique.moteurPhys = moteurPhys;
        maBoucle.jeuPhysique.moteurPhys.monde = moteurPhys.monde;

        maBoucle.lanceBouclePrincipale();
    }
}
