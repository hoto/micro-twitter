package com.microtwitter.commands

import com.microtwitter.presenters.ConsoleMessagePresenter
import com.microtwitter.presenters.MessagePresenter
import com.microtwitter.time.FixedClock
import com.microtwitter.users.User
import spock.lang.Specification

import static java.util.concurrent.TimeUnit.MINUTES

class ReadCommandTest extends Specification {
    private FixedClock clock
    private User alice
    private MessagePresenter presenter
    private ReadCommand command
    private PrintStream out

    def setup() {
        clock = new FixedClock()
        alice = new User('Alice', clock)
        presenter = new ConsoleMessagePresenter(clock)
        command = new ReadCommand(alice, presenter)
        out = Mock(PrintStream)
    }

    def 'should print user message when executed'() {
        when:
        clock.setMillis(MINUTES.toMillis(1))
        alice.post('I love the weather today')

        and:
        clock.setMillis(MINUTES.toMillis(6))
        command.execute(out)

        then:
        1 * out.println('Alice - I love the weather today (5 minutes ago)')
    }

    def 'should print two different user messages when executed'() {
        when:
        clock.setMillis(MINUTES.toMillis(1))
        alice.post('I love the weather today')
        clock.setMillis(MINUTES.toMillis(2))
        alice.post('Really nice weather')

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
        alice.post('I love the weather today')

        and:
        clock.setMillis(MINUTES.toMillis(6))
        command.execute(out)
        command.execute(out)

        then:
        2 * out.println('Alice - I love the weather today (5 minutes ago)')
    }
}
