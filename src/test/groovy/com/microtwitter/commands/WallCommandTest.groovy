package com.microtwitter.commands

import com.microtwitter.presenters.PlainMessagePresenter
import com.microtwitter.presenters.MessagePresenter
import com.microtwitter.time.Clock
import com.microtwitter.users.User
import spock.lang.Specification

class WallCommandTest extends Specification {
    private MessagePresenter presenter
    private User alice
    private User bob
    private WallCommand command
    private PrintStream out

    def setup() {
        presenter = new PlainMessagePresenter()
        alice = new User('Alice', Mock(Clock))
        bob = new User('Bob', Mock(Clock))
        command = new WallCommand(alice, presenter)
        out = Mock(PrintStream)
    }

    def 'should print user message when executed'() {
        when:
        alice.post('I love the weather today')

        and:
        command.execute(out)

        then:
        1 * out.println('Alice - I love the weather today')
    }

    def 'should print user and folowee messages when executed'() {
        when:
        alice.follow(bob)
        alice.post('I love the weather today')
        bob.post('Damn! We lost!')

        and:
        command.execute(out)

        then:
        1 * out.println('Alice - I love the weather today')
        1 * out.println('Bob - Damn! We lost!')
    }
}
