package twitter;

import java.util.ArrayList;

public class Timeline {
    private ArrayList<String> timeline = new ArrayList<>();

    public ArrayList<String> view(String user) {
        return timeline;
    }

    public void post(String user, String message) {
        timeline = new ArrayList<>();
        timeline.add(message);
    }
}
