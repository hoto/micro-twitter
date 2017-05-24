package com.microtwitter.time;

public class FixedClock implements Clock {
    private long timestamp;

    @Override
    public long millis() {
        return timestamp;
    }

    public void setMillis(long timestamp) {
        this.timestamp = timestamp;
    }
}