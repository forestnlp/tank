package com.tank;

import java.awt.*;
import java.util.Random;

import static com.tank.ResourceMgr.expoldeAudio;

public class Explode {

    private int x , y ;

    public static final int WIDTH = ResourceMgr.explodes[0].getWidth();
    public static final int HEIGHT = ResourceMgr.explodes[0].getHeight();

    TankFrame frame;

    private  int step = 0;

    public Explode(int x, int y, TankFrame frame) {
        this.x = x;
        this.y = y;
        this.frame = frame;
        //expoldeAudio.play();
    }

    public void paint(Graphics g) {
        g.drawImage(ResourceMgr.explodes[step++],x,y,null);
        if(step>=ResourceMgr.explodes.length)
            frame.explodes.remove(this);
    }

}
