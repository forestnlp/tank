package com.tank;

import java.awt.*;
import java.util.Random;

import static com.tank.DIR.UP;

public class Explode {

    private int x , y ;

    public static final int WIDTH = ResourceMgr.explodes[0].getWidth();
    public static final int HEIGHT = ResourceMgr.explodes[0].getHeight();

    private boolean living = true;

    TankFrame frame;

    private  int step = 0;

    private Random random = new Random();
    public Explode(int x, int y, TankFrame frame) {
        this.x = x;
        this.y = y;
        this.frame = frame;
    }

    public void paint(Graphics g) {
        g.drawImage(ResourceMgr.explodes[step++],x,y,null);
        if(step>=ResourceMgr.explodes.length) step=0;
    }

}
