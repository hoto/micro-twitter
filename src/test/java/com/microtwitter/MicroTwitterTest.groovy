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

    def 'should return timeline with no messages when user never posted'() {
        when:
        List<Message> aliceTimeline = twitter.read('Alice')

        then:
        aliceTimeline == []
    }

    def 'should return timeline with one message when user posted that message'() {
        given:
        twitter.post('Alice', 'I love the weather today')

        when:
        List<Message> aliceTimeline = twitter.read('Alice')

        then:
        aliceTimeline.get(0).text == 'I love the weather today'
    }

    def 'should return timeline with two messages when user posted two different messages'() {
        given:
        twitter.post('Bob', 'Damn! We lost!')
        twitter.post('Bob', 'Good game though.')

        when:
        List<Message> bobTimeline = twitter.read('Bob')

        then:
        bobTimeline.get(0).text == 'Damn! We lost!'
        bobTimeline.get(1).text == 'Good game though.'
    }

    def 'should return timeline with two messages when user posted same message twice'() {
        given:
        twitter.post('Alice', 'I love the weather today')
        twitter.post('Alice', 'I love the weather today')

        when:
        List<Message> aliceTimeline = twitter.read('Alice')

        then:
        aliceTimeline.get(0).text == 'I love the weather today'
        aliceTimeline.get(1).text == 'I love the weather today'
    }

    def 'should return two timelines when two users posted a message'() {
        given:
        twitter.post('Alice', 'I love the weather today')
        twitter.post('Bob', 'Damn! We lost!')

        when:
        List<Message> aliceTimeline = twitter.read('Alice')
        List<Message> bobTimeline = twitter.read('Bob')

        then:
        aliceTimeline.get(0).text == 'I love the weather today'
        bobTimeline.get(0).text == 'Damn! We lost!'
    }

    def 'should return two timelines when two users posted three messages'() {
        given:
        twitter.post('Alice', 'I love the weather today')
        twitter.post('Bob', 'Damn! We lost!')
        twitter.post('Bob', 'Good game though.')

        when:
        List<Message> aliceTimeline = twitter.read('Alice')
        List<Message> bobTimeline = twitter.read('Bob')

        then:
        aliceTimeline.get(0).text == 'I love the weather today'
        bobTimeline.get(0).text == 'Damn! We lost!'
        bobTimeline.get(1).text == 'Good game though.'
    }

    def 'should store timestamp with the message when user posted'() {
        given:
        clock.setMillis(1000)
        twitter.post('Alice', 'I love the weather today')

        when:
        List<Message> aliceTimeline = twitter.read('Alice')

        then:
        aliceTimeline.get(0).text == 'I love the weather today'
        aliceTimeline.get(0).timestamp == 1000
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
        List<Message> aliceTimeline = twitter.read('Alice')
        List<Message> bobTimeline = twitter.read('Bob')
        List<Message> charlieTimeline = twitter.read('Charlie')

        then:
        aliceTimeline.get(0).text == 'I love the weather today'
        aliceTimeline.get(0).timestamp == 1000
        bobTimeline.get(0).text == 'Damn! We lost!'
        bobTimeline.get(0).timestamp == 1000
        bobTimeline.get(1).text == 'Good game though.'
        bobTimeline.get(1).timestamp == 2000
        charlieTimeline.get(0).text == "I'm in New York today! Anyone want to have a coffee?"
        charlieTimeline.get(0).timestamp == 3000
    }

    def 'should return empty wall when user has no messages and follows no one'() {
        when:
        List<Message> aliceWall = twitter.wall('Alice')

        then:
        aliceWall == []
    }

    def 'should return wall with user message when user posted that message'() {
        given:
        twitter.post('Alice', 'I love the weather today')

        when:
        List<Message> aliceWall = twitter.wall('Alice')

        then:
        aliceWall.get(0).text == 'I love the weather today'
    }
}
