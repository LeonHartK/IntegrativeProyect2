package com.example.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ControllerKey implements KeyListener {

    public boolean upPressed, downPressed, rightPressed, leftPressed, keyPPressed;

    private boolean keyPJustPressed = false;

    public boolean KeyPJustPressed() {
        return keyPJustPressed;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W) {
            upPressed = true;
        }
        if (code == KeyEvent.VK_S) {
            downPressed = true;
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = true;
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = true;
        }
        if (code == KeyEvent.VK_P) {
            if (!keyPPressed) {
                keyPJustPressed = true;
            }
            keyPPressed = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W) {
            upPressed = false;
        }
        if (code == KeyEvent.VK_S) {
            downPressed = false;
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = false;
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = false;
        }
        if (code == KeyEvent.VK_P) {
            keyPPressed = false;
            keyPJustPressed = false;
        }
    }

}
