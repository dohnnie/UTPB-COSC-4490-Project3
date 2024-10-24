package src.Drawing;

import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public interface ImageTransform {

    BufferedImage getImage();

    Point getCenter();

    default AffineTransformOp rotate(double radians) {
        Point center = getCenter();
        AffineTransform at = new AffineTransform();
        at.rotate(radians, center.x, center.y);
        return new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
    }

    default AffineTransformOp move(int dx, int dy) {
        AffineTransform at = new AffineTransform();
        at.translate(dx, dy);
        return new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
    }

    default AffineTransformOp scale(double factor) {
        AffineTransform at = new AffineTransform();
        at.scale(factor, factor);
        return new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
    }
}