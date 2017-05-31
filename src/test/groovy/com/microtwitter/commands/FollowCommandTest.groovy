package com.microtwitter.commands

import com.microtwitter.users.User
import spock.lang.Specification

class FollowCommandTest extends Specification {

    def 'should print user message when executed'() {
        given:
        User user = Mock(User)
        User folowee = new User('Bob')
        FollowCommand command = new FollowCommand(user, folowee)
        PrintStream out = Mock(PrintStream)

        when:
        command.execute(out)

        then:
        1 * user.follow(folowee)
    }
}
