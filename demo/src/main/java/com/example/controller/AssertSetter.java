package com.example.controller;

import com.example.view.GamePanel;
import com.example.view.ObjExit;

public class AssertSetter {

    GamePanel gp;

    public AssertSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {
        gp.obj[0] = new ObjExit();
        gp.obj[0].worldX = 49 * gp.tileSize;
        gp.obj[0].worldY = 49 * gp.tileSize;
    }
}
