package src.Objects.UI.Menu;

import src.Objects.UI.ImageObject;
import src.Threads.Engine;
import src.Threads.GameStates;

import java.awt.*;

public class MainMenu extends Menu{

    public MainMenu(Engine e) {
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
    public void enter() {
        switch (cursor) {
            case 0 -> {} // new game
            case 1 -> {} // continue
            case 2 -> {} // load game
            case 3 -> {} // level select
            case 4 -> {} // endless mode
            case 5 -> {} // classic mode
            case 6 -> {engine.engineState = GameStates.OPTIONS_MENU;} // options
            case 7 -> {engine.engineState = GameStates.CREDITS;} // credits
            case 8 -> {System.exit(0);} // TODO: May want to create an "are you sure" dialog here
        }
    }

    @Override
    public void draw(Graphics2D g2d) {
        if (menuBackground != null) {
            g2d.drawImage(menuBackground.image, 0, 0, null);
        }
        if (menuTitle != null) {
            g2d.drawImage(menuTitle.image, menuXCenter - menuTitle.targetWidth/2, menuYOrigin, null);
        }
        int yOffset = menuTitle.targetHeight + padding;
        for (int i = 0; i < menuItems.size(); i++) {
            ImageObject img = menuItems.get(i);
            g2d.drawImage(img.image, menuXCenter - img.targetWidth/2, menuYOrigin + yOffset, null);
            yOffset += img.targetHeight + padding;
            if (cursor == i) {
                g2d.drawImage(cursorImg.image,
                        menuXCenter - img.targetWidth/2 - padding - cursorImg.targetWidth,
                        menuYOrigin + yOffset,
                        null);
            }
        }
    }
}
