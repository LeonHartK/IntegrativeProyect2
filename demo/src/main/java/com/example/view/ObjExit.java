package com.example.view;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ObjExit extends SuperObject {
    public ObjExit() {
        name = "Door";
        try {
            File file = new File(
                    System.getProperty("user.dir") + "/demo/src/main/java/com/example/resources/Map/Exit.png");
            image = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
