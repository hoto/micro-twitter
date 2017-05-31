package com.microtwitter.commands;

import com.microtwitter.presenters.ConsoleMessagePresenter;
import com.microtwitter.time.Clock;
import com.microtwitter.time.SystemClock;
import com.microtwitter.users.User;

import java.io.PrintStream;

public class ReadCommand implements Command {
    private final User user;
    private final ConsoleMessagePresenter presenter;

    public ReadCommand(User user) {
        this.user = user;
        presenter = new ConsoleMessagePresenter(new SystemClock());
    }

    public ReadCommand(User user, Clock clock) {
        this.user = user;
        presenter = new ConsoleMessagePresenter(clock);
    }

    @Override
    public void execute(PrintStream out) {
        user.timeline()
            .stream()
            .map(presenter::present)
            .forEach(out::println);
    }
}
