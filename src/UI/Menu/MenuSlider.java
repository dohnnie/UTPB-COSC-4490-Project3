package src.UI.Menu;

import java.awt.*;
import java.awt.image.BufferedImage;

public class MenuSlider implements MenuDrawable {
    BufferedImage pipOrig;
    BufferedImage dashOrig;
    BufferedImage pip;
    BufferedImage dash;
    int pWidth;
    int pHeight;
    int dWidth;
    int dHeight;
    int numPlaces;
    int padding;

    public MenuSlider(BufferedImage p, BufferedImage d, int n) {
        pipOrig = p;
        pip = p;
        dashOrig = d;
        dash = d;
        numPlaces = n;
    }

    public void setPadding(int p) {
        padding = p;
    }

    @Override
    public void scaleToWidth(int w) {
        return;
    }

    @Override
    public void scaleToHeight(int h) {
        return;
    }

    @Override
    public void scale(double f) {
        Image temp = pip;
        pWidth = (int)(pWidth * f);
        pHeight = (int)(pHeight * f);
        temp = temp.getScaledInstance(pWidth, pHeight, BufferedImage.SCALE_SMOOTH);
        pip = new BufferedImage(pWidth, pHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics x = pip.getGraphics();
        x.drawImage(temp, 0, 0, null);
        x.dispose();

        temp = dash;
        dWidth = (int)(dWidth * f);
        dHeight = (int)(dHeight * f);
        temp = temp.getScaledInstance(dWidth, dHeight, BufferedImage.SCALE_SMOOTH);
        dash = new BufferedImage(dWidth, dHeight, BufferedImage.TYPE_INT_ARGB);
        x = dash.getGraphics();
        x.drawImage(temp, 0, 0, null);
        x.dispose();
    }

    @Override
    public void drawAt(Graphics2D g2d, int x, int y) {
        return;
    }

    @Override
    public void drawCentered(Graphics2D g2d, int x, int y) {
        return;
    }

    @Override
    public void drawCenteredX(Graphics2D g2d, int x, int y) {
        return;
    }

    @Override
    public void drawCenteredY(Graphics2D g2d, int x, int y) {
        return;
    }
}
