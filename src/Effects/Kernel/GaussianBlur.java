package src.Effects.Kernel;

import src.Settings.Settings;

import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ConvolveOp;

public class GaussianBlur implements Kernel{

    private final int size = 3;

    @Override
    public float[] createKernel() {
        int radius = size / 2;
        float sigma = radius / 2.0f;  // Standard deviation based on radius
        float[] matrix = new float[size * size];
        float sum = 0;

        for (int y = -radius; y <= radius; y++) {
            for (int x = -radius; x <= radius; x++) {
                float value = (float) (Math.exp(-(x * x + y * y) / (2 * sigma * sigma)) / (2 * Math.PI * sigma * sigma));
                matrix[(y + radius) * size + (x + radius)] = value;
                sum += value;
            }
        }

        // Normalize the kernel so that the sum of all elements equals 1
        for (int i = 0; i < matrix.length; i++) {
            matrix[i] /= sum;
        }

        return matrix;
    }

    @Override
    public int getSize() {
        return size;
    }
}
