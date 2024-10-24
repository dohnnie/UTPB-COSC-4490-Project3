package src.Collision;

import java.awt.Point;
import java.lang.reflect.Array;
import java.util.ArrayList;

public abstract class CollideShape extends CollisionBody implements CollisionTransform{
    ArrayList<CollideLine> edges;

    @Override
    public ArrayList<CollideLine> getEdges() {
        return edges;
    }

    @Override
    public void setEdges(ArrayList<CollideLine> e) {
        edges = e;
    }

    public CollideShape() {
        this.edges = new ArrayList<>();
    }

    public void addLine(CollideLine line) {
        edges.add(line);
    }

    // Helper method to get all the corner points of the shape
    @Override
    public ArrayList<Point> getCorners() {
        ArrayList<Point> corners = new ArrayList<>();
        for (CollideLine edge : edges) {
            corners.add(edge.p1);
        }
        return corners;
    }

    @Override
    public Point getCenter() {
        double cx = 0.0;
        double cy = 0.0;
        for (Point p : getCorners()) {
            cx += p.getX();
            cy += p.getY();
        }
        cx /= getCorners().size();
        cy /= getCorners().size();
        return new Point((int)cx, (int)cy);
    }
}
