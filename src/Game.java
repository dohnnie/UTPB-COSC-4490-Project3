package src;

import Audio.AudioPlayer;
import Collision.BoxCollider;
import Enums.Directions;
import Enums.GameStates;
import GameObjects.*;
import LevelEditor.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import javax.swing.*;

/*
 * Class responsible for handling all the game logic, entry point for program
 */
public class Game implements Runnable
{
    public Toolkit tk;
    private final GameCanvas canvas;
    public final int SPRITE_SIZE = 100;
    private int bottomBoundary;
    private int boundaryGrace = 45;

    //Player movement clamps and flags
    public Player player;
    private boolean goRight = false;
    private boolean goLeft = false;
    public final double MAX_X_SPEED = 5;
    public final double ACCELERATION = 0.1;
    public final float JUMP_SPEED = 14f;

    //Related to fps
    private final double rateTarget = 100.0;
    public double waitTime = 1000.0 / rateTarget;
    public double rate = 1000 / waitTime;

    //Array objects needed to load level data
    public Sprite winFlag;
    public ArrayList<Sprite> platforms;
    public ArrayList<Enemy> enemies;

    //Misc.
    public boolean debug = false;
    public boolean lastDebugState = debug;
    public GameStates state = GameStates.Menu;
    public double volume = 0.3;
    String filePath = "./data/test_level.csv";

