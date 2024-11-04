package src.UI.Menu;

import src.Loader.Loader;
import src.Threads.Engine;

import java.awt.*;
import java.io.IOException;

public class PauseMenu extends Menu {

    public PauseMenu(Engine e) {
        super(e, 0, 0);

        menuTitle = new MenuText("Paused!");
        menuItems.add(new MenuText("Save Game"));
        menuItems.add(new MenuText("Load Game"));
        menuItems.add(new MenuText("Options Menu"));
        menuItems.add(new MenuText("Resume"));
        menuItems.add(new MenuText("Restart Level"));
        menuItems.add(new MenuText("Quit to Main Menu"));
        menuItems.add(new MenuText("Quit Game"));
    }

    @Override
    public void load(Loader l) throws IOException {

    }

    @Override
    public void select() {}
}
