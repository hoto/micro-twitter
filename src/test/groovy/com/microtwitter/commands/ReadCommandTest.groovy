package com.microtwitter.commands

import com.microtwitter.time.FixedClock
import com.microtwitter.users.User
import spock.lang.Specification

import static java.util.concurrent.TimeUnit.MINUTES

class ReadCommandTest extends Specification {
    private FixedClock clock
    private User user
    private ReadCommand command
    private PrintStream out

    def setup() {
        clock = new FixedClock()
        user = new User('Alice', clock)
        command = new ReadCommand(user, clock)
        out = Mock(PrintStream)
    }

    def 'should print user message when executed'() {
        when:
        clock.setMillis(MINUTES.toMillis(1))
        user.post('I love the weather today')

        and:
        clock.setMillis(MINUTES.toMillis(6))
        command.execute(out)

        then:
        1 * out.println('Alice - I love the weather today (5 minutes ago)')
    }

    def 'should print two different user messages when executed'() {
        when:
        clock.setMillis(MINUTES.toMillis(1))
        user.post('I love the weather today')
        clock.setMillis(MINUTES.toMillis(2))
        user.post('Really nice weather')

        and:
        clock.setMillis(MINUTES.toMillis(6))
        command.execute(out)

        then:
        1 * out.println('Alice - I love the weather today (5 minutes ago)')
        1 * out.println('Alice - Really nice weather (4 minutes ago)')
    }

    def 'should print same user message twice when executed twice'() {
        when:
        clock.setMillis(MINUTES.toMillis(1))
        user.post('I love the weather today')

        and:
        clock.setMillis(MINUTES.toMillis(6))
        command.execute(out)
        command.execute(out)

        then:
        2 * out.println('Alice - I love the weather today (5 minutes ago)')
    }
}
