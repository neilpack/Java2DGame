package tile;

import Main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {
    GamePanel gp;
    Tile[] tile;
    int mapTileNum[][];

    public TileManager(GamePanel gp) {
        this.gp = gp;

        tile = new Tile[10]; //10 tiles add more later
        mapTileNum = new int[gp.maxScreenCol][gp.maxScreenRow];

        getTileImage();
        loadMap("/res/maps/map00.txt");
    }
    public void getTileImage() {
        try {
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/grass.png"));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/planks.png"));

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/water.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void loadMap(String filePath) {
        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is)); //this just reads the text file

            int col = 0;
            int row = 0;

            while (col < gp.maxScreenCol && row < gp.maxScreenRow) {
                String line = br.readLine(); //reads single line and puts into string

                while(col < gp.maxScreenCol) {
                    String numbers[] = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row] = num;
                    col++;
                }
                if(col == gp.maxScreenCol) { //goes to next line until loop is over
                    col = 0;
                    row++;
                }
            }
            br.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void draw(Graphics2D g2) {
        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        while (col < gp.maxScreenCol && row < gp.maxScreenRow) {

            int tileNum = mapTileNum[col][row];

            g2.drawImage(tile[tileNum].image, x, y, gp.tileSize, gp.tileSize, null);
            col++;
            x += gp.tileSize;

            if (col == gp.maxScreenCol) {
                col = 0;
                x = 0;
                row++;
                y += gp.tileSize;
            }
        }
        //g2.drawImage(tile[0].image, 0, 0, gp.tileSize, gp.tileSize, null);
        //g2.drawImage(tile[0].image, 48, 0, gp.tileSize, gp.tileSize, null);
        //g2.drawImage(tile[0].image, 96, 0, gp.tileSize, gp.tileSize, null);
        //g2.drawImage(tile[0].image, 144, 0, gp.tileSize, gp.tileSize, null);
        //g2.drawImage(tile[0].image, 192, 0, gp.tileSize, gp.tileSize, null);

        //g2.drawImage(tile[0].image, 0, 48, gp.tileSize, gp.tileSize, null);
        //g2.drawImage(tile[0].image, 48, 48, gp.tileSize, gp.tileSize, null);
        //g2.drawImage(tile[0].image, 96, 48, gp.tileSize, gp.tileSize, null);
        //g2.drawImage(tile[0].image, 144, 48, gp.tileSize, gp.tileSize, null);
        //g2.drawImage(tile[0].image, 192, 48, gp.tileSize, gp.tileSize, null);

        //g2.drawImage(tile[0].image, 0, 96, gp.tileSize, gp.tileSize, null);
        //g2.drawImage(tile[0].image, 48, 96, gp.tileSize, gp.tileSize, null);
        //g2.drawImage(tile[2].image, 96, 96, gp.tileSize, gp.tileSize, null);
        //g2.drawImage(tile[2].image, 144, 96, gp.tileSize, gp.tileSize, null);
        //g2.drawImage(tile[2].image, 192, 96, gp.tileSize, gp.tileSize, null);
    }
}