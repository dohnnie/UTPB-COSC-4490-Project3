package src.Objects.UI.Menu;

import src.Objects.UI.ImageObject;
import src.Threads.Engine;

import java.awt.*;

public class OptionsMenu extends Menu {

    public ImageObject menuTitle;
    public ImageObject videoOpts;
    public ImageObject audioOpts;
    public ImageObject controlOpts;
    public ImageObject gameplayOpts;
    public ImageObject backButton;
    public ImageObject resetAllOpts;

    public OptionsMenu(Engine e) {
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
    public void enter() {}

    @Override
    public void draw(Graphics2D g2d) {

    }
}
