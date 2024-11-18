package src.Loader;

import src.Interface.*;
import src.Interface.Menu.Menu;

import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Loader {

    public static String path;
    public static String menuPath;
    public static String actorPath;
    public static String fxPath;
    public static String audioPath;

    public static String menuFontName = "Ravie";

    public Loader() {
        path = String.format("%s%sdata%s", System.getProperty("user.dir"), File.separator, File.separator);
        menuPath = String.format("%smenu%s", path, File.separator);
        actorPath = String.format("%sactors%s", path, File.separator);
        fxPath = String.format("%seffects%s", path, File.separator);
        audioPath = String.format("%saudio%s", path, File.separator);
    }

    public static BufferedImage[] splitFramesHorizontal(BufferedImage image, int numFrames) {
        BufferedImage[] array = new BufferedImage[numFrames];
        return array;
    }

    public static BufferedImage[] splitFramesVertical(BufferedImage image, int numFrames) {
        BufferedImage[] array = new BufferedImage[numFrames];
        return array;
    }

    public static BufferedImage loadImage(String fileName) throws IOException {
        return ImageIO.read(new File(fileName));
    }

    public static Clip loadAudio(String fileName) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        AudioInputStream ais = AudioSystem.getAudioInputStream(new File("data/audio/" + fileName).getAbsoluteFile());
        Clip clip = AudioSystem.getClip();
        clip.open(ais);
        return clip;
    }

    public static void loadMusicLoop(String fileName, int startFrame, int endFrame) {}

    public static InterfaceImage loadSplash() throws IOException {
        InterfaceImage splash = new InterfaceImage(loadImage(path + "splash.png"));
        Toolkit tk = Toolkit.getDefaultToolkit();
        splash.scaleToWidth(tk.getScreenSize().width/4);
        return splash;
    }

    public void loadGeneral() {
        try {
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            for (String fontName : ge.getAvailableFontFamilyNames()) {
                if (fontName.equals(menuFontName)) {
                    return;
                }
            }

            File fontFile = new File(path + "ravie.ttf");
            Font font = Font.createFont(Font.TRUETYPE_FONT, fontFile);
            if (ge.registerFont(font)) {
                return;
            } else {
                menuFontName = "Arial";
            }
        } catch (IOException | FontFormatException _) {
            menuFontName = "Arial";
        }
    }
}
