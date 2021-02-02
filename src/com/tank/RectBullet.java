package com.tank;

import com.tank.factory.BaseBullet;
import com.tank.factory.BaseTank;

import java.awt.*;

import static com.tank.DIR.UP;

public class RectBullet extends BaseBullet {
    int x, y;
    public static final int speed = 20;
    public static final int WIDTH = ResourceMgr.bulletU.getWidth();
    public static final int HEIGHT = ResourceMgr.bulletU.getHeight();
    private Group group;
    DIR dir = UP;
    TankFrame frame = null;

    Rectangle rec = new Rectangle();

    boolean living = true;

    public RectBullet(int x, int y, DIR dir, TankFrame frame, Group group) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.frame = frame;
        this.group = group;
        rec.x = this.x;
        rec.y = this.y;
        rec.width = WIDTH;
        rec.height = HEIGHT;
        frame.bullets.add(this);
    }

    public void paint(Graphics g) {
        if(!living) frame.bullets.remove(this);
        Color c = g.getColor();
        g.setColor(Color.BLUE);
        g.fillRect(x,y,20,20);
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
        rec.x = this.x;
        rec.y = this.y;
        if(x<0||y<0||x>frame.GAME_WIDTH||y>frame.GAME_HEIGHT)
            die();
    }

    public void collideWith(BaseTank tank) {
        if(this.group==tank.getGroup()) return;

        //Rectangle 单例优化
        if(rec.intersects(tank.rec)) {
            tank.die();
            this.die();
            frame.explodes.add(frame.factory.createExplode(tank.getX(),tank.getY(),frame));
        }
    }

    private void die() {
        this.living=false;
    }
}

