package twitter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Timeline {
    private Map<String, ArrayList<String>> timeline = new HashMap<>();

    public List<String> view(String user) {
        if (timeline.containsKey(user)) return timeline.get(user);
        return new ArrayList<>();
    }

    public void post(String user, String message) {
        ArrayList<String> messages;
        if (timeline.get(user) == null)
            messages = new ArrayList<>();
        else
            messages = timeline.get(user);
        messages.add(message);
        timeline.put(user, messages);
    }
}
