package twitter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Collections.singletonList;

public class Timeline {
    private Map<String, List<String>> timeline = new HashMap<>();

    public List<String> view(String user) {
        if (timeline.containsKey(user)) return timeline.get(user);
        return new ArrayList<>();
    }

    public void post(String user, String message) {
        if (timeline.containsKey(user))
            timeline.get(user).add(message);
        else
            timeline.put(user, new ArrayList<>(singletonList(message)));
    }
}
