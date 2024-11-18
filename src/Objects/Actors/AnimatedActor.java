package src.Objects.Actors;

import src.Collision.Collideable;
import src.Collision.CollisionBody;
import src.Drawing.AnimLoop;
import src.Threads.Engine;
import src.Threads.Updateable;
import src.Transformable;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;

public class AnimatedActor extends Collideable implements Updateable, Transformable {
    public Engine engine;
    public Toolkit tk;

    public double yPos;
    public double yVel = 0.0;
    public double yAcc = 0.0;

    public double xPos;
    public double xVel = 0.0;
    public double xAcc = 0.0;

    HashMap<Integer, AnimLoop> animLoops = new HashMap<>();
    HashMap<String, Integer> animGraph = new HashMap<>();
    private int loopIndex = 0;
    private String currAnim = "";

    public AnimatedActor(Engine e) {
        super(false);
        engine = e;
        tk = Toolkit.getDefaultToolkit();
    }

    public void addArt(String animAlias, BufferedImage image) {
        animGraph.put(animAlias, loopIndex);
        AnimLoop loop = new AnimLoop(image);
        animLoops.put(loopIndex, loop);
        loopIndex++;
    }

    public void addArt(String animAlias, BufferedImage[] animFrames) {
        animGraph.put(animAlias, loopIndex);
        AnimLoop loop = new AnimLoop(animFrames);
        animLoops.put(loopIndex, loop);
        loopIndex++;
    }

    public void swapAnim(String newAnim) {
        if (animGraph.containsKey(newAnim)) {
            currAnim = newAnim;
        }
    }

    @Override
    public void update() {
        animLoops.get(animGraph.get(currAnim)).update();
        for (AnimLoop al : animLoops.values()) {
            al.setAnchor((int) xPos, (int) yPos);
        }
    }

    @Override
    public Point getCenter() {
        return animLoops.get(animGraph.get(currAnim)).getCenter();
    }

    @Override
    public void rotate(double radians) {
        animLoops.get(animGraph.get(currAnim)).rotate(radians);
    }

    @Override
    public void move(int dx, int dy) {
        animLoops.get(animGraph.get(currAnim)).move(dx, dy);
    }

    @Override
    public void scale(double factor) {
        animLoops.get(animGraph.get(currAnim)).scale(factor);
    }

    public void draw(Graphics2D g2d) {
        animLoops.get(animGraph.get(currAnim)).draw(g2d);

        if (engine.debug)
        {
            for (CollisionBody cb : body) {
                cb.draw(g2d);
            }
        }
    }
}
