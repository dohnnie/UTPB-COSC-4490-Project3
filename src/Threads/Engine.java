package src.Threads;

import src.Levels.Classic.ClassicMode;
import src.Levels.Level;
import src.Loader.Loader;
import src.Interface.*;
import src.Interface.Menu.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Engine extends RateLimited
{
    //private final DrawLoop canvas;

    public InterfaceImage splash;
    MainMenu mainMenu;
    OptionsMenu optionsMenu;
    PauseMenu pauseMenu;
    GameOverMenu killMenu;
    LoadMenu loadMenu;
    SaveMenu saveMenu;
    int gameLevel = 1;
    Level level = null;

    public int mouseX;
    public int mouseY;
    public int fireRate = 10;
    public int fireCounter = 0;
    public boolean firing = false;

    public JFrame frame;
    public Toolkit tk;
    public boolean debug = true;
    public int engineState = GameStates.PRELOAD;

    public int updateCounter = 0;

    public Engine()
    {
        super();
        frame = new JFrame("Game");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        frame.setUndecorated(true);

        tk = Toolkit.getDefaultToolkit();

        frame.setVisible(true);
        frame.requestFocus();

        mainMenu = new MainMenu(this);
        optionsMenu = new OptionsMenu(this);
        loadMenu = new LoadMenu(this);
        saveMenu = new SaveMenu(this);
        pauseMenu = new PauseMenu(this);
        killMenu = new GameOverMenu(this);

        engineState = GameStates.PRELOAD;

        DrawLoop canvas = new DrawLoop(this, frame.getGraphics(), tk);
        frame.add(canvas.getPanel());

        Thread drawLoop = new Thread(canvas);
        drawLoop.start();

        frame.addKeyListener(new KeyListener()
        {
            @Override
            public void keyTyped(KeyEvent e)
            {
            }

            @Override
            public void keyPressed(KeyEvent e)
            {
                if(e.getKeyCode() == KeyEvent.VK_SPACE)
                {
                    switch (engineState) {
                        case GameStates.RUNNING_CLASSIC -> {
                            level.player.flap();
                        }
                    }
                }
                if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
                {
                    switch (engineState) {
                        case GameStates.RUNNING_CLASSIC -> {}
                        case GameStates.RUNNING_ENDLESS -> {}
                        case GameStates.RUNNING_NORMAL -> {}
                        case GameStates.PAUSE_MENU -> {}
                    }
                }
                if(e.getKeyCode() == KeyEvent.VK_UP)
                {
                    switch (engineState) {
                        case GameStates.PRELOAD -> {}
                        case GameStates.LOADING -> {}
                        case GameStates.MAIN_MENU -> {
                            mainMenu.cursorUp();
                        }
                        case GameStates.OPTIONS_MENU -> {
                            optionsMenu.cursorUp();
                        }
                        case GameStates.PAUSE_MENU -> {
                            pauseMenu.cursorUp();
                        }
                        case GameStates.GAME_OVER -> {
                            killMenu.cursorUp();
                        }
                        case GameStates.LOAD_MENU -> {
                            loadMenu.cursorUp();
                        }
                        case GameStates.SAVE_MENU -> {
                            saveMenu.cursorUp();
                        }
                        case GameStates.RUNNING_CLASSIC -> {}
                    }
                }
                if(e.getKeyCode() == KeyEvent.VK_DOWN)
                {
                    switch (engineState) {
                        case GameStates.PRELOAD -> {}
                        case GameStates.LOADING -> {}
                        case GameStates.MAIN_MENU -> {
                            mainMenu.cursorDn();
                        }
                        case GameStates.OPTIONS_MENU -> {
                            optionsMenu.cursorDn();
                        }
                        case GameStates.PAUSE_MENU -> {
                            pauseMenu.cursorDn();
                        }
                        case GameStates.GAME_OVER -> {
                            killMenu.cursorDn();
                        }
                        case GameStates.LOAD_MENU -> {
                            loadMenu.cursorDn();
                        }
                        case GameStates.SAVE_MENU -> {
                            saveMenu.cursorDn();
                        }
                        case GameStates.RUNNING_CLASSIC -> {}
                    }
                }
                if(e.getKeyCode() == KeyEvent.VK_RIGHT)
                {
                    switch (engineState) {
                        case GameStates.PRELOAD -> {}
                        case GameStates.LOADING -> {}
                        case GameStates.MAIN_MENU -> {
                            mainMenu.cursorRt();
                        }
                        case GameStates.OPTIONS_MENU -> {
                            optionsMenu.cursorRt();
                        }
                        case GameStates.PAUSE_MENU -> {
                            pauseMenu.cursorRt();
                        }
                        case GameStates.GAME_OVER -> {
                            killMenu.cursorRt();
                        }
                        case GameStates.LOAD_MENU -> {
                            loadMenu.cursorRt();
                        }
                        case GameStates.SAVE_MENU -> {
                            saveMenu.cursorRt();
                        }
                        case GameStates.RUNNING_CLASSIC -> {}
                    }
                }
                if(e.getKeyCode() == KeyEvent.VK_LEFT)
                {
                    switch (engineState) {
                        case GameStates.PRELOAD -> {}
                        case GameStates.LOADING -> {}
                        case GameStates.MAIN_MENU -> {
                            mainMenu.cursorLt();
                        }
                        case GameStates.OPTIONS_MENU -> {
                            optionsMenu.cursorLt();
                        }
                        case GameStates.PAUSE_MENU -> {
                            pauseMenu.cursorLt();
                        }
                        case GameStates.GAME_OVER -> {
                            killMenu.cursorLt();
                        }
                        case GameStates.LOAD_MENU -> {
                            loadMenu.cursorLt();
                        }
                        case GameStates.SAVE_MENU -> {
                            saveMenu.cursorLt();
                        }
                        case GameStates.RUNNING_CLASSIC -> {}
                    }
                }
                if(e.getKeyCode() == KeyEvent.VK_ENTER)
                {
                    switch (engineState) {
                        case GameStates.PRELOAD -> {}
                        case GameStates.LOADING -> {}
                        case GameStates.MAIN_MENU -> {
                            mainMenu.select();
                        }
                        case GameStates.OPTIONS_MENU -> {
                            optionsMenu.select();
                        }
                        case GameStates.PAUSE_MENU -> {
                            pauseMenu.select();
                        }
                        case GameStates.GAME_OVER -> {
                            killMenu.select();
                        }
                        case GameStates.LOAD_MENU -> {
                            loadMenu.select();
                        }
                        case GameStates.SAVE_MENU -> {
                            saveMenu.select();
                        }
                        case GameStates.RUNNING_CLASSIC -> {}
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e)
            {
            }
        });

        frame.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    firing = true;
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    firing = false;
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        frame.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {

            }

            @Override
            public void mouseMoved(MouseEvent e) {
                mouseX = e.getX();
                mouseY = e.getY();
            }
        });

        frame.addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {

            }
        });
    }

    public void applyBlurEffect(boolean blur) {
        JComponent glass = (JComponent) frame.getGlassPane();
        glass.setVisible(blur); // Ensure glass pane is visible when applying blur

        if (blur) {
            // Paint the blurred image to the glass pane
            glass.setOpaque(false);
            glass.setLayout(new BorderLayout());
        } else {
            // Clear the glass pane to return it to being transparent
            glass.removeAll();
            glass.repaint();
        }
    }

    @Override
    public void run()
    {
        while(true)
        {
            long startTime = System.nanoTime();
            updateCounter += 1;

            switch (engineState) {
                case GameStates.PRELOAD -> {
                    try {
                        Loader.loadGeneral();
                        splash = Loader.loadSplash();
                        engineState = GameStates.LOADING;
                    } catch (Exception ex) {
                        System.out.printf("Fatal exception when loading splash screen!%n");
                        ex.printStackTrace();
                        System.exit(0);
                    }
                }
                case GameStates.LOADING -> {
                    try {
                        System.out.println("Loading main menu graphics...");
                        mainMenu.load();
                        System.out.println("Loading options menu graphics...");
                        optionsMenu.load();
                        System.out.println("Loading pause menu graphics...");
                        pauseMenu.load();
                        System.out.println("Loading kill menu graphics...");
                        killMenu.load();
                        System.out.println("Loading load menu graphics...");
                        loadMenu.load();
                        System.out.println("Loading save menu graphics...");
                        saveMenu.load();
                        engineState = GameStates.MAIN_MENU;
                    } catch (Exception ex) {
                        System.out.printf("Fatal exception during loading!%n");
                        ex.printStackTrace();
                        System.exit(0);
                    }
                }
                case GameStates.MAIN_MENU -> {}
                case GameStates.OPTIONS_MENU -> {}
                case GameStates.RUNNING_CLASSIC -> {
                    if (level != null) {
                        level.update();
                    }
                }
                case GameStates.RUNNING_NORMAL -> {
                }
                case GameStates.PAUSE_MENU -> {}
                case GameStates.GAME_OVER -> {}
                case GameStates.LOAD_MENU -> {}
                case GameStates.SAVE_MENU -> {}
            }

            limit(startTime);
        }
    }

    public void loadLevel(int index) {
    }

    public void loadSave(int index) {
    }

    public void loadEndless() {
    }

    public void loadClassic() {
        engineState = GameStates.LOADING_CLASSIC;
        level = new ClassicMode(this);
        try {
            level.loadArt();
            engineState = GameStates.RUNNING_CLASSIC;
            updateCounter = 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(0);
        }
    }

    public void reset()
    {
        updateCounter = 0;
        level.reset();
    }
}
