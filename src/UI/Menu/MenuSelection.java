package src.UI.Menu;

import java.awt.*;
import java.awt.image.BufferedImage;

public class MenuSelection extends MenuImage {
    private Runnable operation;

    public MenuSelection(Runnable op, BufferedImage b) {
        super(b);
        operation = op;
    }

    public void run() {
        operation.run();
    }
}
