package com.microtwitter.presenters;

import com.microtwitter.messages.Message;
import com.microtwitter.time.Clock;
import com.microtwitter.time.RelativeTime;

public class TimelineMessagePresenter implements MessagePresenter {
    private final Clock clock;
    private final RelativeTime relativeTime = new RelativeTime();

    public TimelineMessagePresenter(Clock clock) {
        this.clock = clock;
    }

    /**
     * Returns message in "<message> (<elapsed_time>)" format.
     */
    public String present(Message message) {
        String elapsedTime = relativeTime.toElapsedDuration(clock.millis() - message.timestamp);
        return String.format("%s (%s)", message.text, elapsedTime);
    }
}
