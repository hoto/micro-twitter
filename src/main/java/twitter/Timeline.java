package twitter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Collections.singletonList;

public class Timeline {
    private Map<String, List<Message>> timeline = new HashMap<>();

    public List<Message> view(String user) {
        if (timeline.containsKey(user))
            return timeline.get(user);
        return new ArrayList<>();
    }

    public void post(String user, String message) {
        Message msg = new Message(message, 123456);
        if (timeline.containsKey(user))
            timeline.get(user).add(msg);
        else
            timeline.put(user, new ArrayList<>(singletonList(msg)));
    }
}
