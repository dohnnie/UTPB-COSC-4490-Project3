package GameObjects;

import java.awt.Point;
import java.io.*;

public class Enemy extends Sprite {
    
    float leftBoundary, rightBoundary;
    public Enemy(String imageFile, Point origin, float bLeft, float bRight) throws IOException {
        super(imageFile, origin);
        
        leftBoundary = bLeft;
        rightBoundary = bRight;
        xVel = 2;
    }

    @Override
    public void update() {
        super.update();

        if(box.getLeft() <= leftBoundary) {
            xVel *= -1;
        }
        else if(box.getRight() >= rightBoundary) {
            xVel *= -1;
        }
        
    }

    @Override
    public void reset() {
        super.reset();

        xVel = 2;
    }
}
