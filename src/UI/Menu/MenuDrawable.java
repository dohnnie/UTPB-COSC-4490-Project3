package src.UI.Menu;

import java.awt.*;

public interface MenuDrawable {
    public void scaleToWidth(int w);
    public void scaleToHeight(int h);
    public void scale(double f);
    public void drawAt(Graphics2D g2d, int x, int y);
    public void drawCentered(Graphics2D g2d, int x, int y);
    public void drawCenteredX(Graphics2D g2d, int x, int y);
    public void drawCenteredY(Graphics2D g2d, int x, int y);
}
