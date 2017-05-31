package com.microtwitter.users;

import com.microtwitter.time.Clock;

import java.util.HashMap;
import java.util.Map;

public class UserRepository {
    private final Clock clock;
    private Map<String, User> users = new HashMap<>();

    public UserRepository(Clock clock) {
        this.clock = clock;
    }

    public User getOrCreate(String name) {
        return users.containsKey(name) ? users.get(name) : newUser(name);
    }

    private User newUser(String name) {
        User user = new User(name, clock);
        users.put(name, user);
        return user;
    }
}
