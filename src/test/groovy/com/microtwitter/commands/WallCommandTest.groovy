package com.microtwitter.commands

import com.microtwitter.presenters.ConsoleMessagePresenter
import com.microtwitter.presenters.MessagePresenter
import com.microtwitter.time.FixedClock
import com.microtwitter.users.User
import spock.lang.Specification

import static java.util.concurrent.TimeUnit.SECONDS

class WallCommandTest extends Specification {
    private FixedClock clock
    private MessagePresenter presenter
    private User alice
    private User bob
    private WallCommand command
    private PrintStream out

    def setup() {
        clock = new FixedClock()
        presenter = new ConsoleMessagePresenter(clock)
        alice = new User('Alice', clock)
        bob = new User('Bob', clock)
        command = new WallCommand(alice, presenter)
        out = Mock(PrintStream)
    }

    def 'should print user message when executed'() {
        when:
        clock.setMillis(SECONDS.toMillis(0))
        alice.post('I love the weather today')

        and:
        clock.setMillis(SECONDS.toMillis(15))
        command.execute(out)

        then:
        1 * out.println('Alice - I love the weather today (15 seconds ago)')
    }

    def 'should print user and folowee messages when executed'() {
        when:
        alice.follow(bob)
        clock.setMillis(SECONDS.toMillis(10))
        alice.post('I love the weather today')
        clock.setMillis(SECONDS.toMillis(20))
        bob.post('Damn! We lost!')

        and:
        clock.setMillis(SECONDS.toMillis(30))
        command.execute(out)

        then:
        1 * out.println('Alice - I love the weather today (20 seconds ago)')
        1 * out.println('Bob - Damn! We lost! (10 seconds ago)')
    }
}
