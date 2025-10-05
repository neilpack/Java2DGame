package Main;
import javax.swing.JPanel;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    //SCREEN SETTINGS
    final int originalTileSize = 16; //16x16 title
    final int scale = 3; //this makes the tiles appear bigger on a monitor

    final int tileSize = originalTileSize * scale; //48x48 tile
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol; // 768 pixels
    final int screenHeight = tileSize * maxScreenRow; //576 pixels

    //FPS
    int FPS = 60;

    KeyHandler keyH = new KeyHandler();
    Thread gameThread;

    //Set player's default position
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;



    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }
    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();

    }
    @Override
    public void run() {
        double drawInterval = 1000000000 / FPS; //we draw the screen every 0.01666 seconds
        double nextDrawTime = System.nanoTime() + drawInterval; //when timer hits target, then draw screen again
        while (gameThread != null) { //while the game thread exists then...

            //UPDATE character position
            update();

            //DRAW the screen with updated information
            repaint();

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime/1000000;

                if (remainingTime < 0) {
                    remainingTime = 0;
                }

                Thread.sleep((long) remainingTime); //pauses game loop until sleep time is over

                nextDrawTime += drawInterval;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void update() {
        if (keyH.upPressed == true) {
            playerY -= playerSpeed;// same as playerY = playerY - playerSpeed;
        }
        else if (keyH.downPressed == true) {
            playerX -= playerSpeed;
        }
        else if (keyH.leftPressed == true) {
            playerY += playerSpeed;
        }
        else if (keyH.rightPressed == true) {
            playerX += playerSpeed;
        }
    }
    public void paintComponent(Graphics g) { //this is actually built into java, call ths this repaint
        super.paintComponent(g);//whenever you use paint you need to type this
        Graphics2D g2 =  (Graphics2D) g;
        g2.setColor(Color.pink);
        g2.fillRect(playerX, playerY, tileSize, tileSize);
        g2.dispose(); //this is good practice to save memory
    }
}
