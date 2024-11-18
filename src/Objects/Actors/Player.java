package src.Objects.Actors;

import src.Drawing.Drawable;
import src.Threads.Engine;
import src.Threads.Updateable;

import java.awt.*;

public abstract class Player extends AnimatedActor implements Damageable, Drawable, Flying, Updateable {

    public Player(Engine e) {
        super(e);
    }

    public abstract void load() throws Exception;

    @Override
    public void draw(Graphics2D g2d) {
        super.draw(g2d);
    }

    public abstract void reset();

    @Override
    public void update() {
        super.update();
    }
}
