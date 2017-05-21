package com.microtwitter

import spock.lang.Specification
import com.microtwitter.time.FixedClock

class MicroTwitterTest extends Specification {
    private MicroTwitter twitter
    private FixedClock clock

    def setup() {
        clock = new FixedClock()
        twitter = new MicroTwitter(clock)
    }

    def 'should have no messages when user never posted'() {
        when:
        List<Message> aliceMessages = twitter.read('Alice')

        then:
        aliceMessages == []
    }

    def 'should have message when user posted a message'() {
        given:
        twitter.post('Alice', 'I love the weather today')

        when:
        List<Message> aliceMessages = twitter.read('Alice')

        then:
        aliceMessages.get(0).text == 'I love the weather today'
    }

    def 'should have two messages when user posted two different messages'() {
        given:
        twitter.post('Bob', 'Damn! We lost!')
        twitter.post('Bob', 'Good game though.')

        when:
        List<Message> bobMessages = twitter.read('Bob')

        then:
        bobMessages.get(0).text == 'Damn! We lost!'
        bobMessages.get(1).text == 'Good game though.'
    }

    def 'should have two messages when user posted same message twice'() {
        given:
        twitter.post('Alice', 'I love the weather today')
        twitter.post('Alice', 'I love the weather today')

        when:
        List<Message> aliceMessages = twitter.read('Alice')

        then:
        aliceMessages.get(0).text == 'I love the weather today'
        aliceMessages.get(1).text == 'I love the weather today'
    }

    def 'should have two messages when two users posted a message each'() {
        given:
        twitter.post('Alice', 'I love the weather today')
        twitter.post('Bob', 'Damn! We lost!')

        when:
        List<Message> aliceMessages = twitter.read('Alice')
        List<Message> bobMessages = twitter.read('Bob')

        then:
        aliceMessages.get(0).text == 'I love the weather today'
        bobMessages.get(0).text == 'Damn! We lost!'
    }

    def 'should have three messages when two users posted three messages'() {
        given:
        twitter.post('Alice', 'I love the weather today')
        twitter.post('Bob', 'Damn! We lost!')
        twitter.post('Bob', 'Good game though.')

        when:
        List<Message> aliceMessages = twitter.read('Alice')
        List<Message> bobMessages = twitter.read('Bob')

        then:
        aliceMessages.get(0).text == 'I love the weather today'
        bobMessages.get(0).text == 'Damn! We lost!'
        bobMessages.get(1).text == 'Good game though.'
    }

    def 'should store timestamp with the message when user posted'() {
        given:
        clock.setMillis(1000)
        twitter.post('Alice', 'I love the weather today')

        when:
        List<Message> aliceMessages = twitter.read('Alice')

        then:
        aliceMessages.get(0).text == 'I love the weather today'
        aliceMessages.get(0).timestamp == 1000
    }

    def 'should store timestamps with the messages when three users posted consecutively'() {
        given:
        clock.setMillis(1000)
        twitter.post('Alice', 'I love the weather today')
        twitter.post('Bob', 'Damn! We lost!')
        clock.setMillis(2000)
        twitter.post('Bob', 'Good game though.')
        clock.setMillis(3000)
        twitter.post('Charlie', "I'm in New York today! Anyone want to have a coffee?")

        when:
        List<Message> aliceMessages = twitter.read('Alice')
        List<Message> bobMessages = twitter.read('Bob')
        List<Message> charlieMessages = twitter.read('Charlie')

        then:
        aliceMessages.get(0).text == 'I love the weather today'
        aliceMessages.get(0).timestamp == 1000
        bobMessages.get(0).text == 'Damn! We lost!'
        bobMessages.get(0).timestamp == 1000
        bobMessages.get(1).text == 'Good game though.'
        bobMessages.get(1).timestamp == 2000
        charlieMessages.get(0).text == "I'm in New York today! Anyone want to have a coffee?"
        charlieMessages.get(0).timestamp == 3000
    }
}
