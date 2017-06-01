package com.microtwitter.commands;

import com.microtwitter.presenters.TimelineMessagePresenter;
import com.microtwitter.presenters.WallMessagePresenter;
import com.microtwitter.time.Clock;
import com.microtwitter.users.User;
import com.microtwitter.users.UserRepository;

public class CommandFactory {
    private final UserRepository userRepository;
    private final WallMessagePresenter wallMessagePresenter;
    private final TimelineMessagePresenter timelineMessagePresenter;

    public CommandFactory(UserRepository userRepository, Clock clock) {
        this.userRepository = userRepository;
        this.wallMessagePresenter = new WallMessagePresenter(clock);
        this.timelineMessagePresenter = new TimelineMessagePresenter(clock);
    }

    public Command create(String userName, String intent, String payload) {
        User user = userRepository.getOrCreate(userName);
        switch (intent) {
            case "wall":
                return new WallCommand(user, wallMessagePresenter);
            case "follows": {
                User folowee = userRepository.getOrCreate(payload);
                return new FollowCommand(user, folowee);
            }
            case "->":
                return new PostCommand(user, payload);
            default:
                return new ReadCommand(user, timelineMessagePresenter);
        }
    }
}
