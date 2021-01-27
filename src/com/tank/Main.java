package com.tank;

import static com.tank.DIR.DOWN;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        TankFrame frame = new TankFrame();

        for(int i=0;i<5;i++)
            frame.tanks.add(new Tank(100+50*i,100,DOWN,frame));

        while (true){
            Thread.sleep(50);
            frame.repaint();
        }
    }
}
