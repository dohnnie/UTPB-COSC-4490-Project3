package src.Objects.UI;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.nio.Buffer;

public class ImageObject {
    public BufferedImage origImage;
    public int defaultWidth;
    public int defaultHeight;
    public BufferedImage image;
    public int targetWidth;
    public int targetHeight;

    public ImageObject(BufferedImage b) {
        origImage = b;
        defaultWidth = b.getWidth();
        defaultHeight = b.getHeight();
        image = b;
        targetWidth = defaultWidth;
        targetHeight = defaultHeight;
    }

    public void scaleToWidth(int w) {
        targetWidth = w;
    }

    public void scaleToHeight(int h) {

    }

    public void scaleToDims(int w, int h) {

    }

    public void scale(double f) {
        Image temp = image;
        targetWidth = (int)(targetWidth * f);
        targetHeight = (int)(targetHeight * f);
        temp = temp.getScaledInstance(targetWidth, targetHeight, BufferedImage.SCALE_SMOOTH);
        image = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics x = image.getGraphics();
        x.drawImage(temp, 0, 0, null);
        x.dispose();
    }

    public void scaleV(double f) {
        Image temp = image;
        targetHeight = (int)(targetHeight * f);
        temp = temp.getScaledInstance(targetWidth, targetHeight, BufferedImage.SCALE_SMOOTH);
        image = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics x = image.getGraphics();
        x.drawImage(temp, 0, 0, null);
        x.dispose();
    }

    public void scaleH(double f) {
        Image temp = image;
        targetWidth = (int)(targetWidth * f);
        temp = temp.getScaledInstance(targetWidth, targetHeight, BufferedImage.SCALE_SMOOTH);
        image = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics x = image.getGraphics();
        x.drawImage(temp, 0, 0, null);
        x.dispose();
    }
}
