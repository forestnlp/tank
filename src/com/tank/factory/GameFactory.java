package com.tank.factory;

import com.tank.DIR;
import com.tank.Group;
import com.tank.TankFrame;

public abstract class GameFactory {
    public abstract BaseExplode createExplode(int x, int y, TankFrame frame);
    public abstract BaseTank createTank(int x, int y, DIR dir, TankFrame frame, Group group);
    public abstract BaseBullet createBullet(int x, int y, DIR dir, TankFrame frame, Group group);
}
