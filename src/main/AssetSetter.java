package main;

import object.*;

public class AssetSetter {
    GamePanel gp;
    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {
        //KEYS
        gp.obj[0] = new OBJ_Key(); //easy left key
        gp.obj[0].worldX = 11 * gp.tileSize;
        gp.obj[0].worldY = 11 * gp.tileSize;

        gp.obj[1] = new OBJ_Key(); //water key
        gp.obj[1].worldX = 40 * gp.tileSize;
        gp.obj[1].worldY = 9 * gp.tileSize;

        gp.obj[9] = new OBJ_Key(); //monster guarding key
        gp.obj[9].worldX = 10 * gp.tileSize;
        gp.obj[9].worldY = 39 * gp.tileSize;

        //DOORS
        gp.obj[2] = new OBJ_Door();
        gp.obj[2].worldX = 22 * gp.tileSize;
        gp.obj[2].worldY = 8 * gp.tileSize;

        gp.obj[5] = new OBJ_Door();
        gp.obj[5].worldX = 18 * gp.tileSize;
        gp.obj[5].worldY = 3 * gp.tileSize;

        gp.obj[3] = new OBJ_Door();
        gp.obj[3].worldX = 28 * gp.tileSize;
        gp.obj[3].worldY = 3 * gp.tileSize;

        //CHEST
        //gp.obj[8] = new OBJ_Chest();
        //gp.obj[8].worldX = 26 * gp.tileSize;
        //gp.obj[8].worldY = 28 * gp.tileSize;

        //ITEMS
        //gp.obj[4] = new OBJ_Sword();
        //gp.obj[4].worldX = 27 * gp.tileSize;
        //gp.obj[4].worldY = 28 * gp.tileSize;

        gp.obj[6] = new OBJ_Boots();
        gp.obj[6].worldX = 40 * gp.tileSize;
        gp.obj[6].worldY = 37 * gp.tileSize;

        //WIN SCENARIO
        gp.obj[7] = new OBJ_Portal();
        gp.obj[7].worldX = 35 * gp.tileSize;
        gp.obj[7].worldY = 4 * gp.tileSize;
    }
}
