package src.Objects.UI.Menu;

import src.Objects.UI.ImageObject;
import src.Threads.Engine;

public class SaveMenu extends LoadMenu {

    public ImageObject menuTitle;
    public ImageObject newSave;
    public ImageObject[] saveThumbs;
    public ImageObject backButton;

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
    public void enter() {}
}
