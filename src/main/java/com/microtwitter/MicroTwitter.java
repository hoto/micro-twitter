package com.microtwitter;

import com.microtwitter.time.Clock;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Collections.singletonList;

public class MicroTwitter {
    private Clock clock;
    private Map<String, List<Message>> timelines = new HashMap<>();

    public MicroTwitter(Clock clock) {
        this.clock = clock;
    }

    public List<Message> read(String user) {
        if (timelines.containsKey(user))
            return timelines.get(user);
        return new ArrayList<>();
    }

    public void post(String user, String text) {
        Message message = new Message(text, clock.millis());
        if (timelines.containsKey(user))
            timelines.get(user).add(message);
        else
            timelines.put(user, new ArrayList<>(singletonList(message)));
    }

    public List<Message> wall(String user) {
        return this.read(user);
    }
}
