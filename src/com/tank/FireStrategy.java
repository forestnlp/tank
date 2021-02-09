package com.tank;


import java.io.Serializable;

public interface FireStrategy extends Serializable {
    public void fire(Tank tank);

}
