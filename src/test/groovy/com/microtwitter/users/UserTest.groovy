package com.microtwitter.users

import com.microtwitter.messages.Message
import com.microtwitter.time.FixedClock
import spock.lang.Specification

class UserTest extends Specification {
    private User alice
    private User bob
    private FixedClock clock
    private User charlie

    def setup() {
        clock = new FixedClock()
        alice = new User('Alice', clock)
        bob = new User('Bob', clock)
        charlie = new User('Charlie', clock)
    }

    def 'should return timeline with no messages when user never posted'() {
        expect:
        alice.timeline().size() == 0
    }

    def 'should return timeline with one message when user posted a message'() {
        when:
        alice.post('I love the weather today')

        then:
        alice.timeline().size() == 1
    }

    def 'should return timeline with two messages when user posted two different messages'() {
        when:
        bob.post('Damn! We lost!')
        bob.post('Good game though.')

        then:
        bob.timeline().size() == 2
    }

    def 'should return timeline with two messages when user posted same message twice'() {
        when:
        alice.post('I love the weather today')
        alice.post('I love the weather today')

        then:
        alice.timeline().size() == 2
    }

    def 'should store timestamp with the message when user posted'() {
        when:
        clock.setMillis(1000)
        alice.post('I love the weather today')

        then:
        alice.timeline().get(0).text == 'I love the weather today'
        alice.timeline().get(0).timestamp == 1000
    }

    def 'should store messages chronologically when user posted consecutively'() {
        when:
        clock.setMillis(1000)
        bob.post('Damn! We lost!')
        clock.setMillis(2000)
        bob.post('Good game though.')

        then:
        bob.timeline().get(0).text == 'Good game though.'
        bob.timeline().get(0).timestamp == 2000
        bob.timeline().get(1).text == 'Damn! We lost!'
        bob.timeline().get(1).timestamp == 1000
    }

    def 'should return empty wall when user has no messages and follows no one'() {
        expect:
        alice.wall().size() == 0
    }

    def 'should return wall with one message when user posted'() {
        when:
        alice.post('I love the weather today')

        then:
        alice.wall().size() == 1
    }

    def 'should return user B wall with user A message when user A posted and user B follows user A'() {
        when:
        alice.post('I love the weather today')

        and:
        bob.follow(alice)

        then:
        bob.wall().size() == 1
    }

    def 'should not be able to follow itself'() {
        when:
        alice.post('I love the weather today')

        and:
        alice.follow(alice)

        then:
        alice.wall().size() == 1
    }

    def 'should return user B wall with messages from user B and his followee when user B and his followee posted'() {
        when:
        alice.post('I love the weather today')
        bob.post('Damn we lost!')

        and:
        bob.follow(alice)

        then:
        bob.wall().size() == 2
    }

    def 'should return user C wall with messages from user C and his followees when user C and his followees posted'() {
        when:
        alice.post('I love the weather today')
        bob.post('Damn! We lost!')
        bob.post('Good game though.')
        charlie.post("I'm in New York today! Anyone want to have a coffee?")

        and:
        charlie.follow(alice)
        charlie.follow(bob)

        then:
        charlie.wall().size() == 4
    }

    def 'should return user C wall with messages in chronological order when user C and his followees posted'() {
        when:
        clock.setMillis(1000)
        bob.post('Damn! We lost!')
        clock.setMillis(2000)
        alice.post('I love the weather today')
        clock.setMillis(3000)
        charlie.post("I'm in New York today! Anyone want to have a coffee?")
        clock.setMillis(4000)
        bob.post('Good game though.')

        and:
        charlie.follow(alice)
        charlie.follow(bob)

        then:
        List<Message> charlieWall = charlie.wall()
        charlieWall.get(0).text == 'Good game though.'
        charlieWall.get(1).text == "I'm in New York today! Anyone want to have a coffee?"
        charlieWall.get(2).text == 'I love the weather today'
        charlieWall.get(3).text == 'Damn! We lost!'
    }
}
