package com.microtwitter.commands;

import com.microtwitter.presenters.ConsoleMessagePresenter;
import com.microtwitter.time.SystemClock;
import com.microtwitter.users.User;

public class CommandFactory {

    public Command create(String userName, String intent, String payload) {
        //TODO: clean this up
        SystemClock clock = new SystemClock();
        ConsoleMessagePresenter presenter = new ConsoleMessagePresenter(clock);
        User user = new User(userName);
        switch (intent) {
            case "wall":
                return new WallCommand(user, presenter);
            case "follows":
                return new FollowCommand(user, user);
            case "->":
                return new PostCommand(user, payload);
            default:
                return new ReadCommand(user, presenter);
        }
    }
}
