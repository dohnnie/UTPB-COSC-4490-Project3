package src.UI.Menu;

import src.Loader.Loader;
import src.Threads.Engine;
import src.Threads.GameStates;
import src.UI.UIImage;

import java.io.IOException;

public class MainMenu extends Menu {

    public MainMenu(Engine e) {
        super(e, 0, 8);

        menuTitle = new MenuText("Flappy Bird DX Gaiden");
        menuItems.add(new MenuText("New Game"));
        menuItems.add(new MenuText("Continue"));
        menuItems.add(new MenuText("Load Saved Game"));
        menuItems.add(new MenuText("Level Select"));
        menuItems.add(new MenuText("Endless Mode"));
        menuItems.add(new MenuText("Classic Mode"));
        menuItems.add(new MenuText("Options Menu"));
        menuItems.add(new MenuText("Credits"));
    }

    @Override
    public void load(Loader l) throws IOException
    {
        menuBackground = new UIImage(l.loadImage(Loader.path + "ph.png"));
        menuBackground.scaleToScreen();
        scale();
    }

    @Override
    public void select() {
        switch (cursor) {
            case 0 -> {engine.loadLevel(1);} // new game
            case 1 -> {engine.loadSave(0);} // continue
            case 2 -> {engine.engineState = GameStates.LOAD_MENU;} // load game
            case 3 -> {engine.engineState = GameStates.LEVEL_SELECT;} // level select
            case 4 -> {engine.loadEndless();} // endless mode
            case 5 -> {engine.loadClassic();} // classic mode
            case 6 -> {engine.engineState = GameStates.OPTIONS_MENU;} // options
            case 7 -> {engine.engineState = GameStates.CREDITS;} // credits
            case 8 -> {System.exit(0);} // TODO: May want to create an "are you sure" dialog here
        }
    }
}
