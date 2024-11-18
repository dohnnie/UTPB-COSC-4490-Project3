package src.Levels.Classic;

import src.Audio.SoundEffect;
import src.Collision.CollideCircle;
import src.Collision.CollisionBody;
import src.Drawing.Drawable;
import src.Drawing.ImageScaler;
import src.Loader.Loader;
import src.Objects.Actors.Damageable;
import src.Objects.Actors.Player;
import src.Settings.Settings;
import src.Threads.Engine;
import src.Threads.GameStates;

import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Bird extends Player implements Drawable, Damageable
{
    SoundEffect hurt;
    SoundEffect score;
    SoundEffect flap;

    private int animRate = 4;
    private int frameCount = 0;

    public Bird(Engine e, BufferedImage[] flapFrames) {
        super(e);

        int tgtWidth = tk.getScreenSize().width/12;
        for (int i = 0; i < flapFrames.length; i++) {
            flapFrames[i] = ImageScaler.scaleToWidth(flapFrames[i], tgtWidth);
        }
        this.addArt("flap", flapFrames);
        this.swapAnim("flap");
        Point center = this.getCenter();
        this.addBody(new CollideCircle(center, tgtWidth/2));

        reset();
    }

    public void load() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        flap = new SoundEffect(Loader.loadAudio("flap.wav"));
        hurt = new SoundEffect(Loader.loadAudio("collide.wav"));
        score = new SoundEffect(Loader.loadAudio("score.wav"));
    }

    @Override
    public void damage(int d) {
        new Thread (() ->{
            hurt.setVolume();
            hurt.play();
        }).start();
        kill();
    }

    @Override
    public void kill() {
        engine.engineState = GameStates.GAMEOVER_CLASSIC;
    }

    @Override
    public void draw(Graphics2D g2d)
    {
        this.rotate(Math.tanh(yVel / 8.0 - 0.2));

        frameCount++;
        animRate = Math.max((int)(4 + yVel), 1);
        if (frameCount % animRate == 0) {
            super.update();
        }
        super.draw(g2d);

        if (engine.debug)
        {
            for (CollisionBody cb : body) {
                cb.draw(g2d);
            }
        }
    }

    @Override
    public Point getAnchor() {
        return new Point(xPos, yPos);
    }

    public void flap()
    {
        yVel -= 5.0;
        new Thread (() ->{
            flap.setVolume();
            flap.play();
        }).start();
    }

    public void score()
    {
        new Thread (() ->{
            score.setVolume();
            score.play();
        }).start();
    }

    public void reset()
    {
        Toolkit tk = Toolkit.getDefaultToolkit();
        xPos = tk.getScreenSize().width / 2;
        yPos = tk.getScreenSize().height / 3;

        yVel = 0.0;
    }

    @Override
    public void update()
    {
        yVel += yAcc;
        yPos += yVel;
    }

    @Override
    public boolean collide() {
        if (super.collide()) {
            damage(0);
        }
        return true;
    }
}
