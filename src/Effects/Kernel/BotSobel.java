package src.Effects.Kernel;

public class BotSobel implements Kernel{
    @Override
    public float[] createKernel() {
        float[] matrix = {-1, -2, -1, 0, 0, 0, 1, 2, 1};

        return matrix;
    }

    @Override
    public int getSize() {
        return 3;
    }
}
