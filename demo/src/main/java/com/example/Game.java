package com.example;

import javax.swing.JFrame;

import com.example.controller.ControllerMazeGenerator;
import com.example.view.GamePanel;

public class Game {
    public static void main(String[] args) {
        JFrame window = new JFrame();

        Thread randomMap = new Thread(new Runnable() {
            public void run() {
                ControllerMazeGenerator.main(args);
                ;
            }
        });
        randomMap.start();

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Backroom");

        GamePanel gamePanel = new GamePanel();

        window.add(gamePanel);

        window.pack();

        window.setLocationRelativeTo(null);

        window.setVisible(true);

        gamePanel.startGameThread();
    }
}
