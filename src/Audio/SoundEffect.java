package src.Audio;

import javax.sound.sampled.Clip;

public class SoundEffect extends Playable{
    public SoundEffect(Clip c, AudioSettings as) {
        clip = c;
        settings = as;
    }

    public void setVolume() {
        super.setVolume(settings.getFxVolume());
    }
}
