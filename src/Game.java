import engine.main.BouclePrincipale;
import engine.controller.KeyboardController;
import engine.hud.Hud;
import engine.hud.gameover.GameOver;
import engine.hud.menu.Menu;
import engine.hud.player.PlayerHud;
import engine.hud.shop.Shop;
import engine.main.GamePhysics;
import engine.physics.PhysicsEngine;
import engine.physics.World;
import engine.tiles.Atlas;
import engine.tiles.TileMap;
import engine.view.CoordinateSystem;
import engine.view.Scene;
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

        PlayerHud playerHud;
        Shop shop;
        Menu menu;
        GameOver gameOver;

        // Construction du monde
        world = new World();

        // on creer le moteur physique
        physicsEngine = new PhysicsEngine();
        // On ajoute le monde au moteur
        physicsEngine.world = world;
        world.setPlayer(0, 0, 50, 20, 100, 10);

        
        // Creating world display
        // Displaying tileMap
		Atlas atlas = new Atlas(
            "assets/tiles/tilemap.png",
            16,
            1,
            4,
			2
        );
        TileMap tileMap = new TileMap(
            16,
            2,
            new int[][] {
                {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                {1,2,2,2,2,2,2,2,2,2,2,2,2,2,2,1},
                {1,2,3,3,3,3,3,3,3,3,3,3,3,3,2,1},
                {1,2,3,3,3,3,3,3,3,3,3,3,3,3,2,1},
                {1,2,3,3,3,3,3,3,3,3,3,3,3,3,2,1},
                {1,2,3,3,3,4,4,4,4,4,4,3,3,3,2,1},
                {1,2,3,3,3,4,4,4,4,4,4,3,3,3,2,1},
                {1,2,3,3,3,4,4,4,4,4,4,3,3,3,2,1},
                {1,2,3,3,3,4,4,4,4,4,4,3,3,3,2,1},
                {1,2,3,3,3,4,4,4,4,4,4,3,3,3,2,1},
                {1,2,3,3,3,4,4,4,4,4,4,3,3,3,2,1},
                {1,2,3,3,3,3,3,3,3,3,3,3,3,3,2,1},
                {1,2,3,3,3,3,3,3,3,3,3,3,3,3,2,1},
                {1,2,3,3,3,3,3,3,3,3,3,3,3,3,2,1},
                {1,2,2,2,2,2,2,2,2,2,2,2,2,2,2,1},
                {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
            },
            atlas
        );
        display = new Display(physicsEngine.world, new Scene(tileMap));

        int imgWidth = display.getDecor().size();
        int imgHeight = display.getDecor().size();
        CoordinateSystem.setWindowHeight(imgHeight);

        // Adding huds
        shop = new Shop(
            world.player,
            display.getWidth() / 2 - (int) (512 / 1.4) / 2,
            display.getHeight() / 2 - (int) (512 / 1.4) / 2,
            (int) (512 / 1.4),
            (int) (512 / 1.4)
        );
        shop.setIsShown(false);
        shop.setInteractable(false);
        world.addHud("shop", shop);

        playerHud = new PlayerHud(world.player, shop);
        playerHud.setIsShown(false);
        playerHud.setInteractable(false);
        world.addHud("hud", playerHud);

        menu = new Menu(playerHud,0, 0, display.getWidth(), display.getHeight());
        world.addHud("menu", menu);

        gameOver = new GameOver(0, 0, display.getWidth(), display.getHeight());
        gameOver.setIsShown(false);
        gameOver.setInteractable(false);
        world.addHud("gameOver", gameOver);

        //Adding huds listeners
        for (Hud hud : world.huds.values()) {
            display.addMouseListener(hud.getMouseController());
        }

        // Adding walls
        int BORDER_WIDTH = 2;

        // Top Border
        world.addWall(
                0,
                imgHeight,
                imgWidth,
                BORDER_WIDTH);
        // Left Border
        world.addWall(
                0 - BORDER_WIDTH,
                0,
                BORDER_WIDTH,
                imgHeight);
        // Bottom Border
        world.addWall(
                0,
                0 - BORDER_WIDTH,
                imgWidth,
                2);
        // Right Border
        world.addWall(
                imgWidth + BORDER_WIDTH,
                0,
                BORDER_WIDTH,
                imgHeight);

        // Adding enemies
        world.addEnemy(0, 0, 100, 100);

        world.addNPC(0, 0, 200, 200);

        // Gestion de la boucle principale
        BouclePrincipale maBoucle = new BouclePrincipale();
        // Ajout du jeu physique
        GamePhysics GP = new GamePhysics();
        maBoucle.jeuPhysique = GP;
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
