package src.Effects.Kernel;

public class EdgeFind implements Kernel{
    @Override
    public float[] createKernel() {
        float[] matrix = {0, -1, 0, -1, 8, -1, 0, -1, 0};

        return matrix;
    }

    @Override
    public int getSize() {
        return 3;
    }
}
