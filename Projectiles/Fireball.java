package Projectiles;

import Audio.AudioPlayer;
import Enums.Directions;
import GameObjects.Player;
import GameObjects.Sprite;
import java.awt.Point;
import java.io.IOException;
import java.util.ArrayList;

public class Fireball extends Sprite{
    
    public int xPos;
    public int yPos;

    public int width;
    public int height;

    public boolean isActive = false;
    public Directions forward;

    public Fireball(String imageFile, Point origin, int width, int height, Player player) throws IOException {
        super(imageFile, origin, width, height);

        if(player.direction == Directions.LEFT) {
            forward = Directions.LEFT;
            xVel = -5;
        }
        else if(player.direction == Directions.RIGHT) {
            forward = Directions.RIGHT;
            xVel = 5;
        }
    }

    public void reset() {
        
    }

    public void update(ArrayList<Sprite> platforms) {
        if(isActive) {
            box.centerX += xVel;

            if(!platforms.isEmpty()) {
                isActive = false;
                AudioPlayer.playSound("./data/sound/fireball-collision.wav");
            }
        } 
    }
}
