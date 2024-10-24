package src.Effects.Kernel;

public class LeftSobel implements Kernel{
    @Override
    public float[] createKernel() {
        float[] matrix = {1, 0, -1, 2, 0, -2, 1, 0, -1};

        return matrix;
    }

    @Override
    public int getSize() {
        return 3;
    }
}
