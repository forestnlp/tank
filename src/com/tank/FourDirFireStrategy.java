package com.tank;

public class FourDirFireStrategy implements FireStrategy{
    @Override
    public void fire(Tank tank) {
        int bX = tank.getX() + Tank.WIDTH/2- Bullet.WIDTH/2;
        int bY = tank.getY() + Tank.HEIGHT/2-Bullet.HEIGHT/2;

        for(DIR dir:DIR.values()) {
            GameModel.getInstance().add(new Bullet(bX,bY,dir,tank.getGroup()));
        }

        if(tank.getGroup()== Group.good)
            new Thread(()->{
                new Audio("audio/tank_fire.wav").play();
            }).start();

    }
}
