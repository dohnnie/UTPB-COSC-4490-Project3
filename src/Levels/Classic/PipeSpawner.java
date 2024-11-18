package src.Levels.Classic;

import src.Objects.Spawner;
import src.Threads.Engine;

public class PipeSpawner extends Spawner {
    final ClassicMode parent;
    final Engine engine;

    public PipeSpawner(ClassicMode p, Engine e) {
        parent = p;
        engine = e;
    }

    @Override
    public boolean spawnCondition() {
        for (Pipe p : parent.pipes)
    }

    @Override
    public void spawn() {
    }
}
