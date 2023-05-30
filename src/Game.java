import engine.main.BouclePrincipale;

import java.util.ArrayList;
import java.util.Arrays;

import engine.controller.KeyboardController;
import engine.dialog.Dialog;
import engine.dialog.DialogController;
import engine.generation.Room;
import engine.generation.MapGenerator;
import engine.hud.Hud;
import engine.hud.end.EndMenu;
import engine.hud.gameover.GameOver;
import engine.hud.map.MapHud;
import engine.hud.menu.Menu;
import engine.hud.npc.DialogHud;
import engine.hud.player.PlayerHud;
import engine.hud.shop.Shop;
import engine.main.GamePhysics;
import engine.physics.Boss;
import engine.physics.Enemy;
import engine.physics.PhysicsEngine;
import engine.physics.World;
import engine.physics.WorldBorder;
import engine.tiles.Directions;
import engine.trigger.Trigger;
import engine.trigger.TriggerMap;
import engine.view.CoordinateSystem;
import engine.view.Coords;
import engine.view.Display;
/** Game class */
public class Game {

    /**
     * Loads a room
     * @param world
     * @param room
     * @param doorHud
     * @param playerHud
     * @param bossDoorClosed
     * @param doorClosed
     */
    public static void loadRoom(Coords playerCoords, World world, Room room, DialogHud doorHud, PlayerHud playerHud, Dialog bossDoorClosed, Dialog doorClosed) {
        if(!world.map.activeRoom.isLocked()) {
            if(!room.isLocked()) {
                for(Enemy enemy: world.map.activeRoom.enemies) {
                    playerHud.removeElement(enemy.healthBar);
                }
                for(Boss boss: world.map.activeRoom.bosses) {
                    playerHud.removeElement(boss.healthBar);
                }
                world.map.setActiveRoom(room.getId());
                world.player.px = playerCoords.getX();
                world.player.py = playerCoords.getY();
                world.setTriggerMapTileMap(world.map.getActiveRoom().getTileMap());
                for(Enemy enemy: room.enemies) {
                    playerHud.addElement(enemy.healthBar);
                }
                for(Boss boss: room.bosses) {
                    playerHud.addElement(boss.healthBar);
                }
            } else {
                DialogController.setCurrentDialog(bossDoorClosed);
                doorHud.setIsShown(true);
            }
        } else {
            DialogController.setCurrentDialog(doorClosed);
            doorHud.setIsShown(true);
        }
    }

    /** Runs the game 
     * @param args
    */
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
        Menu menu;
        GameOver gameOver;
        EndMenu endMenu;
        DialogHud npcHud;
        


        // Construction du monde
        world = new World();

        // on creer le moteur physique
        physicsEngine = new PhysicsEngine();
        // On ajoute le monde au moteur
        physicsEngine.world = world;
        
        Room startRoom = new Room(world, new Directions[] {Directions.UP});
        Room endRoom = new Room(world, Directions.values()); 
        endRoom.lockRoom();


        // WARNING : DO NOT MOVE OR REMOVE
        {
            world.setMap(MapGenerator.GenerateMap(world, startRoom, endRoom, 10, 5, 5));
            display = new Display(physicsEngine.world, world.map);

            world.map.setPosX(
                (display.getWidth()/2) -
                (world.map.size()/2)
            );
            world.map.setPosY(
                (display.getHeight()/2) -
                (world.map.size()/2)
            );

            MapGenerator.populateMap(world.map, 0, 1, new ArrayList<Integer>(
                Arrays.asList(startRoom.getId(), endRoom.getId()))
            );
        }
        // END OF WARNING
        world.setPlayer(0, 0, 256,20, 100, 10);

        DialogHud doorHud = new DialogHud(display, 0,0, display.getWidth(),display.getHeight()/5);
        doorHud.setInteractable(false);
        doorHud.setIsShown(false);
        world.addHud("doorClosed", doorHud);
        // dialogue des portes
        Dialog doorClosed = new Dialog();
        doorClosed.addLine(new String[]{
            "Hop pas si vite !", 
            "la porte est fermée"
        });

        Dialog bossDoorClosed = new Dialog();
        bossDoorClosed.addLine(new String[]{
            "La porte est fermée",
            "Tuez tous les monstres afin qu'elle s'ouvre"
        });

        
        world.mapHud = new MapHud(
            display,
            world.map,
            display.getWidth()  / 2 - (int) (world.map.size() / 1.4 / 2 ),
            display.getHeight() / 2 - (int) (world.map.size() / 1.4 / 2 ),
            (int) (world.map.size() / 1.4),
            (int) (world.map.size() / 1.4)
        );
        world.addHud("mapHud", world.mapHud);

        int imgWidth = display.getMap().size();
        int imgHeight = display.getMap().size();

        CoordinateSystem.setWindowHeight(display.getFrame().getHeight());

