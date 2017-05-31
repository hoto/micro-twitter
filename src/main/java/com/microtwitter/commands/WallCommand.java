package com.microtwitter.commands;

import com.microtwitter.presenters.MessagePresenter;
import com.microtwitter.users.User;

import java.io.PrintStream;

public class WallCommand implements Command {
    private final User user;
    private final MessagePresenter presenter;

    public WallCommand(User user, MessagePresenter presenter) {
        this.user = user;
        this.presenter = presenter;
    }

    @Override
    public void execute(PrintStream out) {
        user.wall()
            .stream()
            .map(presenter::present)
            .forEach(out::println);
    }
}
