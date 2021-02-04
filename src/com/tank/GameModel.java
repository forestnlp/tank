package com.tank;

import java.awt.*;
import java.util.List;
import java.util.ArrayList;

import static com.tank.DIR.DOWN;
import static com.tank.DIR.UP;

public class GameModel {

    private static  GameModel INSTANCE =  new GameModel();

    static {
        INSTANCE.init();
    }

    Tank myTank;

    public static GameModel getInstance (){
        return INSTANCE;
    }


    List<GameObject> gameObjects = new ArrayList<>();

    ColliderChain colliderChain = new ColliderChain();


    public void add(GameObject o) {
        gameObjects.add(o);
    }

    public void remove(GameObject o){
        gameObjects.remove(o);
    }

    private GameModel() {

    }

    private  void init(){

        int initTankcount = Integer.parseInt((String)PropertyMgr.get("init_tank_num"));

        myTank =  new Tank(200,400,UP,Group.good);

        for(int i=0;i<initTankcount;i++) {
            new Tank(100+70*i,100,DOWN,Group.bad);
        }

        new Wall(350,250,200,50);
        new Wall(400,400,50,200);

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
