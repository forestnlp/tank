package com.tank;

import com.tank.factory.BaseExplode;

import java.awt.*;

public class RectExplode extends BaseExplode {

    private int x , y ;

    public static final int WIDTH = ResourceMgr.explodes[0].getWidth();
    public static final int HEIGHT = ResourceMgr.explodes[0].getHeight();

    TankFrame frame;

    private  int step = 0;

    public RectExplode(int x, int y, TankFrame frame) {
        this.x = x;
        this.y = y;
        this.frame = frame;
        new Thread(()->new Audio("audio/explode.wav").play()).start();
    }

    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.RED);
        g.fillRect(x,y,10*step,10*step);
        step++;
        if(step>=15)
            frame.explodes.remove(this);

        g.setColor(c);
    }

}
