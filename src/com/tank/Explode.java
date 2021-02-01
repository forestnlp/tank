package com.tank;

import com.tank.factory.BaseExplode;

import java.awt.*;
import java.util.Random;

import static com.tank.ResourceMgr.expoldeAudio;

public class Explode extends BaseExplode {

    private int x , y ;

    public static final int WIDTH = ResourceMgr.explodes[0].getWidth();
    public static final int HEIGHT = ResourceMgr.explodes[0].getHeight();

    TankFrame frame;

    private  int step = 0;

    public Explode(int x, int y, TankFrame frame) {
        this.x = x;
        this.y = y;
        this.frame = frame;
        new Thread(()->new Audio("audio/explode.wav").play()).start();
    }

    public void paint(Graphics g) {
        int eX = this.x + Tank.WIDTH/2-Explode.WIDTH/2;
        int eY = this.y + Tank.HEIGHT/2-Explode.HEIGHT/2;

        g.drawImage(ResourceMgr.explodes[step++],eX,eY,null);
        if(step>=ResourceMgr.explodes.length)
            frame.explodes.remove(this);
    }

}
