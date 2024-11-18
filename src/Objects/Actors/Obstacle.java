package src.Objects.Actors;

import src.Drawing.DrawObject;

import java.util.ArrayList;

public class Obstacle extends Enemy {
    private double xAcc;
    private double xVel;
    private double xPos;
    private double yAcc;
    private double yVel;
    private double yPos;

    ArrayList<DrawObject> drawables = new ArrayList<>();

    public Obstacle () {

    }

    @Override
    public void update() {

    }

    @Override
    public void damage(int d) {}

    @Override
    public void kill() {}

    @Override
    public void draw() {}
}
