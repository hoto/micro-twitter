package com.microtwitter.users

import com.microtwitter.messages.Message
import com.microtwitter.time.FixedClock
import spock.lang.Specification

class UserTest extends Specification {

    def 'should return timeline with no messages when user never posted'() {
        given:
        User alice = new User('Alice')

        expect:
        alice.timeline().size() == 0
    }

    def 'should return timeline with one message when user posted a message'() {
        given:
        User alice = new User('Alice')

        when:
        alice.post('I love the weather today')

        then:
        alice.timeline().size() == 1
    }

    def 'should return timeline with two messages when user posted two different messages'() {
        given:
        User bob = new User('Bob')

        when:
        bob.post('Damn! We lost!')
        bob.post('Good game though.')

        then:
        bob.timeline().size() == 2
    }

    def 'should return timeline with two messages when user posted same message twice'() {
        given:
        User alice = new User('Alice')

        when:
        alice.post('I love the weather today')
        alice.post('I love the weather today')

        then:
        alice.timeline().size() == 2
    }

    def 'should store timestamp with the message when user posted'() {
        given:
        FixedClock clock = new FixedClock()
        User alice = new User('Alice', clock)

        when:
        clock.setMillis(1000)
        alice.post('I love the weather today')

        then:
        alice.timeline().get(0).text == 'I love the weather today'
        alice.timeline().get(0).timestamp == 1000
    }

    def 'should store accurate timestamps with the messages when user posted consecutively'() {
        given:
        FixedClock clock = new FixedClock()
        User bob = new User('Bob', clock)

        when:
        clock.setMillis(1000)
        bob.post('Damn! We lost!')
        clock.setMillis(2000)
        bob.post('Good game though.')

        then:
        bob.timeline().get(0).text == 'Damn! We lost!'
        bob.timeline().get(0).timestamp == 1000
        bob.timeline().get(1).text == 'Good game though.'
        bob.timeline().get(1).timestamp == 2000
    }

    def 'should return empty wall when user has no messages and follows no one'() {
        given:
        User alice = new User('Alice')

        expect:
        alice.wall().size() == 0
    }

    def 'should return wall with one message when user posted'() {
        given:
        User alice = new User('Alice')

        when:
        alice.post('I love the weather today')

        then:
        alice.wall().size() == 1
    }

    def 'should return user B wall with user A message when user A posted and user B follows user A'() {
        given:
        User bob = new User('Bob')
        User alice = new User('Alice')

        when:
        alice.post('I love the weather today')

        and:
        bob.follow(alice)

        then:
        bob.wall().size() == 1
    }

    def 'should return user B wall with messages from user B and his followee when user B and his followee posted'() {
        given:
        User alice = new User('Alice')
        User bob = new User('Bob')

        when:
        alice.post('I love the weather today')
        bob.post('Damn we lost!')

        and:
        bob.follow(alice)

        then:
        bob.wall().size() == 2
    }

    def 'should return user C wall with messages from user C and his followees when user C and his followees posted'() {
        given:
        User alice = new User('Alice')
        User bob = new User('Bob')
        User charlie = new User('Charlie')

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
        given:
        FixedClock clock = new FixedClock()
        User alice = new User('Alice', clock)
        User bob = new User('Bob', clock)
        User charlie = new User('Charlie', clock)

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
        charlieWall.get(0).text == 'Damn! We lost!'
        charlieWall.get(1).text == 'I love the weather today'
        charlieWall.get(2).text == "I'm in New York today! Anyone want to have a coffee?"
        charlieWall.get(3).text == 'Good game though.'
    }
}
