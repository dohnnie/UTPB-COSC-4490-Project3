package src.Objects;

public abstract class EnemySpawner {
    public abstract void spawn(Runnable op);

    public abstract void onSpawn(Runnable op);
}
