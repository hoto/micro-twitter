package com.microtwitter.messages;

public class Message implements Comparable<Message> {
    public final String userName;
    public final String text;
    public final long timestamp;

    public Message(String userName, String text, long timestamp) {
        this.userName = userName;
        this.text = text;
        this.timestamp = timestamp;
    }

    @Override
    public int compareTo(Message anotherMessage) {
        return this.timestamp >= anotherMessage.timestamp ? 1 : -1;
    }
}
