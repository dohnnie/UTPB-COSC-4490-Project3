package src.Interface.Menu;

import src.Loader.Loader;
import src.Threads.Engine;

import java.io.IOException;

public class GameOverMenu extends Menu {
    public GameOverMenu(Engine e) {
        super(e, 0, 6);

        menuTitle = new MenuText("Game Over!");
        menuItems.add(new MenuText("Load Game"));
        menuItems.add(new MenuText("Options Menu"));
        menuItems.add(new MenuText("Resume"));
        menuItems.add(new MenuText("Restart Level"));
        menuItems.add(new MenuText("Quit to Main Menu"));
        menuItems.add(new MenuText("Quit Game"));

        menuItems.get(0).select();
    }

    @Override
    public void load() throws IOException {
        scale();
    }

    @Override
    public void select() {}
}
