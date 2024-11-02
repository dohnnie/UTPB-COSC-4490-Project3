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

    public int menuCursor = 0;
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

    private BufferedImage rotateImg(BufferedImage image, double rot) {
        AffineTransform at = new AffineTransform();
        at.rotate(rot, image.getWidth() / 2, image.getHeight() / 2);
        AffineTransformOp ato = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
        return ato.filter(image, null);
    }


    @Override
    public void run() {
        while(true)
        {
            long startTime = System.nanoTime();

            int width = tk.getScreenSize().width;
            int height = tk.getScreenSize().height;

            BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = image.createGraphics();

            switch(engine.engineState) {
                case GameStates.PRELOAD -> {
                    g2d.setColor(Color.BLACK);
                    g2d.fillRect(0, 0, width, height);
                }
                case GameStates.LOADING -> {
                    engine.splash.drawCentered(g2d, width/2, height/2);
                }
                case GameStates.MAIN_MENU -> {
                    g2d.setColor(Color.CYAN);
                    g2d.fillRect(0, 0, width, height);
                    engine.mainMenu.draw(g2d);
                }
                case GameStates.OPTIONS_MENU -> {
                    engine.optionsMenu.draw(g2d);
                }
                case GameStates.RUNNING_CLASSIC -> {
                    g2d.setColor(Color.CYAN);
                    g2d.fillRect(0, 0, width, height);

                    for (int i = 0; i < engine.clouds.length; i++)
                    {
                        if (engine.clouds[i] != null && !engine.clouds[i].passed)
                            engine.clouds[i].drawCloud(g2d);
                    }

                    engine.bird.drawBird(g2d);

                    g2d.setColor(Color.RED);
                    g2d.drawOval(engine.mouseX - Settings.CURSORSCALE, engine.mouseY - Settings.CURSORSCALE, Settings.CURSORSCALE*2, Settings.CURSORSCALE*2);
                    g2d.drawLine(engine.mouseX - Settings.CURSORSCALE, engine.mouseY, engine.mouseX+Settings.CURSORSCALE, engine.mouseY);
                    g2d.drawLine(engine.mouseX, engine.mouseY-Settings.CURSORSCALE, engine.mouseX, engine.mouseY+Settings.CURSORSCALE);

                    for (int i = 0; i < engine.pipes.length; i++)
                    {
                        if (engine.pipes[i] != null)
                            engine.pipes[i].drawPipe(g2d);
                    }

                    g2d.setColor(Color.BLACK);

                    //g2d.drawString(String.format("Score: %d", engine.score), 25, 25);
                    //g2d.drawString(String.format("High Score: %d", engine.highScore), 25, 50);

                    if (engine.debug) {
                        g2d.drawString(String.format("FPS = %.1f", getRate()), 200, 25);
                        g2d.drawString(String.format("UPS = %.1f", engine.getRate()), 200, 50);
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
