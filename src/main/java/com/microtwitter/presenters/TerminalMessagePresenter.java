package com.microtwitter.presenters;

import com.microtwitter.messages.Message;
import com.microtwitter.time.Clock;
import com.microtwitter.time.RelativeTime;

public class TerminalMessagePresenter {
    private final Clock clock;
    private final RelativeTime relativeTime = new RelativeTime();

    public TerminalMessagePresenter(Clock clock) {
        this.clock = clock;
    }

    /**
     * Returns message in "<user_name> - <message> (<elapsed_time>)" format.
     */
    public String present(Message message) {
        String elapsedTime = relativeTime.toElapsedDuration(clock.millis() - message.timestamp);
        return String.format("%s - %s (%s)", message.userName, message.text, elapsedTime);
    }
}
