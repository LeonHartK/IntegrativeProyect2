package com.example.view;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import com.example.controller.ControllerKey;

public class Player extends Entity {

    GamePanel gp;

    ControllerKey KeyH;

    public Player(GamePanel gp, ControllerKey KeyH) {
        this.gp = gp;
        this.KeyH = KeyH;

        setDefault();
        getPlayerImage();
    }

    public void setDefault() {
        x = 100;
        y = 100;
        speed = 4;
        direction = "down";
    }

    public void getPlayerImage() {
        try {

            // Up

            File fileup1 = new File(
                    System.getProperty("user.dir") + "/demo/src/main/java/com/example/resources/Player/walkingUp1.png");
            up1 = ImageIO.read(fileup1);
            File fileup2 = new File(
                    System.getProperty("user.dir") + "/demo/src/main/java/com/example/resources/Player/walkingUp2.png");
            up2 = ImageIO.read(fileup2);
            File fileup3 = new File(
                    System.getProperty("user.dir") + "/demo/src/main/java/com/example/resources/Player/walkingUp3.png");
            up3 = ImageIO.read(fileup3);
            File fileup4 = new File(
                    System.getProperty("user.dir") + "/demo/src/main/java/com/example/resources/Player/walkingUp4.png");
            up4 = ImageIO.read(fileup4);

            // Down

            File fileDown1 = new File(
                    System.getProperty("user.dir")
                            + "/demo/src/main/java/com/example/resources/Player/walkingDown1.png");
            down1 = ImageIO.read(fileDown1);
            File fileDown2 = new File(
                    System.getProperty("user.dir")
                            + "/demo/src/main/java/com/example/resources/Player/walkingDown2.png");
            down2 = ImageIO.read(fileDown2);
            File fileDown3 = new File(
                    System.getProperty("user.dir")
                            + "/demo/src/main/java/com/example/resources/Player/walkingDown3.png");
            down3 = ImageIO.read(fileDown3);
            File fileDown4 = new File(
                    System.getProperty("user.dir")
                            + "/demo/src/main/java/com/example/resources/Player/walkingDown4.png");
            down4 = ImageIO.read(fileDown4);

            // Right

            File fileRight1 = new File(
                    System.getProperty("user.dir")
                            + "/demo/src/main/java/com/example/resources/Player/walkingRight1.png");
            right1 = ImageIO.read(fileRight1);
            File fileRight2 = new File(
                    System.getProperty("user.dir")
                            + "/demo/src/main/java/com/example/resources/Player/walkingRight2.png");
            right2 = ImageIO.read(fileRight2);
            File fileRight3 = new File(
                    System.getProperty("user.dir")
                            + "/demo/src/main/java/com/example/resources/Player/walkingRight3.png");
            right3 = ImageIO.read(fileRight3);
            File fileRight4 = new File(
                    System.getProperty("user.dir")
                            + "/demo/src/main/java/com/example/resources/Player/walkingRight4.png");
            right4 = ImageIO.read(fileRight4);

            // left

            File fileLeft1 = new File(
                    System.getProperty("user.dir")
                            + "/demo/src/main/java/com/example/resources/Player/walkingLeft1.png");
            left1 = ImageIO.read(fileLeft1);
            File fileLeft2 = new File(
                    System.getProperty("user.dir")
                            + "/demo/src/main/java/com/example/resources/Player/walkingLeft2.png");
            left2 = ImageIO.read(fileLeft2);
            File fileLeft3 = new File(
                    System.getProperty("user.dir")
                            + "/demo/src/main/java/com/example/resources/Player/walkingLeft3.png");
            left3 = ImageIO.read(fileLeft3);
            File fileLeft4 = new File(
                    System.getProperty("user.dir")
                            + "/demo/src/main/java/com/example/resources/Player/walkingLeft4.png");
            left4 = ImageIO.read(fileLeft4);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update() {

        if (KeyH.upPressed == true || KeyH.downPressed == true || KeyH.rightPressed == true
                || KeyH.leftPressed == true) {
            if (KeyH.upPressed == true) {
                direction = "up";
                y -= speed;
            } else if (KeyH.downPressed == true) {
                direction = "down";
                y += speed;
            } else if (KeyH.leftPressed == true) {
                direction = "left";
                x -= speed;
            } else if (KeyH.rightPressed == true) {
                direction = "right";
                x += speed;
            }

            spriteCounter++;
            if (spriteCounter > 10) {
                if (spriteNumber == 1) {
                    spriteNumber = 2;
                } else if (spriteNumber == 2) {
                    spriteNumber = 3;
                } else if (spriteNumber == 3) {
                    spriteNumber = 4;
                } else {
                    spriteNumber = 1;
                }
                spriteCounter = 0;
            }
        }

    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;

        switch (direction) {
            case "up":
                if (spriteNumber == 1) {
                    image = up1;
                }
                if (spriteNumber == 2) {
                    image = up2;
                }
                if (spriteNumber == 3) {
                    image = up3;
                }
                if (spriteNumber == 3) {
                    image = up3;
                }
                break;
            case "down":
                if (spriteNumber == 1) {
                    image = down1;
                }
                if (spriteNumber == 2) {
                    image = down2;
                }
                if (spriteNumber == 3) {
                    image = down3;
                }
                if (spriteNumber == 4) {
                    image = down4;
                }
                break;
            case "left":
                if (spriteNumber == 1) {
                    image = left1;
                }
                if (spriteNumber == 2) {
                    image = left2;
                }
                if (spriteNumber == 3) {
                    image = left3;
                }
                if (spriteNumber == 4) {
                    image = left4;
                }
                break;
            case "right":
                if (spriteNumber == 1) {
                    image = right1;
                }
                if (spriteNumber == 2) {
                    image = right2;
                }
                if (spriteNumber == 3) {
                    image = right3;
                }
                if (spriteNumber == 4) {
                    image = right4;
                }
                break;
            default:
                break;
        }

        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
    }

}
