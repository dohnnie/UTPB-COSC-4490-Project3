package src.Objects;

import src.Threads.Updateable;

public abstract class Spawner implements Updateable {
    public abstract boolean spawnCondition();
    public abstract void spawn();

    public void update () {
        if (spawnCondition()) {
            spawn();
        }
    }
}
