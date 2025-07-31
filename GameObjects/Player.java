package GameObjects;

import Audio.AudioPlayer;
import Enums.Directions;
import ParticleSystem.Particle;
import Projectiles.Fireball;
import java.awt.*;
import java.io.IOException;

public class Player extends Sprite{
    public Directions direction;
    public Fireball fb;

    public boolean firing;
    public int projCount = 1;

    public Particle[] fbParticle;

    public Player(String imageFile, Point origin) throws IOException {
        super(imageFile, origin);

        firing = false;
        direction = Directions.RIGHT;
        fbParticle = new Particle[5];
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

        if(firing && projCount > 0) {
            try {
                int fbWidth = 50;
                int fbHeight = 50;
                int drawX = (int)box.centerX - (fbWidth / 2);
                int drawY = (int)box.centerY - (fbHeight / 2);
                fb = new Fireball("./data/Mario.png", new Point(drawX, drawY), fbWidth, fbHeight, this);
                fb.isActive = true;
                AudioPlayer.playSound("./data/sound/firing.wav");

            } catch (IOException e) {
                System.out.println("Failed in creating a fireball");
                e.printStackTrace();
            }
            projCount--;
        }

        if(fbParticle[0] != null) {
            for(Particle particle : fbParticle) {
                particle.update(1);
            }
        }
    }

    @Override
    public void reset() {
        super.reset();
        if(fb != null) {
            fb.isActive = false;
        }
        firing = false;
        direction = Directions.RIGHT;
    }
}