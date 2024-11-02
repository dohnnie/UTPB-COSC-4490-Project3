package src.Loader;

import src.UI.*;
import src.UI.Menu.*;

import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Loader {

    private String path;
    private String menuPath;
    private String actorPath;
    private String fxPath;
    private String audioPath;

    public Loader() {
        path = String.format("%s%sdata%s", System.getProperty("user.dir"), File.separator, File.separator);
        menuPath = String.format("%smenu%s", path, File.separator);
        actorPath = String.format("%sactors%s", path, File.separator);
        fxPath = String.format("%seffects%s", path, File.separator);
        audioPath = String.format("%saudio%s", path, File.separator);
    }

    public BufferedImage[] splitFramesHorizontal(BufferedImage image, int numFrames) {
        BufferedImage[] array = new BufferedImage[numFrames];
        return array;
    }

    public BufferedImage[] splitFramesVertical(BufferedImage image, int numFrames) {
        BufferedImage[] array = new BufferedImage[numFrames];
        return array;
    }

    public BufferedImage loadImage(String fileName) throws IOException {
        return ImageIO.read(new File(fileName));
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

    private void loadAudio(String fileName) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        AudioInputStream ais = AudioSystem.getAudioInputStream(new File("data/audio/flap.wav").getAbsoluteFile());
        Clip clip = AudioSystem.getClip();
        clip.open(ais);
    }

    private void loadMusicLoop(String fileName, int startFrame, int endFrame) {}

    public MenuImage loadSplash() throws IOException {
        MenuImage splash = new MenuImage(loadImage(path + "splash.png"));
        Toolkit tk = Toolkit.getDefaultToolkit();
        splash.scaleToWidth(tk.getScreenSize().width/4);
        return splash;
    }

    public void loadGeneral(GeneralUI ui) throws IOException {
        String fmt = String.format("data%smenu%s", File.separator, File.separator);
        ui.num0 = new MenuImage(loadImage(fmt + "0.png"));
        ui.num1 = new MenuImage(loadImage(fmt + "1.png"));
        ui.num2 = new MenuImage(loadImage(fmt + "2.png"));
        ui.num3 = new MenuImage(loadImage(fmt + "3.png"));
        ui.num4 = new MenuImage(loadImage(fmt + "4.png"));
        ui.num5 = new MenuImage(loadImage(fmt + "5.png"));
        ui.num6 = new MenuImage(loadImage(fmt + "6.png"));
        ui.num7 = new MenuImage(loadImage(fmt + "7.png"));
        ui.num8 = new MenuImage(loadImage(fmt + "8.png"));
        ui.num9 = new MenuImage(loadImage(fmt + "9.png"));

        ui.percent = new MenuImage(loadImage(fmt + "percent.png"));
    }

    public void loadMain(MainMenu menu) throws IOException {
        //menu.titleMusicLoop = loadAudio(null);
        menu.menuBackground = new MenuImage(loadImage(path + "ph.png")); // TODO: real menu background
        menu.menuTitle = new MenuImage(loadImage(menuPath + "gametitle.png"));
        menu.cursorImg = new MenuImage(loadImage(menuPath + "arrow.png"));
        menu.menuItems.add(new MenuSelection(() -> {}, loadImage(menuPath + "newGame.png")));
        menu.menuItems.add(new MenuSelection(() -> {}, loadImage(menuPath + "continue.png")));
        menu.menuItems.add(new MenuSelection(() -> {}, loadImage(menuPath + "loadGame.png")));
        menu.menuItems.add(new MenuSelection(() -> {}, loadImage(menuPath + "levelSelect.png")));
        menu.menuItems.add(new MenuSelection(() -> {}, loadImage(menuPath + "endlessMode.png")));
        menu.menuItems.add(new MenuSelection(() -> {}, loadImage(menuPath + "classicMode.png")));
        menu.menuItems.add(new MenuSelection(() -> {}, loadImage(menuPath + "options.png")));
        menu.menuItems.add(new MenuSelection(() -> {}, loadImage(menuPath + "exitGame.png"))); // TODO: actual credits
        menu.menuItems.add(new MenuSelection(() -> {}, loadImage(menuPath + "quitGame.png")));
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
