package com.tank;

import java.awt.*;

public class TailDecorator extends GOdecorator{

    public TailDecorator(GameObject go) {
        super(go);
    }

    @Override
    public void paint(Graphics g) {
        go.paint(g);

        Color c = g.getColor();

        g.setColor(Color.RED);

        g.drawOval(go.x,go.y,go.getWidth(),go.getHeight());

        g.setColor(c);
    }

}
