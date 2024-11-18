package src.Objects.Actors;

import src.Collision.Collideable;
import src.Collision.CollisionBody;
import src.Drawing.Drawable;
import src.Threads.Engine;
import src.Threads.Updateable;

import java.awt.Graphics2D;
import java.awt.Point;

public abstract class Enemy extends Collideable implements Damageable, Drawable, Updateable {
    Engine engine;

    public abstract void update();
    public abstract void damage(int d);
    public abstract void kill();
    public void draw(Graphics2D g2d) {
        if (engine.debug) {
            for (CollisionBody b : body) {
                b.draw(g2d);
            }
        }
    }
    public abstract Point getAnchor();

    public Enemy(Engine e)
    {
        super(false);
        engine = e;
    }
}
