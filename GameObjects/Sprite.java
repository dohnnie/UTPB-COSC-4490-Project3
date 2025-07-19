package GameObjects;

import Collision.BoxCollider;
import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.ImageIO;

//Note: All sprites are 100x100 in size
public class Sprite {
    public final float GRAVITY = 0.6f;

    BufferedImage image;
    public BoxCollider box;
    public float xVel, yVel;

    public Sprite(String imageFile, Point origin, int w, int h) throws IOException {
        box = new BoxCollider(origin.x, origin.y, w, h);

        image = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics x = image.getGraphics();
        BufferedImage rawImage = ImageIO.read(new FileInputStream(imageFile));
        Image tempImage = rawImage.getScaledInstance(box.width, box.height, Image.SCALE_SMOOTH);
        x.drawImage(tempImage, 0, 0, null);
        x.dispose();
        xVel = 0.0f;
        yVel = 0.0f;
    }

    public Sprite(String imageFile, Point origin) throws IOException{
        this(imageFile, origin, 100, 100);
    }

    public void draw(Graphics g) {
        g.drawImage(image, (int)box.centerX - (box.width / 2), (int)box.centerY - (box.height / 2), null);
    }

    public void reset() {
        xVel = 0;
        yVel = 0;
        box.centerX = (float) box.origin.x + (box.width / 2);
        box.centerY = (float) box.origin.y + (box.height / 2);
    }

    public void update() {
        box.centerX += xVel;
        box.centerY += yVel;
    }
}
