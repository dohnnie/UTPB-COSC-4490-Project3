package src.Objects.Actors;

import src.Objects.Actors.Enemy;
import src.Threads.Engine;

import java.awt.Graphics2D;
import java.awt.Point;

public class SpawnerEnemy extends Enemy {

    public SpawnerEnemy (Engine e) {
        super(e);
    }

    @Override
    public void update() {}

    @Override
    public void damage(int d) {}

    @Override
    public void kill() {}

    @Override
    public Point getAnchor() {return null;}

    @Override
    public void draw(Graphics2D g2d) {}
}
