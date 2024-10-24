package src.Effects.Kernel;

public class BoxBlur implements Kernel{
    @Override
    public float[] createKernel() {
        float[] matrix = {1.0f/9.0f, 1.0f/9.0f, 1.0f/9.0f, 1.0f/9.0f, 1.0f/9.0f, 1.0f/9.0f, 1.0f/9.0f, 1.0f/9.0f, 1.0f/9.0f};

        return matrix;
    }

    @Override
    public int getSize() {
        return 3;
    }
}
