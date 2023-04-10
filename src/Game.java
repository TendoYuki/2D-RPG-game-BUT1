import engine.main.BouclePrincipale;
import engine.controller.KeyboardController;
import engine.hud.TestHud;
import engine.main.GamePhysics;
import engine.physics.Enemy;
import engine.physics.PhysicsEngine;
import engine.physics.Player;
import engine.physics.Wall;
import engine.physics.World;
import engine.view.CoordinateSystem;
import engine.view.Display;

public class Game {
    public static void main(String[] args) throws Exception {

        // le moteur physique
        PhysicsEngine physicsEngine;
        // le rendu
        Display display;
        // leS controlerS
        KeyboardController keyboardController = new KeyboardController(true);
        // Le monde
        World world;

        // Construction du monde
        world = new World();

        // on creer le moteur physique
        physicsEngine = new PhysicsEngine();
        // On ajoute le monde au moteur
        physicsEngine.world = world;
        world.setPlayer(0, 0, 50, 20, 100, 10);
        // on creer l'afficheur du monde
        display = new Display(physicsEngine.world);

        int imgWidth = display.getDecor().size();
        int imgHeight = display.getDecor().size();
        CoordinateSystem.setWindowHeight(imgHeight);

        //////////////////////
        // Les MURS
        /////////////////////

        int BORDER_WIDTH = 2;

        // Top Border
        world.addWall(
                0,
                imgHeight,
                imgWidth,
                BORDER_WIDTH
        );
        // Left Border
        world.addWall(
                0 - BORDER_WIDTH,
                0,
                BORDER_WIDTH,
                imgHeight
        );
        // Bottom Border
        world.addWall(
                0,
                0 - BORDER_WIDTH,
                imgWidth,
                2
        );
        // Right Border
        world.addWall(
                imgWidth + BORDER_WIDTH,
                0,
                BORDER_WIDTH,
                imgHeight
        );

        world.addEnemy(1, 1, 100, 100);
        

        // Gestion de la boucle principale
        BouclePrincipale maBoucle = new BouclePrincipale();
        // Ajout du jeu physique
        GamePhysics MonJeuPhysique = new GamePhysics();
        maBoucle.jeuPhysique = MonJeuPhysique;
        // Ajout du controler a le fenetre
        maBoucle.cClavier = keyboardController;
        // Ajout de la vue au jeu
        maBoucle.jeuPhysique.display = display;
        // Ajout du jeu a la boucle
        maBoucle.jeuPhysique.physicsEngine = physicsEngine;
        maBoucle.jeuPhysique.physicsEngine.world = physicsEngine.world;

        maBoucle.lanceBouclePrincipale();
    }
}
