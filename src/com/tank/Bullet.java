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
    GameModel gm;

    Rectangle rec = new Rectangle();

    boolean living = true;

    public Bullet(int x, int y, DIR dir,GameModel gm,Group group) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.gm = gm;
        this.group = group;
        rec.x = this.x;
        rec.y = this.y;
        rec.width = WIDTH;
        rec.height = HEIGHT;
    }

    public void paint(Graphics g) {
        if(!living) gm.bullets.remove(this);
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
        rec.x = this.x;
        rec.y = this.y;
        if(x<0||y<0||x>TankFrame.GAME_WIDTH||y>TankFrame.GAME_HEIGHT)
            die();
    }

    public void collideWith(Tank tank) {
        if(this.group==tank.getGroup()) return;
        //Rectangle 单例优化
        if(rec.intersects(tank.rec)) {
            tank.die();
            this.die();
            gm.explodes.add(new Explode(x,y,gm));
        }
    }

    private void die() {
        this.living=false;
    }
}

