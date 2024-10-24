package src.Objects.UI.Menu;

import src.Objects.UI.ImageObject;
import src.Threads.Engine;

import java.awt.*;
import java.util.ArrayList;

public abstract class Menu {
    Engine engine;
    int cursor = 0;
    int cursorMax = 0;

    public ImageObject menuBackground;
    public ImageObject menuTitle;
    public ArrayList<ImageObject> menuItems;
    public ImageObject cursorImg;
    int menuW;
    int menuH;
    int menuXCenter;
    int menuYOrigin;
    int padding;

    public Menu(Engine e, int cDefault, int cMax) {
        engine = e;
        cursor = cDefault;
        cursorMax = cMax;
    }

    public void scale() {
        int scnW = engine.tk.getScreenSize().width;
        int scnH = engine.tk.getScreenSize().height;

        menuW = scnW / 2;
        menuH = scnH * 6 / 8;
        menuYOrigin = menuH / 2;
        menuXCenter = scnW / 2;
        padding = scnH / 100;

        int maxW = menuTitle.defaultWidth;
        for (ImageObject img : menuItems) {
            if (img.defaultWidth > maxW) {
                maxW = img.defaultWidth;
            }
        }
        double scaleFactor = (double)(scnW/2) / (double)maxW;
        menuTitle.scale(scaleFactor);
        cursorImg.scale(scaleFactor);
        for (ImageObject img : menuItems) {
            img.scale(scaleFactor);
        }

        int totalHeight = padding * menuItems.size();
        for (ImageObject img : menuItems) {
            totalHeight += img.targetHeight;
        }
        if (totalHeight > menuH) {
            scaleFactor = (double) menuH / (double) totalHeight;
            menuTitle.scale(scaleFactor);
            cursorImg.scale(scaleFactor);
            for (ImageObject img : menuItems) {
                img.scale(scaleFactor);
            }
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

    public void enter() {
    }

    public abstract void draw(Graphics2D g2d);


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
