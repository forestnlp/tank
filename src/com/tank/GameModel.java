package com.tank;

import java.awt.*;
import java.util.LinkedList;

import static com.tank.DIR.DOWN;
import static com.tank.DIR.UP;

public class GameModel {

    private Tank myTank = new Tank(200,400,UP,this,Group.good);

    java.util.List<Tank> tanks = new LinkedList<>();
    java.util.List<Bullet> bullets = new LinkedList<>();
    java.util.List<Explode> explodes = new LinkedList<>();


    public GameModel() {
        int initTankcount = Integer.parseInt((String)PropertyMgr.get("init_tank_num"));

        for(int i=0;i<initTankcount;i++) {
            tanks.add(new Tank(100+50*i,100,DOWN,this,Group.bad));
        }
    }


    public void paint(Graphics g) {
        myTank.paint(g);
        Color c = g.getColor();
        g.setColor(Color.red);
        g.drawString("bullets:"+bullets.size()+",tanks:"+tanks.size()+",explodes:"+explodes.size(),100,100);
        g.setColor(c);
        for(int i=0;i<bullets.size();i++)
            bullets.get(i).paint(g);

        for(int i=0;i<tanks.size();i++)
            tanks.get(i).paint(g);

        for(int i=0;i<bullets.size();i++)
            for(int k=0;k<tanks.size();k++)
                bullets.get(i).collideWith(tanks.get(k));

        for(int i=0;i<explodes.size();i++)
            explodes.get(i).paint(g);
    }

    public Tank getMainTank() {
        return myTank;
    }
}
