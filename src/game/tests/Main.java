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

import engine.tiles.Atlas;
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
    Atlas atlas;
    GUI() {
        super("");
        tm = new TM();
        atlas = new Atlas(
            "assets/tiles/tilemap.png",
            16,
            1,
            4
        );
        System.out.println(atlas);
        tileMap = new TileMap(
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
        add(tm, BorderLayout.CENTER);
    }
    class TM extends JPanel {
        public void paintComponent(Graphics g) {
            tileMap.draw(g);
        }
    }
}