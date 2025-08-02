package Collision;

import GameObjects.Enemy;
import GameObjects.Sprite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

public class BoxCollider {
    public float centerX, centerY;
    public int width, height;
    public Point origin;

    private static float buffer = 5;

    public BoxCollider(int x, int y, int width, int height) {
        this.width = width;
        this.height = height;

        origin = new Point(x, y);
        centerX = x + (float)(width / 2);
        centerY = y + (float)(height / 2);
    }

    public void drawBox(Graphics g) {
        g.setColor(Color.red);
        g.drawRect((int)centerX - (width / 2), (int)centerY - (height / 2), width, height);
    }

    public float getLeft() {
        return centerX - (float)(width / 2);
    }

    public void setLeft(float newLeft) {
        centerX = newLeft + (float)(width / 2);
    }

    public float getRight() {
        return centerX + (float)(width / 2);
    }

    public void setRight(float newRight) {
        centerX = newRight - (float)(width / 2);
    }

    public float getTop() {
        return centerY - (float)(height / 2);
    }

    public void setTop(float newTop) {
        centerY = newTop + (float)(height / 2);
    }

    public float getBottom() {
        return centerY + (float)(height / 2);
    }

    public void setBottom(float newBottom) {
        centerY = newBottom - (float)(height / 2);
    }

    public static boolean checkCollision(Sprite s1, Sprite s2) {
        boolean noXOverlap = s1.box.getRight() <= s2.box.getLeft() || s1.box.getLeft() >= s2.box.getRight();
        boolean noYOverlap = s1.box.getBottom() <= s2.box.getTop() || s1.box.getTop() >= s2.box.getBottom();


        return !(noXOverlap || noYOverlap);
    }

    public static ArrayList<Sprite> checkCollisionList(Sprite sprite, ArrayList<Sprite> list) {
        ArrayList<Sprite> collisionList = new ArrayList<>();
        for(Sprite toCollide : list) {
            if(checkCollision(sprite, toCollide)) {
                collisionList.add(toCollide);
            }
        }

        return collisionList;
    }

    public static ArrayList<Enemy> checkEnemyCollisionList(Sprite sprite, ArrayList<Enemy> list) {
        ArrayList<Enemy> collisionList = new ArrayList<>();
        for(Enemy enemy : list) {
            if(checkCollision(sprite, enemy)) {
                collisionList.add(enemy);
            }
        }

        return collisionList;
    }
    
    public static boolean isOnPlatforms(Sprite s, ArrayList<Sprite> walls) {
        s.box.centerY += 5;
        ArrayList<Sprite> colList = checkCollisionList(s, walls);
        s.box.centerY -= 5;

        return !colList.isEmpty();
    }

    /*
     * Bug: Because the sprite's y component changes every frame due to gravity it will always
     * perform the vertical collision resolutions so if a horizontal collision were to occur it
     * will just do the vertical resolution instead of the correct horizontal collision
     * 
     * I don't know how to fix this bug without doing a major overhaul to the collision system
     * which i've already done like 5 times, so i'm just going to leave it like this till i figure
     * it out or till i have to change it.
     */
    public static void resolvePlatformCollisions(Sprite s, ArrayList<Sprite> walls) {
        s.yVel += s.GRAVITY;
        s.box.centerY += s.yVel;

        ArrayList<Sprite> colList = BoxCollider.checkCollisionList(s, walls);
        if(!colList.isEmpty()) {
            Sprite collided = colList.getFirst();
            if(s.yVel > 0) {
                s.box.setBottom(collided.box.getTop());
            } else if(s.yVel < 0) {
                s.box.setTop(collided.box.getBottom());
            }
            s.yVel = 0;
        }
        
        //Horizontal collision check
        s.box.centerX += s.xVel;
        colList = BoxCollider.checkCollisionList(s, walls);
        if(!colList.isEmpty()) {
            Sprite collided = colList.getFirst();
            if(s.xVel > 0) {
                s.box.setRight(collided.box.getLeft());
            } else if(s.xVel < 0){
                s.box.setLeft(collided.box.getRight());
            }
            s.xVel = 0;
        }
    }
}