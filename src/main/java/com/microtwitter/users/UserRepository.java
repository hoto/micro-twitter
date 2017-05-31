package com.microtwitter.users;

import java.util.HashMap;
import java.util.Map;

public class UserRepository {
    private Map<String, User> users = new HashMap<>();

    public User getOrCreate(String name) {
        return users.containsKey(name) ? users.get(name) : newUser(name);
    }

    private User newUser(String name) {
        User user = new User(name);
        users.put(name, user);
        return user;
    }
}
