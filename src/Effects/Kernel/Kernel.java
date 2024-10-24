package src.Effects.Kernel;

import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ConvolveOp;

public interface Kernel {
    float[] createKernel();

    int getSize();

    default BufferedImage applyKernel(BufferedImage srcImage) {
        float[] matrix = createKernel();

        BufferedImageOp op = new ConvolveOp(new java.awt.image.Kernel(getSize(), getSize(), matrix), ConvolveOp.EDGE_NO_OP, null);
        BufferedImage destination = new BufferedImage(srcImage.getWidth(), srcImage.getHeight(), srcImage.getType());
        return op.filter(srcImage, destination);
    }
}
