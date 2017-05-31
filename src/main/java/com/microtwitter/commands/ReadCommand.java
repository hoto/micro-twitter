package com.microtwitter.commands;

import com.microtwitter.presenters.ConsoleMessagePresenter;
import com.microtwitter.users.User;

import java.io.PrintStream;

public class ReadCommand implements Command {
    private final User user;
    private final ConsoleMessagePresenter presenter;

    public ReadCommand(User user, ConsoleMessagePresenter presenter) {
        this.user = user;
        this.presenter = presenter;
    }

    @Override
    public void execute(PrintStream out) {
        user.timeline()
            .stream()
            .map(presenter::present)
            .forEach(out::println);
    }
}
