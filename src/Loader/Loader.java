package src.Loader;

import src.Objects.UI.*;
import src.Objects.UI.Menu.*;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.tools.Tool;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Loader {
    public BufferedImage[] loadImageArray(String fileName, int numFrames) {
        return null;
    }

    public BufferedImage loadImage(String fileName) {
        return null;
    }

    /*private BufferedImage loadImage(String fileName, double scale, boolean toScreen) throws IOException {
        Image temp = ImageIO.read(new File(fileName));
        double width = temp.getWidth(null)*scale;
        double height = temp.getHeight(null)*scale;
        int w = toScreen ? (int)(width * tk.getScreenSize().width / width) : (int)width;
        int h = toScreen ? (int)(height * tk.getScreenSize().height / height) : (int)height;
        temp = temp.getScaledInstance(w, h, BufferedImage.SCALE_SMOOTH);
        BufferedImage outImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics x = outImage.getGraphics();
        x.drawImage(temp, 0, 0, null);
        x.dispose();
        return outImage;
    }*/

    private void loadAudio(String fileName) {
        try
        {
            AudioInputStream ais = AudioSystem.getAudioInputStream(new File("data/audio/flap.wav").getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(ais);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    private void loadMusicLoop(String fileName, int startFrame, int endFrame) {}

    public ImageObject loadSplash() {
        return null;
    }

    public void loadGeneral(GeneralUI ui) {
        String fmt = String.format("data%smenu%s", File.separator, File.separator);
        ui.num0 = new ImageObject(loadImage(fmt + "0.png"));
        ui.num1 = new ImageObject(loadImage(fmt + "1.png"));
        ui.num2 = new ImageObject(loadImage(fmt + "2.png"));
        ui.num3 = new ImageObject(loadImage(fmt + "3.png"));
        ui.num4 = new ImageObject(loadImage(fmt + "4.png"));
        ui.num5 = new ImageObject(loadImage(fmt + "5.png"));
        ui.num6 = new ImageObject(loadImage(fmt + "6.png"));
        ui.num7 = new ImageObject(loadImage(fmt + "7.png"));
        ui.num8 = new ImageObject(loadImage(fmt + "8.png"));
        ui.num9 = new ImageObject(loadImage(fmt + "9.png"));

        ui.percent = new ImageObject(loadImage(fmt + "percent.png"));

        ui.sliderPip = new ImageObject(loadImage(fmt + "pip.png"));
        ui.sliderDash = new ImageObject(loadImage(fmt + "dash.png"));
    }

    public void loadMain(MainMenu menu) {
        String imgPath = String.format("data%smenu%s", File.separator, File.separator);
        //menu.titleMusicLoop = loadAudio(null);
        menu.menuBackground = null;//new ImageObject(loadImage(null));
        menu.menuTitle = new ImageObject(loadImage(imgPath + "gametitle.png"));
        menu.cursorImg = new ImageObject(loadImage(imgPath + "arrow.png"));
        menu.menuItems.add(new ImageObject(loadImage(imgPath + "newGame.png")));
        menu.menuItems.add(new ImageObject(loadImage(imgPath + "continue.png")));
        menu.menuItems.add(new ImageObject(loadImage(imgPath + "loadGame.png")));
        menu.menuItems.add(new ImageObject(loadImage(imgPath + "levelSelect.png")));
        menu.menuItems.add(new ImageObject(loadImage(imgPath + "endlessMode.png")));
        menu.menuItems.add(new ImageObject(loadImage(imgPath + "classicMode.png")));
        menu.menuItems.add(new ImageObject(loadImage(imgPath + "options.png")));
        menu.menuItems.add(new ImageObject(loadImage(imgPath + "credits.png")));
        menu.menuItems.add(new ImageObject(loadImage(imgPath + "quitGame.png")));
        menu.scale();
    }

    public void loadOptions(OptionsMenu menu) {
    }

    public void loadPause(PauseMenu menu) {
    }

    public void loadKill(GameOverMenu menu) {
    }

    public void loadLoad(LoadMenu menu) {
    }

    public void loadSave(SaveMenu menu) {
    }
}
