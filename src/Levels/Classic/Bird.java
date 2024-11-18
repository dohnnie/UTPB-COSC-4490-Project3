package src.Levels.Classic;

import src.Audio.SoundEffect;
import src.Collision.CollideCircle;
import src.Collision.CollisionBody;
import src.Drawing.Drawable;
import src.Drawing.ImageScaler;
import src.Func;
import src.Loader.Loader;
import src.Objects.Actors.Damageable;
import src.Objects.Actors.Flying;
import src.Objects.Actors.Player;
import src.Threads.Engine;
import src.Threads.GameStates;
import src.Threads.Updateable;

import javax.sound.sampled.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Bird extends Player implements Drawable, Damageable, Flying, Updateable
{
    private SoundEffect flapSFX;
    private SoundEffect hurtSFX;
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
        this.addBody(new CollideCircle(center, (double) tgtWidth / 2));

        yAcc = 0.1;

        reset();
    }

    @Override
    public void damage(int d) {
        hurtSFX.setVolume();
        hurtSFX.play();
        kill();
    }

    @Override
    public void kill() {
        engine.engineState = GameStates.GAMEOVER_CLASSIC;
    }

    @Override
    public void draw(Graphics2D g2d)
    {
        System.out.printf("Frame count: %d  yV: %.2f  yP: %.2f%n", frameCount, yVel, yPos);
        this.rotate(Func.tanh(yVel / 8.0 - 0.2));

        frameCount++;
		int animRate = Math.max((int) (4 + yVel), 1);
        System.out.printf("Rotation: %.2f  Anim rate: %d%n", Func.tanh(yVel / 8.0 - 0.2), animRate);
        if (frameCount % animRate == 0) {
            super.update();
        }
        super.draw(g2d);
    }

    @Override
    public Point getAnchor() {
        return new Point((int) xPos, (int) yPos);
    }

    @Override
    public void flap()
    {
        yVel -= 5.0;
        flapSFX.setVolume();
        flapSFX.play();
    }

    @Override
    public void glide() {}

    @Override
    public void dive() {}

    @Override
    public void load() throws Exception {
        flapSFX = new SoundEffect(Loader.loadAudio(Loader.audioPath + "flap.wav"));
        hurtSFX = new SoundEffect(Loader.loadAudio(Loader.audioPath + "collide.wav"));
    }

    public void reset()
    {
        Toolkit tk = Toolkit.getDefaultToolkit();
        xPos = (double) tk.getScreenSize().width / 2;
        yPos = (double) tk.getScreenSize().height / 3;

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
