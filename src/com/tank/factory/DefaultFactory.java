package com.tank.factory;

import com.tank.Explode;
import com.tank.RectExplode;
import com.tank.TankFrame;

public class DefaultFactory extends GameFactory {
    @Override
    public BaseExplode createExplode(int x, int y, TankFrame frame) {
        return new Explode(x,y,frame);
    }

    @Override
    public BaseTank createTank() {
        return null;
    }

    @Override
    public BaseBullet createBullet() {
        return null;
    }
}
