package com.tank;

import static com.tank.DIR.DOWN;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        TankFrame frame = new TankFrame();

        int initTankcount = Integer.parseInt((String)PropertyMgr.get("init_tank_num"));

        for(int i=0;i<initTankcount;i++) {
            frame.tanks.add(new Tank(100+50*i,100,DOWN,frame,Group.bad));
        }

        new Thread(()->{
            new Audio("audio/war1.wav").loop();
        }).start();

        while (true){
            Thread.sleep(50);
            frame.repaint();
        }
    }
}
