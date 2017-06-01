package com.microtwitter.commands

import com.microtwitter.io.Console
import com.microtwitter.users.User
import spock.lang.Specification

class FollowCommandTest extends Specification {

    def 'should print user message when executed'() {
        given:
        User user = Mock(User)
        User folowee = Mock(User)
        FollowCommand command = new FollowCommand(user, folowee)
        Console console = Mock(Console)

        when:
        command.execute(console)

        then:
        1 * user.follow(folowee)
    }
}
