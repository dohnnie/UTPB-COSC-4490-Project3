package src;

import Enums.GameStates;
import GameObjects.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;

public class GameCanvas extends JPanel implements Runnable
{

    private final Game game;
    private final Graphics graphics;

    private final double rateTarget = 60.0;
    private final double waitTime = 1000.0 / rateTarget;
    private double rate = 1000.0 / waitTime;

    public Point viewOrigin;
    public static float leftMargin = 60;
    public static float rightMargin = 400;
    public static float verticalMargin = 40;

    public int screenWidth, screenHeight;
    int width, height;

    public GameCanvas(Game game, Graphics g)
    {
        this.game = game;
        graphics = g;
        viewOrigin = new Point(0, 0);
        screenWidth = game.tk.getScreenSize().width;
        screenHeight = game.tk.getScreenSize().height;

    }

    public void scroll() {
        float rightBoundary = viewOrigin.x + width - rightMargin;
        if(game.player.box.getRight() > rightBoundary) {
            viewOrigin.x += game.player.box.getRight() - rightBoundary;
        }

        float leftBoundary = viewOrigin.x + leftMargin;
        if(game.player.box.getLeft() < leftBoundary) {
            viewOrigin.x -= leftBoundary - game.player.box.getLeft();
        }

        float bottomBoundary = viewOrigin.y + height - verticalMargin;
        if(game.player.box.getBottom() > bottomBoundary) {
            viewOrigin.y += game.player.box.getBottom() - bottomBoundary;
        }

        float topBoundary = viewOrigin.y + verticalMargin;
        if(game.player.box.getTop() < topBoundary){
            viewOrigin.y -= topBoundary - game.player.box.getTop();
        }
    }

    @Override
    public void run() {
        while(true) {
            long startTime = System.nanoTime();

            width = game.tk.getScreenSize().width;
            height = game.tk.getScreenSize().height;

            BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = image.createGraphics();

            if(game.state == GameStates.Running) {
                g2d.setColor(Color.CYAN);
                g2d.fillRect(0, 0, width, height);

                scroll();
                g2d.translate(-viewOrigin.x, -viewOrigin.y);

                game.player.draw(g2d);
                if(game.player.fb != null && game.player.fb.isActive) {
                    game.player.fb.draw(g2d);
                }
                game.winFlag.draw(g2d);
                for (Sprite platform : game.platforms) {
                    platform.draw(g2d);
                }
                for (Enemy enemy : game.enemies) {
                    if(enemy.isAlive) {
                        enemy.draw(g2d);
                    }
                }

                if (game.debug) {
                    g2d.setColor(Color.BLACK);
                    g2d.drawString(String.format("FPS = %.1f", rate), viewOrigin.x + 200, viewOrigin.y + 25);
                    g2d.drawString(String.format("UPS = %.1f", game.rate), viewOrigin.x + 200, viewOrigin.y + 50);
                    game.player.box.drawBox(g2d);
                    for (Sprite enemy : game.enemies) {
                        enemy.box.drawBox(g2d);
                    }
                    for (Sprite platform : game.platforms) {
                        platform.box.drawBox(g2d);
                    }
                    if(game.player.fb != null) {
                        game.player.fb.box.drawBox(g2d);
                    }
                }
            } else if(game.state != GameStates.Running && game.state != GameStates.Loading) {
                g2d.setColor(Color.BLACK);
                g2d.fillRect(0, 0, screenWidth, screenHeight);
                g2d.setColor(Color.WHITE);
                switch(game.state) {
                    case Pause -> {
                        g2d.setFont(new Font("Default", Font.BOLD, 50));
                        g2d.drawString(String.format("PAUSE"),(screenWidth / 2) - 100,(screenHeight / 2) - 100);
                        g2d.setFont(new Font("Default", Font.PLAIN, 30));
                        g2d.drawString(String.format("[C] Continue"),(screenWidth / 2) - 100,(screenHeight / 2) - 50);
                        g2d.drawString(String.format("[R] Restart"),(screenWidth / 2) - 90,(screenHeight / 2) - 10);
                        g2d.drawString(String.format("[E] Exit"),(screenWidth / 2) - 70, (screenHeight / 2) + 30);
                        g2d.drawString(String.format("[1] Debug Mode %s", game.lastDebugState ? "(ON)" : "(OFF)"),(screenWidth / 2) - 160, (screenHeight / 2) + 70);
                    }
                    case Win -> {
                        g2d.setFont(new Font("Default", Font.BOLD, 50));
                        g2d.drawString(String.format("YOU WIN"),(screenWidth / 2) - 115,(screenHeight / 2) - 100);
                        g2d.setFont(new Font("Default", Font.PLAIN, 30));
                        g2d.drawString(String.format("[C] Continue"),(screenWidth / 2) - 90,(screenHeight / 2) - 50);
                        g2d.drawString(String.format("[E] Exit"),(screenWidth / 2) - 70, (screenHeight / 2) - 10);
                    }
                    case Lose -> {
                        g2d.setFont(new Font("Default", Font.BOLD, 50));
                        g2d.drawString(String.format("YOU LOSE"),(screenWidth / 2) - 140,(screenHeight / 2) - 100);
                        g2d.setFont(new Font("Default", Font.PLAIN, 30));
                        g2d.drawString(String.format("[R] Restart"),(screenWidth / 2) - 90,(screenHeight / 2) - 50);
                        g2d.drawString(String.format("[E] Exit"),(screenWidth / 2) - 70, (screenHeight / 2) - 10);
                    }
                    case Menu -> {
                        g2d.setFont(new Font("Default", Font.BOLD, 100));
                        g2d.drawString(String.format("Super M Man"),(screenWidth / 2) - 300,(screenHeight / 2) - 100);
                        g2d.setFont(new Font("Default", Font.PLAIN, 50));
                        g2d.drawString(String.format("[S] Start"),(screenWidth / 2) - 100,(screenHeight / 2) );
                        g2d.drawString(String.format("[E] Exit"),(screenWidth / 2) - 100, (screenHeight / 2) + 60);
                    }
                }
            } else if(game.state == GameStates.Loading) {
                g2d.setColor(Color.BLACK);
                g2d.fillRect(0, 0, screenWidth, screenHeight);
                g2d.setFont(new Font("Default", Font.BOLD, 100));
                g2d.drawString("LOADING", (screenWidth / 2) - 50, (screenHeight / 2) - 50);
            }

            graphics.drawImage(image, 0, 0, null);

            long sleep = (long) waitTime - (System.nanoTime() - startTime) / 1000000;
            rate = 1000.0 / Math.max(waitTime - sleep, waitTime);

            try {
                Thread.sleep(Math.max(sleep, 0));
            } catch (InterruptedException ex) {
                ex.printStackTrace();
                System.exit(0);
            }
        }
    }
}
