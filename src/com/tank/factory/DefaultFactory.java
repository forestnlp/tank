package com.tank.factory;

import com.tank.*;

public class DefaultFactory extends GameFactory {
    @Override
    public BaseExplode createExplode(int x, int y, TankFrame frame) {
        return new RectExplode(x, y, frame);
    }

    @Override
    public BaseTank createTank(int x, int y, DIR dir, TankFrame frame, Group group) {
        return new RectTank(x,y,dir,frame,group);
    }

    @Override
    public BaseBullet createBullet(int x, int y, DIR dir, TankFrame frame, Group group) {
        return new RectBullet(x, y, dir, frame, group);
    }
}
