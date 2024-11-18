package src.Drawing;

import src.Transformable;

import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public interface ImageTransform extends Transformable {

    BufferedImage getImage();

    Point getCenter();
    void findCenter();
    void setCenter(int x, int y);
    Point getAnchor();
    void setAnchor(int x, int y);

    default void rotate(double radians) {
        Point center = getCenter();
        AffineTransform at = new AffineTransform();
        at.rotate(radians, center.x, center.y);
        AffineTransformOp ato = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
        ato.filter(getImage(), null);
    }

    default void move(int dx, int dy) {
        AffineTransform at = new AffineTransform();
        at.translate(dx, dy);
        AffineTransformOp ato = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
        ato.filter(getImage(), null);
    }

    default void scale(double factor) {
        AffineTransform at = new AffineTransform();
        at.scale(factor, factor);
        AffineTransformOp ato = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
        ato.filter(getImage(), null);
    }
}