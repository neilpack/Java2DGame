package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Soup extends SuperObject{
    public OBJ_Soup() {
        name = "Soup";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/soup.png"));
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}