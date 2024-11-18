package src.Audio;

import src.Settings.Settings;

import javax.sound.sampled.Clip;

public class MusicFanfare extends Playable{

    public MusicFanfare(Clip c) {
        clip = c;
        test();
    }

    public void setVolume() {
        super.setVolume(Settings.musicVolume);
    }
}
