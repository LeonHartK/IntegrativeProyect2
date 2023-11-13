package com.example.view;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Entity {
    public int worldX, worldY, speed;
    public BufferedImage up1, up2, up3, up4, down1, down2, down3, down4, right1, right2, right3, right4, left1, left2,
            left3, left4;
    public String direction;
    public int spriteCounter = 0;
    public int spriteNumber = 1;
    public Rectangle solidArea;
    public boolean collision = false;
}
