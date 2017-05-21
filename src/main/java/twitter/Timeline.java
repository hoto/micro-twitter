package twitter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Collections.singletonList;

public class Timeline {
    private Map<String, List<Message>> timeline = new HashMap<>();
    private Clock clock;

    public Timeline(Clock clock) {
        this.clock = clock;
    }

    public List<Message> view(String user) {
        if (timeline.containsKey(user))
            return timeline.get(user);
        return new ArrayList<>();
    }

    public void post(String user, String text) {
        Message message = new Message(text, clock.millis());
        if (timeline.containsKey(user))
            timeline.get(user).add(message);
        else
            timeline.put(user, new ArrayList<>(singletonList(message)));
    }
}
