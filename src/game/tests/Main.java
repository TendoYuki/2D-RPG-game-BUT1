package game.tests;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import engine.tiles.TileMap;

public class Main{
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new GUI();
                frame.setVisible(true);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(new Dimension(512, 512));
            }
        });
    }
}


class GUI extends JFrame {
    TM tm;
    TileMap tileMap;
    HashMap<Integer, BufferedImage> atlas;
    GUI() {
        super("");
        tm = new TM();
        atlas = new HashMap<Integer, BufferedImage>();

        try{
            for(int i = 1; i <= 4; i++) {
                atlas.put(i, ImageIO.read(new File("assets/tiles/" + i + ".png")));
            }
        }
        catch(Exception e) {}
        tileMap = new TileMap(
            16,
            new int[][] {{1,2,3,4,1}, {1,3,2,2,3},{1,2,3,4,2},{1,2,3,4,1}, {1,3,2,2,3},{1,2,3,4,2},{1,2,3,4,1}, {1,3,2,2,3},{1,2,3,4,2},{1,2,3,4,1}, {1,3,2,2,3},{1,2,3,4,2},{1,2,3,4,1}, {1,3,2,2,3},{1,2,3,4,2},{1,2,3,4,1}, {1,3,2,2,3},{1,2,3,4,2}},
            atlas
        );
        add(tm, BorderLayout.CENTER);
    }
    class TM extends JPanel {
        public void paintComponent(Graphics g) {
            tileMap.draw(g);
        }
    }
}