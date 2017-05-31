package com.microtwitter.commands;

import com.microtwitter.users.User;

public class CommandFactory {

    public Command create(String userName, String intent, String payload) {
        switch (intent) {
            case "wall":
                return new WallCommand();
            case "follows":
                return new FollowCommand(new User(userName), new User(userName));
            case "->":
                return new PostCommand();
            default:
                return new ReadCommand(new User(userName));
        }
    }
}
