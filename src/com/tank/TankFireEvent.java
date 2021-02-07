package com.tank;

import java.util.Date;

public class TankFireEvent {

    private Date eventTime;

    private Tank source;

    public TankFireEvent(Date eventTime, Tank source) {
        this.eventTime = eventTime;
        this.source = source;
    }

    public Date getEventTime() {
        return eventTime;
    }

    public Tank getSource() {
        return source;
    }
}
