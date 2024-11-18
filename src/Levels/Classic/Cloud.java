package src.Levels.Classic;

import src.Drawing.Drawable;
import src.Rand;
import src.Threads.Engine;
import src.Threads.Updateable;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Cloud implements Drawable, Updateable
{
    ClassicMode parent;
    Toolkit tk;

    public int yPos;
    public int xPos;

    public double xVel;

    public int width;
    public int height;

    public BufferedImage image;
    public int index;

    public Cloud(ClassicMode p, BufferedImage img, int idx) {
        tk = Toolkit.getDefaultToolkit();
        parent = p;
        index = idx;

        int r = Rand.range(3,6);
        width = tk.getScreenSize().width / r;
        height = (int)(((double)width / (double)img.getWidth()) * (img.getHeight()));

        Image temp = img.getScaledInstance(width, height, BufferedImage.SCALE_SMOOTH);
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics x = image.getGraphics();
        x.drawImage(temp, 0, 0, null);
        x.dispose();

        xPos = tk.getScreenSize().width + 10;
        yPos = Rand.range(4 * tk.getScreenSize().height / 5);

        xVel = Rand.interval(1.0, 2.0);
    }

    @Override
    public void draw(Graphics2D g2d)
    {
        g2d.drawImage(image, xPos, yPos, null);
    }

    @Override
    public Point getAnchor()
    {
        return new Point(xPos, yPos);
    }

    public void update()
    {
        xPos -= xVel;
        if (xPos + width < -tk.getScreenSize().width / 2)
            parent.killCloud(index);
    }
}
