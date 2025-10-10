package Main;

import entity.Player;
import tile.TileManager;

import javax.swing.JPanel;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    //SCREEN SETTINGS
    final int originalTileSize = 16; //16x16 title
    final int scale = 3; //this makes the tiles appear bigger on a monitor

    public final int tileSize = originalTileSize * scale; //48x48 tile
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol; // 768 pixels
    public final int screenHeight = tileSize * maxScreenRow; //576 pixels

    //FPS
    int FPS = 60;

    //Objects
    TileManager tm = new TileManager(this);
    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    Player player = new Player(this,keyH);

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
        player.update();
    }
    public void paintComponent(Graphics g) { //this is actually built into java, call ths this repaint
        super.paintComponent(g);//whenever you use paint you need to type this
        Graphics2D g2 =  (Graphics2D) g;

        tm.draw(g2); //we draw the tiles first before the player, so that way the player can walk over tiles
        player.draw(g2);

        g2.dispose(); //this is good practice to save memory
    }
}
