package com.tank.factory;


import com.tank.DIR;
import com.tank.Group;
import com.tank.TankFrame;

import java.awt.*;

public abstract class BaseTank {

    protected Group group = Group.bad;

    public Rectangle rec = new Rectangle();

    public TankFrame frame;
    public DIR dir;

    public abstract Group getGroup();

    public abstract void paint(Graphics g);

    public abstract void die();

    public abstract int getX();

    public abstract int getY();

}
