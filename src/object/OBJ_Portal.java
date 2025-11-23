package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Portal extends SuperObject{
    public OBJ_Portal() {
        name = "Portal";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/portal.png"));
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}