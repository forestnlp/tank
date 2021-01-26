package com.tank;

import java.awt.*;

import static com.tank.DIR.UP;

public class Tank {
    int x = 200, y = 200;

    DIR dir = UP;

    TankFrame frame;

    public Tank(int x, int y, DIR dir, TankFrame frame) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.frame = frame;
    }

    boolean moving = false;

    static final int speed = 15;

    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.red);
        g.fillRect(x, y, 50, 50);
        g.setColor(c);
        move();
    }

    private void move() {
        if (!moving) return;

        switch (dir) {
            case LEFT:
                x -= speed;
                break;
            case RIGHT:
                x += speed;
                break;
            case UP:
                y -= speed;
                break;
            case DOWN:
                y += speed;
                break;
        }
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public DIR getDir() {
        return dir;
    }

    public void setDir(DIR dir) {
        this.dir = dir;
    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public void fire() {
        frame.bullets.add(new Bullet(this.x,this.y,dir,frame));
    }
}
