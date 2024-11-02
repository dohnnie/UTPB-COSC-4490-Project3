package src.UI.Menu;

import src.Threads.Engine;

import java.awt.*;

public class PauseMenu extends Menu {

    public MenuSelection menuTitle;
    public MenuSelection saveGame;
    public MenuSelection loadGame;
    public MenuSelection resetLevel;
    public MenuSelection optsMenu;
    public MenuSelection resumeGame;
    public MenuSelection backToMain;
    public MenuSelection exitGame;

    public PauseMenu(Engine e) {
        super(e, 0, 0);
    }

    @Override
    public void cursorUp() {
        super.cursorUp();
    }

    @Override
    public void cursorDn() {
        super.cursorDn();
    }

    @Override
    public void cursorRt() {

    }

    @Override
    public void cursorLt() {

    }

    @Override
    public void select() {}

    @Override
    public void draw(Graphics2D g2d) {

    }
}
