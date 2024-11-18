package src.Collision;

import src.Threads.RateLimited;
import src.Threads.Updateable;

import java.util.ArrayList;

public class Collideable extends RateLimited implements Updateable {

    protected ArrayList<CollisionBody> body = new ArrayList<>();
    private ArrayList<Collideable> colliders = new ArrayList<>();
    private boolean inverse = false;

    public Collideable(boolean inv) {
        super();
        inverse = inv;
    }

    public synchronized ArrayList<CollisionBody> getList() {
        return body;
    }
    public synchronized void addBody(CollisionBody b) {
        body.add(b);
    }

    public synchronized void dropBody(CollisionBody b) {
        body.remove(b);
    }

    public synchronized void addCollider(Collideable c) {
        colliders.add(c);
    }

    public synchronized void dropCollider(Collideable c) {
        colliders.remove(c);
    }

    public boolean collide() {
        for (Collideable c : colliders) {
            for (CollisionBody b1 : body) {
                for (CollisionBody b2: c.body) {
                    if (b1.collide(b2)) {
                        return !inverse;
                    }
                }
            }
        }
        return inverse;
    }

    @Override
    public void run() {
        while (true) {
            long startTime = System.nanoTime();
            update();
            collide();
            limit(startTime);
        }
    }
}