package com.microtwitter.commands

import com.microtwitter.io.Console
import com.microtwitter.presenters.MessagePresenter
import com.microtwitter.presenters.PlainMessagePresenter
import com.microtwitter.time.Clock
import com.microtwitter.users.User
import spock.lang.Specification

class ReadCommandTest extends Specification {
    private User alice
    private MessagePresenter presenter
    private ReadCommand command
    private Console console

    def setup() {
        alice = new User('Alice', Mock(Clock))
        presenter = new PlainMessagePresenter()
        command = new ReadCommand(alice, presenter)
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

    def 'should print two different user messages when executed'() {
        when:
        alice.post('I love the weather today')
        alice.post('Really nice weather')

        and:
        command.execute(console)

        then:
        1 * console.writeOutput('Alice - I love the weather today')
        1 * console.writeOutput('Alice - Really nice weather')
    }

    def 'should print same user message twice when executed twice'() {
        when:
        alice.post('I love the weather today')

        and:
        command.execute(console)
        command.execute(console)

        then:
        2 * console.writeOutput('Alice - I love the weather today')
    }
}
