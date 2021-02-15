package com.tank;

import java.awt.*;
import java.util.Random;
import java.util.UUID;

import static com.tank.DIR.UP;

public class Tank {

    private int x , y ;

    public static final int WIDTH = ResourceMgr.goodTankU.getWidth();
    public static final int HEIGHT = ResourceMgr.goodTankU.getHeight();

    private boolean living = true;
    private Group group;

    private UUID id = UUID.randomUUID();

    Rectangle rec = new Rectangle();

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

    public Tank(int x, int y, DIR dir, TankFrame frame,Group group) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.frame = frame;
        this.group = group;
        rec.x = this.x;
        rec.y = this.y;
        rec.width = WIDTH;
        rec.height = HEIGHT;
        if(this.getGroup() == Group.bad)
            fireStrategy = new DefaultFireStrategy();
        else
            fireStrategy = new FourDirFireStrategy();
    }

    public void paint(Graphics g) {
        if(!this.living) frame.tanks.remove(this);
        Color c = g.getColor();
        g.setColor(Color.YELLOW);
        g.drawString(id.toString(), this.x, this.y - 20);
        g.drawString("live=" + living, x, y-10);
        g.setColor(c);
        switch (dir){
            case LEFT:
                g.drawImage(this.group==Group.good?ResourceMgr.goodTankL:ResourceMgr.badTankL,x,y,null);
                break;
            case RIGHT:
                g.drawImage(this.group==Group.good?ResourceMgr.goodTankR:ResourceMgr.badTankR,x,y,null);
                break;
            case UP:
                g.drawImage(this.group==Group.good?ResourceMgr.goodTankU:ResourceMgr.badTankU,x,y,null);
                break;
            case DOWN:
                g.drawImage(this.group==Group.good?ResourceMgr.goodTankD:ResourceMgr.badTankD,x,y,null);
                break;
            default:
                break;
        }
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

        boundsCheck();
        rec.x = this.x;
        rec.y = this.y;
    }

    private void boundsCheck() {
        if(x<0) x=0;
        if(y<30) y=30;
        if(x>frame.GAME_WIDTH-Tank.WIDTH) x = frame.GAME_WIDTH-Tank.WIDTH;
        if(y>frame.GAME_HEIGHT-Tank.HEIGHT) y = frame.GAME_HEIGHT-Tank.HEIGHT;
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
        fireStrategy.fire(this);
    }

    public void die() {
        this.living=false;
    }

    public UUID getid() {
        return id;
    }
}
