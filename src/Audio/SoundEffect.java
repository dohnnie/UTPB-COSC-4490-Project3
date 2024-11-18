package src.Audio;

import src.Settings.Settings;

import javax.sound.sampled.Clip;

public class SoundEffect extends Playable{
    public SoundEffect(Clip c) {
        clip = c;
    }

    public void test() {
        setVolume(0.0);
        play();
        setVolume();
    }

    public void setVolume() {
        super.setVolume(Settings.fxVolume);
    }
}
