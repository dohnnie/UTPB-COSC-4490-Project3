package src;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

public interface Transformable {
    Point getCenter();
    void rotate(double radians);
    void move(int dx, int dy);
    void scale(double factor);
}
