import engine.main.BouclePrincipale;

import java.util.ArrayList;
import java.util.Arrays;

import engine.controller.KeyboardController;
import engine.dialog.Dialog;
import engine.dialog.DialogController;
import engine.generation.Room;
import engine.generation.MapGenerator;
import engine.hud.Hud;
import engine.hud.gameover.GameOver;
import engine.hud.map.MapHud;
import engine.hud.menu.Menu;
import engine.hud.npc.DialogHud;
import engine.hud.player.PlayerHud;
import engine.hud.shop.Shop;
import engine.main.GamePhysics;
import engine.physics.Enemy;
import engine.physics.PhysicsEngine;
import engine.physics.World;
import engine.physics.WorldBorder;
import engine.tiles.Directions;
import engine.trigger.Trigger;
import engine.trigger.TriggerMap;
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


        PlayerHud playerHud;
        Shop shop;
        Menu menu;
        GameOver gameOver;
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

        world.setMap(MapGenerator.GenerateMap(world, startRoom, endRoom, 10, 5, 5));
        MapGenerator.populateMap(world.map, 0, 5, new ArrayList<Integer>(
            Arrays.asList(startRoom.getId(), endRoom.getId()))
        );

        world.setPlayer(0, 0, 250,250, 100, 10);
        display = new Display(physicsEngine.world, world.map);

        DialogHud doorHud = new DialogHud(display, 0,display.getHeight() - display.getHeight()/5, display.getWidth(),display.getHeight()/5);
        doorHud.setInteractable(false);
        doorHud.setIsShown(false);
        world.addHud("doorClosed", doorHud);
        // dialogue des portes
        Dialog doorClosed = new Dialog();
        doorClosed.addLine(new String[]{
            "La porte est fermée",
            "Tuez tous les monstres de la pièce afin qu'elle s'ouvre"
        });

        Dialog bossDoorClosed = new Dialog();
        bossDoorClosed.addLine(new String[]{
            "La porte est fermée",
            "Tuez tous les monstres du donjon afin qu'elle s'ouvre"
        });

        
        world.mapHud = new MapHud(
            display,
            world.map,
            (display.getWidth() / 2 - (int) (world.map.size()/1.4)/2) -5,
            (display.getHeight() / 2 - (int) (world.map.size()/1.4)/2) -15,
            (int) (world.map.size()/1.4),
            (int) (world.map.size()/1.4)
        );
        world.addHud("mapHud", world.mapHud);

        int imgWidth = display.getMap().size();
        int imgHeight = display.getMap().size();
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
            "Ramène le moi et je te promets une récompense. "
        });
        npc1.addLine(new String[] {
            "Pas si vite! ",
            "Tu n’as encore appris à te battre, tu ne peux donc pas rentrer pour le moment.",
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
            "Plus aucun programme ne vous sera impossible a faire !"
        });

       
        
        Dialog porteOuverte = new Dialog();
        porteOuverte.addLine(new String[] {
            "Vous avez tue tous les monstres de cette zone",
            "Les portes s'ouvrent"
        });

        // Dialogue du boss
        Dialog bossStart = new Dialog();
        bossStart.addLine(new String[]{
            "Oh tient tient... Un nouvel aventurier qui pense pouvoir me battre",
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
            display,
            world.player,
            (display.getWidth() / 2 - (int) (512 / 1.4) / 2) -5,
            (display.getHeight() / 2 - (int) (512 / 1.4) / 2) -15,
            (int) (512 / 1.4),
            (int) (512 / 1.4)
        );
        shop.setIsShown(false);
        shop.setInteractable(false);
        world.addHud("shop", shop);

        playerHud = new PlayerHud(display, world.player, shop);
        playerHud.setIsShown(false);
        playerHud.setInteractable(false);
        world.addHud("hud", playerHud);

        menu = new Menu(display, playerHud,0, 0, display.getWidth(), display.getHeight());
        world.addHud("menu", menu);

        
        gameOver = new GameOver(display, 0, 0, display.getWidth(), display.getHeight());
        gameOver.setIsShown(false);
        gameOver.setInteractable(false);
        world.addHud("gameOver", gameOver);

        npcHud = new DialogHud(display, 0,display.getHeight() - display.getHeight()/5, display.getWidth(),display.getHeight()/5);
        npcHud.setInteractable(false);
        npcHud.setIsShown(false);
        world.addHud("npc", npcHud);

        //Adding huds listeners
        for (Hud hud : world.huds.values()) {
            display.addMouseListener(hud.getMouseController());
        }

        world.setWorldBorder(new WorldBorder(
            world,
            imgWidth,
            imgHeight
        ));

        // Adding enemies
        world.map.activeRoom.addEnemy(0, 0, 100, 100,1);

        // Adding NPCs + link dialogs
        world.map.activeRoom.addNPC(0, 0, 200, 200);
        world.map.activeRoom.npcs.get(0).addDialog("start", npc1);
        world.map.activeRoom.npcs.get(0).addDialog("end", npc1End);
        world.map.activeRoom.npcs.get(0).setActiveDialog("start");

        world.map.activeRoom.addNPC(0,0,400,300);
        world.map.activeRoom.npcs.get(1).addDialog("start", bossStart);
        world.map.activeRoom.npcs.get(1).addDialog("end", bossMort);
        world.map.activeRoom.npcs.get(1).setActiveDialog("end");

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
                if(!world.map.activeRoom.isLocked()) {
                    if(!up.isLocked()) {
                        for(Enemy enemy: world.map.activeRoom.enemies) {
                            playerHud.removeElement(enemy.healthBar);
                        }
                        world.map.setActiveRoom(up.getId());
                        world.player.py = 50;
                        world.setTriggerMapTileMap(world.map.getActiveRoom().getTileMap());
                        for(Enemy enemy: up.enemies) {
                            playerHud.addElement(enemy.healthBar);
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

            @Override
            public void onTriggerExit() {
                doorHud.setIsShown(false);
            }

        });
        triggerMap.addTrigger(2, new Trigger() {

            @Override
            public void onTriggered() {
                Room left = world.map.getAdjacentRoom(Directions.LEFT);
                if(!world.map.activeRoom.isLocked()) {
                    if(!left.isLocked()) {
                        for(Enemy enemy: world.map.activeRoom.enemies) {
                            playerHud.removeElement(enemy.healthBar);
                        }
                        world.map.setActiveRoom(left.getId());
                        world.player.px = world.map.getActiveRoom().getTileMap().size() - 50;
                        world.setTriggerMapTileMap(world.map.getActiveRoom().getTileMap());
                        for(Enemy enemy: left.enemies) {
                            playerHud.addElement(enemy.healthBar);
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

            @Override
            public void onTriggerExit() {
                doorHud.setIsShown(false);
            }
            
        });
        triggerMap.addTrigger(3, new Trigger() {

            @Override
            public void onTriggered() {
                Room down = world.map.getAdjacentRoom(Directions.DOWN);
                if(!world.map.activeRoom.isLocked()) {
                    if(!down.isLocked()) {
                        for(Enemy enemy: world.map.activeRoom.enemies) {
                            playerHud.removeElement(enemy.healthBar);
                        }
                        world.map.setActiveRoom(down.getId());
                        world.player.py = world.map.getActiveRoom().getTileMap().size() - 50;
                        world.setTriggerMapTileMap(world.map.getActiveRoom().getTileMap());
                        for(Enemy enemy: down.enemies) {
                            playerHud.addElement(enemy.healthBar);
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
            
            @Override
            public void onTriggerExit() {
                doorHud.setIsShown(false);
            }
            
        });
        triggerMap.addTrigger(4, new Trigger() {

            @Override
            public void onTriggered() {
                Room right = world.map.getAdjacentRoom(Directions.RIGHT);
                if(!world.map.activeRoom.isLocked()) {
                    if(!right.isLocked()) {
                        for(Enemy enemy: world.map.activeRoom.enemies) {
                            playerHud.removeElement(enemy.healthBar);
                        }
                        world.map.setActiveRoom(right.getId());
                        world.player.px = 50;
                        world.setTriggerMapTileMap(world.map.getActiveRoom().getTileMap());
                        for(Enemy enemy: right.enemies) {
                            playerHud.addElement(enemy.healthBar);
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
            
            @Override
            public void onTriggerExit() {
                doorHud.setIsShown(false);
            }
            
        });
        world.addTriggerMap(triggerMap);


        maBoucle.lanceBouclePrincipale();
    }
}
