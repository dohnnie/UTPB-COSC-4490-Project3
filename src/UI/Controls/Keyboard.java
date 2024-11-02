package src.UI.Controls;

import java.awt.event.KeyEvent;

public class Keyboard extends Controller {
    public Keyboard() {
        controls.put(KeyEvent.VK_ESCAPE, () -> {});
        controls.put(KeyEvent.VK_SPACE, () -> {});
        controls.put(KeyEvent.VK_W, () -> {});
        controls.put(KeyEvent.VK_A, () -> {});
        controls.put(KeyEvent.VK_S, () -> {});
        controls.put(KeyEvent.VK_D, () -> {});
        controls.put(KeyEvent.VK_UP, () -> {});
        controls.put(KeyEvent.VK_LEFT, () -> {});
        controls.put(KeyEvent.VK_DOWN, () -> {});
        controls.put(KeyEvent.VK_RIGHT, () -> {});
    }
}
