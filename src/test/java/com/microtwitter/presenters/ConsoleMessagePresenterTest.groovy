package com.microtwitter.presenters

import com.microtwitter.messages.Message
import com.microtwitter.time.Clock
import com.microtwitter.time.FixedClock
import spock.lang.Specification
import spock.lang.Unroll

import static java.util.concurrent.TimeUnit.*

class ConsoleMessagePresenterTest extends Specification {

    @Unroll
    def 'should return message in "<user_name> - <message> (<elapsed_time>)" format'() {
        given:
        Clock clock = new FixedClock()
        ConsoleMessagePresenter presenter = new ConsoleMessagePresenter(clock)
        Message message = new Message(user, text, 0)

        and:
        clock.setMillis(elapsedTime)

        expect:
        presenter.present(message) == output

        where:
        user      | text                            | elapsedTime         || output
        'Alice'   | 'I love the weather today'      | SECONDS.toMillis(1) || 'Alice - I love the weather today (1 second ago)'
        'Bob'     | 'Damn! We lost!'                | MINUTES.toMillis(5) || 'Bob - Damn! We lost! (5 minutes ago)'
        'Bob'     | 'Good game though.'             | HOURS.toMillis(14)  || 'Bob - Good game though. (14 hours ago)'
        'Charlie' | "I'm in New York today!"        | HOURS.toMillis(24)  || "Charlie - I'm in New York today! (1 day ago)"
        'Charlie' | 'Anyone want to have a coffee?' | DAYS.toMillis(3)    || "Charlie - Anyone want to have a coffee? (3 days ago)"
    }
}
