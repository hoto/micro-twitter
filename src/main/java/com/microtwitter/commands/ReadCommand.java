package com.microtwitter.commands;

import com.microtwitter.io.Console;
import com.microtwitter.presenters.MessagePresenter;
import com.microtwitter.users.User;

public class ReadCommand implements Command {
    private final User user;
    private final MessagePresenter presenter;

    public ReadCommand(User user, MessagePresenter presenter) {
        this.user = user;
        this.presenter = presenter;
    }

    @Override
    public void execute(Console out) {
        user.timeline()
            .stream()
            .map(presenter::present)
            .forEach(out::writeOutput);
    }
}
