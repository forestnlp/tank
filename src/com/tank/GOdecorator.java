package com.tank;

import java.awt.*;

public class GOdecorator extends GameObject{


    GameObject go;

    public GOdecorator(GameObject go){
        this.go = go;
    }

    @Override
    public void paint(Graphics g) {
        go.paint(g);
    }

    @Override
    public int getWidth() {
        return go.getWidth();
    }

    @Override
    public int getHeight() {
        return go.getHeight();
    }
}
