package src.Levels.Classic;

import src.Audio.SoundEffect;
import src.Collision.CollideRect;
import src.Collision.Collideable;
import src.Drawing.Drawable;
import src.Interface.InterfaceText;
import src.Levels.Level;
import src.Loader.Loader;
import src.Threads.Engine;
import src.Threads.Updateable;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

public class ClassicMode extends Level implements Drawable, Updateable {
    Engine engine;
    Toolkit tk;
    int width, height;

    ConcurrentHashMap<Integer, Pipe> pipes = new ConcurrentHashMap<>();
    PipeSpawner pipeSpawner;
    int pipeCount;
    final int maxPipes = 5;
    final int minPipeSpace;
    int pipeWidth;
    int pipeHeight;
    BufferedImage pipeImage;
    BufferedImage pipeFlipped;

    ConcurrentHashMap<Integer, Cloud> clouds = new ConcurrentHashMap<>();
    CloudSpawner cloudSpawner;
    public int cloudCount;
    public final int maxClouds = 20;
    public final double cloudSpawnChance = 0.05;

    public Collideable worldBounds = new Collideable(true);

    int score = 0;
    InterfaceText scoreText = new InterfaceText();
    String scoreFormat = "Score: %d";
    int highScore = 0;
    InterfaceText highScoreText = new InterfaceText();
    String highScoreFormat = "High Score: %d";

    SoundEffect scoreSFX;

    public ClassicMode(Engine e) {
        engine = e;

        tk = Toolkit.getDefaultToolkit();
        width = tk.getScreenSize().width;
        height = tk.getScreenSize().height;
        minPipeSpace = width/5;
        pipeSpawner = new PipeSpawner(this, e);
        cloudSpawner = new CloudSpawner(this, e);

        worldBounds.addBody(new CollideRect(new Point(0, 0), width, height));
    }

    public synchronized void addCloud(int i, Cloud c) {
        clouds.put(i, c);
        cloudCount += 1;
    }

    public synchronized void killCloud(int index) {
		if (clouds.remove(index) != null) {
            cloudCount -= 1;
        }
    }

    public synchronized Collection<Cloud> getClouds() {
        return clouds.values();
    }

    public synchronized void addPipe(int i, Pipe p) {
        pipes.put(i, p);
        pipeCount += 1;
        player.addCollider(p);
    }

    public synchronized void killPipe(int index) {
        Pipe p = pipes.remove(index);
		if (p != null) {
            pipeCount -= 1;
            player.dropCollider(p);
        }
    }

    public synchronized Collection<Pipe> getPipes() {
        return pipes.values();
    }

    public void score() {
        score += 1;
        scoreSFX.setVolume();
        scoreSFX.play();
        highScore = Math.max(score, highScore);
        scoreText.setText(String.format(scoreFormat, score));
        highScoreText.setText(String.format(highScoreFormat, highScore));
    }

    @Override
    public void loadArt() throws Exception {
        player = new Bird(engine, Loader.splitFramesVertical(Loader.loadImage(Loader.actorPath + "bird.png"), 4));
        player.load();
        player.addCollider(worldBounds);
        cloudSpawner.addArt(Loader.splitFramesVertical(Loader.loadImage(Loader.actorPath + "clouds.png"), 21));

        scoreSFX = new SoundEffect(Loader.loadAudio(Loader.audioPath + "score.wav"));

        BufferedImage pipeTemp = Loader.loadImage(Loader.actorPath + "pipe.png");

        Toolkit tk = Toolkit.getDefaultToolkit();
        pipeWidth = tk.getScreenSize().width / 16;
        pipeHeight = (int)(((double)pipeWidth / (double)pipeTemp.getWidth()) * pipeTemp.getHeight());

        Image temp = pipeTemp.getScaledInstance(pipeWidth, pipeHeight, BufferedImage.SCALE_SMOOTH);
        pipeImage = new BufferedImage(pipeWidth, pipeHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics g = pipeImage.getGraphics();
        g.drawImage(temp, 0, 0, null);
        g.dispose();

        AffineTransform at = new AffineTransform();
        at.rotate(Math.PI, (double) pipeTemp.getWidth() / 2, (double) pipeTemp.getHeight() / 2);
        AffineTransformOp ato = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
        BufferedImage flipped = ato.filter(pipeTemp, null);

        temp = flipped.getScaledInstance(pipeWidth, pipeHeight, BufferedImage.SCALE_SMOOTH);
        pipeFlipped = new BufferedImage(pipeWidth, pipeHeight, BufferedImage.TYPE_INT_ARGB);
        g = pipeFlipped.getGraphics();
        g.drawImage(temp, 0, 0, null);
        g.dispose();

        scoreText.setText(String.format(scoreFormat, 999));
        scoreText.sizeFont(engine.frame.getGraphics(), tk.getScreenSize().width, tk.getScreenSize().height / 12);

        highScoreText.setText(String.format(highScoreFormat, 999));
        highScoreText.sizeFont(engine.frame.getGraphics(), tk.getScreenSize().width, tk.getScreenSize().height / 12);

        scoreText.setText(String.format(scoreFormat, 0));
        File scoreFile = new File("score.txt");
        if(scoreFile.exists())
        {
            try {
                FileReader fr = new FileReader(scoreFile);
                BufferedReader br = new BufferedReader(fr);
                highScore = Integer.parseInt(br.readLine());
                br.close();
                fr.close();
            } catch (Exception _) {}
        }
        highScoreText.setText(String.format(highScoreFormat, highScore));
    }

    @Override
    public Point getAnchor() {
        return null;
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(Color.CYAN);
        g2d.fillRect(0, 0, width, height);

        for (Cloud c : getClouds()) {
            c.draw(g2d);
        }

        for (Pipe p : getPipes()) {
            p.draw(g2d);
        }

        player.draw(g2d);

        int textOffset = height * 11 / 12;
        int textCenter = width/2;
        int textPadding = 30;
        highScoreText.drawAt(g2d, textPadding, textOffset);
        scoreText.drawAt(g2d, textCenter+textPadding, textOffset);
    }

    @Override
    public void reset() {
        player.reset();
        pipes = new ConcurrentHashMap<>();
        clouds = new ConcurrentHashMap<>();
        pipeCount = 0;
        cloudCount = 0;
        score = 0;
    }

    @Override
    public void update() {
        player.update();
        pipeSpawner.update();
        cloudSpawner.update();
        for (Pipe p: pipes.values()) {
            p.update();
        }
        for (Cloud c : clouds.values()) {
            c.update();
        }
    }
}
