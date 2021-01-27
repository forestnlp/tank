package com.tank;

import java.awt.*;
import java.util.Random;

import static com.tank.DIR.UP;

public class Tank {

    private int x , y ;

    public static final int WIDTH = ResourceMgr.tankd.getWidth();
    public static final int HEIGHT = ResourceMgr.tankd.getHeight();

    private boolean living = true;
    private Group group;

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

    private Random random = new Random();
    public Tank(int x, int y, DIR dir, TankFrame frame,Group group) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.frame = frame;
        this.group = group;
    }



    public void paint(Graphics g) {
        if(!this.living) frame.tanks.remove(this);
        Color c = g.getColor();
        g.setColor(Color.red);
        switch (dir){
            case LEFT:
                g.drawImage(ResourceMgr.tankl,x,y,null);
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.tankr,x,y,null);
                break;
            case UP:
                g.drawImage(ResourceMgr.tanku,x,y,null);
                break;
            case DOWN:
                g.drawImage(ResourceMgr.tankd,x,y,null);
                break;
            default:
                break;
        }
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
        if(group==Group.bad&&random.nextInt(100)>95)
            fire();
        if(group==Group.bad&&random.nextInt(100)>95)
            randomDir();
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
        int bX = this.x + Tank.WIDTH/2-Bullet.WIDTH/2;
        int bY = this.y + Tank.HEIGHT/2-Bullet.HEIGHT/2;
        frame.bullets.add(new Bullet(bX,bY,dir,frame,this.group));
    }

    public void die() {
        this.living=false;
    }
}
