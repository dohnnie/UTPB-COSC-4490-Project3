package GameObjects;

import Enums.Directions;
import java.awt.*;
import java.io.IOException;

public class Player extends Sprite{
    public Directions direction;

    public Player(String imageFile, Point origin) throws IOException {
        super(imageFile, origin);

        direction = Directions.RIGHT;
    }

    public void selectDirection() {
        if(xVel > 0)
            direction = Directions.RIGHT;
        else if(xVel < 0)
            direction = Directions.LEFT;
    }

    public void moveHorizontal() {
        if(xVel > 5) {
            xVel = 5;
        } else if(xVel < -5) {
            xVel = -5;
        }
        box.centerX += xVel;
    }

    @Override
    public void update() {
        
    }

    @Override
    public void reset() {
        super.reset();
        
        direction = Directions.RIGHT;
    }
}