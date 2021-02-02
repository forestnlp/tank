package com.tank.factory;

import com.tank.Tank;

import java.awt.*;

public abstract class BaseBullet {
    public abstract void collideWith(BaseTank tank);
    public abstract void paint(Graphics g);
}
