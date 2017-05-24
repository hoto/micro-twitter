package com.microtwitter;


public class Message {
    public final User user;
    public final String text;
    public final long timestamp;

    Message(User user, String text, long timestamp) {
        this.user = user;
        this.text = text;
        this.timestamp = timestamp;
    }
}
