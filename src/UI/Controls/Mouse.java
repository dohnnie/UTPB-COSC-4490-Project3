package src.UI.Controls;

import java.awt.event.MouseEvent;

public class Mouse extends Controller {

    double sensitivity;
    boolean flipX;
    boolean flipY;

    public Mouse() {
        controls.put(MouseEvent.BUTTON1, () -> {});
        controls.put(MouseEvent.BUTTON2, () -> {});
        controls.put(MouseEvent.BUTTON3, () -> {});
        controls.put(MouseEvent.MOUSE_ENTERED, () -> {});
        controls.put(MouseEvent.MOUSE_EXITED, () -> {});
        controls.put(MouseEvent.MOUSE_WHEEL, () -> {});
    }
}
