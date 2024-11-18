package src.Drawing;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageScaler {
    public static BufferedImage scaleTo(BufferedImage image, int width, int height) {
        BufferedImage outImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Image scaled = image.getScaledInstance(width, height, BufferedImage.SCALE_SMOOTH);
        Graphics x = outImage.getGraphics();
        x.drawImage(scaled, 0, 0, null);
        x.dispose();
        return outImage;
    }

    public static BufferedImage scaleToWidth(BufferedImage image, int width) {
        double scale = (double)width / (double)image.getWidth();
        int height = (int)(scale * image.getHeight());
        return scaleTo(image, width, height);
    }

    public static BufferedImage scaleToHeight(BufferedImage image, int height) {
        double scale = (double)height / (double)image.getHeight();
        int width = (int)(scale * image.getWidth());
        return scaleTo(image, width, height);
    }

    public static BufferedImage scaleToScreen(BufferedImage image) {
        Toolkit tk = Toolkit.getDefaultToolkit();
        return scaleTo(image, tk.getScreenSize().width, tk.getScreenSize().height);
    }
}
