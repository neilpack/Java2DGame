package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    public boolean upPressed, downPressed, leftPressed, rightPressed;
    public String lastDirection = null;

    @Override
    public void keyPressed(KeyEvent e) { //new key handler movement so we can gather last key pressed down
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
            upPressed = true;
            lastDirection = "up";
        }
        if (code == KeyEvent.VK_A  || code == KeyEvent.VK_LEFT) {
            leftPressed = true;
            lastDirection = "left";
        }
        if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
            downPressed = true;
            lastDirection = "down";
        }
        if (code == KeyEvent.VK_D ||  code == KeyEvent.VK_RIGHT) {
            rightPressed = true;
            lastDirection = "right";
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //we will never use this
    }

    //@Override
    //public void keyPressed(KeyEvent e) { this is the old key handler movement, with up down bug
    //    int code = e.getKeyCode();
    //    if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
    //        upPressed = true;
    //    }
    //    if (code == KeyEvent.VK_A  || code == KeyEvent.VK_LEFT) {
    //        leftPressed = true;
    //    }
    //    if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
    //        downPressed = true;
    //    }
    //    if (code == KeyEvent.VK_D ||  code == KeyEvent.VK_RIGHT) {
    //        rightPressed = true;
    //    }
    //}

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
            upPressed = false;
        }
        if (code == KeyEvent.VK_A  || code == KeyEvent.VK_LEFT) {
            leftPressed = false;
        }
        if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
            downPressed = false;
        }
        if (code == KeyEvent.VK_D ||  code == KeyEvent.VK_RIGHT) {
            rightPressed = false;
        }
    }
}
