package com.microtwitter.commands;

import com.microtwitter.io.Console;
import com.microtwitter.users.User;

public class PostCommand implements Command {
    private final User user;
    private final String message;

    public PostCommand(User user, String message) {
        this.user = user;
        this.message = message;
    }

    @Override
    public void execute(Console console) {
        user.post(message);
    }
}
