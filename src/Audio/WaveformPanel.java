package src.Audio;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class WaveformPanel extends JPanel {
    private byte[] audioData;
    private AudioFormat format;
    private Clip audioClip;
    private int playbackPosition = 0;  // Frame position of current playback

    public void loadAudioData(AudioInputStream audioStream) throws IOException {
        format = audioStream.getFormat();
        audioData = audioStream.readAllBytes();
    }

    public void setAudioClip(Clip audioClip) {
        this.audioClip = audioClip;
    }

    public void setPlaybackPosition(int playbackPosition) {
        this.playbackPosition = playbackPosition;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (audioData == null || format == null) {
            return;
        }

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int width = getWidth();
        int height = getHeight();

        // Divide the height in two for left and right channels
        int halfHeight = height / 2;
        int quarterHeight = height / 4;

        // Draw the left and right channels separately
        drawWaveform(g2d, width, halfHeight - quarterHeight, quarterHeight, audioData, format, 0);  // Left channel
        drawWaveform(g2d, width, halfHeight + quarterHeight, quarterHeight, audioData, format, 1);  // Right channel

        // Draw the vertical green line showing current playback position
        if (audioClip != null && audioClip.isRunning()) {
            int currentFrame = playbackPosition;
            double positionRatio = (double) currentFrame / audioClip.getFrameLength();
            int xPosition = (int) (positionRatio * width);

            g2d.setColor(Color.GREEN);
            g2d.drawLine(xPosition, 0, xPosition, height);  // Vertical green line
        }
    }

    private void drawWaveform(Graphics2D g2d, int width, int yOffset, double vertScale, byte[] audioData, AudioFormat format, int channel) {
        int frameSize = format.getFrameSize();
        int sampleSizeInBits = format.getSampleSizeInBits();
        int sampleBytes = sampleSizeInBits / 8;

        // Scale factor to fit the waveform in the available space
        int framesPerPixel = audioData.length / frameSize / width;

        g2d.setColor(Color.BLACK);
        g2d.drawLine(0, yOffset, width, yOffset); // Draw center line

        g2d.setColor(Color.BLUE);

        // Extract samples and draw the waveform
        for (int x = 0; x < width; x++) {
            int sampleIndex = x * framesPerPixel * frameSize + channel * sampleBytes;
            int sample = getSampleValue(audioData, sampleIndex, sampleSizeInBits, format.isBigEndian());
            double scale = vertScale / 100.0;
            System.out.printf("%d %d %.2f %d%n",
                    (int) (sample * scale * (100.0 / (1 << (sampleSizeInBits - 1)))),
                    sample,
                    scale,
                    (1 << (sampleSizeInBits - 1))
            );

            // Scale the sample value to fit the drawing area
            int sampleHeight = (int) (sample * scale * (100.0 / (1 << (sampleSizeInBits - 1))));

            g2d.drawLine(x, yOffset, x, yOffset - sampleHeight);
        }
    }

    // Helper method to get the sample value from the byte array
    private int getSampleValue(byte[] buffer, int start, int sampleSizeInBits, boolean isBigEndian) {
        if (start < 0 || start >= buffer.length - (sampleSizeInBits / 8)) {
            return 0; // Avoid array out of bounds
        }

        ByteBuffer byteBuffer = ByteBuffer.wrap(buffer, start, sampleSizeInBits / 8);
        byteBuffer.order(isBigEndian ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN);

        if (sampleSizeInBits == 16) {
            return byteBuffer.getShort(); // 16-bit sample
        } else if (sampleSizeInBits == 8) {
            return byteBuffer.get(); // 8-bit sample
        } else {
            throw new UnsupportedOperationException("Unsupported sample size: " + sampleSizeInBits);
        }
    }
}
