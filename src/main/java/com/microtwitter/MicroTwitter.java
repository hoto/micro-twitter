package com.microtwitter;

import com.microtwitter.time.Clock;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Collections.singletonList;

public class MicroTwitter {
    private Clock clock;
    private Map<String, List<Message>> timelines = new HashMap<>();
    private Map<String, List<String>> followees = new HashMap<>();

    public MicroTwitter(Clock clock) {
        this.clock = clock;
    }

    public void post(String user, String text) {
        Message message = new Message(text, clock.millis());
        if (userHasMessages(user))
            timelines.get(user).add(message);
        else
            timelines.put(user, new ArrayList<>(singletonList(message)));
    }

    public List<Message> wall(String user) {
        List<Message> wall = read(user);
        if (userHasFollowees(user)) {
            wall.addAll(getFolloweesMessages(user));
        }
        return wall;
    }

    private List<Message> getFolloweesMessages(String user) {
        return followees
                .get(user)
                .stream()
                .map(this::read)
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

    public List<Message> read(String user) {
        if (userHasMessages(user))
            return timelines.get(user);
        return new ArrayList<>();
    }

    private boolean userHasMessages(String user) {
        return timelines.containsKey(user);
    }

    public void follow(String user, String followee) {
        if (userHasFollowees(user))
            followees.get(user).add(followee);
        else
            followees.put(user, new ArrayList<>(singletonList(followee)));
    }

    private boolean userHasFollowees(String user) {
        return followees.containsKey(user);
    }
}
