package src.Audio;

import javax.sound.sampled.Clip;

public class MusicLoop extends Playable{
    int loopStartFrame;
    int loopEndFrame;
    private double fadeSeconds = 3.0;

    public MusicLoop(Clip c, AudioSettings as, int ls, int le) {
        clip = c;
        settings = as;
        loopStartFrame = ls;
        loopEndFrame = le;
    }

    public void setVolume() {
        super.setVolume(settings.getMusicVolume());
    }

    @Override
    public void play() {
        clip.setFramePosition(0);
        clip.loop(Clip.LOOP_CONTINUOUSLY);
        clip.setLoopPoints(loopStartFrame, loopEndFrame);
        clip.start();
    }

    // Method to stop playing and fade out over 3 seconds
    public void stop(boolean fade) {
        if (clip != null && clip.isRunning()) {
            if (fade) {
                new Thread(() -> {
                    // Fade out over 3 seconds
                    for (int i = (int) (100 * fadeSeconds); i >= 0; i--) {
                        double volume = i / 100.0 * fadeSeconds; // From 1.0 to 0.0
                        setVolume(volume * settings.getMusicVolume());

                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                    clip.stop(); // Stop the clip after fade-out
                }).start();
            } else {
                clip.loop(0);
            }
        }
    }
}
