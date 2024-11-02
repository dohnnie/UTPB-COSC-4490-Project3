package src.UI.Controls;

import java.util.HashMap;

public abstract class Controller {
    HashMap<Integer, Runnable> controls = new HashMap<>();

    public void run(int control) {
        if (controls.containsKey(control)) {
            controls.get(control).run();
        }
    }
}
