package src.Effects.Screenspace;

import src.Effects.Effect;

public interface ScreenSpaceEffect extends Effect {
    double getXParallax();
    double getYParallax();
    int getZLayer();

}
