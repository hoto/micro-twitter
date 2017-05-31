package com.microtwitter.commands

import com.microtwitter.users.User
import spock.lang.Specification

class PostCommandTest extends Specification {

    def 'should post user message when executed'() {
        given:
        User user = Mock(User)
        String message = 'I love the weather today'
        PostCommand command = new PostCommand(user, message)
        PrintStream out = Mock(PrintStream)

        when:
        command.execute(out)

        then:
        1 * user.post('I love the weather today')
    }
}
