package com.microtwitter.commands

import com.microtwitter.io.Console
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
    private Console console

    def setup() {
        presenter = new PlainMessagePresenter()
        alice = new User('Alice', Mock(Clock))
        bob = new User('Bob', Mock(Clock))
        command = new WallCommand(alice, presenter)
        console = Mock(Console)
    }

    def 'should print user message when executed'() {
        when:
        alice.post('I love the weather today')

        and:
        command.execute(console)

        then:
        1 * console.writeOutput('Alice - I love the weather today')
    }

    def 'should print user and folowee messages when executed'() {
        when:
        alice.follow(bob)
        alice.post('I love the weather today')
        bob.post('Damn! We lost!')

        and:
        command.execute(console)

        then:
        1 * console.writeOutput('Alice - I love the weather today')
        1 * console.writeOutput('Bob - Damn! We lost!')
    }
}
