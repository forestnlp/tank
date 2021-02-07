package com.tank;

public class TankFireConcreteObserver implements TankFireObserver {
    @Override
    public void handle(TankFireEvent event) {
        System.out.println(event.getEventTime());
        Tank source = event.getSource();
        source.fire();
    }
}
