package com.tank;

import java.awt.*;

public class Bullet {
    int x, y;
    public static final int speed = 25;
    public static final int WIDTH = 20, HEIGHT = 20;
    DIR dir = DIR.UP;
    TankFrame frame = null;

    boolean isAlive = true;

    public Bullet(int x, int y, DIR dir,TankFrame frame) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.frame = frame;
    }

    public void paint(Graphics g) {
        if(!isAlive) frame.bullets.remove(this);
        Color c = g.getColor();
        g.setColor(Color.red);
        switch (dir){
            case LEFT:
                g.drawImage(ResourceMgr.bulletl,x,y,null);
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.bulletr,x,y,null);
                break;
            case UP:
                g.drawImage(ResourceMgr.bulletu,x,y,null);
                break;
            case DOWN:
                g.drawImage(ResourceMgr.bulletd,x,y,null);
                break;
            default:
                break;
        }        g.setColor(c);
        move();
    }

    private void move() {
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

        if(x<0||y<0||x>frame.GAME_WIDTH||y>frame.GAME_HEIGHT) isAlive=false;
    }
}

