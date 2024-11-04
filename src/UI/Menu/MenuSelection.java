package src.UI.Menu;

import java.awt.*;
import java.awt.image.BufferedImage;

public class MenuSelection extends MenuText {
    private Runnable operation;

    public MenuSelection(Runnable op, String text) {
        super(text);
        operation = op;
    }

    public void run() {
        operation.run();
    }
}
