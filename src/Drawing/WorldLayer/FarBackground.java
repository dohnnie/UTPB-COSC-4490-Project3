package src.Drawing.WorldLayer;

import src.Collision.CollideRect;
import src.Drawing.ImageParallax;
import src.Drawing.ImageTransform;
import src.Drawing.ZBuffered;

import java.awt.*;
import java.awt.image.BufferedImage;

public class FarBackground implements ImageParallax, ImageTransform, ZBuffered {
    @Override
    public double getFactor() {
        return 0;
    }

    @Override
    public Point getOrigin() {
        return null;
    }

    @Override
    public void setOrigin(Point o) {

    }

    @Override
    public double getXFactor() {
        return 0;
    }

    @Override
    public double getYFactor() {
        return 0;
    }

    @Override
    public BufferedImage getImage() {
        return null;
    }

    @Override
    public Point getCenter() {
        return null;
    }

    @Override
    public boolean isTransparent() {
        return false;
    }

    @Override
    public int getZLayer() {
        return 0;
    }

    @Override
    public CollideRect getBoundingRect() {
        return null;
    }
}
