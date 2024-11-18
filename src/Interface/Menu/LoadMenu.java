package src.Interface.Menu;

import src.Loader.Loader;
import src.Threads.Engine;

import java.io.IOException;

public class LoadMenu extends Menu {

    public MenuSelection[] saveThumbs;

    public LoadMenu(Engine e) {
        super(e, 0, 0);
    }

    @Override
    public void load() throws IOException {
        //scale();
    }

    @Override
    public void select() {}
}
