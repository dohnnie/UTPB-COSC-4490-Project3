package src.Audio;
/*
import be.tarsos.dsp.AudioEvent;
import be.tarsos.dsp.AudioProcessor;
import be.tarsos.dsp.GainProcessor;
import be.tarsos.dsp.filters.LowPassFS;
import be.tarsos.dsp.io.jvm.JVMAudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.sound.sampled.LineUnavailableException;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class AudioEffects {

    public static Clip applyEffects(Clip clip) {
        // Convert Clip to TarsosDSP format
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(clip.getFormat(), clip.getFramePosition());
        AudioInputStream ais = AudioInputStream.
        JVMAudioInputStream tarsosStream = new JVMAudioInputStream(audioInputStream);

        // Set up a low-pass filter with a 500Hz cutoff frequency
        LowPassFS lowPass = new LowPassFS(500, (float) clip.getFormat().getSampleRate());

        // Set up a gain processor to adjust volume (reduction by 30%)
        GainProcessor gainProcessor = new GainProcessor(0.7f);

        // Create audio processing pipeline
        AudioProcessor processor = new AudioProcessor() {
            @Override
            public boolean process(AudioEvent audioEvent) {
                lowPass.process(audioEvent);
                gainProcessor.process(audioEvent);
                return true;
            }

            @Override
            public void processingFinished() {
                // Optionally, add any cleanup or final steps here
            }
        };

        // Apply the processors and return the modified clip
        tarsosStream.addProcessor(processor);
        // Rebuild the clip from the processed audio stream here...
    }

    // Applies effects to the audio Clip (low-pass filter and volume reduction)
    public static Clip applyEffects(Clip clip) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        // Get the AudioFormat and AudioInputStream of the Clip
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(clip.getFormat(), new ByteArrayInputStream(getAudioData(clip)));

        // Apply the low-pass filter
        byte[] filteredData = applyLowPassFilter(audioInputStream);

        // Apply volume reduction
        byte[] adjustedData = adjustVolume(filteredData, 0.7f); // Reduce volume by 30%

        // Return a new Clip with processed audio
        ByteArrayInputStream bais = new ByteArrayInputStream(adjustedData);
        AudioInputStream processedAudioInputStream = new AudioInputStream(bais, audioInputStream.getFormat(), adjustedData.length);

        Clip processedClip = AudioSystem.getClip();
        processedClip.open(processedAudioInputStream);

        return processedClip;
    }

    // Get the audio data from the Clip
    private static byte[] getAudioData(Clip clip) throws IOException {
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(clip.getFormat(), clip.getFramePosition());
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = audioInputStream.read(buffer)) != -1) {
            baos.write(buffer, 0, bytesRead);
        }
        return baos.toByteArray();
    }

    // Apply a simple low-pass filter (attenuating high frequencies)
    private static byte[] applyLowPassFilter(AudioInputStream audioInputStream) throws IOException {
        byte[] audioData = audioInputStream.readAllBytes();
        byte[] filteredData = new byte[audioData.length];

        // Simple low-pass filter implementation (averaging neighboring samples)
        for (int i = 1; i < audioData.length - 1; i++) {
            // Average current sample with the previous and next samples
            filteredData[i] = (byte) ((audioData[i - 1] + audioData[i] + audioData[i + 1]) / 3);
        }

        return filteredData;
    }

    // Adjust volume by multiplying sample values by a factor
    private static byte[] adjustVolume(byte[] audioData, float volumeFactor) {
        byte[] adjustedData = new byte[audioData.length];

        for (int i = 0; i < audioData.length; i++) {
            // Multiply each sample by the volume factor (0.0 - 1.0)
            adjustedData[i] = (byte) (audioData[i] * volumeFactor);
        }

        return adjustedData;
    }
}
*/