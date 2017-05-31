package com.microtwitter.commands;

import com.microtwitter.users.User;

public class CommandFactory {

    public Command create(String user, String intent, String payload) {
        switch (intent) {
            case "wall":
                return new WallCommand();
            case "follows":
                return new FollowCommand();
            case "->":
                return new PostCommand();
            default:
                return new ReadCommand(new User(user));
        }
    }
}
