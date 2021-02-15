package com.tank;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        TankFrame frame = TankFrame.INSTANCE;
        frame.setVisible(true);

//        int initTankcount = Integer.parseInt((String)PropertyMgr.get("init_tank_num"));
//
//        for(int i=0;i<initTankcount;i++) {
//            frame.tanks.add(new Tank(100+50*i,100,DOWN,frame,Group.bad));
//        }

        new Thread(()->{
            new Audio("audio/war1.wav").loop();
        }).start();

        new Thread(()->{
            while (true){
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                frame.repaint();
            }
        }).start();

        Client c = Client.INSTANCE;
        c.connect();
    }
}
