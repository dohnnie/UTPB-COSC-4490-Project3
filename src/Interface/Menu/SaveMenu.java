package src.Interface.Menu;

import src.Loader.Loader;
import src.Threads.Engine;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class SaveMenu extends Menu {

    public BufferedImage[] saveThumbs;

    public SaveMenu(Engine e) {
        super(e, 0, 0);
    }

    @Override
    public void load() throws IOException {
        //scale();
    }

    @Override
    public void select() {}
}
