package src.Audio;

import javax.sound.sampled.Clip;

public class MusicFanfare extends Playable{

    public MusicFanfare(Clip c, AudioSettings as) {
        clip = c;
        settings = as;
    }

    public void setVolume() {
        super.setVolume(settings.getMusicVolume());
    }
}
