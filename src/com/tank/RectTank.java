package com.tank;

import com.tank.factory.BaseBullet;
import com.tank.factory.BaseTank;

import java.awt.*;
import java.util.Random;

import static com.tank.DIR.UP;

public class RectTank extends BaseTank {

    private int x, y;

    public static final int WIDTH = ResourceMgr.goodTankU.getWidth();
    public static final int HEIGHT = ResourceMgr.goodTankU.getHeight();

    private boolean living = true;
    private Group group;

    //Rectangle rec = new Rectangle();

    DIR dir = UP;

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    TankFrame frame;

    boolean moving = true;

    static final int speed = 5;

    FireStrategy fireStrategy;

    private Random random = new Random();

    public RectTank(int x, int y, DIR dir, TankFrame frame, Group group) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.frame = frame;
        this.group = group;
        rec.x = this.x;
        rec.y = this.y;
        rec.width = WIDTH;
        rec.height = HEIGHT;
    }

    public void paint(Graphics g) {
        if (!this.living) frame.tanks.remove(this);
        Color c = g.getColor();
        g.setColor(Color.red);
        if(group==Group.bad) {
            g.setColor(Color.YELLOW);
        } else {
            g.setColor(Color.RED);
        }
        g.fillRect(x,y,40,40);
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

        if (group == Group.bad && random.nextInt(100) > 95)
            fire();
        if (group == Group.bad && random.nextInt(100) > 95)
            randomDir();

        boundsCheck();
        rec.x = this.x;
        rec.y = this.y;
    }

    private void boundsCheck() {
        if (x < 0) x = 0;
        if (y < 30) y = 30;
        if (x > frame.GAME_WIDTH - RectTank.WIDTH) x = frame.GAME_WIDTH - RectTank.WIDTH;
        if (y > frame.GAME_HEIGHT - RectTank.HEIGHT) y = frame.GAME_HEIGHT - RectTank.HEIGHT;
    }

    private void randomDir() {
        dir = DIR.values()[random.nextInt(4)];
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
        int bX = this.getX() + this.WIDTH/2-this.WIDTH/2;
        int bY = this.getY() + this.HEIGHT/2-this.HEIGHT/2;

        BaseBullet bullet = this.frame.factory.createBullet(this.getX(), this.getY(), this.dir, this.frame, this.getGroup());

        if(this.getGroup()==Group.good)
            new Thread(()->{
                new Audio("audio/tank_fire.wav").play();
            }).start();

    }

    public void die() {
        this.living = false;
    }
}
