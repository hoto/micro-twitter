import org.junit.Before;
import org.junit.Test;
import twitter.Timeline;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TimelineTest {
    private Timeline timeline;

    @Before
    public void beforeEach() {
        timeline = new Timeline();
    }

    @Test
    public void
    should_return_no_messages_when_user_posted_no_messages() {
        ArrayList<String> aliceMessages = timeline.view("Alice");
        assertTrue(aliceMessages.isEmpty());
    }

    @Test
    public void
    should_store_message_when_user_posts_a_message() {
        timeline.post("Alice", "I love the weather today.");
        assertEquals(timeline.view("Alice").get(0), "I love the weather today.");
    }

}
