package src.Collision;

import java.awt.*;
import java.util.ArrayList;

public class CollideCircle extends CollisionBody implements CollisionTransform {
    Point center;
    double radius;

    public CollideCircle(Point center, double radius) {
        this.type = CollideType.CIRCLE;
        this.center = center;
        this.radius = radius;
    }

    @Override
    public boolean collideLine(CollideLine l) {
        return doesLineIntersectCircle(this, l);
    }

    @Override
    public boolean collideCircle(CollideCircle l) {
        return doCirclesOverlap(this, l);
    }

    @Override
    public boolean collideRect(CollideRect l) {
        return doesLineIntersectCircle(this, l.getEdges());
    }

    @Override
    public boolean collideConvShape(CollideConvShape l) {
        return doesLineIntersectCircle(this, l.edges);
    }

    @Override
    public boolean collideConcShape(CollideConcShape l) {
        return doesLineIntersectCircle(this, l.edges);
    }

    @Override
    public ArrayList<CollideLine> getEdges() {
        return new ArrayList<>();
    }

    @Override
    public void setEdges(ArrayList<CollideLine> e) {}

    @Override
    public ArrayList<Point> getCorners() {
        return new ArrayList<>();
    }

    @Override
    public Point getCenter() {
        return center;
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.drawOval((int)(center.x-radius), (int)(center.y-radius), (int)(radius*2.0), (int)(radius*2.0));
    }
}