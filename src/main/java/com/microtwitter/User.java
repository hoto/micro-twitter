package com.microtwitter;

import com.microtwitter.time.Clock;
import com.microtwitter.time.SystemClock;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class User {
    private final String name;
    private final List<Message> messages = new ArrayList<>();
    private final List<User> followees = new ArrayList<>();
    private final Clock clock;

    public User(String name) {
        this.name = name;
        this.clock = new SystemClock();
    }

    protected User(String name, Clock clock) {
        this.name = name;
        this.clock = clock;
    }

    public List<Message> timeline() {
        return messages;
    }

    public void post(String text) {
        messages.add(new Message(this, text, clock.millis()));
    }

    public List<Message> wall() {
        List<Message> wall = new ArrayList<>();
        wall.addAll(messages);
        wall.addAll(getFolloweesMessages());
        Collections.sort(wall);
        return wall;
    }

    private List<Message> getFolloweesMessages() {
        return followees
                .stream()
                .map(User::timeline)
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

    public void follow(User user) {
        followees.add(user);
    }
}
