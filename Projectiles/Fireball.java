package Projectiles;

import Audio.AudioPlayer;
import Enums.Directions;
import GameObjects.Player;
import GameObjects.Sprite;
import ParticleSystem.Particle;
import java.awt.Point;
import java.io.IOException;

public class Fireball extends Sprite{
    
    public int xPos;
    public int yPos;

    public int width;
    public int height;

    public boolean isActive;
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
        isActive = true;
    }

    @Override
    public void update() {
        if(isActive) {
            box.centerX += xVel;
        } 
    }

    public void collision(Particle[] particles, int timeToLive) {
        isActive = false;
        for(Particle particle : particles) {
            particle.setPosition((int)box.centerX, (int)box.centerY, timeToLive);
        }
        AudioPlayer.playSound("./data/sound/fireball-collision.wav");
    }
}
