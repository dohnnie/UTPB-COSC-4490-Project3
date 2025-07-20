package Audio;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;


public class AudioPlayer {
    public static void playSound(String filename) {
        try {
            File file = new File(filename);
            AudioInputStream audio = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(audio);
            clip.start();
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            e.printStackTrace();
        }
    }
}