    public Game()
    {
        JFrame frame = new JFrame("Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(true);
        //String filePath = "./data/test_level.csv";

        tk = Toolkit.getDefaultToolkit();
        System.out.println("Screen Width: " + tk.getScreenSize().width + ", Screen Height: " + tk.getScreenSize().height);
        bottomBoundary = tk.getScreenSize().height;

        frame.setVisible(true);
        frame.requestFocus();

        canvas = new GameCanvas(this, frame.getGraphics());
        frame.add(canvas);

        /*try {
            if(state == GameStates.Loading) {
                platforms = new ArrayList<>();
                enemies = new ArrayList<>();


                 
                 * Because you can't pass by reference in java, i have to an evil workaround to pass an object reference between the level loader
                 * and the game engine, because i'm not saving the player or win object to an array list.
                 * This is probably scalable to allow for multiple player objects and win objects to exist within a list but i'm not messing with that
                
                Player[] playerRef = new Player[1];
                Sprite[] objRef = new Sprite[1];
                LevelLoader.readCSV(new File(filePath), SPRITE_SIZE, platforms, enemies, playerRef, objRef);

                winFlag = objRef[0];
                player = playerRef[0];
                state = GameStates.Running;

                //Debug print statements
                System.out.println("Platform Amount: " + platforms.size());
                System.out.println("Enemies Amount: " + enemies.size());
                System.out.println("Player Center X: " + player.box.centerX + ", Player Center Y: " + player.box.centerY);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error reading file");
            System.exit(0);
        }
        */

        Thread drawLoop = new Thread(canvas);
        drawLoop.start();

        //Player controls
        frame.addKeyListener(new KeyListener()
        {
            @Override
            public void keyTyped(KeyEvent e)
            {

            }

            @Override
            public void keyPressed(KeyEvent e)
            {
                switch(e.getKeyCode()) {
                    case KeyEvent.VK_SPACE -> {
                        if(state == GameStates.Running) {
                            player.firing = true;
                        }
                    }
                    case KeyEvent.VK_ESCAPE -> {
                        switch(state) {
                            case GameStates.Running -> { 
                                state = GameStates.Pause;
                                lastDebugState = debug;
                                debug = false;
                            }
                            case GameStates.Pause -> { 
                                state = GameStates.Running;
                                debug = lastDebugState;
                            }
                            default -> { }
                        }
                    }
                    case KeyEvent.VK_W -> {
                        if(state == GameStates.Running) {
                            if(BoxCollider.isOnPlatforms(player, platforms)) {
                                player.yVel -= JUMP_SPEED;
                                AudioPlayer.playSound("./data/sound/jumping.wav");
                            }
                        }
                    }
                    case KeyEvent.VK_A -> {
                        goLeft = true;
                    }
                    case KeyEvent.VK_D -> {
                        goRight = true;
                    }
                    case KeyEvent.VK_1 -> {
                        switch(state) {
                            case Running -> { debug = !debug; }
                        }
                    }
                    case KeyEvent.VK_R -> {
                        if(state == GameStates.Lose || state == GameStates.Pause || state == GameStates.Win) {
                            reset();
                        }
                    }
                    case KeyEvent.VK_E -> {
                        switch(state) {
                            case Pause:
                            case Win:
                            case Menu:
                            case Lose: { 
                                System.exit(0);
                            }
                        }
                    }
                    case KeyEvent.VK_C -> {
                        switch(state) {
                            case Win -> {
                                reset();
                            }
                            case Pause -> {
                                state = GameStates.Running;
                            }
                        }
                    }
                    case KeyEvent.VK_S -> {
                        switch(state) {
                            case Menu -> {
                                state = GameStates.Loading;
                                start(filePath);
                            }
                        }
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e)
            {
                switch(e.getKeyCode()) {
                    case KeyEvent.VK_SPACE -> {
                        player.firing = false;
                        player.projCount = 1;
                    }
                    case KeyEvent.VK_A -> {
                        player.xVel = 0;
                        goLeft = false;
                    }
                    case KeyEvent.VK_D -> {
                        player.xVel = 0;
                        goRight = false;
                    }
                }
            }
        });
    }

    //Game Logic starts
    @Override
    public void run()
    {
        while(true)
        {
            long startTime = System.nanoTime();

            if (state == GameStates.Running)
            {
                if(goLeft) {
                    player.direction = Directions.LEFT;
                    player.xVel -= 0.1f;
                    player.moveHorizontal();
                }
                
                if(goRight) {
                    player.direction = Directions.RIGHT;
                    player.xVel += 0.1f;
                    player.moveHorizontal();
                }

                player.update();

                if(player.fb != null) {
                    ArrayList<Sprite> projColList = BoxCollider.checkCollisionList(player.fb, platforms);
                    player.fb.update(projColList);
                }

                //Evil collision checks
                //I don't know a better way to check for collisions other than iterating through a list
                //and checking each object separately with each other object
                BoxCollider.resolvePlatformCollisions(player, platforms);
                for(Enemy enemy : enemies) {
                    if(enemy.isAlive) {
                        enemy.update();
                        BoxCollider.resolvePlatformCollisions(enemy, platforms);
                        boolean playerCollision = BoxCollider.checkCollision(player, enemy);

                        //I don't want to have to play the game if i want to test something
                        if(!debug) {
                            if(playerCollision) {
                                System.out.println("You died!");
                                state = GameStates.Lose;
                                AudioPlayer.playSound("./data/sound/lose.wav");
                            }

                            if(player.fb != null && player.fb.isActive) {
                                boolean isDead = BoxCollider.checkCollision(player.fb, enemy);
                                if(isDead) {
                                    enemy.isAlive = false;
                                    player.fb.isActive = false;
                                    AudioPlayer.playSound("./data/sound/fireball-collision.wav");
                                }
                            }
                        }
                    }
                }

                boolean outOfBounds = player.box.getBottom() > bottomBoundary + boundaryGrace;
                if(outOfBounds) {
                    state = GameStates.Lose;
                    AudioPlayer.playSound("./data/sound/lose.wav");
                }

                if(BoxCollider.checkCollision(player, winFlag)) {
                    state = GameStates.Win;
                    AudioPlayer.playSound("./data/sound/win.wav");
                }
            }

            long sleep = (long) waitTime - (System.nanoTime() - startTime) / 1000000;
            rate = 1000.0 / Math.max(waitTime - sleep, waitTime);

            try
            {
                Thread.sleep(Math.max(sleep, 0));
            } catch (InterruptedException ex) {
                System.err.println(ex.getMessage());
                System.exit(0);
            }
        }
    }

    public void reset()
    {
        player.reset();
        for(Enemy enemy : enemies) {
            enemy.reset();
        }
        state = GameStates.Running;
        debug = false;
        canvas.viewOrigin.x = 0;
        canvas.viewOrigin.y = 0;
    }

    public void start(String filePath) {
        try {
            if(state == GameStates.Loading) {
                platforms = new ArrayList<>();
                enemies = new ArrayList<>();


                        
                /* Because you can't pass by reference in java, i have to an evil workaround to pass an object reference between the level loader
                * and the game engine, because i'm not saving the player or win object to an array list.
                * This is probably scalable to allow for multiple player objects and win objects to exist within a list but i'm not messing with that
                */
                Player[] playerRef = new Player[1];
                Sprite[] objRef = new Sprite[1];
                LevelLoader.readCSV(new File(filePath), SPRITE_SIZE, platforms, enemies, playerRef, objRef);

                winFlag = objRef[0];
                player = playerRef[0];
                state = GameStates.Running;

                //Debug print statements
                System.out.println("Platform Amount: " + platforms.size());
                System.out.println("Enemies Amount: " + enemies.size());
                System.out.println("Player Center X: " + player.box.centerX + ", Player Center Y: " + player.box.centerY);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error reading file");
            System.exit(0);
        }
    }

    public static void main(String[] args)
    {
        Game game = new Game();

        Thread logicLoop = new Thread(game);
        logicLoop.start();
    }
}
