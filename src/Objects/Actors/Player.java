package src.Objects.Actors;

import src.Audio.SoundEffect;
import src.Threads.Engine;

import java.awt.*;

public abstract class Player extends AnimatedActor {

    public Player(Engine e) {
        super(e);
    }

    public abstract void load() throws Exception;

    @Override
    public void draw(Graphics2D g2d) {
        super.draw(g2d);
    }

    @Override
    public void update() {
        super.update();
    }
}
