package src.UI.Menu;

import java.awt.*;
import java.awt.image.BufferedImage;

public class MenuImage implements MenuDrawable{
    public BufferedImage origImage;
    public int defaultWidth;
    public int defaultHeight;

    public BufferedImage image;
    public int width;
    public int height;

    public MenuImage(BufferedImage b) {
        origImage = b;
        defaultWidth = b.getWidth();
        defaultHeight = b.getHeight();
        image = b;
        width = defaultWidth;
        height = defaultHeight;
    }

    @Override
    public void scaleToWidth(int w) {
        double scaleFactor = (double)w / (double)width;
        //System.out.printf("%d / %d = %.3f%n", width, w, scaleFactor);
        scale(scaleFactor);
    }

    @Override
    public void scaleToHeight(int h) {
        double scaleFactor = (double)h / (double)height;
        scale(scaleFactor);
    }

    @Override
    public void scale(double f) {
        Image temp = image;
        width = (int)(width * f);
        height = (int)(height * f);
        //System.out.printf("%d, %d%n", width, height);
        temp = temp.getScaledInstance(width, height, BufferedImage.SCALE_SMOOTH);
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics x = image.getGraphics();
        x.drawImage(temp, 0, 0, null);
        x.dispose();
    }

    @Override
    public void drawAt(Graphics2D g2d, int x, int y) {
        g2d.drawImage(image, x, y, null);
    }

    @Override
    public void drawCentered(Graphics2D g2d, int x, int y) {
        g2d.drawImage(image, x - width/2, y - width/2, null);
    }

    @Override
    public void drawCenteredX(Graphics2D g2d, int x, int y) {
        g2d.drawImage(image, x - width/2, y, null);
    }

    @Override
    public void drawCenteredY(Graphics2D g2d, int x, int y) {
        g2d.drawImage(image, x, y - height/2, null);
    }
}
