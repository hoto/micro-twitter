package com.microtwitter;

public class Message implements Comparable<Message> {
    public final User user;
    public final String text;
    public final long timestamp;

    public Message(User user, String text, long timestamp) {
        this.user = user;
        this.text = text;
        this.timestamp = timestamp;
    }

    @Override
    public int compareTo(Message anotherMessage) {
        return this.timestamp >= anotherMessage.timestamp ? 1 : -1;
    }
}
