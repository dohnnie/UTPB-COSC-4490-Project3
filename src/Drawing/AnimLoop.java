package src.Drawing;

import src.Threads.Updateable;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class AnimLoop implements Updateable, Drawable, ImageTransform {
    private final ArrayList<DrawObject> animFrames = new ArrayList<>();
    private int animFrame = 0;
    private int numFrames = 0;
    private double rotation = 0.0;
    private double scale = 1.0;
    private int dx = 0;
    private int dy = 0;

    public AnimLoop(BufferedImage frame) {
        addFrame(frame);
    }

    public AnimLoop(BufferedImage[] frames) {
        for (BufferedImage frame : frames) {
            addFrame(frame);
        }
    }

    public void addFrame(BufferedImage frame) {
        DrawObject drawObj = new DrawObject(frame);
        animFrames.add(drawObj);
        numFrames++;
    }

    @Override
    public void update() {
        animFrame++;
        if (animFrame >= numFrames) {
            animFrame = 0;
        }
    }

    @Override
    public void draw(Graphics2D g2d) {
        DrawObject frame = animFrames.get(animFrame);
        if (rotation != 0.0) {
            frame.rotate(rotation);
        }
        if (dx != 0.0 || dy != 0.0) {
            frame.move(dx, dy);
        }
        if (scale != 1.0) {
            frame.scale(scale);
        }
        frame.draw(g2d);
    }

    @Override
    public Point getCenter() {
        return animFrames.get(animFrame).getCenter();
    }

    @Override
    public void findCenter() {
        for (DrawObject drawObj : animFrames) {
            drawObj.findCenter();
        }
    }

    @Override
    public void setCenter(int x, int y) {
        for (DrawObject drawObj : animFrames) {
            drawObj.setCenter(x, y);
        }
    }

    @Override
    public Point getAnchor() {
        return animFrames.get(animFrame).getAnchor();
    }

    @Override
    public void setAnchor(int x, int y) {
        for (DrawObject drawObj : animFrames) {
            drawObj.setAnchor(x, y);
        }
    }

    @Override
    public BufferedImage getImage() {
        return animFrames.get(animFrame).getImage();
    }

    @Override
    public void setImage(BufferedImage img) {}

    @Override
    public void rotate(double radians) {
        rotation = radians;
    }

    @Override
    public void move(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }

    @Override
    public void scale(double factor) {
        scale = factor;
    }
}
