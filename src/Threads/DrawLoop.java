package src.Threads;

import src.Settings.Settings;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;

public class DrawLoop extends RateLimited
{

    private Engine engine;
    private JPanel panel;
    private Graphics2D glass;
    private Graphics graphics;
    protected BufferedImage lastFrame;
    Toolkit tk;
    private int frameCounter = 0;

    public int blurCounter = 0;

    public DrawLoop(Engine engine, Graphics g, Toolkit tk)
    {
        super(Settings.FRAMERATE);
        this.engine = engine;
        panel = new JPanel();
        graphics = g;
        this.tk = tk;
    }

    public JPanel getPanel() {
        return panel;
    }

    private double randomRotate(double currRot, double delta) {
        return currRot + delta;
    }

    @Override
    public void run() {
        while(true)
        {
            long startTime = System.nanoTime();
            frameCounter += 1;

            int width = tk.getScreenSize().width;
            int height = tk.getScreenSize().height;

            BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = image.createGraphics();

            g2d.setColor(Color.BLACK);
            g2d.fillRect(0, 0, width, height);

            switch(engine.engineState) {
                case GameStates.PRELOAD -> {
                }
                case GameStates.LOADING, GameStates.LOADING_CLASSIC -> {
                    engine.splash.drawCentered(g2d, width/2, height/2);
                }
                case GameStates.MAIN_MENU -> {
                    engine.mainMenu.draw(g2d);
                }
                case GameStates.OPTIONS_MENU -> {
                    engine.optionsMenu.draw(g2d);
                }
                case GameStates.RUNNING_CLASSIC -> {
                    engine.level.draw(g2d);
                    if (engine.debug) {
                        g2d.setColor(Color.RED);
                        g2d.drawString(String.format("UPS: %.2f  Update: %d", engine.rate, engine.updateCounter), 30, 30);
                        g2d.drawString(String.format("FPS: %.2f  Frame: %d", rate, frameCounter), 30, 30 + height/24);
                    }
                }
                case GameStates.PAUSE_MENU -> {
                    JComponent glasspane = (JComponent) engine.frame.getGlassPane();
                    glass = (Graphics2D) glasspane.getGraphics();

                    // Apply Gaussian blur kernel with specified size
                    //BufferedImage blurredImage = applyGaussianBlur(lastFrame);

                    /*if (blurCounter < Settings.BLURCOUNT) {
                        lastFrame = blurredImage;
                        blurCounter += 1;
                    }*/

                    //Graphics g = glass.getGraphics();
                    //glass.drawImage(blurredImage, 0, 0, null);

                    engine.pauseMenu.draw(g2d);
                }
                case GameStates.GAME_OVER -> {
                    JComponent glasspane = (JComponent) engine.frame.getGlassPane();
                    glass = (Graphics2D) glasspane.getGraphics();

                    // Apply Gaussian blur kernel with specified size
                    //BufferedImage blurredImage = applyGaussianBlur(lastFrame);

                    /*if (blurCounter < Settings.BLURCOUNT) {
                        lastFrame = blurredImage;
                        blurCounter += 1;
                    }*/

                    //Graphics g = glass.getGraphics();
                    //glass.drawImage(blurredImage, 0, 0, null);

                    engine.killMenu.draw(g2d);
                }
                case GameStates.LOAD_MENU -> {}
                case GameStates.SAVE_MENU -> {}
            }

            graphics.drawImage(image, 0, 0, null);
            lastFrame = image;
            blurCounter = 0;

            limit(startTime);
        }
    }
}
