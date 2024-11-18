package src.Audio;

import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public abstract class Playable {
    Clip clip;
    //AudioSettings settings;

    public void setVolume(double volume) {
        FloatControl gain = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        gain.setValue(20f * (float) Math.log10(volume));
    }

    public void play() {
        clip.setFramePosition(0);
        clip.start();
    }
}
