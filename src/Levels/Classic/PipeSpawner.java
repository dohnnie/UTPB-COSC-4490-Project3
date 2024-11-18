package src.Levels.Classic;

import src.Objects.Spawner;
import src.Rand;
import src.Threads.Engine;

import java.awt.Toolkit;

public class PipeSpawner extends Spawner {
    final ClassicMode parent;
    final Engine engine;
    final Toolkit tk;

    public PipeSpawner(ClassicMode p, Engine e) {
        parent = p;
        engine = e;
        tk = Toolkit.getDefaultToolkit();
    }

    @Override
    public boolean spawnCondition() {
        if (parent.pipeCount < parent.maxPipes) {
            int rightMost = 0;
            for (Pipe p : parent.pipes.values()) {
                rightMost = Math.max(rightMost, p.xPos);
            }
			return tk.getScreenSize().width - rightMost > parent.minPipeSpace;
        }
        return false;
    }

    @Override
    public void spawn() {
        new Thread(() -> {
            if (spawnCondition()) {
                int pipeNum = Rand.range(50);
                while (parent.pipes.containsKey(pipeNum)) {
                    pipeNum = Rand.range(50);
                }
                Pipe p = new Pipe(engine, parent, pipeNum);
                parent.addPipe(pipeNum, p);
            }
        }).start();
    }
}
