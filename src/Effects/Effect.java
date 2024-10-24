package src.Effects;

import java.awt.image.BufferedImage;

public interface Effect {
    void update();

    void setOpacity(double opacity);

    BufferedImage getEffect();
}
