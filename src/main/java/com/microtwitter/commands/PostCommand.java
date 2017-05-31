package com.microtwitter.commands;

import com.microtwitter.users.User;

import java.io.PrintStream;

public class PostCommand implements Command {
    private final User user;
    private final String message;

    public PostCommand(User user, String message) {
        this.user = user;
        this.message = message;
    }

    @Override
    public void execute(PrintStream out) {
        user.post(message);
    }
}
