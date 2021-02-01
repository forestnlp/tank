package com.tank.factory;

import com.tank.TankFrame;

public abstract class GameFactory {
    public abstract BaseExplode createExplode(int x, int y, TankFrame frame);
    public abstract BaseTank createTank();
    public abstract BaseBullet createBullet();
}
