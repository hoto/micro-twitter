package com.microtwitter.users;

import com.microtwitter.messages.Message;
import com.microtwitter.time.Clock;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class User {
    public final String name;
    private final List<Message> messages = new ArrayList<>();
    private final List<User> followees = new ArrayList<>();
    private final Clock clock;

    public User(String name, Clock clock) {
        this.name = name;
        this.clock = clock;
    }

    public List<Message> timeline() {
        return messages;
    }

    public void post(String text) {
        messages.add(new Message(name, text, clock.millis()));
        Collections.sort(messages);
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
        if (name.equals(user.name)) return;
        followees.add(user);
    }
}
