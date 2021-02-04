package com.tank;

public class BulletWallCollider implements Collider {
    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        if ((o1 instanceof Bullet) && (o2 instanceof Wall)) {

            Bullet b = (Bullet)o1;
            Wall w = (Wall)o2;
            if(b.rec.intersects(w.rec)){
                b.die();
            }
            return true;

        } else if (o1 instanceof Wall && o2 instanceof Bullet) {
            return collide(o2, o1);
        }
        return true;
    }
}
