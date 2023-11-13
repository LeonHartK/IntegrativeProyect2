package com.example.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import com.example.controller.ControllerKey;

public class GamePanel extends JPanel implements Runnable {

    final int originalTileSize = 16;
    final int scale = 3;

    public final int tileSize = originalTileSize * scale;

    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol;
    final int screenHeight = tileSize * maxScreenRow;

    Thread gameThread;

    ControllerKey KeyH = new ControllerKey();
    Player player = new Player(this, KeyH);

    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;

    int FPS = 60;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);

        this.addKeyListener(KeyH);
        this.setFocusable(true);
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

        player.draw(g2);

        g2.dispose();
    }
}
