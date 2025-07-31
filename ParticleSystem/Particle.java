package ParticleSystem;
import java.awt.*;

public class Particle{

    public int xPos;
    public int yPos;

    public int xVel;
    public int yVel;

    public int ttl; // Time to live

    public Particle(int xPos, int yPos, int ttl, int angle, int speed) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.ttl = ttl;

        double angleInRadians = angle * Math.PI / 180;
        this.xVel = (int)(speed * Math.cos(angleInRadians));
        this.yVel = (int)(-speed * Math.sin(angleInRadians));
    }

    public void update(double time) {
        ttl -= time;
        if(this.isAlive()) {
            xPos += xVel * time;
            yPos += yVel * time;
        }
    }

    public void draw(Graphics g) {
        g.fillOval(xPos, yPos, 10, 10);
    }

    public boolean isAlive() {
        return ttl > 0;
    }
}
