package entity;

import Main.GamePanel;
import Main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {
    GamePanel gp;
    KeyHandler keyH;
    public final int screenX;
    public final int screenY;
    public int hasKey = 0;

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.screenWidth / 2 - (gp.tileSize/2);
        screenY = gp.screenHeight / 2 -  (gp.tileSize/2);

        //solid box so there is
        //collision for the player
        //x, y, width, and, height
        solidArea = new Rectangle(8, 16, 32, 32);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        setDefaultValues();
        getPlayerImage();
    }
    public void setDefaultValues() {
        //PLAYER START POSITION ----------------
        worldX = gp.tileSize * 25;
        worldY = gp.tileSize * 25;
        speed = 4;
        direction = "down";
    }
    public void getPlayerImage() {
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/res/player/dude_up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/res/player/dude_up_2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/res/player/dude_down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/res/player/dude_down_2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/res/player/dude_left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/res/player/dude_left_2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/res/player/dude_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/res/player/dude_right_2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void update() {

        if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {
            if (keyH.upPressed) {
                direction = "up";
            }
            else if (keyH.downPressed) {
                direction = "down";
            }
            else if (keyH.leftPressed) {
                direction = "left";
            }
            else if (keyH.rightPressed){
                direction = "right";
            }
            spriteCounter++;
            if (spriteCounter > 12) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                    gp.playSE(3); //when steps, play walking noise
                }
                else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }

            //CHECK TILE COLLISION
            collisionOn = false;
            gp.cChecker.checkTile(this);

            //CHECK OBJECT COLLISION
            int objIndex = gp.cChecker.checkObject(this, true);
            pickUpObject(objIndex);

            //IF COLLISION IS FALSE, PLAYER CAN MOVE
            if (!collisionOn) {
                switch (direction) {
                    case "up": worldY -= speed; break;
                    case "down": worldY += speed; break;
                    case "left": worldX -= speed; break;
                    case "right": worldX += speed; break;
                }
            }
        }
    }
    public void pickUpObject(int i) {
        if (i != 999) { //deletes object we just touched

            String objectName = gp.obj[i].name;

            switch (objectName) {
                case "Key":
                    gp.playSE(2);
                    hasKey++;
                    gp.obj[i] = null;
                    gp.ui.showMessage("You got a key");
                    break;
                case "Door":
                    if (hasKey > 0) {
                        gp.playSE(1);
                        gp.obj[i] = null;
                        hasKey--;
                        gp.ui.showMessage("You opened the door");
                    }
                    else {
                        gp.ui.showMessage("You need a key");
                    }
                    break;
                case "Chest":
                    if (hasKey > 0) {
                        gp.playSE(4);
                        gp.obj[i] = null;
                        hasKey--;
                        gp.ui.gameFinished = true;
                        gp.stopMusic();
                    } else {
                        gp.ui.showMessage("You need a key");
                    }
                    break;
                case "Sword":
                    gp.playSE(2);
                    speed += 1; //delete this later (makes player fast)
                    gp.obj[i] = null;
                    gp.ui.showMessage("You got a sword");
                    break;

                    //end game scenario
                    //gp.ui.gameFinished = true;
                    //gp.stopMusic();
                    //gp.playerSE(4);
            }
        }
    }
    public void draw(Graphics g2) {
        BufferedImage image = null;
        switch (direction) {
            case "up":
                if (spriteNum == 1) {
                    image = up1;
                }
                if (spriteNum == 2) {
                    image = up2;
                }
                break;
            case "down":
                if (spriteNum == 1) {
                    image = down1;
                }
                if (spriteNum == 2) {
                    image = down2;
                }
                break;
            case "left":
                if (spriteNum == 1) {
                    image = left1;
                }
                if (spriteNum == 2) {
                    image = left2;
                }
                break;
            case "right":
                if (spriteNum == 1) {
                    image = right1;
                }
                if (spriteNum == 2) {
                    image = right2;
                }
                break;
        }
        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }
}