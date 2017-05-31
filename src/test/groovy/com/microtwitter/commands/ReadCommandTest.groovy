package com.microtwitter.commands

import com.microtwitter.presenters.MessagePresenter
import com.microtwitter.presenters.PlainMessagePresenter
import com.microtwitter.time.Clock
import com.microtwitter.users.User
import spock.lang.Specification

class ReadCommandTest extends Specification {
    private User alice
    private MessagePresenter presenter
    private ReadCommand command
    private PrintStream out

    def setup() {
        alice = new User('Alice', Mock(Clock))
        presenter = new PlainMessagePresenter()
        command = new ReadCommand(alice, presenter)
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

    def 'should print two different user messages when executed'() {
        when:
        alice.post('I love the weather today')
        alice.post('Really nice weather')

        and:
        command.execute(out)

        then:
        1 * out.println('Alice - I love the weather today')
        1 * out.println('Alice - Really nice weather')
    }

    def 'should print same user message twice when executed twice'() {
        when:
        alice.post('I love the weather today')

        and:
        command.execute(out)
        command.execute(out)

        then:
        2 * out.println('Alice - I love the weather today')
    }
}
