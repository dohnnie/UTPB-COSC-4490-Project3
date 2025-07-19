package GameObjects;

import java.awt.Point;
import java.io.*;

public class Enemy extends Sprite {
    
    float leftBoundary, rightBoundary;
    public boolean isAlive;

    public Enemy(String imageFile, Point origin, float bLeft, float bRight) throws IOException {
        super(imageFile, origin);
        
        leftBoundary = bLeft;
        rightBoundary = bRight;
        xVel = 2;
        isAlive = true;
    }

    @Override
    public void update() {
        super.update();
        if(isAlive) {
            if(box.getLeft() <= leftBoundary) {
            xVel *= -1;
            }
            else if(box.getRight() >= rightBoundary) {
                xVel *= -1;
            }
        }
    }

    @Override
    public void reset() {
        super.reset();

        isAlive = true;
        xVel = 2;
    }
}
