import engine.main.BouclePrincipale;
import engine.afficheur.Afficheur;
import engine.controle.ControleurClavier;
import engine.hud.TestHud;
import engine.main.JeuPhysique;
import engine.physique.Monde;
import engine.physique.MoteurPhysique;
import engine.afficheur.Repere;
import engine.physique.Monde;
import engine.physique.MoteurPhysique;
import engine.physique.Heros;
import engine.physique.Monstre;
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
        monMonde.addHero(0, 0, 50, 20, 100, 10);
        // on creer l'afficheur du monde
        affiche = new Afficheur(moteurPhys.monde);

        int imgWidth = affiche.getDecor().size();
        int imgHeight = affiche.getDecor().size();
        Repere.setWindowHeight(imgHeight);

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
        maBoucle.jeuPhysique.moteurPhys.monde = moteurPhys.monde;

        maBoucle.lanceBouclePrincipale();
    }
}
