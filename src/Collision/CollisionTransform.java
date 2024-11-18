package src.Collision;

import src.Transformable;

import java.awt.*;
import java.util.ArrayList;

public interface CollisionTransform extends Transformable {

    ArrayList<CollideLine> getEdges();
    void setEdges(ArrayList<CollideLine> e);
    ArrayList<Point> getCorners();
    Point getCenter();

    // Helper method to rotate a single point around the center
    private static Point rotatePoint(Point center, double radians, Point p) {
        // Translate the point to the origin (relative to the center)
        int translatedX = p.x - center.x;
        int translatedY = p.y - center.y;

        // Apply the rotation formula
        double rotatedX = translatedX * Math.cos(radians) - translatedY * Math.sin(radians);
        double rotatedY = translatedX * Math.sin(radians) + translatedY * Math.cos(radians);

        // Translate the point back to the original position relative to the center
        return new Point((int) Math.round(rotatedX + center.x), (int) Math.round(rotatedY + center.y));
    }

    default void rotatePoints(double radians) {
        ArrayList<Point> rotated = new ArrayList<>();

        for (Point p : getCorners()) {
            // Translate the point to the origin (relative to the center)
            int translatedX = p.x - getCenter().x;
            int translatedY = p.y - getCenter().y;

            // Apply the rotation formula
            double rotatedX = translatedX * Math.cos(radians) - translatedY * Math.sin(radians);
            double rotatedY = translatedX * Math.sin(radians) + translatedY * Math.cos(radians);

            // Translate the point back to the original position relative to the center
            rotated.add(new Point((int) Math.round(rotatedX + getCenter().x), (int) Math.round(rotatedY + getCenter().y)));
        }
    }

    default void rotate(double radians) {
        ArrayList<CollideLine> rotated = new ArrayList<>();

        for (CollideLine line : getEdges()) {
            Point rot1 = rotatePoint(getCenter(), radians, line.p1);
            Point rot2 = rotatePoint(getCenter(), radians, line.p2);
            rotated.add(new CollideLine(rot1, rot2));
        }

        setEdges(rotated);
    }

    // Helper method to translate a single point by dx and dy
    private static Point translatePoint(Point p, int dx, int dy) {
        return new Point(p.x + dx, p.y + dy);
    }

    default void move(int dx, int dy) {
        ArrayList<CollideLine> translated = new ArrayList<>();

        for (CollideLine line : getEdges()) {
            Point newP1 = translatePoint(line.p1, dx, dy);
            Point newP2 = translatePoint(line.p2, dx, dy);
            translated.add(new CollideLine(newP1, newP2));
        }

        setEdges(translated);
    }

    // Helper method to scale a point around the center
    private static Point scalePoint(Point center, double factor, Point p) {
        int scaledX = (int) Math.round(center.x + factor * (p.x - center.x));
        int scaledY = (int) Math.round(center.y + factor * (p.y - center.y));
        return new Point(scaledX, scaledY);
    }

    default void scale(double factor) {
        ArrayList<CollideLine> scaled = new ArrayList<>();

        for (CollideLine line : getEdges()) {
            Point newP1 = scalePoint(getCenter(), factor, line.p1);
            Point newP2 = scalePoint(getCenter(), factor, line.p2);
            scaled.add(new CollideLine(newP1, newP2));
        }

        setEdges(scaled);
    }
}
