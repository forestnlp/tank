package com.tank;

import com.tank.factory.BaseBullet;
import com.tank.factory.BaseTank;

public class FourDirFireStrategy implements FireStrategy{
    @Override
    public void fire(BaseTank tank) {
        int bX = tank.getX() + Tank.WIDTH/2-Bullet.WIDTH/2;
        int bY = tank.getY() + Tank.HEIGHT/2-Bullet.HEIGHT/2;

        for(DIR dir:DIR.values()) {
           tank.frame.factory.createBullet(tank.getX(), tank.getY(), dir, tank.frame, tank.getGroup());
        }

        if(tank.getGroup()==Group.good)
            new Thread(()->{
                new Audio("audio/tank_fire.wav").play();
            }).start();

    }
}
