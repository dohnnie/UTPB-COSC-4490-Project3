package src.Interface.Menu;

import src.Loader.Loader;
import src.Threads.Engine;

import java.io.IOException;

public class OptionsMenu extends Menu {

    public OptionsMenu(Engine e) {
        super(e, 0, 4);

        menuTitle = new MenuText("Options");
        menuItems.add(new MenuText("Video Options"));
        menuItems.add(new MenuText("Audio Options"));
        menuItems.add(new MenuText("Gameplay Options"));
        menuItems.add(new MenuText("Control Options"));
        menuItems.add(new MenuText("Back"));

        menuItems.get(0).select();
    }

    @Override
    public void load() throws IOException {
        scale();
    }

    @Override
    public void select() {}
}
