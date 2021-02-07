package com.tank;

public class TankFireLogObserver implements TankFireObserver {
    @Override
    public void handle(TankFireEvent event) {
        System.out.println("logevent:"+ event.getSource().toString());
    }
}
