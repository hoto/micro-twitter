package com.microtwitter.commands;

import com.microtwitter.presenters.ConsoleMessagePresenter;
import com.microtwitter.time.SystemClock;
import com.microtwitter.users.User;

public class CommandFactory {

    public Command create(String userName, String intent, String payload) {
        switch (intent) {
            case "wall":
                return new WallCommand(new User(userName),
                    new ConsoleMessagePresenter(new SystemClock()));
            case "follows":
                return new FollowCommand(new User(userName), new User(userName));
            case "->":
                return new PostCommand(new User(userName), payload);
            default:
                return new ReadCommand(new User(userName));
        }
    }
}
