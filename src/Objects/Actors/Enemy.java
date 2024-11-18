package src.Objects.Actors;

import src.Collision.Collideable;
import src.Threads.Updateable;

public abstract class Enemy extends Collideable implements Updateable, Damageable {
    public abstract void update();
    public abstract void damage(int d);
    public abstract void kill();
    public abstract void draw();
}
