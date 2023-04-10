import engine.main.BouclePrincipale;
import engine.afficheur.Afficheur;
import engine.controle.KeyboardController;
import engine.hud.TestHud;
import engine.main.JeuPhysique;
import engine.physique.World;
import engine.physique.PhysicsEngine;
import engine.afficheur.CoordinateSystem;
import engine.physique.World;
import engine.physique.PhysicsEngine;
import engine.physique.Player;
import engine.physique.Enemy;
import engine.physique.Wall;

public class Game {
    public static void main(String[] args) throws Exception {

        // le moteur physique
        PhysicsEngine moteurPhys;
        // le rendu
        Afficheur affiche;
        // leS controlerS
        KeyboardController cClavier1 = new KeyboardController(true);
        // Le monde
        World monMonde;

        // Construction du monde
        monMonde = new World();

        // on creer le moteur physique
        moteurPhys = new PhysicsEngine();
        // On ajoute le monde au moteur
        moteurPhys.world = monMonde;
        monMonde.setHero(0, 0, 50, 20, 100, 10);
        // on creer l'afficheur du monde
        affiche = new Afficheur(moteurPhys.world);

        int imgWidth = affiche.getDecor().size();
        int imgHeight = affiche.getDecor().size();
        CoordinateSystem.setWindowHeight(imgHeight);

        //////////////////////
        // Les MURS
        /////////////////////

        int BORDER_WIDTH = 2;

        // Top Border
        monMonde.addMur(
                0,
                imgHeight,
                imgWidth,
                BORDER_WIDTH
        );
        // Left Border
        monMonde.addMur(
                0 - BORDER_WIDTH,
                0,
                BORDER_WIDTH,
                imgHeight
        );
        // Bottom Border
        monMonde.addMur(
                0,
                0 - BORDER_WIDTH,
                imgWidth,
                2
        );
        // Right Border
        monMonde.addMur(
                imgWidth + BORDER_WIDTH,
                0,
                BORDER_WIDTH,
                imgHeight
        );

        monMonde.addMonstre(1, 1, 100, 100);
        

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
        maBoucle.jeuPhysique.moteurPhys.world = moteurPhys.world;

        maBoucle.lanceBouclePrincipale();
    }
}
