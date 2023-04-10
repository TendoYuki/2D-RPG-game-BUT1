import engine.main.BouclePrincipale;
import engine.controller.KeyboardController;
import engine.dialog.Dialog;
import engine.dialog.DialogController;
import engine.hud.Hud;
import engine.hud.gameover.GameOver;
import engine.hud.menu.Menu;
import engine.hud.npc.NPCHud;
import engine.hud.player.PlayerHud;
import engine.hud.shop.Shop;
import engine.main.GamePhysics;
import engine.physics.PhysicsEngine;
import engine.physics.World;
import engine.tiles.Atlas;
import engine.tiles.TileMap;
import engine.trigger.Trigger;
import engine.trigger.TriggerMap;
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
        NPCHud npcHud;

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
        TriggerMap triggerMap = new TriggerMap(world.player, tileMap);
        triggerMap.addTrigger(4, new Trigger() {

            @Override
            public void onTriggered() {
                System.out.println("triggered !");
                
            }
            
        });
        world.addTriggerMap(triggerMap);

        int imgWidth = display.getDecor().size();
        int imgHeight = display.getDecor().size();
        CoordinateSystem.setWindowHeight(imgHeight);

        // Dialogue du gardien
        Dialog npc1 = new Dialog();
        npc1.addLine(new String[] {
            "Bienvenue aventurier ! ",
            "J'espère que tu n'as pas eu de problème sur le chemin jusque ici",
            "Appuie sur «F» pour parler au gardien afin qu'il te donne ta première quête."
        });
        npc1.addLine(new String[] {
            "C’est donc toi le redoutable aventurier dont on m’a parlé.",
            "Ça fait plaisir de rencontrer quelqu’un qui pourrait m’aider.",
            "Voilà, j’ai  un problème depuis quelques semaines maintenant.",
            "Un objet qui m’est très chère est perdu dans ce donjon.",
            "Ramène le moi et je te promet une récompense. "
        });
        npc1.addLine(new String[] {
            "Pas si vite! ",
            "Tu n’as encore appris à te battre, tu ne peux donc pas rentrer pour le moment.",
            "Vient ici que je t’apprennes toutes mes techniques."
        });
        npc1.addLine(new String[] {
            "Tu peux appuyer sur l'icone $ en bas a droite de ton ecran afin d'ameliorer ",
            "tes competences a tout moment",
            "Tu peux augmenter ta vie, ta defense et ton attaque",
            "Si tu as des pieces d'or sur toi je te conseille d'ameliorer dans un premier temps,",
            "bien entendu tu n'es pas oblige de m'ecouter mais tu sera le seul responsable",
            "s'il t'arrive quelque chose.",
        });
        Dialog npc1End = new Dialog();
        npc1End.addLine(new String[]{
            "Oh tu es de retour, ça fait plaisir de te revoir, tu as bien mon objet hein ?"
        });
        npc1End.addLine(new String[]{
            "Oh mon dieu tu as vraiment reussis a le recuperer, me voila bien surpris",
            "Tiens donc la recompense que je t'ai promis"
        });
        npc1End.addLine(new String[]{
            "Vous avez recu la competence DIEU DU DEV !!",
            "Plus aucun programme ne vous sera impossible a faire !"
        });

        // dialogue des portes
        Dialog porteFermee = new Dialog();
        porteFermee.addLine(new String[]{
            "La porte est fermee",
            "Tuez tout les monstres afin qu'elle s'ouvre"
        });
        
        Dialog porteOuverte = new Dialog();
        porteOuverte.addLine(new String[] {
            "Vous avez tuer tout les monstres de cette zone",
            "Les portes s'ouvrent"
        });

        // Dialogue du boss
        Dialog bossStart = new Dialog();
        bossStart.addLine(new String[]{
            "Oh tient tient... Un nouvel aventurier qui pense pouvroir me batre",
            "MUAHAHAHAHAHHAHAHAHAHA"
        });

        Dialog bossMort = new Dialog();
        bossMort.addLine(new String[]{
            "AAAAAAAAAARRrrrrrrrrrrrrrghhhhhhhhhh"
        });
        bossMort.addLine(new String[] {
            "Vous avez recupere le baton magique du gardien"
        });

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

        npcHud = new NPCHud(0,display.getHeight() - display.getHeight()/5, display.getWidth(),display.getHeight()/5);
        npcHud.setInteractable(false);
        npcHud.setIsShown(false);
        world.addHud("npc", npcHud);

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

        // Adding NPCs + link dialogs
        world.addNPC(0, 0, 200, 200);
        world.npcs.get(0).addDialog("start", npc1);
        world.npcs.get(0).addDialog("end", npc1End);
        world.npcs.get(0).setActiveDialog("start");

        world.addNPC(0,0,400,300);
        world.npcs.get(1).addDialog("start", bossStart);
        world.npcs.get(1).addDialog("end", bossMort);
        world.npcs.get(1).setActiveDialog("end");

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
