package com.tank;

public class TankWallCollider implements Collider {
    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        if ((o1 instanceof Tank) && (o2 instanceof Wall)) {

            Tank t = (Tank)o1;
            Wall w = (Wall)o2;
            if(t.rec.intersects(w.rec)){
                t.back();
            }
            return true;

        } else if (o1 instanceof Wall && o2 instanceof Tank) {
            return collide(o2, o1);
        }
        return true;
    }
}
