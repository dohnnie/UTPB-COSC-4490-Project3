package src.Audio;

import javax.sound.sampled.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AudioWaveformViewer extends JFrame {
    private WaveformPanel waveformPanel;
    private Clip audioClip;
    private JButton playPauseButton;
    private JButton stopButton;
    private JSlider volumeSlider;
    private JLabel volumePercentageLabel;
    private boolean isPlaying = false;
    private ExecutorService playbackExecutor;
    private AudioInputStream originalAudioStream;
    private byte[] originalAudioData;

    public AudioWaveformViewer() {
        super("Audio Waveform Viewer");

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);

        waveformPanel = new WaveformPanel();
        add(waveformPanel, BorderLayout.CENTER);

        playPauseButton = new JButton("Play");
        playPauseButton.setEnabled(false);  // Disable button until file is loaded
        playPauseButton.addActionListener(e -> togglePlayPause());

        stopButton = new JButton("Stop");
        stopButton.setEnabled(false);
        stopButton.addActionListener(e -> stopPlayback());

        // Create a volume slider and its related labels
        JLabel volumeLabel = new JLabel("Volume:");
        volumeSlider = new JSlider(0, 100, 50);  // Range 0% to 100%, starting at 50%
        volumePercentageLabel = new JLabel("50%");

        // Add a change listener to the slider to update the volume and label dynamically
        volumeSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int volume = volumeSlider.getValue();
                volumePercentageLabel.setText(volume + "%");
                setVolume(volume / 100.0f);  // Convert percentage to a fraction (0.0 to 1.0)
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(playPauseButton);
        buttonPanel.add(stopButton);
        buttonPanel.add(volumeLabel);
        buttonPanel.add(volumeSlider);
        buttonPanel.add(volumePercentageLabel);
        add(buttonPanel, BorderLayout.SOUTH);

        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");

        JMenuItem openItem = new JMenuItem("Open File");
        openItem.addActionListener(e -> openAudioFile());
        fileMenu.add(openItem);

        JMenuItem undoItem = new JMenuItem("Undo Edits");
        undoItem.addActionListener(e -> revertEdits());
        fileMenu.add(undoItem);

        JMenuItem saveItem = new JMenuItem("Save File");
        saveItem.addActionListener(e -> {});//saveAudioFile());
        fileMenu.add(saveItem);

        JMenuItem exitItem = new JMenuItem("Exit Program");
        exitItem.addActionListener(e -> System.exit(0));
        fileMenu.addSeparator();
        fileMenu.add(exitItem);

        menuBar.add(fileMenu);

        JMenu effectsMenu = new JMenu("Effects");

        JMenuItem highPassFilterItem = new JMenuItem("High-Pass Filter");
        highPassFilterItem.addActionListener(e -> openHighPassFilterDialog());
        effectsMenu.add(highPassFilterItem);

        menuBar.add(effectsMenu);

        JMenu loopMenu = new JMenu("Looping");

        menuBar.add(loopMenu);

        setJMenuBar(menuBar);

        setSize(800, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    // Method to handle file opening and decoding
    private void openAudioFile() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("Audio Files", "wav", "mp3"));
        int result = fileChooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            File audioFile = fileChooser.getSelectedFile();

            try {
                AudioInputStream audioStream = getAudioInputStream(audioFile);
                originalAudioStream = audioStream;
                originalAudioData = audioStream.readAllBytes();
                if (audioStream != null) {
                    if (audioClip != null) {
                        audioClip.close();
                    }

                    audioClip = AudioSystem.getClip();
                    audioClip.open(audioStream);

                    waveformPanel.loadAudioData(audioStream);
                    waveformPanel.setAudioClip(audioClip);
                    waveformPanel.repaint();

                    playPauseButton.setEnabled(true);
                    stopButton.setEnabled(true);

                    // Set the initial volume to 50% (slider default)
                    setVolume(0.5f);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error loading audio file: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        }
    }

    // Method to get AudioInputStream, handles wav files (for mp3 support, use JLayer or mp3spi library)
    private AudioInputStream getAudioInputStream(File audioFile) throws UnsupportedAudioFileException, IOException {
        if (audioFile.getName().toLowerCase().endsWith(".wav")) {
            return AudioSystem.getAudioInputStream(audioFile);
        }
        // TODO: Add MP3 decoding using JLayer/mp3spi if needed.
        return null;
    }

    // Method to toggle play/pause functionality
    private void togglePlayPause() {
        if (isPlaying) {
            // Pause the audio
            audioClip.stop();
            playPauseButton.setText("Play");
            isPlaying = false;
        } else {
            // Start or resume the audio
            if (audioClip.getFramePosition() == audioClip.getFrameLength()) {
                audioClip.setFramePosition(0);  // If the audio is at the end, reset to the beginning
            }

            audioClip.start();
            playPauseButton.setText("Pause");
            isPlaying = true;

            // Start a background thread to track the current playback position
            playbackExecutor = Executors.newSingleThreadExecutor();
            playbackExecutor.submit(() -> {
                while (isPlaying && audioClip.isRunning()) {
                    waveformPanel.setPlaybackPosition(audioClip.getFramePosition());
                    waveformPanel.repaint();

                    try {
                        Thread.sleep(50); // Update the position every 50 ms
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            });
        }
    }

    private void stopPlayback() {
        if (isPlaying) {
            // Stop the audio
            audioClip.stop();
            playPauseButton.setText("Play");
            isPlaying = false;
            // Reset to beginning;
            audioClip.setFramePosition(0);
            // Update the waveformPanel
            waveformPanel.setPlaybackPosition(audioClip.getFramePosition());
            waveformPanel.repaint();
        }
    }

    // Method to set the volume
    private void setVolume(float volume) {
        if (audioClip != null && audioClip.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
            FloatControl volumeControl = (FloatControl) audioClip.getControl(FloatControl.Type.MASTER_GAIN);
            float min = volumeControl.getMinimum();
            float max = volumeControl.getMaximum();
            float dB = min + (max - min) * volume;  // Scale volume to decibel range
            volumeControl.setValue(dB);
        }
    }

    // Method to open the High-Pass Filter settings dialog
    private void openHighPassFilterDialog() {
        JDialog dialog = new JDialog(this, "High-Pass Filter Settings", true);
        dialog.setLayout(new GridLayout(3, 2));

        JLabel cutoffLabel = new JLabel("Cutoff Frequency (Hz): ");
        JTextField cutoffField = new JTextField("1000");  // Default value for cutoff frequency
        dialog.add(cutoffLabel);
        dialog.add(cutoffField);

        JButton applyButton = new JButton("Apply");
        JButton cancelButton = new JButton("Cancel");

        dialog.add(applyButton);
        dialog.add(cancelButton);

        applyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int cutoffFrequency = Integer.parseInt(cutoffField.getText());
                    applyHighPassFilter(cutoffFrequency);
                    dialog.dispose();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(dialog, "Invalid cutoff frequency!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        cancelButton.addActionListener(e -> dialog.dispose());

        dialog.pack();
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    private void revertEdits() {
        try {
            AudioFormat format = originalAudioStream.getFormat();
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(originalAudioData);
            AudioInputStream filteredAudioStream = new AudioInputStream(byteArrayInputStream, format, originalAudioData.length / format.getFrameSize());

            // Reload the Clip with the filtered data
            audioClip.close();
            audioClip.open(filteredAudioStream);
            waveformPanel.loadAudioData(filteredAudioStream);
            waveformPanel.repaint();
        } catch (IOException | LineUnavailableException ioEx) {

        }
    }

    // Method to apply a high-pass filter to the audio data and reload the Clip
    private void applyHighPassFilter(int cutoffFrequency) {
        try {
            // Retrieve the audio format
            AudioFormat format = originalAudioStream.getFormat();
            byte[] filteredAudioData = highPassFilter(originalAudioData, format, cutoffFrequency);

            // Create a new AudioInputStream with the filtered data
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(filteredAudioData);
            AudioInputStream filteredAudioStream = new AudioInputStream(byteArrayInputStream, format, filteredAudioData.length / format.getFrameSize());

            // Reload the Clip with the filtered data
            audioClip.close();
            audioClip.open(filteredAudioStream);
            waveformPanel.loadAudioData(filteredAudioStream);
            waveformPanel.repaint();
        } catch (IOException | LineUnavailableException ex) {
            ex.printStackTrace();
        }
    }

    // Method to perform the high-pass filter on raw audio data
    private byte[] highPassFilter(byte[] audioData, AudioFormat format, int cutoffFrequency) {
        // Implement a simple high-pass filter (e.g., using a Butterworth or FIR filter)
        // This method will process the audioData array and return the filtered version of it.

        int sampleRate = (int) format.getSampleRate();
        int sampleSizeInBits = format.getSampleSizeInBits();
        boolean isBigEndian = format.isBigEndian();

        // Filter implementation goes here (simplified example):
        byte[] filteredData = new byte[audioData.length];

        // Assuming the audio is in PCM format (signed 16-bit audio)
        for (int i = 0; i < audioData.length - 2; i += 2) {
            // Extract the sample (assuming 16-bit samples)
            int sample = (isBigEndian)
                    ? ((audioData[i] << 8) | (audioData[i + 1] & 0xFF))
                    : ((audioData[i + 1] << 8) | (audioData[i] & 0xFF));

            // Apply a simple high-pass filter (this is a placeholder; in practice you'd use a proper filter)
            // You can use DSP techniques or pre-existing libraries for this.
            int filteredSample = applySimpleHighPassFilter(sample, cutoffFrequency, sampleRate);

            // Store the filtered sample back (assuming 16-bit audio)
            if (isBigEndian) {
                filteredData[i] = (byte) (filteredSample >> 8);
                filteredData[i + 1] = (byte) (filteredSample & 0xFF);
            } else {
                filteredData[i] = (byte) (filteredSample & 0xFF);
                filteredData[i + 1] = (byte) (filteredSample >> 8);
            }
        }

        return filteredData;
    }

    // Placeholder for a simple high-pass filter (replace this with a real implementation)
    private int applySimpleHighPassFilter(int sample, int cutoffFrequency, int sampleRate) {
        // Implement a high-pass filter here. This is just a simple placeholder for the actual logic.
        // You could use algorithms like FIR filters or Butterworth filters.

        // Example: Use a simple high-pass filter logic
        // (this is oversimplified and should be replaced with real filtering)
        return sample;  // Return the unfiltered sample as a placeholder
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AudioWaveformViewer viewer = new AudioWaveformViewer();
            viewer.setVisible(true);
        });
    }
}
