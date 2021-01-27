package com.tank;

import java.awt.*;

import static com.tank.DIR.UP;

public class Bullet {
    int x, y;
    public static final int speed = 20;
    public static final int WIDTH = ResourceMgr.bulletU.getWidth();
    public static final int HEIGHT = ResourceMgr.bulletU.getHeight();
    private Group group;
    DIR dir = UP;
    TankFrame frame = null;

    boolean living = true;

    public Bullet(int x, int y, DIR dir,TankFrame frame,Group group) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.frame = frame;
        this.group = group;
    }

    public void paint(Graphics g) {
        if(!living) frame.bullets.remove(this);
        Color c = g.getColor();
        g.setColor(Color.red);
        switch (dir){
            case LEFT:
                g.drawImage(ResourceMgr.bulletL,x,y,null);
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.bulletR,x,y,null);
                break;
            case UP:
                g.drawImage(ResourceMgr.bulletU,x,y,null);
                break;
            case DOWN:
                g.drawImage(ResourceMgr.bulletD,x,y,null);
                break;
            default:
                break;
        }
        g.setColor(c);
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

        if(x<0||y<0||x>frame.GAME_WIDTH||y>frame.GAME_HEIGHT) living =false;
    }

    public void collideWith(Tank tank) {
        if(this.group==tank.getGroup()) return;
        //Rectangle 单例优化
        Rectangle rec1 = new Rectangle(this.x,this.y,WIDTH,HEIGHT);
        Rectangle rec2 = new Rectangle(tank.getX(),tank.getY(),Tank.WIDTH,Tank.HEIGHT);
        if(rec1.intersects(rec2)) {
            tank.die();
            this.die();
            frame.explodes.add(new Explode(x,y,frame));
        }
    }

    private void die() {
        this.living=false;
    }
}

