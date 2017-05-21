package com.microtwitter;

public class Message {
    public final String text;
    public final long timestamp;

    Message(String text, long timestamp) {
        this.text = text;
        this.timestamp = timestamp;
    }
}
