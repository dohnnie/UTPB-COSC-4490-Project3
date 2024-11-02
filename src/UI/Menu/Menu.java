package src.UI.Menu;

import src.Threads.Engine;

import java.awt.*;
import java.util.ArrayList;

public abstract class Menu {
    Engine engine;
    int cursor = 0;
    int cursorMax = 0;

    public MenuImage menuBackground;
    public MenuImage menuTitle;
    public ArrayList<MenuImage> menuItems;
    public MenuImage cursorImg;
    int menuW;
    int menuH;
    int menuXCenter;
    int menuYOrigin;
    int padding;

    public Menu(Engine e, int cDefault, int cMax) {
        engine = e;
        cursor = cDefault;
        cursorMax = cMax;

        menuItems = new ArrayList<>();
    }

    public void scale() {
        int scnW = engine.tk.getScreenSize().width;
        int scnH = engine.tk.getScreenSize().height;

        menuW = scnW / 2;
        menuH = scnH * 6 / 8;
        menuYOrigin = menuH / 6;
        menuXCenter = scnW / 2;
        padding = scnH / 100;

        int maxW = menuTitle.defaultWidth;
        for (MenuImage img : menuItems) {
            if (img.defaultWidth > maxW) {
                maxW = img.defaultWidth;
            }
        }
        double scaleFactor = (double)(scnW/2) / (double)maxW;
        menuTitle.scale(scaleFactor);
        cursorImg.scale(scaleFactor);
        for (MenuImage img : menuItems) {
            img.scale(scaleFactor);
        }

        int totalHeight = padding * menuItems.size();
        for (MenuImage img : menuItems) {
            totalHeight += img.height;
        }
        if (totalHeight > menuH) {
            scaleFactor = (double) menuH / (double) totalHeight;
            menuTitle.scale(scaleFactor);
            cursorImg.scale(scaleFactor);
            for (MenuImage img : menuItems) {
                img.scale(scaleFactor);
            }
        } else {
            menuYOrigin += (menuH - totalHeight) / 2;
        }
    }

    public void cursorUp() {
        cursor -= 1;
        cursor = Math.max(cursor, 0);
    }

    public void cursorDn() {
        cursor += 1;
        cursor = Math.min(cursor, cursorMax);
    }

    public void cursorRt() {
    }

    public void cursorLt() {
    }

    public void select() {
    }

    public void draw(Graphics2D g2d) {
        if (menuBackground != null) {
            g2d.drawImage(menuBackground.image, 0, 0, null);
        }
        if (menuTitle != null) {
            g2d.drawImage(menuTitle.image, menuXCenter - menuTitle.width/2, menuYOrigin, null);
        }
        int yOffset = menuTitle.height + padding;
        for (int i = 0; i < menuItems.size(); i++) {
            MenuImage img = menuItems.get(i);
            img.drawCenteredX(g2d, menuXCenter, menuYOrigin + yOffset);
            if (cursor == i) {
                cursorImg.drawAt(g2d, menuXCenter - img.width/2 - padding - cursorImg.width, menuYOrigin + yOffset);
            }
            yOffset += img.height + padding;
        }
    }


    /*
                g2d.drawString(String.format("%s Reset Game", menuCursor == 0 ? ">" : " "), 25, 25);
                g2d.drawString(String.format("%s Exit Game", menuCursor == 1 ? ">" : " "), 25, 50);
                String vol = "";
                for (int i = 0; i < 11; i++)
                {
                    if ((int) (engine.volume * 10) == i)
                    {
                        vol += "|";
                    } else {
                        vol += "-";
                    }
                }
                g2d.drawString(String.format("%s Volume %s", menuCursor == 2 ? ">" : " ", vol), 25, 75);
                g2d.drawString(String.format("%s Randomize Gaps %s", menuCursor == 3 ? ">" : " ", engine.randomGaps ? "(ON)" : "(OFF)"), 25, 100);
                String dif = "";
                for (double i = 0.0; i <= 3.0; i+= 0.5)
                {
                    if (engine.difficulty == i)
                    {
                        dif += "|";
                    } else {
                        dif += "-";
                    }
                }
                g2d.drawString(String.format("%s Difficulty %s", menuCursor == 4 ? ">" : " ", dif), 25, 125);
                g2d.drawString(String.format("%s Ramping %s", menuCursor == 5 ? ">" : " ", engine.ramping ? "(ON)" : "(OFF)"), 25, 150);
                g2d.drawString(String.format("%s Debug Mode %s", menuCursor == 6 ? ">" : " ", engine.debug ? "(ON)" : "(OFF)"), 25, 175);
                */
}
