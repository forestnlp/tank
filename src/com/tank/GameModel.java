package com.tank;

import java.awt.*;
import java.util.List;
import java.util.ArrayList;

import static com.tank.DIR.DOWN;
import static com.tank.DIR.UP;

public class GameModel {

    private Tank myTank = new Tank(200,400,UP,this,Group.good);

    List<GameObject> gameObjects = new ArrayList<>();


    ColliderChain colliderChain = new ColliderChain();


    public void add(GameObject o) {
        gameObjects.add(o);
    }

    public void remove(GameObject o){
        gameObjects.remove(o);
    }

    public GameModel() {
        int initTankcount = Integer.parseInt((String)PropertyMgr.get("init_tank_num"));

        for(int i=0;i<initTankcount;i++) {
            gameObjects.add(new Tank(100+70*i,100,DOWN,this,Group.bad));
        }
    }

    public void paint(Graphics g) {
        myTank.paint(g);

        for(int i=0;i<gameObjects.size();i++)
            gameObjects.get(i).paint(g);

        for(int i=0;i<gameObjects.size();i++) {
            for(int k=i+1;k<gameObjects.size();k++) {
                colliderChain.collide(gameObjects.get(i),gameObjects.get(k));
            }
        }

    }

    public Tank getMainTank() {
        return myTank;
    }
}
