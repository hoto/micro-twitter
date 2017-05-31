package com.microtwitter.commands;

import com.microtwitter.users.User;

import java.io.PrintStream;

public class FollowCommand implements Command {
    private final User user;
    private final User folowee;

    public FollowCommand(User user, User folowee) {
        this.user = user;
        this.folowee = folowee;
    }

    @Override
    public void execute(PrintStream out) {
        user.follow(folowee);
    }
}
