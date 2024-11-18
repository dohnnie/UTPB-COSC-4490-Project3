package src.Levels;

import java.io.IOException;
import java.util.ArrayList;
import src.Audio.MusicFanfare;
import src.Audio.MusicLoop;
import src.Drawing.Drawable;
import src.Drawing.WorldLayer.Layer;
import src.Objects.Activators.Activator;
import src.Objects.Actors.Player;
import src.Objects.Platform;
import src.Objects.Spawner;
import src.Objects.WorldFloor;
import src.Threads.Updateable;

public abstract class Level implements Drawable, Updateable {
    protected MusicLoop looped;
    protected MusicFanfare endLevel;
    protected MusicFanfare bossEntry;
    protected MusicFanfare bossVictory;

    public Player player;

    ArrayList<Layer> worldArt = new ArrayList<>();
    WorldFloor floor;
    ArrayList<Platform> worldGeom = new ArrayList<>();
    ArrayList<Activator> activators = new ArrayList<>();

    ArrayList<Spawner> spawners = new ArrayList<>();

    ArrayList<Spawner> spawnPoints = new ArrayList<>();

    public void loadFromFile(String fileName) {}

    public void saveToFile(String fileName) {}

    public abstract void loadArt() throws Exception;

    public abstract void reset();

    public abstract void update();
}
