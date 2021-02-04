package com.tank;

import java.awt.*;

public class RectDecorator extends GOdecorator{

    public RectDecorator(GameObject go) {
        super(go);
    }

    @Override
    public void paint(Graphics g) {
        go.paint(g);

        Color c = g.getColor();

        g.setColor(Color.YELLOW);

        g.drawRect(go.x,go.y,getWidth(),getHeight());

        g.setColor(c);
    }
}
