package com.tank;

import static com.tank.DIR.DOWN;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        TankFrame frame = new TankFrame();


        for(int i=0;i<20;i++) {
            frame.tanks.add(new Tank(100+50*i,100,DOWN,frame,Group.bad));
        }

        while (true){
            Thread.sleep(50);
            frame.repaint();
        }
    }
}
