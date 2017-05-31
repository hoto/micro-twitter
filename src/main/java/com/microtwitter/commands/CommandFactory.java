package com.microtwitter.commands;

import com.microtwitter.presenters.MessagePresenter;
import com.microtwitter.users.User;
import com.microtwitter.users.UserRepository;

public class CommandFactory {
    private final UserRepository userRepository;
    private final MessagePresenter presenter;

    public CommandFactory(UserRepository userRepository, MessagePresenter presenter) {
        this.userRepository = userRepository;
        this.presenter = presenter;
    }

    public Command create(String userName, String intent, String payload) {
        User user = userRepository.getOrCreate(userName);
        switch (intent) {
            case "wall":
                return new WallCommand(user, presenter);
            case "follows": {
                User folowee = userRepository.getOrCreate(payload);
                return new FollowCommand(user, folowee);
            }
            case "->":
                return new PostCommand(user, payload);
            default:
                return new ReadCommand(user, presenter);
        }
    }
}
