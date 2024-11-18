package src.Levels.Classic;

import src.Collision.CollideRect;
import src.Collision.Collideable;
import src.Levels.Classic.*;
import src.Levels.Level;
import src.Loader.Loader;
import src.Threads.Engine;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class ClassicMode extends Level {
    Engine engine;

    ArrayList<Pipe> pipes = new ArrayList<>();
    PipeSpawner pipeSpawner;
    int pipeCount;
    final int maxPipes = 5;
    final int minPipeSpace;

    HashMap<Integer, Cloud> clouds = new HashMap<>();
    HashMap<>
    CloudSpawner cloudSpawner;
    public int cloudCount;
    public final int maxClouds = 20;
    public final double cloudSpawnChance = 0.05;

    public Collideable worldBounds = new Collideable();

    public ClassicMode(Engine e) {
        engine = e;

        Toolkit tk = Toolkit.getDefaultToolkit();
        int width = tk.getScreenSize().width;
        int height = tk.getScreenSize().height;
        minPipeSpace = width/5;
        pipeSpawner = new PipeSpawner(this, e);
        cloudSpawner = new CloudSpawner(this, e);

        worldBounds.addBody(new CollideRect(new Point(-500, -500), width+1000, 500));
        worldBounds.addBody(new CollideRect(new Point(-500, -500), 500, height+1000));
        worldBounds.addBody(new CollideRect(new Point(-500, height), width+1000, 500));
        worldBounds.addBody(new CollideRect(new Point(width, -500), 500, height+1000));
    }

    @Override
    public void loadArt() throws Exception {
        player = new Bird(engine, Loader.splitFramesVertical(Loader.loadImage(Loader.actorPath + "bird.png"), 4));
        player.load();
        cloudSpawner.addArt(Loader.splitFramesVertical(Loader.loadImage(Loader.actorPath + "clouds.png"), 21));

    }

    @Override
    public void update() {
        pipeSpawner.update();

    }
}
