package src.UI.Menu;

import src.Threads.Engine;

public class SaveMenu extends LoadMenu {

    public MenuSelection menuTitle;
    public MenuSelection newSave;
    public MenuSelection[] saveThumbs;
    public MenuSelection backButton;

    public SaveMenu(Engine e) {
        super(e);
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
}
