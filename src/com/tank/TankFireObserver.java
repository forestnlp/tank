package com.tank;

import java.io.Serializable;

public interface TankFireObserver extends Serializable {

    void handle(TankFireEvent event);

}
