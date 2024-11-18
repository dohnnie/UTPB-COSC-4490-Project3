package src.Drawing;

import java.awt.*;
import java.awt.image.BufferedImage;

public class DrawObject implements ImageTransform, Drawable {
    private BufferedImage image;
    private Point anchor;
    private Point center;

    public DrawObject(BufferedImage bi) {
        image = bi;
        setAnchor(0, 0);
        findCenter();
    }

    @Override
    public BufferedImage getImage() {
        return image;
    }

    @Override
    public void setImage(BufferedImage img) {
        image = img;
    }

    @Override
    public Point getCenter() {
        if (center == null) {
            findCenter();
        }
        return center;
    }

    public void findCenter() {
        center = new Point(anchor.x + image.getWidth()/2, anchor.y + image.getHeight()/2);
    }

    public void setCenter(int x, int y) {
        center = new Point(x, y);
    }

    @Override
    public Point getAnchor() {
        return anchor;
    }

    public void setAnchor(int x, int y) {
        anchor = new Point(x, y);
    }

    @Override
    public void draw(Graphics2D g2d) {
        Point anchor = getAnchor();
        g2d.drawImage(image, anchor.x, anchor.y, null);
    }
}
