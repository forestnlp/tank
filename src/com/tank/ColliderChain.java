package com.tank;

import java.util.LinkedList;
import java.util.List;

public class ColliderChain implements Collider{

    private List<Collider> colliders;

    public ColliderChain() {
        colliders = new LinkedList<>();
        add(new BulletTankCollider());
        add(new BulletWallCollider());
        add(new TankTankCollider());
        add(new TankWallCollider());
    }

    //链条可以和链条进行组合
    public void add(Collider o) {
        colliders.add(o);
    }

    public boolean collide(GameObject o1, GameObject o2) {
        for (Collider collider : colliders) {
            if(!collider.collide(o1, o2)) return false;
        }
        return true;
    }

}
