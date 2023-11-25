package com.example.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import com.example.controller.AssertSetter;
import com.example.controller.ControllerCollision;
import com.example.controller.ControllerKey;
import com.example.controller.ControllerTile;

public class GamePanel extends JPanel implements Runnable {

    final int originalTileSize = 16;
    final int scale = 3;

    public final int tileSize = originalTileSize * scale;

    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol;
    final int screenHeight = tileSize * maxScreenRow;

    Thread gameThread;

    public ControllerTile tileM = new ControllerTile(this);
    ControllerKey KeyH = new ControllerKey();
    public Player player = new Player(this, KeyH);

    public Player getPlayer() {
        return player;
    }

    ControllerCollision cC = new ControllerCollision(this);
    public AssertSetter aSetter = new AssertSetter(this);
    public SuperObject obj[] = new SuperObject[10];

    int FPS = 60;

    public final int maxWorldCol = 51;
    public final int maxWorldRow = 51;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);

        this.addKeyListener(KeyH);
        this.setFocusable(true);
    }

    public void setupGame() {
        aSetter.setObject();
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        double dI = 1000000000 / FPS;
        double nDT = System.nanoTime() + dI;

        while (gameThread != null) {

            update();

            repaint();

            try {
                double remainingTime = nDT - System.nanoTime();
                remainingTime = remainingTime / 1000000;

                if (remainingTime < 0) {
                    remainingTime = 0;
                }

                Thread.sleep((long) remainingTime);

                nDT += dI;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void update() {
        player.update();
    }

    public void paintComponent(Graphics gp) {
        super.paintComponent(gp);

        Graphics2D g2 = (Graphics2D) gp;

        tileM.draw(g2);

        for (int i = 0; i < obj.length; i++) {
            if (obj[i] != null) {
                obj[i].draw(g2, this);
            }
        }

        player.draw(g2);

        g2.dispose();
    }
}
