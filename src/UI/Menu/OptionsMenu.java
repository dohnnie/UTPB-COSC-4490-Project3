package src.UI.Menu;

import src.Loader.Loader;
import src.Threads.Engine;

import java.awt.*;
import java.io.IOException;

public class OptionsMenu extends Menu {

    public OptionsMenu(Engine e) {
        super(e, 0, 0);

        menuTitle = new MenuText("Options");
        menuItems.add(new MenuText("Video Options"));
        menuItems.add(new MenuText("Audio Options"));
        menuItems.add(new MenuText("Gameplay Options"));
        menuItems.add(new MenuText("Control Options"));
        menuItems.add(new MenuText("Back"));
    }

    @Override
    public void load(Loader l) throws IOException {

    }

    @Override
    public void select() {}
}
