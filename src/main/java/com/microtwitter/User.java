package com.microtwitter;

import com.microtwitter.time.Clock;

import java.util.ArrayList;
import java.util.List;

public class User {
    private List<Message> messages = new ArrayList<>();
    private final Clock clock;

    public User(String name) {
        this.clock = new Clock();
    }

    protected User(String name, Clock clock) {
        this.clock = clock;
    }

    public List<Message> timeline() {
        return messages;
    }

    public void post(String text) {
        messages.add(new Message(this, text, clock.millis()));
    }

    public List<Message> wall() {
        return messages;
    }

    public void follow(User user) {

    }
}
