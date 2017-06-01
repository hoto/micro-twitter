package com.microtwitter.commands;

import com.microtwitter.io.Console;
import com.microtwitter.users.User;

public class FollowCommand implements Command {
    private final User user;
    private final User folowee;

    public FollowCommand(User user, User folowee) {
        this.user = user;
        this.folowee = folowee;
    }

    @Override
    public void execute(Console out) {
        user.follow(folowee);
    }
}
