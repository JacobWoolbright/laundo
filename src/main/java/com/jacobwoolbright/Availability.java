package com.jacobwoolbright;

import java.util.Date;

public class Availability {
    private java.util.Date time;
    private Integer available;

    public Availability(java.util.Date time, Integer available) {
        this.time = time;
        this.available = available;
    }

    public Date getTime() {
        return time;
    }

    public Integer getAvailable() {
        return available;
    }
}