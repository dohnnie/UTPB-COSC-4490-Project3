package src.Objects.Actors;

import src.Drawing.DrawObject;
import src.Threads.Engine;

import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;

public class Obstacle extends Enemy {
    public double xAcc;
    public double xVel;
    public int xPos;
    public double yAcc;
    public double yVel;
    public int yPos;

    ArrayList<DrawObject> drawables = new ArrayList<>();

    public Obstacle (Engine e) {
		super(e);
	}

    @Override
    public void update() {}

    @Override
    public void damage(int d) {}

    @Override
    public void kill() {}

    @Override
    public void draw(Graphics2D g2d) {
        super.draw(g2d);
    }

    @Override
    public Point getAnchor() {
        return new Point(xPos, yPos);
    }

}
