package src.Audio;

import src.Settings.Settings;

import javax.sound.sampled.Clip;

public class SoundEffect extends Playable{
    public SoundEffect(Clip c) {
        clip = c;
        test();
    }

    public void setVolume() {
        super.setVolume(Settings.fxVolume);
    }
}