        // Dialogue du gardien
        Dialog npc1 = new Dialog();
        npc1.addLine(new String[] {
            "Bienvenue aventurier ! ",
            "J'espère que tu n'as pas eu de problème sur le chemin jusqu'ici",
            "Appuie sur «F» pour parler au gardien afin qu'il te donne ta première quête."
        });
        npc1.addLine(new String[] {
            "C’est donc toi le redoutable aventurier dont on m’a parlé.",
            "Ça fait plaisir de rencontrer quelqu’un qui pourrait m’aider.",
            "Voilà, j’ai  un problème depuis quelques semaines maintenant.",
            "Un objet qui m’est très chère est perdu dans ce donjon.",
            "Ramène le moi et je te promets une récompense. "
        });
        npc1.addLine(new String[] {
            "Pas si vite! ",
            "Tu n’as pas encore appris à te battre, tu ne peux donc pas rentrer pour le moment.",
            "Vient ici que je t’apprennes toutes mes techniques."
        });
        npc1.addLine(new String[] {
            "Tu peux appuyer sur l'icone $ en bas a droite de ton ecran afin d'ameliorer",
            "tes competences a tout moment",
            "Tu peux augmenter ta vie, ta defense et ton attaque",
            "Si tu as des gems d'or sur toi, je te conseille d'ameliorer ton attaque dans un premier temps,",
            "bien entendu tu n'es pas oblige de m'ecouter mais tu seras le seul responsable",
            "s'il t'arrive quelque chose.",
        });
        npc1.addLine(new String[] {
            "Bonne chance à vous aventurier, je vous attend ici"
        });
        Dialog npc1End = new Dialog();
        npc1End.addLine(new String[]{
            "Oh tu es de retour, ça fait plaisir de te revoir, tu as bien mon objet hein ?"
        });
        npc1End.addLine(new String[]{
            "Oh mon dieu tu as vraiment reussi a le recuperer, me voila bien surpris",
            "Tiens donc la recompense que je t'ai promis"
        });
        npc1End.addLine(new String[]{
            "Vous avez recu la competence DIEU DU DEV !!",
            "Plus aucun programme ne vous sera impossible a faire !",
            "Bonne continuation dans votre carriere !"
        });

        // Adding huds
        world.shop = new Shop(
            display,
            world.player,
            display.getWidth() / 2 - (int) (world.map.size() / 1.4 / 2),
            display.getHeight() / 2 - (int) (world.map.size() / 1.4 / 2 ),
            (int) (world.map.size() / 1.4),
            (int) (world.map.size() / 1.4),
            world.map
        );

        playerHud = new PlayerHud(display, world.player, world.shop);
        playerHud.setIsShown(false);
        playerHud.setInteractable(false);
        world.addHud("hud", playerHud);

        world.shop.setIsShown(false);
        world.shop.setInteractable(false);
        world.addHud("shop", world.shop);

        menu = new Menu(display, playerHud,0, 0, display.getWidth(), display.getHeight());
        world.addHud("menu", menu);

        
        gameOver = new GameOver(display, 0, 0, display.getWidth(), display.getHeight());
        gameOver.setIsShown(false);
        gameOver.setInteractable(false);
        world.addHud("gameOver", gameOver);

        endMenu = new EndMenu(display, 0, 0, display.getWidth(), display.getHeight());
        endMenu.setIsShown(false);
        endMenu.setInteractable(false);
        world.addHud("endMenu", endMenu);

        npcHud = new DialogHud(display, 0,0, display.getWidth(),display.getHeight()/5);
        npcHud.setInteractable(false);
        npcHud.setIsShown(false);
        world.addHud("npc", npcHud);

        //Adding huds listeners
        for (Hud hud : world.huds.values()) {
            display.addMouseListener(hud.getMouseController());
        }

        world.setWorldBorder(new WorldBorder(
            world,
            imgWidth-64,
            imgHeight-64
        ));

        // Adding NPCs + link dialogs
        world.map.activeRoom.addNPC(0, 0, 240, 240);
        world.map.activeRoom.npcs.get(0).addDialog("start", npc1);
        world.map.activeRoom.npcs.get(0).addDialog("end", npc1End);
        world.map.activeRoom.npcs.get(0).setActiveDialog("start");

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


        TriggerMap triggerMap = new TriggerMap(world.player, world.map.getActiveRoom().getTileMap());
        world.setTriggerMap(triggerMap);
        triggerMap.addTrigger(1, new Trigger() {

            @Override
            public void onTriggered() {
                Room up = world.map.getAdjacentRoom(Directions.UP);
                loadRoom(new Coords((int)world.player.px, 90), world, up, doorHud, playerHud, bossDoorClosed, doorClosed);
            }

            @Override
            public void onTriggerExit() {
                doorHud.setIsShown(false);
            }

        });
        triggerMap.addTrigger(2, new Trigger() {

            @Override
            public void onTriggered() {
                Room left = world.map.getAdjacentRoom(Directions.LEFT);
                loadRoom(new Coords(world.map.getActiveRoom().size() - 90, (int)world.player.py), world, left, doorHud, playerHud, bossDoorClosed, doorClosed);
            }

            @Override
            public void onTriggerExit() {
                doorHud.setIsShown(false);
            }
            
        });
        triggerMap.addTrigger(3, new Trigger() {

            @Override
            public void onTriggered() {
                Room down = world.map.getAdjacentRoom(Directions.DOWN);
                loadRoom(new Coords((int)world.player.px, world.map.getActiveRoom().size() - 90), world, down, doorHud, playerHud, bossDoorClosed, doorClosed);
            }
            
            @Override
            public void onTriggerExit() {
                doorHud.setIsShown(false);
            }
            
        });
        triggerMap.addTrigger(4, new Trigger() {

            @Override
            public void onTriggered() {
                Room right = world.map.getAdjacentRoom(Directions.RIGHT);
                loadRoom(new Coords(90, (int)world.player.py),world, right, doorHud, playerHud, bossDoorClosed, doorClosed);
            }
            
            @Override
            public void onTriggerExit() {
                doorHud.setIsShown(false);
            }
            
        });
        world.addTriggerMap(triggerMap);


        maBoucle.lanceBouclePrincipale();
    }
    
}

