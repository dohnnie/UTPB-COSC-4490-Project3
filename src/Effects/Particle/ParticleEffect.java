package src.Effects.Particle;

import src.Effects.Effect;

public interface ParticleEffect extends Effect {
    int getZLayer();
    void spawn(int x, int y);
}
