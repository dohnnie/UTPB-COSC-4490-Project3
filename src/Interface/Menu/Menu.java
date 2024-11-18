package src.Interface.Menu;

import src.Loader.Loader;
import src.Threads.Engine;
import src.Interface.InterfaceImage;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public abstract class Menu {
    Engine engine;
    int cursor = 0;
    int cursorMax = 0;

    public InterfaceImage menuBackground;
    public MenuText menuTitle;
    public ArrayList<MenuText> menuItems;
    int menuW;
    int menuH;
    int textH;
    int menuXCenter;
    int menuYOrigin;
    int padding;

    public Menu(Engine e, int cDefault, int cMax) {
        engine = e;
        cursor = cDefault;
        cursorMax = cMax;

        menuItems = new ArrayList<>();
    }

    public abstract void load() throws IOException;

    public void scale() {
        int scnW = engine.tk.getScreenSize().width;
        int scnH = engine.tk.getScreenSize().height;

        menuW = scnW / 2;
        menuH = scnH * 6 / 8;
        menuYOrigin = menuH / 6;
        menuXCenter = scnW / 2;
        padding = scnH / 100;

        textH = (menuH - (padding * menuItems.size())) / menuItems.size();
        menuTitle.sizeFont(engine.frame.getGraphics(), menuW, textH);
        int fontSize = menuTitle.fontSize;
        for (MenuText text : menuItems) {
            text.sizeFont(engine.frame.getGraphics(), menuW, textH);
            if (text.fontSize < fontSize) {
                fontSize = text.fontSize;
            }
        }

        for (MenuText text : menuItems) {
            text.setFontSize(fontSize);
        }
    }

    public void cursorUp() {
        menuItems.get(cursor).deselect();
        cursor -= 1;
        cursor = Math.max(cursor, 0);
        menuItems.get(cursor).select();
    }

    public void cursorDn() {
        menuItems.get(cursor).deselect();
        cursor += 1;
        cursor = Math.min(cursor, cursorMax);
        menuItems.get(cursor).select();
    }

    public void cursorRt() {
    }

    public void cursorLt() {
    }

    public void select() {
    }

    public void draw(Graphics2D g2d) {
        if (menuBackground != null) {
            menuBackground.drawAt(g2d, 0, 0);
        }
        if (menuTitle != null) {
            menuTitle.drawCenteredX(g2d, menuXCenter, menuYOrigin);
        }
        int yOffset = textH + padding;
		for (MenuText menuItem : menuItems) {
			menuItem.drawCenteredX(g2d, menuXCenter, menuYOrigin + yOffset);
			yOffset += textH + padding;
		}
    }
}
