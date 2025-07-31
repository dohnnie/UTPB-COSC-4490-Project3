package Projectiles;

import Audio.AudioPlayer;
import Enums.Directions;
import GameObjects.Player;
import GameObjects.Sprite;
import ParticleSystem.Particle;
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
    public Particle[] particles;

    public Fireball(String imageFile, Point origin, int width, int height, Player player) throws IOException {
        super(imageFile, origin, width, height);

        particles = new Particle[5];
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

    public void update(ArrayList<Sprite> platforms, Particle[] fbParticle) {
        if(isActive) {
            box.centerX += xVel;

            if(!platforms.isEmpty()) {
                isActive = false;
                for(int i = 0; i < fbParticle.length; i++) {
                    Particle particle = new Particle((int)box.centerX, (int)box.centerY, 20, (int)(Math.random() * 180), 5);
                    fbParticle[i] = particle;
                }
                AudioPlayer.playSound("./data/sound/fireball-collision.wav");
            }
        } 
    }
}
