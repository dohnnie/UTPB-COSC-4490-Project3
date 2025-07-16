package LevelEditor;

import Enums.Elements;
import GameObjects.Enemy;
import GameObjects.Player;
import GameObjects.Sprite;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public class LevelLoader {

    static Toolkit tk = Toolkit.getDefaultToolkit();

    /*
     * Takes a file path as an input, and if the file exists it will
     * go through the file and return a 2d int array of the csv data
     */

    public static void readCSV(File csvFile, int spriteSize, ArrayList<Sprite> platforms, ArrayList<Enemy> enemies, Player[] pLoc, Sprite[] wLoc) throws IOException {
        float height = tk.getScreenSize().height;
        BufferedReader br = new BufferedReader(new FileReader(csvFile.toString()));
        try {
            String line;
            int row = 0;
            while ((line = br.readLine()) != null) {
                int col = 0;
                String[] data = line.split(",");
                for(String index : data) {
                    Elements gameElement = Elements.getElements(Integer.parseInt(index));
                    int xPos = col * spriteSize;
                    int yPos = (int)(height - (height - (row * spriteSize)));
                    switch (gameElement) {
                        case Platform -> {
                            Sprite platform = new Sprite("./data/Brick.png", new Point(xPos, yPos));
                            platforms.add(platform);
                        }
                        case BasicEnemy -> {
                            float bLeft = col * 100;
                            float bRight = bLeft + 4 * 100;
                            Enemy enemy = new Enemy("./data/Goomba.png", new Point(xPos, yPos), bLeft, bRight);
                            enemies.add(enemy);
                        }
                        case Player -> {
                            Player player = new Player("./data/Mario.png", new Point(xPos, yPos));
                            pLoc[0] = player;
                       }
                        case Flag -> {
                            Sprite flag = new Sprite("./data/flag.png", new Point(xPos, yPos));
                            wLoc[0] = flag;
                       }
                    }
                    col++;
                }
                row++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.err.println("File not found");
        }
    }
}