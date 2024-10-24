package src.Levels;

import java.util.ArrayList;
import src.Audio.MusicFanfare;
import src.Audio.MusicLoop;
import src.Drawing.WorldLayer.Layer;
import src.Objects.Activators.Activator;
import src.Objects.Platform;
import src.Objects.EnemySpawner;
import src.Objects.PlayerSpawner;
import src.Objects.WorldFloor;

public class Level {
    private MusicLoop looped;
    private MusicFanfare endLevel;
    private MusicFanfare bossEntry;
    private MusicFanfare bossVictory;

    ArrayList<Layer> worldArt = new ArrayList<>();
    WorldFloor floor;
    ArrayList<Platform> worldGeom = new ArrayList<>();
    ArrayList<Activator> activators = new ArrayList<>();

    ArrayList<EnemySpawner> spawners = new ArrayList<>();

    ArrayList<PlayerSpawner> spawnPoints = new ArrayList<>();

    public void loadFromFile(String fileName) {

    }

    public void saveToFile(String fileName) {

    }

    public void loadArt() {

    }
}
