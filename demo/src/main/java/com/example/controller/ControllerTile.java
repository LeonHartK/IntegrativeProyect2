package com.example.controller;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

import javax.imageio.ImageIO;

import com.example.Game;
import com.example.model.Graph;
import com.example.model.Pair;
import com.example.model.Vertex;
import com.example.view.GamePanel;
import com.example.view.Tile;

public class ControllerTile {

    Graph<Vertex<Pair<Integer, Integer>>> newGraph = new Graph<>(false);
    GamePanel gp;
    ControllerKey createPath;
    public Tile[] tile;
    public static int mapTileNum[][];
    public long currentTime;
    public boolean generatingPath;
    public boolean correct; // Mapa generado correctamente

    public ControllerTile(GamePanel gPanel) {
        generatingPath = false;

        correct = false;

        currentTime = System.currentTimeMillis();

        this.gp = gPanel;

        createPath = new ControllerKey();

        tile = new Tile[10];

        mapTileNum = new int[51][51];

        getTileImage();

        loadMap("/demo/src/main/java/com/example/resources/Map/laberinto.txt");
    }

    public void loadMap(String path) {
        while (!correct) {
            try {
                File newFile = new File(
                        System.getProperty("user.dir") + path);
                InputStream is = new FileInputStream(newFile);
                BufferedReader br = new BufferedReader(new InputStreamReader(is));

                int col = 0;
                int row = 0;

                while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
                    String line = br.readLine();
                    while (col < gp.maxWorldCol) {
                        String numbers[] = line.split(" ");

                        int num = Integer.parseInt(numbers[col]);

                        mapTileNum[col][row] = num;
                        col++;
                    }
                    if (col == gp.maxWorldCol) {
                        col = 0;
                        row++;
                    }
                }
                br.close();

                // for (int i = 0; i < mapTileNum.length; i++) {
                // for (int j = 0; j < mapTileNum[i].length; j++) {
                // System.out.print(mapTileNum[i][j] + " "); // Mostrar el elemento
                // }
                // System.out.println(); // Cambiar de línea al final de cada fila
                // }
            } catch (Exception e) {

            }
            for (int i = 0; i < mapTileNum.length; i++) {
                for (int j = 0; j < mapTileNum[i].length; j++) {
                    if (mapTileNum[i][j] != 0) {
                        correct = true; // Si se encuentra un elemento no cero, retorna falso
                    }
                }
            }
        }

    }

    public void getTileImage() {
        try {
            tile[0] = new Tile();
            File newFile = new File(
                    System.getProperty("user.dir") + "/demo/src/main/java/com/example/resources/Map/Path.png");
            tile[0].image = ImageIO.read(newFile);
            tile[0].collision = true;
            tile[1] = new Tile();
            File newFile2 = new File(
                    System.getProperty("user.dir") + "/demo/src/main/java/com/example/resources/Map/Wall.png");
            tile[1].image = ImageIO.read(newFile2);
            tile[2] = new Tile();
            File newFile3 = new File(
                    System.getProperty("user.dir") + "/demo/src/main/java/com/example/resources/Map/GreenPath.png");
            tile[2].image = ImageIO.read(newFile3);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {

        int worldCol = 0;
        int worldRow = 0;

        HashMap<String, int[]> path = new HashMap<>();

        if (Game.getGamePanel().getPlayer().getKeyH().KeyPJustPressed()) {
            generatingPath = true;
            currentTime = System.currentTimeMillis();
            path = ControllerMazeGenerator.getMinimunPath();
        }

        if (generatingPath) {
            if (System.currentTimeMillis() - currentTime > 1000) {
                generatingPath = false;
            }
        }

        while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {

            int tileNum = mapTileNum[worldCol][worldRow];

            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                    worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                    worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                    worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {

                if (generatingPath && path.containsKey(worldCol + "," + worldRow)) {
                    g2.drawImage(tile[2].image, screenX, screenY, gp.tileSize, gp.tileSize,
                            null);
                    System.out.println("Generando");
                } else {
                    g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
                }
            }

            worldCol++;

            if (worldCol == gp.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }

        }
    }

}
