package src.Loader;

import src.UI.*;
import src.UI.Menu.*;
import src.UI.Menu.Menu;

import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javafx.scene.text.Font;

public class Loader {

    public static String path;
    public static String menuPath;
    public static String actorPath;
    public static String fxPath;
    public static String audioPath;

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

    public UIImage loadSplash() throws IOException {
        UIImage splash = new UIImage(loadImage(path + "splash.png"));
        Toolkit tk = Toolkit.getDefaultToolkit();
        splash.scaleToWidth(tk.getScreenSize().width/4);
        return splash;
    }

    public void loadMenu(Menu menu) throws IOException {
        menu.load(this);
    }

    public static Font loadFont(double size) {
        if (!Font.getFontNames().contains("Ravie")) {
            return Font.loadFont(path + "Ravie.ttf", size);
        }
        return new Font("Ravie", size);
    }

    public void loadGeneral() {
    }
}
